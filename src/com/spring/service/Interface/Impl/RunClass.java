package com.spring.service.Interface.Impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.spring.constant.SysConfig;
import com.spring.service.Interface.FileSystemClassLoader;
import com.spring.service.Interface.Run;

@Component("runClassA")
public class RunClass implements Run {
	@Resource(name = "FileSystemClassLoaderA")
	private FileSystemClassLoader fileSystemClassLoader;

	@Override
	public boolean run(){
		// 创建线程实例
		Callable<Object> call = new CallableTask();
		// 使用FutureTask具备返回值的线程监控类
		FutureTask<?> task = new FutureTask<Object>(call);
		// 创建Thread，用于运行task
		Thread thead = new Thread(task);
		// 设置为后台线程
		thead.setDaemon(true);
		// 启动线程
		thead.start();
		// 设置timeout时间，查看返回结果
		try{
			task.get(SysConfig.runTimeOut,TimeUnit.MILLISECONDS);
		} catch (InterruptedException | ExecutionException e) {
			task.cancel(true);
			e.printStackTrace();
			return false;
		} catch (TimeoutException e) {
			task.cancel(true);
			System.err.println("TimeoutException");
			return false;
		}
		return true;
	}

	class CallableTask implements Callable<Object> {

		@Override
		public Object call() throws Exception {
			fileSystemClassLoader=fileSystemClassLoader.getClassLoader();
			fileSystemClassLoader.setPath(SysConfig.pathJava);
			try {
				Class<?> cla= fileSystemClassLoader
						.getClass(SysConfig.javaName);
				Method method = cla.getDeclaredMethod("main", String[].class);
				method.invoke(null, (Object) new String[] {});	
			} catch (ClassNotFoundException | NoSuchMethodException
					| SecurityException | IllegalAccessException
					| IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}
			return null;
		}

	}

}
