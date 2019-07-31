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
		// �����߳�ʵ��
		Callable<Object> call = new CallableTask();
		// ʹ��FutureTask�߱�����ֵ���̼߳����
		FutureTask<?> task = new FutureTask<Object>(call);
		// ����Thread����������task
		Thread thead = new Thread(task);
		// ����Ϊ��̨�߳�
		thead.setDaemon(true);
		// �����߳�
		thead.start();
		// ����timeoutʱ�䣬�鿴���ؽ��
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
