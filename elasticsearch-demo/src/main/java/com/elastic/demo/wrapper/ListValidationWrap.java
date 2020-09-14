package com.elastic.demo.wrapper;

import java.util.List;

import javax.validation.Valid;

public class ListValidationWrap<T> {
	
	@Valid
	private List<T> requestBody;

	public List<T> getRequestBody() {
		return requestBody;
	}

	public void setRequestBody(List<T> requestBody) {
		this.requestBody = requestBody;
	}
	
}
