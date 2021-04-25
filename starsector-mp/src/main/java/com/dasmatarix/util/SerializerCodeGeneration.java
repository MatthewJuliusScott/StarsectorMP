package com.dasmatarix.util;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.Set;

import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import com.fs.util.DoNotObfuscate;
import com.sun.codemodel.JClass;
import com.sun.codemodel.JClassAlreadyExistsException;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JExpr;
import com.sun.codemodel.JFieldVar;
import com.sun.codemodel.JInvocation;
import com.sun.codemodel.JMethod;
import com.sun.codemodel.JMod;
import com.sun.codemodel.JPackage;
import com.sun.codemodel.JVar;

public class SerializerCodeGeneration {
	
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
					JClass jClazz = codeModel.directClass(clazz.getCanonicalName());

					// add the serialize method
					JMethod serializeMethod = jdefinedClass.method(JMod.PUBLIC, byte[].class, "serialize");
					serializeMethod.body().directStatement("return new byte[0];");
					// TODO add each of the values as bytes including recursively calling serialize
					// and collections to fixed length arrays

					// add the deserialize method
					JMethod deserializeMethod = jdefinedClass.method(JMod.PUBLIC, clazz, "deserialize");
					JVar jObj = deserializeMethod.body().decl(jObjenesis, "objenesis", JExpr._new(jObjenesisStd));
					JInvocation newInstanceExpression = jObj.invoke("newInstance");
					newInstanceExpression.arg(jClazz.dotclass());
					JVar jClazzObj = deserializeMethod.body().decl(jClazz, "obj", newInstanceExpression);
					// set all the values from bytes before return
					deserializeMethod.body()._return(jClazzObj);

					/*
					ByteArrayOutputStream bos = new ByteArrayOutputStream();
					ObjectOutputStream out = null;
					try {
					  out = new ObjectOutputStream(bos);   
					  out.writeObject(yourObject);
					  out.flush();
					  byte[] yourBytes = bos.toByteArray();
					  // ...
					} finally {
					  try {
					    bos.close();
					  } catch (IOException ex) {
					    // ignore close exception
					  }
					}
					
					
					ByteArrayInputStream bis = new ByteArrayInputStream(yourBytes);
					ObjectInput in = null;
					try {
					  in = new ObjectInputStream(bis);
					  Object o = in.readObject();
					  // ...
					} finally {
					  try {
					    if (in != null) {
					      in.close();
					    }
					  } catch (IOException ex) {
					    // ignore close exception
					  }
					}
					*/
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
									System.out.println("    " + propertyDescriptor.getName() + " : " + propertyDescriptor.getPropertyType().getName());
									System.out.println("        " + readMethod.getName());
									System.out.println("        " + writeMethod.getName());
									// serializeMethod.body().decl();
								} // TODO add if propertyDescriptor.getPropertyType() instanceof MutableValue.class etc.

								count++;
							}
						} catch (Exception | ExceptionInInitializerError | NoClassDefFoundError e2) {
							// do nothing
						}

					}
					if (count > 0) {

					}
				} catch (IllegalArgumentException | IntrospectionException | NoClassDefFoundError | SecurityException
						| ExceptionInInitializerError | InstantiationError | JClassAlreadyExistsException e1) {
					e1.printStackTrace();
					continue;
				}
				// Generate the code
				if (count > 0) {
					codeModel.build(new File("src/main/java/"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
