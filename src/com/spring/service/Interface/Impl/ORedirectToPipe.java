package com.spring.service.Interface.Impl;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;

import org.springframework.stereotype.Component;

import com.spring.service.Interface.ORedirection;

@Component("ORedirectToPipeA")
public class ORedirectToPipe implements ORedirection {

	private PrintStream oldOutPrintStream;
	private PrintStream oldErrPrintStream;
	private PipedInputStream pipedIs;
	private PipedOutputStream pipedOs;
	private PrintStream pStream;
	@Override
	public void oRedirect() {
		oldOutPrintStream= System.out;
		oldErrPrintStream=System.err;
		pipedIs = new PipedInputStream();
		pipedOs = new PipedOutputStream();

		try {
			pipedOs.connect(pipedIs);
		} catch (IOException e) {
			e.printStackTrace();
		}
		pStream = new PrintStream(pipedOs);
		System.setOut(pStream);
		System.setErr(pStream);	
	}
	
	@Override
	public void oReset() {
		System.setErr(oldOutPrintStream);
		System.setOut(oldErrPrintStream);
	}
	
	@Override
	public void close(){
		try {
			pipedIs.close();
			pipedOs.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		pStream.close();
	}
	@Override
	public String read(){
		byte bytes[] = null;
		try {
			int length = pipedIs.available();
			if(length!=0){
				bytes = new byte[length];
				pipedIs.read(bytes);
			}else{
				return "";
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(bytes!=null)
				return new String(bytes);
		return null;
	}
	@Override
	public void write(String str){
		try {
			pipedOs.write(str.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void clear() {
		byte bytes[] = null;
		try {
			int length = pipedIs.available();
			if(length!=0){
				bytes = new byte[length];
				pipedIs.read(bytes);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
}
