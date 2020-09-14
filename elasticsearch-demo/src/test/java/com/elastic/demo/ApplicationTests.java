package com.elastic.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import com.google.gson.*;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.internal.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.elastic.demo.controller.CompanyServiceController;
import com.elastic.demo.dto.CompanyDto;
import com.elastic.demo.entity.Company;
import com.elastic.demo.exception.NotFoundException;
import com.elastic.demo.service.CompanyService;
import com.elastic.demo.service.CompanyServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ApplicationTests {
	@InjectMocks
	private CompanyServiceController controller;
	
	@Mock
	private CompanyService companyService;
	
	Gson gson= new Gson();
	
	private MockMvc mockMvc;
	
	@Before
	public void setUp() {
		mockMvc= MockMvcBuilders.standaloneSetup(controller).build();
	}
	
	@Test
	public void welcomeTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/"))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.content().string("Welcome To Company Service Using Elasticsearch DB"));
	}
	
	@Test
	public void testGetAllCompany() throws Exception {
		
		Mockito.when(companyService.getAll()).thenReturn(new ArrayList<CompanyDto>());
		
		MvcResult result= mockMvc.perform(MockMvcRequestBuilders.get("/api/company")).andReturn();
		
		assertEquals(HttpStatus.OK.value(),result.getResponse().getStatus());
	}
	@Test
	public void testGetCompany() throws Exception {
		Mockito.when(companyService.getByName(Mockito.anyString()) ).thenReturn(new CompanyDto());
		
		String companyName= RandomString.make(7);
		mockMvc.perform(MockMvcRequestBuilders.get("/api/company/"+companyName))
		.andExpect(MockMvcResultMatchers.status().isFound());
	}
	
	@Test
	public void testAddCompany() throws Exception {
		Mockito.doNothing().when(companyService).create(Mockito.any());
		
		CompanyDto companyDto = new CompanyDto();
		companyDto.setName("xyz");
		companyDto.setSectorName("abcd");

		mockMvc.perform(
				MockMvcRequestBuilders.post("/api/company/add")
				.content(gson.toJson(companyDto))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.ALL)
				)
				.andExpect(MockMvcResultMatchers.status().isCreated());
	}
	
}
