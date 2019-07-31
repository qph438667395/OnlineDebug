package com.spring.service.Interface.Impl;

import javax.tools.JavaCompiler;

import javax.tools.ToolProvider;

import org.springframework.stereotype.Component;

import com.spring.constant.SysConfig;
import com.spring.service.Interface.Compiler;

@Component("compilerJavaFileA")
public class CompilerJavaFile implements Compiler {

	@Override
	public boolean compiler() {
		JavaCompiler javaCompiler=ToolProvider.getSystemJavaCompiler();
		int result=javaCompiler.run(null,null, null,SysConfig.pathJava+SysConfig.javaName+".java");
		if(result==0){
			return true;
		}
		return false;
	}

}
