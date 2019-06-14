package com.proxy.cglib;

public class BookieService {
	
	public String save( String str) {
		System.out.println("save方法。。。" + str);
		return "result: " + str;
	}
	
}
