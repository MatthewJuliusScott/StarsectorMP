package com.dasmatarix.util;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.File;
import java.lang.reflect.Method;
import java.util.Set;

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
			for (Class clazz : doNotObfuscateTypes) {
				JCodeModel codeModel = new JCodeModel();
				JPackage jPackage = codeModel._package("com.dasmatarix.multiplayer.serializer");
				int count = 0;
				try {
					String className = clazz.getName();
					if (className.lastIndexOf('.') > -1) {
						className = className.replace('$', '.');
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
					JClass jClazz = codeModel.directClass(clazz.getCanonicalName());

					// add the serialize method
					JMethod serializeMethod = jdefinedClass.method(JMod.PUBLIC, byte[].class, "serialize");
					// TODO add each of the values as bytes including recursively calling serialize
					// and collections to fixed length arrays

					JVar jBos = serializeMethod.body().decl(jByteArrayOutputStream, "bos", JExpr._new(jByteArrayOutputStream));
					serializeMethod.body().decl(jObjectOutputStream, "out", JExpr._null());
					JTryBlock tryBlock = serializeMethod.body()._try();
					JExpression returnStatement = jBos.invoke("toByteArray");
					tryBlock.body()._return(returnStatement);
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
								} // TODO add if propertyDescriptor.getPropertyType() instanceof
									// MutableValue.class etc.

								count++;
							}
						} catch (Exception | ExceptionInInitializerError | NoClassDefFoundError e2) {
							// do nothing
						}

					}
					// Generate the code
					if (count > 0) {						
						deserializeMethod.body()._return(jClazzObj);
						codeModel.build(new File("src/main/java/"));
					}
				} catch (IllegalArgumentException | IntrospectionException | NoClassDefFoundError | SecurityException
						| ExceptionInInitializerError | InstantiationError | JClassAlreadyExistsException e1) {
					e1.printStackTrace();
					continue;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}