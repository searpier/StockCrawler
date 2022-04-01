package com.antkiller.jobs.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class StockInfo {

	private String corpCode;
	private String corpName;
	private String stockCode;
	private String per;
	private String eps;
	private String cnsPer;
	private String cnsEps;
	private String pbr;
	private String dvr;
	private String indate;
	private String roa;
	private String roe;
	private String sichong;
	private String profit;
	private String category;

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

	public String getPer() {
		return per;
	}

	public void setPer(String per) {
		this.per = per;
	}

	public String getEps() {
		return eps;
	}

	public void setEps(String eps) {
		this.eps = eps;
	}

	public String getCnsPer() {
		return cnsPer;
	}

	public void setCnsPer(String cnsPer) {
		this.cnsPer = cnsPer;
	}

	public String getCnsEps() {
		return cnsEps;
	}

	public void setCnsEps(String cnsEps) {
		this.cnsEps = cnsEps;
	}

	public String getPbr() {
		return pbr;
	}

	public void setPbr(String pbr) {
		this.pbr = pbr;
	}

	public String getDvr() {
		return dvr;
	}

	public void setDvr(String dvr) {
		this.dvr = dvr;
	}

	public String getIndate() {
		return indate;
	}

	public void setIndate(String indate) {
		this.indate = indate;
	}

	public String getRoa() {
		return roa;
	}

	public void setRoa(String roa) {
		this.roa = roa;
	}

	public String getRoe() {
		return roe;
	}

	public void setRoe(String roe) {
		this.roe = roe;
	}

	public String getSichong() {
		return sichong;
	}

	public void setSichong(String sichong) {
		this.sichong = sichong;
	}

	public String getProfit() {
		return profit;
	}

	public void setProfit(String profit) {
		this.profit = profit;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

}