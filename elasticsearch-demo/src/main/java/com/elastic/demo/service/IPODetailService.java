package com.elastic.demo.service;

import com.elastic.demo.dto.IPODetailDto;
import com.elastic.demo.exception.NotFoundException;

public interface IPODetailService {
	public IPODetailDto getByCompanyName(String companyName) throws NotFoundException;
	public void create(IPODetailDto ipoDto, String companyName) throws NotFoundException;
}
