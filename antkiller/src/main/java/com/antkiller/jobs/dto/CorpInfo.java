package com.antkiller.jobs.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@XmlRootElement(name = "result")
public class CorpInfo {

	@XmlElement(name = "list")
	private List<Corp> corpList;

	@Getter
	@Setter
	@ToString
	@XmlRootElement(name = "list")
	public static class Corp {

		@XmlElement(name = "corp_code")
		private String corpCode;
		@XmlElement(name = "corp_name")
		private String corpName;
		
		private String stockCode;
		@XmlElement(name = "modify_date")
		private String modifyDate;
		
		@Override
		public String toString() {
			return this.corpCode+":"+this.corpName+":"+this.stockCode+":"+this.modifyDate;
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

		public String getModifyDate() {
			return modifyDate;
		}

		public void setModifyDate(String modifyDate) {
			this.modifyDate = modifyDate;
		}

		public String getStockCode() {
			return this.stockCode;
		}
		
		@XmlElement(name = "stock_code")
		public void setStockCode(String stockCode) {
			stockCode=stockCode.replaceAll(" ", "");
			if("".equals(stockCode)) {
				stockCode=null;
			}
			this.stockCode=stockCode;
		}

	}
	
	public List<Corp> getCorpList(){
		return this.corpList;
	}

}
