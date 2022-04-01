package com.antkiller.jobs.tasklet;

import java.io.IOException;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.jsoup.Jsoup;

import com.antkiller.jobs.dto.CorpInfo.Corp;
import com.antkiller.jobs.dto.StockInfo;
import com.antkiller.jobs.service.AntKillerService;

@Component
public class StockInfoTasklet implements Tasklet {

	@Autowired
	AntKillerService antKillerService;
	
	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		// TODO Auto-generated method stub
		List<Corp> cList=antKillerService.selectCorpCode();
		
		for(Corp c:cList) {
			
			Document doc;
			Document rdoc;
			Document sdoc;
			Document cdoc;
			String stockCode=c.getStockCode();
			System.out.println(stockCode);
			String rURL="https://comp.fnguide.com/SVO2/ASP/SVD_FinanceRatio.asp?pGB=1&gicode=A"+stockCode+"&cID=&MenuYn=Y&ReportGB=&NewMenuID=104&stkGb=701";
			String sURL="https://comp.fnguide.com/SVO2/ASP/SVD_Invest.asp?pGB=1&gicode=A"+stockCode+"&cID=&MenuYn=Y&ReportGB=&NewMenuID=105&stkGb=701";
			String cURL="https://comp.fnguide.com/SVO2/ASP/SVD_Finance.asp?pGB=1&gicode=A"+stockCode+"&cID=&MenuYn=Y&ReportGB=&NewMenuID=103&stkGb=701";
			String URL = "https://finance.naver.com/item/main.nhn?code=";
			URL+=stockCode;
			
			StockInfo stock=new StockInfo();
			
			try {
				doc = Jsoup.connect(URL).get();
				rdoc = Jsoup.connect(rURL).get();
				sdoc = Jsoup.connect(sURL).get();
				cdoc = Jsoup.connect(cURL).get();

				Elements per = doc.select("#_per");
				Elements eps = doc.select("#_eps");
				Elements cnsPer = doc.select("#_cns_per");
				Elements cnsEps = doc.select("#_cns_eps");
				Elements pbr = doc.select("#_pbr");
				Elements dvr = doc.select("#_dvr");
				Elements roae = rdoc.select("#p_grid1_17 > td.r.cle");
				Elements roee = rdoc.select("#p_grid1_18 > td.r.cle");	

				Elements sichong = sdoc.select("#compBody > div.section.ul_de > div:nth-child(4) > div.um_table > table > tbody > tr:nth-child(2) > td:nth-child(10)");
				Elements profit = sdoc.select("#compBody > div.section.ul_de > div.ul_col2wrap.pd_t25 > div.um_table > table > tbody > tr:nth-child(47) > td.cle.r.tdbg_y");	
				Elements category = cdoc.select("#strMarketTxt");	
				
				stock.setCorpCode(c.getCorpCode());
				stock.setCorpName(c.getCorpName());
				stock.setStockCode(stockCode);

				if(per.size()>0) {
					stock.setPer(per.get(0).text().replaceAll(",",""));
				}
				if(eps.size()>0) {
					stock.setEps(eps.get(0).text().replaceAll(",",""));
				}
				if(cnsPer.size()>0) {
					stock.setCnsPer(cnsPer.get(0).text().replaceAll(",",""));
				}
				if(cnsEps.size()>0) {
					stock.setCnsEps(cnsEps.get(0).text().replaceAll(",",""));
				}
				if(pbr.size()>0) {
					stock.setPbr(pbr.get(0).text().replaceAll(",",""));
				}
				if(dvr.size()>0) {
					stock.setDvr(dvr.get(0).text().replaceAll(",",""));
				}

				if(dvr.size()>0) {
					stock.setDvr(dvr.get(0).text().replaceAll(",",""));
				}

				if(roae.size()>0) {
					String roa=roae.get(0).text().replaceAll(",","");
					stock.setRoa(roa);
				}
				if(roee.size()>0) {
					String roe=roee.get(0).text().replaceAll(",","");
					stock.setRoe(roe);
				}
				
				if(sichong.size()>0) {
					String sichongParam=sichong.get(0).text().replaceAll(",","");
					stock.setSichong(sichongParam);
				}
				if(profit.size()>0) {
					String profitParam=profit.get(0).text().replaceAll(",","");
					stock.setProfit(profitParam);
				}
				if(category.size()>0) {
					String cate=category.get(0).text();
					stock.setCategory(cate);
				}
						
				antKillerService.insertStockInfo(stock);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return null;
	}

}
