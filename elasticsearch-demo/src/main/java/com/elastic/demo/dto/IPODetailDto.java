package com.elastic.demo.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class IPODetailDto {
	
	@NotNull
	@Size(min=2)
	private String companyName;
	
	@NotNull
	private double pricePerShare;
	
	private long totalShare;
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
