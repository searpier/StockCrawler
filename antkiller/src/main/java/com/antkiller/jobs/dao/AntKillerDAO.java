package com.antkiller.jobs.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.antkiller.jobs.dto.CorpInfo.Corp;
import com.antkiller.jobs.dto.PriceInfo;
import com.antkiller.jobs.dto.StockInfo;

@Repository
public class AntKillerDAO {
	
	  @Autowired
	  private SqlSessionFactory sqlFactory;

      public int insertCorpInfo(Corp info) {
    	  return sqlFactory.openSession().insert("antkiller.mappers.AntKillerMapper.insertCorpInfo", info);
      }
      public List<Corp> selectCorpCode() {
    	  return sqlFactory.openSession().selectList("antkiller.mappers.AntKillerMapper.selectCorpCode");
      }
      public int insertStockInfo(StockInfo info) {
    	  return sqlFactory.openSession().insert("antkiller.mappers.AntKillerMapper.insertStockInfo", info);
      }
      public int insertPriceInfo(PriceInfo info) {
    	  return sqlFactory.openSession().insert("antkiller.mappers.AntKillerMapper.insertPriceInfo", info);
      }

}
