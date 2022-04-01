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
import com.antkiller.jobs.dto.PriceInfo;
import com.antkiller.jobs.service.AntKillerService;

@Component
public class PriceInfoTasklet implements Tasklet {

	@Autowired
	AntKillerService antKillerService;
	
	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		// TODO Auto-generated method stub
		List<Corp> cList=antKillerService.selectCorpCode();
		
		for(Corp c:cList) {
			
			Document doc;
			String stockCode=c.getStockCode();
			System.out.println(stockCode);
			String URL = "https://finance.naver.com/item/main.nhn?code=";
			URL+=stockCode;
			
			PriceInfo price=new PriceInfo();
			
			try {
				doc = Jsoup.connect(URL).get();
				Elements elem = doc.select("#time > em");
				String[] str = elem.text().split(" ");

				Elements todaylist =doc.select(".new_totalinfo dl>dd");

				if(todaylist.size()>9) {
				
				String juga = todaylist.get(3).text().split(" ")[1].replaceAll(",","");
				String DungRakrate = todaylist.get(3).text().split(" ")[6].replaceAll(",","");
				String siga =  todaylist.get(5).text().split(" ")[1].replaceAll(",","");
				String goga = todaylist.get(6).text().split(" ")[1].replaceAll(",","");
				String zeoga = todaylist.get(8).text().split(" ")[1].replaceAll(",","");
				String georaeryang = todaylist.get(10).text().split(" ")[1].replaceAll(",","");
				String stype = todaylist.get(3).text().split(" ")[3].replaceAll(",",""); //상한가,상승,보합,하한가,하락 구분
				String vsyesterday = todaylist.get(3).text().split(" ")[4].replaceAll(",","");
				price.setStockPrice(juga);
				if(!DungRakrate.equals("퍼센트"))
					price.setPriceChangeRate(DungRakrate);
				price.setMarketPrice(siga);
				price.setHighPrice(goga);
				price.setLowPrice(zeoga);
				price.setDealAmount(georaeryang);
				price.setMarketType(stype);
				price.setYesterday(vsyesterday);
				}
				
				price.setCorpCode(c.getCorpCode());
				price.setCorpName(c.getCorpName());
				price.setStockCode(stockCode);
			
				if(str.length>1) {
					price.setCrTime(str[0]+str[1]);
				}
				
				antKillerService.insertPriceInfo(price);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return null;
	}

}
