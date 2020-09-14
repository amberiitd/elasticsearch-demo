package com.elastic.demo.service;

import java.util.Iterator;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.elastic.demo.dao.IPODetailRepository;
import com.elastic.demo.dto.IPODetailDto;
import com.elastic.demo.entity.Company;
import com.elastic.demo.entity.IPODetail;
import com.elastic.demo.exception.NotFoundException;

@Service
public class IPODetailServiceImpl implements IPODetailService{
	
	private static long refId= 1000L;
	
	@Autowired
	private IPODetailRepository ipoRepo;
	
	@Autowired
	private CompanyService companyService;
	
	@Autowired 
	private ModelMapper modelMapper;

	@Override
	public IPODetailDto getByCompanyName(String companyName) throws NotFoundException {
		// TODO Auto-generated method stub
		Iterator<IPODetail> iter = ipoRepo.findByCompanyName(companyName).iterator();
		if (iter.hasNext())
			return modelMapper.map(iter.next(), IPODetailDto.class);
		else
			throw new NotFoundException("company with name \""+companyName+"\", not found");
	}

	@Override
	@Transactional
	public void create(IPODetailDto ipoDto, String companyName) throws NotFoundException {
		// TODO Auto-generated method stub
		try {
			companyService.addIPODetail(companyName, ipoDto);
			IPODetail ipo= modelMapper.map(ipoDto, IPODetail.class);
			ipo.setId(++refId);
			ipoRepo.save(ipo);
		}catch(NotFoundException e) {
			throw e;
		}
		
	}

}
