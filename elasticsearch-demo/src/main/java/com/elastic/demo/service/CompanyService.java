package com.elastic.demo.service;

import java.util.List;

import com.elastic.demo.dto.CompanyDto;
import com.elastic.demo.dto.IPODetailDto;
import com.elastic.demo.exception.NotFoundException;

public interface CompanyService {
	public List<CompanyDto> getAll();
	public CompanyDto getByName(String companyName) throws NotFoundException;
	public void create(CompanyDto companyDto);
	public void addIPODetail(String companyName,IPODetailDto ipoDto) throws NotFoundException;
	public List<CompanyDto> getMatchingCompany(String matchString);
}
