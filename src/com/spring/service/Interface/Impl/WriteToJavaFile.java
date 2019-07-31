package com.spring.service.Interface.Impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.stereotype.Component;

import com.spring.constant.SysConfig;
import com.spring.service.Interface.WriteToFile;
@Component("writeToJavaFileA")
public class WriteToJavaFile implements WriteToFile {

	@Override
	public void write(String str) {
		File file=new File(SysConfig.pathJava+SysConfig.javaName+".java");
		try {
			if(!file.exists()){
					file.createNewFile();
			}
			FileOutputStream fileOutputStream=new FileOutputStream(file);
			fileOutputStream.write(str.getBytes());
			fileOutputStream.flush();
			fileOutputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
