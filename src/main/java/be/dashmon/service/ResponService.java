package be.dashmon.service;

import java.io.InputStream;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

//import be.dashmon.domain.AuthLogin;
import be.dashmon.domain.ResponServ;
import be.dashmon.log.Logapp;
import be.dashmon.util.Utility;

@Component
public class ResponService {
	@Autowired
	Utility myutil;
	
	@Autowired
	Logapp mylog;
	
	Properties prop = new Properties();
	String propFileName = "application.properties";
	InputStream inputStream;
	
	public void setResponSuccess(ResponServ respon, String lcCode, String lcmethod, int reqCode, String lcParam,String LogNm) throws Exception{
	
		inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
		prop.load(inputStream);
		
		respon.setRsltcode(lcCode);
		ObjectMapper mapper = new ObjectMapper();
		String lcstat = prop.getProperty("reststat"+lcCode);		
		respon.setRsltmsg(lcstat);		
	mylog.setLogReg(LogNm, lcmethod,reqCode, lcParam, lcCode, mapper.writeValueAsString(respon), respon.getRsltcode(),respon.getRsltmsg(),1);
	}
							
	public void setResponError(ResponServ respon, String lcCode, String lcmethod, int reqCode, String lcParam,String LogNm) throws Exception{
		respon.setRsltcode(lcCode);
		ObjectMapper mapper = new ObjectMapper();
		inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
		prop.load(inputStream);
		String lcstat = prop.getProperty("reststat"+lcCode);		
		respon.setRsltmsg(lcstat);		
		mylog.setLogReg(LogNm, lcmethod,reqCode, lcParam, lcCode, mapper.writeValueAsString(respon), respon.getRsltcode(),respon.getRsltmsg(),0);
	}
	
	public void setExeptionError(ResponServ respon, String lcCode, String lcmethod, int reqCode, String lcParam,String LogNm) throws Exception{
		respon.setRsltcode(lcCode);
		ObjectMapper mapper = new ObjectMapper();
		inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
		prop.load(inputStream);
		String lcstat = prop.getProperty("reststat"+lcCode);		
		respon.setRsltmsg(lcstat);		
		mylog.setLogReg(LogNm, lcmethod,reqCode, lcParam, lcCode, mapper.writeValueAsString(respon), respon.getRsltcode(),respon.getRsltmsg(),0);
	}
	
	public void setCleintError(String lcParam,String LogNm) throws Exception{
		mylog.GetLogClient(LogNm, lcParam);
	}
}
