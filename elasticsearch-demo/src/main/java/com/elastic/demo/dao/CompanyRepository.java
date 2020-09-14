package com.elastic.demo.dao;

import java.util.List;

import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.elastic.demo.entity.Company;

public interface CompanyRepository extends ElasticsearchRepository<Company, Long>{
	public List<Company> findByName(String name);
	
	@Query("{ \"match\": { \"name\": { \"query\": \"?0\", \"operator\": \"or\" } } }")
	public List<Company> findMatching(String matchString);
}
