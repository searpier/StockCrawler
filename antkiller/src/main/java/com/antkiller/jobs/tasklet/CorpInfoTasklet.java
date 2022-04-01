package com.antkiller.jobs.tasklet;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.antkiller.jobs.dto.CorpInfo;
import com.antkiller.jobs.dto.CorpInfo.Corp;
import com.antkiller.jobs.service.AntKillerService;

@Component
public class CorpInfoTasklet implements Tasklet {

	@Autowired
	AntKillerService antKillerService;
	
    public RepeatStatus execute(final StepContribution contribution, final ChunkContext chunkContext) throws Exception {
    	
    	Map<String, CorpInfo> map = new HashMap<>();

    	    try {
    	    	String html="";
                FileInputStream input=new FileInputStream("C:/Users/전상수/Downloads/corpCode/CORPCODEbak.xml");
                InputStreamReader reader=new InputStreamReader(input,"UTF-8");
                @SuppressWarnings("resource")
				BufferedReader bufReader=new BufferedReader(reader,32768);
                
                String line = "";
                while((line = bufReader.readLine()) != null){
                	html+=line+"\n";
                }
    	        
    	        JAXBContext jaxbContext = JAXBContext.newInstance(CorpInfo.class); // JAXB Context 생성
    	        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller(); // Unmarshaller Object 생성
    	        CorpInfo corpInfo = (CorpInfo) unmarshaller.unmarshal(new StringReader(html)); // unmarshall 메소드 호출

    	        map.put("corpInfo", corpInfo);
    	        
    	        
    	        for(Corp c:corpInfo.getCorpList()) {
    	        	antKillerService.insertCorpInfo(c);
        	        System.out.println(c);
    	        }
    	        
    	        return null;

    	    } catch (JAXBException | IOException e) {
    	        e.printStackTrace();
    	        return null;
    	    }
    }
    
}