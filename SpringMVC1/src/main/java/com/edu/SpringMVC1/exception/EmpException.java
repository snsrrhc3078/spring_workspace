package com.edu.SpringMVC1.exception;

public class EmpException extends RuntimeException{
	public EmpException(String msg) {
		super(msg);
	}
	public EmpException(String msg, Throwable e) {
		super(msg, e);
	}
}
