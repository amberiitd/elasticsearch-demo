package com.elastic.demo.dao;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.elastic.demo.entity.IPODetail;

public interface IPODetailRepository extends ElasticsearchRepository<IPODetail, Long> {
	public List<IPODetail> findByCompanyName(String companyName);
}
