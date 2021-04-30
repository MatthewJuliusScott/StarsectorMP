package com.dasmatarix.util;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import com.fs.util.DoNotObfuscate;
import com.sun.codemodel.JBlock;
import com.sun.codemodel.JClass;
import com.sun.codemodel.JClassAlreadyExistsException;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JExpr;
import com.sun.codemodel.JExpression;
import com.sun.codemodel.JFieldRef;
import com.sun.codemodel.JForLoop;
import com.sun.codemodel.JInvocation;
import com.sun.codemodel.JMethod;
import com.sun.codemodel.JMod;
import com.sun.codemodel.JPackage;
import com.sun.codemodel.JStatement;
import com.sun.codemodel.JTryBlock;
import com.sun.codemodel.JVar;

/**
 * The Class _ASerializerCodeGeneration.
 */
public class _ASerializerCodeGeneration {

	private static Set<String> serializedClasses = new HashSet<String>();

	public static Set<String> classBlacklist = new HashSet<String>();

	private static Map<JClass, JDefinedClass> classSerializerMap = new HashMap<JClass, JDefinedClass>();

	private static ConcurrentMap<Class, Class> classesToSerialize = new ConcurrentHashMap<Class, Class>();

	public static Set<String> reservedWords = new HashSet<String>(Arrays.asList("abstract", "continue", "for", "new",
			"switch", "assert", "default", "goto", "package", "synchronized", "boolean", "do", "if", "private", "this",
			"break", "double", "implements", "protected", "throw", "byte", "else", "import", "public", "throws", "case",
			"enum", "instanceof", "return", "transient", "catch", "extends", "int", "short", "try", "char", "final",
			"interface", "static", "void", "class", "finally", "long", "strictfp", "volatile", "const", "float",
			"native", "super", "while"));
	}

	private static void generateSerializer(String _package, Class specificClass, Class subTypeOf) {
		try {
			Reflections reflections = new Reflections(_package, new SubTypesScanner(false));
			Set<Class<? extends Object>> types = reflections.getSubTypesOf(subTypeOf);

			for (Class clazz : types) {
				if (clazz.getName().contains("$") || classBlacklist.contains(clazz.getSimpleName())
						|| reservedWords.contains(clazz.getSimpleName())) {
					continue;
				}
				JCodeModel codeModel = new JCodeModel();
				JPackage jPackage = codeModel._package("com.generated.src.serializer");
				int count = 0;
				try {
					String className = clazz.getName();
					if (className.lastIndexOf('.') > -1) {
						className = className.substring(className.lastIndexOf('.') + 1);
					}
					System.out.println(
							"Plugin output: Class name " + clazz.getName() + ", creating " + className + "Serializer");
					JDefinedClass jdefinedClass = jPackage._class(className + "Serializer");
					Objenesis objenesis = new ObjenesisStd();
					Object bean = objenesis.newInstance(clazz);
					PropertyDescriptor[] propertyDescriptors = Introspector.getBeanInfo(clazz, Object.class)
							.getPropertyDescriptors();

					// add the imports
					JClass jObjenesis = codeModel.directClass("org.objenesis.Objenesis");
					JClass jObjenesisStd = codeModel.directClass("org.objenesis.ObjenesisStd");
					JClass jByteArrayOutputStream = codeModel.directClass("java.io.ByteArrayOutputStream");
					JClass jObjectOutputStream = codeModel.directClass("java.io.ObjectOutputStream");
					JClass jIOException = codeModel.directClass("java.io.IOException");
					JClass jSerializerNotFoundException = codeModel
							.directClass("com.dasmatarix.multiplayer.exception.SerializerNotFoundException");
					JClass jMessageSerializer = codeModel.directClass("com.dasmatarix.multiplayer.MessageSerializer");
					JClass jISerializer = codeModel.directClass("com.dasmatarix.multiplayer.ISerializer");
					JClass jClazz = codeModel.directClass(clazz.getCanonicalName());

					// add the serialize method
					JMethod serializeMethod = jdefinedClass.method(JMod.PUBLIC, byte[].class, "serialize");
					serializeMethod._throws(jIOException);
					JVar jParam = serializeMethod.param(clazz, "obj");
					// TODO add each of the values as bytes including recursively calling serialize
					// and collections to fixed length arrays

					JVar jBos = serializeMethod.body().decl(jByteArrayOutputStream, "bos",
							JExpr._new(jByteArrayOutputStream));
					JVar jOut = serializeMethod.body().decl(jObjectOutputStream, "out", JExpr._null());
					JTryBlock tryBlock = serializeMethod.body()._try();

					// initialize out inside try block
					tryBlock.body().assign(jOut, JExpr._new(jObjectOutputStream).arg(jBos));

					JExpression returnStatement = jBos.invoke("toByteArray");

					JBlock finallyBlock = tryBlock._finally();
					JTryBlock finallyTryBlock = finallyBlock._try();
					JStatement bosClose = jBos.invoke("close");
					finallyTryBlock.body().add(bosClose);
					finallyTryBlock._catch(jIOException);

					// add the deserialize method
					JMethod deserializeMethod = jdefinedClass.method(JMod.PUBLIC, clazz, "deserialize");
					JVar jObj = deserializeMethod.body().decl(jObjenesis, "objenesis", JExpr._new(jObjenesisStd));
					JInvocation newInstanceExpression = jObj.invoke("newInstance");
					newInstanceExpression.arg(jClazz.dotclass());
					JVar jClazzObj = deserializeMethod.body().decl(jClazz, "obj", newInstanceExpression);
					// set all the values from bytes before return

					for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
						try {
							if (clazz != null && propertyDescriptor != null
									&& propertyDescriptor.getReadMethod() != null) {
								propertyDescriptor.getReadMethod().invoke(bean);

								// here are the getters and setters
								// note its not as simple as is there are a getter and setter, some fields like
								// MutableValue are set via MutableValue X; getX().set();

								Method readMethod = propertyDescriptor.getReadMethod();
								Method writeMethod = propertyDescriptor.getWriteMethod();

								if (readMethod != null && writeMethod != null) {
									System.out.println("    " + propertyDescriptor.getName() + " : "
											+ propertyDescriptor.getPropertyType().getName());
									System.out.println("        " + readMethod);
									System.out.println("        " + writeMethod.getName());

									write(propertyDescriptor.getPropertyType(), codeModel, jOut, jParam,
											tryBlock.body(), jParam.invoke(readMethod.getName()),
											jSerializerNotFoundException, jMessageSerializer, jISerializer);
									count++;
								} else if (readMethod != null && !propertyDescriptor.getPropertyType().isPrimitive()
										&& Collection.class.isAssignableFrom(propertyDescriptor.getPropertyType())) {
									// is a collection, convert to a fixed length array and write length, then bytes
									try {

										Field collectionField = clazz.getDeclaredField(propertyDescriptor.getName());
										ParameterizedType collectionType = (ParameterizedType) collectionField
												.getGenericType();
										Class<?> parameterClass = (Class<?>) collectionType.getActualTypeArguments()[0];
										Class<?> parameterArrayClass = java.lang.reflect.Array
												.newInstance(parameterClass, 0).getClass();
										JClass jComponent = codeModel.directClass(parameterClass.getCanonicalName());
										System.out.println("FOUND A COLLECTION");
										System.out.println(propertyDescriptor.getName() + " : "
												+ propertyDescriptor.getPropertyType().getCanonicalName() + "<"
												+ parameterClass.getSimpleName() + ">");

										JExpression array = tryBlock.body().decl(jComponent.array(),
												propertyDescriptor.getName() + "Array",
												jParam.invoke(readMethod.getName()).invoke("toArray")
														.arg(JExpr.newArray(jComponent, 0)));
										write(parameterArrayClass, codeModel, jOut, jParam, tryBlock.body(), array,
												jSerializerNotFoundException, jMessageSerializer, jISerializer);
										count++;
									} catch (Exception e3) {
									}
								}
							}
						} catch (Exception | ExceptionInInitializerError | NoClassDefFoundError e2) {
							// do nothing
						}

					}
					// Generate the code
					if (count > 0) {
						// serialize
						JStatement outFlush = jOut.invoke("flush");
						tryBlock.body().add(outFlush);
						tryBlock.body()._return(returnStatement);

						// deserialize
						deserializeMethod.body()._return(jClazzObj);
						classSerializerMap.put(jClazz, jdefinedClass);
						serializedClasses.add(clazz.getCanonicalName());
						codeModel.build(new File("src/main/java/"));
					} else {
						// there was nothing generated, create a dummy serializer for a NOP
						JCodeModel dummyCodeModel = new JCodeModel();
						jPackage = dummyCodeModel._package("com.generated.src.serializer.dummy");
						jdefinedClass = jPackage._class(className + "DummySerializer");
						serializeMethod = jdefinedClass.method(JMod.PUBLIC, byte[].class, "serialize");
						serializeMethod._throws(jIOException);
						jParam = serializeMethod.param(clazz, "obj");
						serializeMethod.body().directStatement("return new byte[0];");

						deserializeMethod = jdefinedClass.method(JMod.PUBLIC, clazz, "deserialize");
						deserializeMethod.body().directStatement("return null;");
						classSerializerMap.put(jClazz, jdefinedClass);
						serializedClasses.add(clazz.getCanonicalName());
						dummyCodeModel.build(new File("src/main/java/"));
					}
				} catch (IllegalArgumentException | IntrospectionException | NoClassDefFoundError | SecurityException
						| ExceptionInInitializerError | InstantiationError | JClassAlreadyExistsException
						| java.lang.UnsatisfiedLinkError e1) {
					e1.printStackTrace();
					continue;
				}
			}
		} catch (

		Exception e) {
			e.printStackTrace();
		}
	}

	private static void generateSerializerManager() throws IOException, JClassAlreadyExistsException {
		JCodeModel codeModel = new JCodeModel();
		JPackage jPackage = codeModel._package("com.generated.src");
		JDefinedClass jdefinedClass = jPackage._class("_AutoGeneratedSerializerManager");
		JClass jMapClass = codeModel.directClass("java.util.Map");
		JClass jHashMapClass = codeModel.directClass("java.util.HashMap");
		JClass jISerializer = codeModel.directClass("com.dasmatarix.multiplayer.ISerializer");

		// Class, ISerializer

		JMethod jMethod = jdefinedClass.method(JMod.PUBLIC + JMod.STATIC, jMapClass, "getSerializers");
		jMethod.generify("Class");
		jMethod.generify(jISerializer.name());
		JVar jObj = jMethod.body().decl(jMapClass, "classSerializerMap", JExpr._new(jHashMapClass));

		for (Entry<JClass, JDefinedClass> entry : classSerializerMap.entrySet()) {
			JInvocation jPut = jObj.invoke("put").arg(entry.getKey().dotclass()).arg(JExpr._new(entry.getValue()));
			jMethod.body().add(jPut);
		}

		jMethod.body()._return(jObj);

		codeModel.build(new File("src/main/java/"));
	}

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws Exception the exception
	 */
	public static void main(String[] args) throws Exception {
		classBlacklist.add("A");
		classBlacklist.add("C");
		classBlacklist.add("D");

		_ASerializerCodeGeneration.generateSerializer("com.fs.starfarer", null, DoNotObfuscate.class);
		_ASerializerCodeGeneration.generateSerializer("com.fs.starfarer.api", null, Object.class);
		Set<Map.Entry<Class, Class>> entrySet = classesToSerialize.entrySet();
		Iterator<Map.Entry<Class, Class>> iterator = entrySet.iterator();
		while (iterator.hasNext()) {
			Class clazz = iterator.next().getKey();
			iterator.remove();
			_ASerializerCodeGeneration.generateSerializer(clazz.getPackage().getName(), clazz, Object.class);
		}
		_ASerializerCodeGeneration.generateSerializerManager();
		System.out.println("DONE");
	}

	/**
	 * Write.
	 *
	 * @param clazz                        the clazz
	 * @param codeModel                    the code model
	 * @param jOut                         the j out
	 * @param jParam                       the j param
	 * @param jBlock                       the j block
	 * @param readMethod                   the read method
	 * @param writeMethod                  the write method
	 * @param jSerializerNotFoundException the j serializer not found exception
	 * @param jMessageSerializer           the j message serializer
	 * @param jISerializer                 the j I serializer
	 */
	private static void write(Class clazz, JCodeModel codeModel, JVar jOut, JVar jParam, JBlock jBlock,
			JExpression expression, JClass jSerializerNotFoundException, JClass jMessageSerializer,
			JClass jISerializer) {
		if (clazz == null) {
			return;
		} else if (clazz.equals(byte.class)) {
			JInvocation write = jOut.invoke("writeByte");
			write.arg(expression);
			jBlock.add(write);
		} else if (clazz.equals(short.class)) {
			JInvocation write = jOut.invoke("writeShort");
			write.arg(expression);
			jBlock.add(write);
		} else if (clazz.equals(int.class)) {
			JInvocation write = jOut.invoke("writeInt");
			write.arg(expression);
			jBlock.add(write);
		} else if (clazz.equals(long.class)) {
			JInvocation write = jOut.invoke("writeLong");
			write.arg(expression);
			jBlock.add(write);
		} else if (clazz.equals(float.class)) {
			JInvocation write = jOut.invoke("writeFloat");
			write.arg(expression);
			jBlock.add(write);
		} else if (clazz.equals(double.class)) {
			JInvocation write = jOut.invoke("writeDouble");
			write.arg(expression);
			jBlock.add(write);
		} else if (clazz.equals(char.class)) {
			JInvocation write = jOut.invoke("writeChar");
			write.arg(expression);
			jBlock.add(write);
		} else if (clazz.equals(boolean.class)) {
			JInvocation write = jOut.invoke("writeBoolean");
			write.arg(expression);
			jBlock.add(write);
		} else if (clazz.equals(String.class)) {
			JInvocation write = jOut.invoke("writeUTF");
			write.arg(expression);
			jBlock.add(write);
		} else if (clazz.isArray()) {
			JInvocation write = jOut.invoke("writeInt");
			JFieldRef len = expression.ref("length");
			write.arg(len);
			jBlock.add(write);

			JForLoop jForLoop = jBlock._for();
			JVar counter = jForLoop.init(codeModel.INT, "i", JExpr.lit(0));
			jForLoop.test(counter.lt(len));
			jForLoop.update(counter.incr());
			JExpression component = expression.component(counter);
			write(clazz.getComponentType(), codeModel, jOut, jParam, jForLoop.body(), component,
					jSerializerNotFoundException, jMessageSerializer, jISerializer);

			// TODO add if clazz instanceof
			// MutableValue.class etc.
			// TODO else if same type as obj class, i.e possible self reference, need to
			// avoid self reference infinite loop
			// TODO ?else detect infinite loop for A references B, B references A
		} else if (clazz.equals(Object.class)) {
			return;
		} else if (clazz.getName().contains("$") || classBlacklist.contains(clazz.getSimpleName())
				|| reservedWords.contains(clazz.getSimpleName())) {
			// no inner classes, blacklisted classes, or reserved word classes (excluding primitives already handled)
			return;
		} else if (clazz.getName() == null) {
			return;
		} else {
			// call the object's serializer if it exists
			JTryBlock writeTryBlock = jBlock._try();
			writeTryBlock._catch(jSerializerNotFoundException);
			JClass jPropertyClass = codeModel.directClass(clazz.getCanonicalName());
			JInvocation jMessageSerializerObj = jMessageSerializer

					.staticInvoke("getInstance").invoke("getSerializer")
					.arg(JExpr.direct(jPropertyClass.fullName() + ".class"));
			JVar jISerializerObj = writeTryBlock.body().decl(jISerializer, "serializer", jMessageSerializerObj);
			JInvocation write = jOut.invoke("write");
			write.arg(jISerializerObj.invoke("serialize").arg(expression));
			writeTryBlock.body().add(write);

			// try and create the serializer in case its not in one of the scanned packages
			if (!serializedClasses.contains(clazz.getCanonicalName())) {
				classesToSerialize.put(clazz, clazz);
			}

		}
	}
}
