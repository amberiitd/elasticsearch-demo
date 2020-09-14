package com.elastic.demo.service;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.elastic.demo.dao.CompanyRepository;
import com.elastic.demo.dto.CompanyDto;
import com.elastic.demo.dto.IPODetailDto;
import com.elastic.demo.entity.Company;
import com.elastic.demo.entity.IPODetail;
import com.elastic.demo.exception.NotFoundException;
import com.elastic.demo.model.Count;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CompanyServiceImpl implements CompanyService{
	private static Logger logger= LoggerFactory.getLogger(CompanyServiceImpl.class);
	
	private static Long refId= 1000L;
	static {
		refId+= getDocumentCount();
	}

	
	@Autowired 
	private CompanyRepository companyRepo;
	
	private static ModelMapper modelMapper = new ModelMapper();

	@Override
	public CompanyDto getByName(String companyName) throws NotFoundException{
		// TODO Auto-generated method stub
		Iterator<Company> iter=companyRepo.findByName(companyName).iterator();
		if(iter.hasNext())
			return modelMapper.map(iter.next(), CompanyDto.class);
		else
			throw new NotFoundException("company with name \""+companyName+"\", not found");
	}

	@Override
	@Transactional
	public void create(CompanyDto companyDto) {
		// TODO Auto-generated method stub
		Company company = modelMapper.map(companyDto, Company.class);
		company.setId(++refId);
		companyRepo.save(company);
	}

	@Override
	@Transactional
	public void addIPODetail(String companyName, IPODetailDto ipoDto) throws NotFoundException{
		// TODO Auto-generated method stub
		Iterator<Company> iter = companyRepo.findByName(companyName).iterator();
		if (iter.hasNext()) {
			Company company= iter.next();
			company.addIPODetail(modelMapper.map(ipoDto, IPODetail.class));
			
			companyRepo.save(company);
		}
		else
			throw new NotFoundException("company with name \""+companyName+"\", not found");
		
	}

	@Override
	public List<CompanyDto> getAll() {
		// TODO Auto-generated method stub
		return StreamSupport.stream(companyRepo.findAll().spliterator(), false)
				.map(company-> modelMapper.map(company, CompanyDto.class)).collect(Collectors.toList());
	}
	
	private static long getDocumentCount() {
		Long count=0L; 
		ObjectMapper objectMapper= new ObjectMapper();// in case of InputStream , use ObjectMaaper instead of ModelMapper
		try {
			HttpUriRequest request= new HttpGet("http://localhost:9200/company/_count?filter_path=count");
			HttpResponse response = HttpClientBuilder.create().build().execute( request );
			
			count = objectMapper.readValue(response.getEntity().getContent(), Count.class).getCount();
		}catch(Exception e) {
			logger.info("caught Exception in getDocument()");
		}
		return count;
		
	}

	@Override
	public List<CompanyDto> getMatchingCompany(String matchString) {
		// TODO Auto-generated method stub
		return StreamSupport.stream(companyRepo.findMatching(matchString).spliterator(), false)
				.map(company-> modelMapper.map(company, CompanyDto.class)).collect(Collectors.toList());
	}

}
