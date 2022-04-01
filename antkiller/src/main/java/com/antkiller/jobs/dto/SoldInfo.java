package com.antkiller.jobs.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SoldInfo {
	private String pickId;
	private String corpCode;
	private String corpName;
	private String stockCode;
	private String bstockPrice;
	private String sstockPrice;
	private String idate;

	public String getPickId() {
		return pickId;
	}

	public void setPickId(String pickId) {
		this.pickId = pickId;
	}

	public String getCorpCode() {
		return corpCode;
	}

	public void setCorpCode(String corpCode) {
		this.corpCode = corpCode;
	}

	public String getCorpName() {
		return corpName;
	}

	public void setCorpName(String corpName) {
		this.corpName = corpName;
	}

	public String getStockCode() {
		return stockCode;
	}

	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}

	public String getBstockPrice() {
		return bstockPrice;
	}

	public void setBstockPrice(String bstockPrice) {
		this.bstockPrice = bstockPrice;
	}

	public String getSstockPrice() {
		return sstockPrice;
	}

	public void setSstockPrice(String sstockPrice) {
		this.sstockPrice = sstockPrice;
	}

	public String getIdate() {
		return idate;
	}

	public void setIdate(String idate) {
		this.idate = idate;
	}

}
