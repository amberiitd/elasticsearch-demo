package com.elastic.demo.exception;

import java.util.Date;

public class ExceptionResponse {
	
	private Date timestamp;
	private String msg;
	private String path; 
	
	public ExceptionResponse(Date date, String msg, String path) {
		super();
		this.timestamp = date;
		this.msg = msg;
		this.path=path;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path= path;
	}
	
	
}
