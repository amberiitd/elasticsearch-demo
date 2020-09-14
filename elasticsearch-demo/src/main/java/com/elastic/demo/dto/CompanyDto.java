package com.elastic.demo.dto;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.elastic.demo.entity.IPODetail;

public class CompanyDto {
	
	@NotNull
	@Size(min=2)
	private String name;
	
	private String ceoName;
	
	@NotNull
	private String sectorName;
	
	private String descr;
	
	
	public List<IPODetail> getIpoList() {
		return ipoList;
	}
	public void setIpoList(List<IPODetail> ipoList) {
		this.ipoList = ipoList;
	}
	private int turnoverInBil;
	private List<IPODetail> ipoList;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCeoName() {
		return ceoName;
	}
	public void setCeoName(String ceoName) {
		this.ceoName = ceoName;
	}
	public String getSectorName() {
		return sectorName;
	}
	public void setSectorName(String sectorName) {
		this.sectorName = sectorName;
	}
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
	public int getTurnoverInBil() {
		return turnoverInBil;
	}
	public void setTurnoverInBil(int turnoverInBil) {
		this.turnoverInBil = turnoverInBil;
	}
	
	
}
