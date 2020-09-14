package com.elastic.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elastic.demo.dto.CompanyDto;
import com.elastic.demo.dto.IPODetailDto;
import com.elastic.demo.exception.InvalidInputException;
import com.elastic.demo.exception.NotFoundException;
import com.elastic.demo.service.CompanyService;
import com.elastic.demo.service.IPODetailService;
import com.elastic.demo.wrapper.ListValidationWrap;

@RestController
@RequestMapping("/api")
public class CompanyServiceController {
	
	//private Logger logger= LoggerFactory.getLogger(CompanyServiceController.class);
	
	@Autowired
	private CompanyService companyService;
	
	@Autowired 
	private IPODetailService ipoService;
	
	@GetMapping("/")
	public String welcome() {
		return "Welcome To Company Service Using Elasticsearch DB";
	}
	@GetMapping("/company")
	public List<CompanyDto> getAllCompany(){
		return companyService.getAll();
	}
	
	@GetMapping("/company/match")
	public List<CompanyDto> getMatchingCompany(@RequestBody String matchString) throws Exception{
		if (matchString==null)
			throw new Exception("Search string is null");
		return companyService.getMatchingCompany(matchString);
	}
	
	@GetMapping("/company/{companyName}")
	public ResponseEntity<CompanyDto> getCompany(@PathVariable("companyName") String companyName) throws NotFoundException{
		CompanyDto companyDto;
		try {
			companyDto= companyService.getByName(companyName);
		}catch(NotFoundException e) {
			throw e;
		}
		return new ResponseEntity<CompanyDto>(companyDto, HttpStatus.FOUND);
	}
	
	@PostMapping("/company/add")
	public ResponseEntity<String> addCompany(@Valid @RequestBody CompanyDto companyDto, BindingResult result ) throws InvalidInputException{
		if(result.hasErrors())
			throw new InvalidInputException("Invalid Request Body");
		companyService.create(companyDto);
		return new ResponseEntity<String>("Success", HttpStatus.CREATED);
	}
	
	@PostMapping("/company/add/bulk")
	public ResponseEntity<String> addCompanyInBulk(@Valid @RequestBody ListValidationWrap<CompanyDto> wrap , BindingResult result)throws InvalidInputException{
		if(result.hasErrors())
			throw new InvalidInputException("Invalid Request Body");
		List<CompanyDto> companyDtoList= wrap.getRequestBody();
		for(CompanyDto companyDto: companyDtoList )
			companyService.create(companyDto);
		return new ResponseEntity<String>("Success", HttpStatus.CREATED);
	}
	
	@GetMapping("/ipo/{companyName}")
	public ResponseEntity<IPODetailDto> getIPODetail(@PathVariable("companyName") String companyName) throws NotFoundException {
		IPODetailDto ipoDto;
		try {
			ipoDto= ipoService.getByCompanyName(companyName);
		}
		catch(NotFoundException e) {
			throw e;
		}
		return new ResponseEntity<IPODetailDto>(ipoDto, HttpStatus.FOUND);
	}
	
	@PostMapping("/ipo/add")
	public ResponseEntity<String> addIPODetail( @Valid @RequestBody IPODetailDto ipoDto, BindingResult result) throws NotFoundException{
		if(result.hasErrors())
			throw new InvalidInputException("Invalid Request Body");
		try {
			ipoService.create(ipoDto, ipoDto.getCompanyName());
		}
		catch(NotFoundException e) {
			throw new NotFoundException(e.getMessage()+", WARNING: few entities might have been pushed!!!");
		}
		return new ResponseEntity<String>("Success", HttpStatus.CREATED);
	}
	
	@PostMapping("/ipo/add/bulk")
	public ResponseEntity<String> addIPODetail( @Valid @RequestBody ListValidationWrap<IPODetailDto> wrap, BindingResult result) throws InvalidInputException, NotFoundException{
		if(result.hasErrors())
			throw new InvalidInputException("Invalid Request Body");
		try {
			List<IPODetailDto> ipoDtoList= wrap.getRequestBody();
			for (IPODetailDto ipoDto: ipoDtoList)
				ipoService.create(ipoDto, ipoDto.getCompanyName());
		}catch(NotFoundException e) {
			throw new NotFoundException(e.getMessage()+", WARNING: few entities might have been pushed!!!");
		}
		return new ResponseEntity<String>("Success", HttpStatus.CREATED);
	}
	
	
}
