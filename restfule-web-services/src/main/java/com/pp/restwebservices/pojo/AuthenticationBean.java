package com.pp.restwebservices.pojo;

public class AuthenticationBean {
	private String msg;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public AuthenticationBean(String msg) {
		this.msg=msg;
	}

	@Override
	public String toString() {
		return "HelloWorldBean [msg=" + msg + "]";
	}
	
}
