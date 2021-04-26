package com.dasmatarix.util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Set;
import java.util.Arrays;
import java.util.HashSet;

import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;
import org.reflections.ReflectionUtils;
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
import com.sun.codemodel.JInvocation;
import com.sun.codemodel.JMethod;
import com.sun.codemodel.JMod;
import com.sun.codemodel.JPackage;
import com.sun.codemodel.JStatement;
import com.sun.codemodel.JTryBlock;
import com.sun.codemodel.JVar;

public class _ASerializerCodeGeneration {

	public static void main(String[] args) throws Exception {
		try {
			Reflections reflections = new Reflections("com.fs.starfarer", new SubTypesScanner(false));
			Set<Class<? extends DoNotObfuscate>> doNotObfuscateTypes = reflections.getSubTypesOf(DoNotObfuscate.class);
			reflections = new Reflections("com.fs.starfarer.api", new SubTypesScanner(false));
			Set<Class<? extends Object>> apiTypes = reflections.getSubTypesOf(Object.class);
			Set<Class<? extends Object>> allTypes = new HashSet();
			allTypes.addAll(doNotObfuscateTypes);
			allTypes.addAll(apiTypes);

			for (Class clazz : allTypes) {
				if (clazz.getName().contains("$")) {
					continue;
				}
				JCodeModel codeModel = new JCodeModel();
				JPackage jPackage = codeModel._package("com.dasmatarix.multiplayer.serializer");
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
									System.out.println("        " + readMethod.getName());
									System.out.println("        " + writeMethod.getName());
									// TODO writeProperty as bytes
									if (propertyDescriptor.getPropertyType().equals(byte.class)) {
										JInvocation write = jOut.invoke("writeByte");
										write.arg(jParam.invoke(readMethod.getName()));
										tryBlock.body().add(write);
									} else if (propertyDescriptor.getPropertyType().equals(short.class)) {
										JInvocation write = jOut.invoke("writeShort");
										write.arg(jParam.invoke(readMethod.getName()));
										tryBlock.body().add(write);
									} else if (propertyDescriptor.getPropertyType().equals(int.class)) {
										JInvocation write = jOut.invoke("writeInt");
										write.arg(jParam.invoke(readMethod.getName()));
										tryBlock.body().add(write);
									} else if (propertyDescriptor.getPropertyType().equals(long.class)) {
										JInvocation write = jOut.invoke("writeLong");
										write.arg(jParam.invoke(readMethod.getName()));
										tryBlock.body().add(write);
									} else if (propertyDescriptor.getPropertyType().equals(float.class)) {
										JInvocation write = jOut.invoke("writeFloat");
										write.arg(jParam.invoke(readMethod.getName()));
										tryBlock.body().add(write);
									} else if (propertyDescriptor.getPropertyType().equals(double.class)) {
										JInvocation write = jOut.invoke("writeDouble");
										write.arg(jParam.invoke(readMethod.getName()));
										tryBlock.body().add(write);
									} else if (propertyDescriptor.getPropertyType().equals(char.class)) {
										JInvocation write = jOut.invoke("writeChar");
										write.arg(jParam.invoke(readMethod.getName()));
										tryBlock.body().add(write);
									} else if (propertyDescriptor.getPropertyType().equals(boolean.class)) {
										JInvocation write = jOut.invoke("writeBoolean");
										write.arg(jParam.invoke(readMethod.getName()));
										tryBlock.body().add(write);
									} else if (propertyDescriptor.getPropertyType().equals(String.class)) {
										JInvocation write = jOut.invoke("writeUTF");
										write.arg(jParam.invoke(readMethod.getName()));
										tryBlock.body().add(write);
										// TODO add if propertyDescriptor.getPropertyType() instanceof
										// MutableValue.class etc.
										// TODO else if same type as obj class, i.e possible self reference, need to
										// avoid self reference infinite loop
										// TODO ?else detect infinite loop for A references B, B references A
									} else if (propertyDescriptor.getPropertyType().getName().contains("$")
											|| propertyDescriptor.getPropertyType().getCanonicalName()
													.contains(clazz.getCanonicalName())) {
										// no inner classes
										continue;
									} else if (propertyDescriptor.getPropertyType().getSimpleName().startsWith("new")
											|| propertyDescriptor.getPropertyType().getSimpleName()
													.startsWith("return")) {
										continue;
									} else if (propertyDescriptor.getPropertyType() == null) {
										continue;
									} else if (propertyDescriptor.getPropertyType().getName() == null) {
										continue;
									} else {
										// call the object's serializer if it exists
										JTryBlock writeTryBlock = tryBlock.body()._try();
										writeTryBlock._catch(jSerializerNotFoundException);
										JClass jPropertyClass = codeModel
												.directClass(propertyDescriptor.getPropertyType().getCanonicalName());
										JInvocation jMessageSerializerObj = jMessageSerializer

												.staticInvoke("getInstance").invoke("getSerializer")
												.arg(JExpr.direct(jPropertyClass.fullName() + ".class"));
										JVar jISerializerObj = writeTryBlock.body().decl(jISerializer, "serializer",
												jMessageSerializerObj);
										JInvocation write = jOut.invoke("write");
										write.arg(jISerializerObj.invoke("serialize")
												.arg(jParam.invoke(readMethod.getName())));
										writeTryBlock.body().add(write);
									}

								}
								count++;
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
						codeModel.build(new File("src/main/java/"));
					}
				} catch (IllegalArgumentException | IntrospectionException | NoClassDefFoundError | SecurityException
						| ExceptionInInitializerError | InstantiationError | JClassAlreadyExistsException e1) {
					e1.printStackTrace();
					continue;
				}
			}
		} catch (

		Exception e) {
			e.printStackTrace();
		}
	}

}
