package com.elastic.demo.entity;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "company")
public class Company {
	@Id
	private long id;
	
	@Field(type= FieldType.Text)
	private String name;
	
	@Field(type= FieldType.Text)
	private String ceoName;
	
	@Field(type= FieldType.Text)
	private String sectorName;
	
	@Field(type= FieldType.Text)
	private String descr;
	
	@Field(type= FieldType.Integer)
	private int turnoverInBil;
	
	@Field(type= FieldType.Nested, includeInParent= true)
	private List<IPODetail> ipoList= new ArrayList<IPODetail>();
	
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
	public List<IPODetail> getIpoList() {
		return ipoList;
	}
	public void addIPODetail(IPODetail ipo) {
		this.ipoList.add(ipo);
	}
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
