package com.spring.service.Interface.Impl;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.stereotype.Component;

import com.spring.service.Interface.FileSystemClassLoader;

@Component("FileSystemClassLoaderA")
public class FileSystemClassLoaderImpl extends ClassLoader implements
		FileSystemClassLoader {
	private String rootDir;

	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		byte[] classData = getClassData(name);
		if (classData == null) {
			throw new ClassNotFoundException();
		} else {
			return defineClass(name, classData, 0, classData.length);
		}
	}

	private byte[] getClassData(String className) {
		String path = classNameToPath(className);
		try {
			@SuppressWarnings("resource")
			InputStream ins = new FileInputStream(path);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			int bufferSize = 4096;
			byte[] buffer = new byte[bufferSize];
			int bytesNumRead = 0;
			while ((bytesNumRead = ins.read(buffer)) != -1) {
				baos.write(buffer, 0, bytesNumRead);
			}
			return baos.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private String classNameToPath(String className) {
		return rootDir + className + ".class";
	}

	@Override
	public void setPath(String path) {
		rootDir = path;
	}

	@Override
	public Class<?> getClass(String name) throws ClassNotFoundException {
		return loadClass(name);
	}

	@Override
	public FileSystemClassLoader getClassLoader() {
		
		return new FileSystemClassLoaderImpl();
	}
	
	

}
