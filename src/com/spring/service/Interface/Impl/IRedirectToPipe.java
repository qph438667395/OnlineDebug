package com.spring.service.Interface.Impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

import org.springframework.stereotype.Component;

import com.spring.service.Interface.IRedirection;

@Component("IRedirectToPipeA")
public class IRedirectToPipe implements IRedirection {
	private InputStream oldInputStream;
	private PipedInputStream pipedIs;
	private PipedOutputStream pipedOs;
	
	@Override
	public void IRedirect() {
		oldInputStream=System.in;
		pipedIs = new PipedInputStream();
		pipedOs = new PipedOutputStream();
		try {
			pipedOs.connect(pipedIs);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.setIn(pipedIs);
	}

	@Override
	public void IReset() {
		System.setIn(oldInputStream);
	}

	@Override
	public String read() {
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
	public void write(String str) {
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

	@Override
	public void writeClose() {
		try {
			pipedOs.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void readClose() {
		try {
			pipedIs.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
