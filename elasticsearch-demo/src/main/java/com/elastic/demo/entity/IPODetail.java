package com.elastic.demo.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "ipo-detail")
public class IPODetail {
	@Id
	private long id;
	
	@Field(type= FieldType.Text)
	private String companyName;
	
	@Field(type= FieldType.Double)
	private double pricePerShare;
	
	@Field(type= FieldType.Long)
	private long totalShare;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public double getPricePerShare() {
		return pricePerShare;
	}
	public void setPricePerShare(double pricePerShare) {
		this.pricePerShare = pricePerShare;
	}
	public long getTotalShare() {
		return totalShare;
	}
	public void setTotalShare(long totalShare) {
		this.totalShare = totalShare;
	}
	
	
}
