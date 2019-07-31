package com.spring.constant;

public class SysConfig {
	public static String pathJava;
	public static String javaName;
	public static long runTimeOut;
	public static long getRunTimeOut() {
		return runTimeOut;
	}
	public static void setRunTimeOut(long runTimeOut) {
		SysConfig.runTimeOut = runTimeOut;
	}
	public static String getPathJava() {
		return pathJava;
	}
	public static void setPathJava(String pathJava) {
		SysConfig.pathJava = pathJava;
	}
	public static String getJavaName() {
		return javaName;
	}
	public static void setJavaName(String javaName) {
		SysConfig.javaName = javaName;
	}
	
}
