package com.spring.service.Interface;

public interface FileSystemClassLoader {
	public void setPath(String path);
	public Class<?> getClass(String name) throws ClassNotFoundException;
	public FileSystemClassLoader getClassLoader();
}
