package com.antkiller.jobs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.antkiller.jobs.dao.AntKillerDAO;
import com.antkiller.jobs.dto.StockInfo;
import com.antkiller.jobs.dto.CorpInfo.Corp;
import com.antkiller.jobs.dto.PriceInfo;

@Service
public class AntKillerService {
	 
    @Autowired
    AntKillerDAO antKillerDAO;
    
    public int insertCorpInfo(Corp info){
          return antKillerDAO.insertCorpInfo(info);
    }

    public List<Corp> selectCorpCode() {
  	  return antKillerDAO.selectCorpCode();
    }
    
    public int insertStockInfo(StockInfo info) {
  	  return antKillerDAO.insertStockInfo(info);
    }
    
    public int insertPriceInfo(PriceInfo info) {
    	  return antKillerDAO.insertPriceInfo(info);
    }

}
