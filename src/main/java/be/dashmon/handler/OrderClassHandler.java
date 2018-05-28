package be.dashmon.handler;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.List;
//
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import be.dashmon.domain.OrderDetail;
import be.dashmon.domain.GetparamDetail;
import be.dashmon.domain.OrderHead;
import be.dashmon.domain.Register;
//import be.dashmon.domain.Mainitem;
//import be.dashmon.domain.Mainmenu;
import be.dashmon.domain.ResponServ;
import be.dashmon.service.DetOrderserv;
import be.dashmon.service.ResponService;
//import be.dashmon.service.RegService;
import be.dashmon.service.SetOrderService;

@Component
@PropertySource(value="classpath:application.properties")

public class OrderClassHandler {

	@Autowired
	SetOrderService checkregis;

	@Autowired
	ResponService apprespon;

	@Autowired
	DetOrderserv detorderserv;
	
	Properties prop = new Properties();
	String propFileName = "application.properties";
	InputStream inputStream;

	public void getProper() throws IOException{
	inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
	prop.load(inputStream);
	}
	
	public void OrderProccess(OrderHead lcdata, String lcmethod, int reqCode, String lcParam, ResponServ respon, String LogNm) throws Exception{
		ResponServ lcrespon = new ResponServ();		
		ObjectMapper mapper = new ObjectMapper();
		this.getProper();

		DateFormat df = new SimpleDateFormat("ddMMyyyyHHmmss");
		String sdt = df.format(new Date(System.currentTimeMillis()));
		String Lcorder ="ORD"+lcdata.getIdmember()+sdt;
		String LogNmsub = "HOrderProcess";

//		try {
		long currentTime = System.currentTimeMillis();
		long oneHourLater = currentTime + TimeUnit.DAYS.toMillis(1);
		lcdata.setInvoorder(Lcorder);		
		lcdata.setTglord(new Date(currentTime));
		lcdata.setTglexp(new Date(oneHourLater));
		lcdata.setStat(0);
//		lcdata.thisOrder(lcdata.getId());
//		for(OrderDetail lcdataI: lcdata.getOrderDetail()) {
//				lcdataI.set(Lcorder);
//		}	
		
		System.out.println(mapper.writeValueAsString(lcdata));
		
		
		checkregis.saveOrder(lcdata,respon);
		JSONArray lsResult = new JSONArray();
		JSONObject laResult = new JSONObject();
		String lcstat = prop.getProperty("message"+lcdata.getStat());
		if (respon.getRsltcode().equals("05")) {
			laResult.put("lcid", lcdata.getId());
			laResult.put("lcinvoorder", lcdata.getInvoorder());
			laResult.put("lcstat", lcstat);
			laResult.put("lcstatcd", lcdata.getStat());
			laResult.put("lcordtotal", lcdata.getOrdtotal());
			laResult.put("lctglord", lcdata.getTglord());
			laResult.put("lctglexp", lcdata.getTglexp());
//			laResult.put("lcdely", lcdata.getAreaadd() +" - "+ lcdata.getDetadd());
			
			respon.setRsltdata(laResult);
		
			apprespon.setResponSuccess(respon, respon.getRsltcode(), lcmethod, reqCode, lcParam, LogNmsub);
		}else{
			apprespon.setResponError(respon, respon.getRsltcode(), lcmethod, reqCode, lcParam, LogNmsub);		
		}		
		
		
			
		
		
		
		
//		System.err.println(mapper.writeValueAsString(respon));
		
	}

	public void OrderDetail(GetparamDetail lcdata, String lcmethod, int reqCode, String lcParam, ResponServ respon, String LogNm) throws Exception{
	String LogNmsub = "HOrderDetail";
	
	try {
//			List<OrderDetail> lcorderlast = detorderserv.Lcidhead(lcdata.getLcitemid());
		
		OrderHead lcorderlast = detorderserv.findbyidhead(lcdata.getLcitemid());
		
		System.out.println(lcorderlast.getInvoorder());
		System.out.println(lcorderlast.getOrderDetail());
		
		ObjectMapper mapper = new ObjectMapper();
		JSONObject laResult = new JSONObject();
		JSONArray lsResult = new JSONArray();
//		System.err.println(mapper.writeValueAsString(lcorderlast.getOrderDetail()));
		
		
		 for (OrderDetail prod : lcorderlast.getOrderDetail()) {
			 laResult = new JSONObject();
			 laResult.put("lcitmsat",  prod.getProduct().getItmsat());
			 laResult.put("lcitmqty",  prod.getProduct().getItmqty());
			 laResult.put("lcitmvol",  prod.getProduct().getItmvol());
			 laResult.put("lcitemicon",  prod.getProduct().getItemicon());
			 laResult.put("lcdeschead",  prod.getProduct().getDeschead());
			 laResult.put("lcitpr",  prod.getItmprice());
			 laResult.put("lcitid",  prod.getIditm());
			 laResult.put("lcitjum",  prod.getQtyitm());
			 laResult.put("lcordstat",  prod.getStat());
			 
			 lsResult.add(laResult);
		 }
		 	
		 
		 	JSONObject lcResult = new JSONObject();
			lcResult.put("lcdetail", lsResult);	
			respon.setRsltdata(lcResult);
		
			if (lcorderlast == null) {
				apprespon.setResponError(respon, "24", lcmethod, reqCode, lcParam, LogNmsub);
			}else{
				
				System.err.println("data detail : "+lsResult);
				 
				apprespon.setResponSuccess(respon, "25", lcmethod, reqCode, lcParam, LogNmsub);	
			}		
		} catch (Exception e) {
			apprespon.setResponError(respon, "11", lcmethod, reqCode, lcParam, LogNmsub);
			 e.printStackTrace();
		}
	
		
		}
		

	public void checkMember(GetparamDetail lcdata, String lcmethod, int reqCode, String lcParam, ResponServ respon, String LogNm) throws Exception{
		String LogNmsub = "HcheckMember";

		try {
		Register checkResult = checkregis.findByLctknAndLcid(lcdata.getLctoken(),lcdata.getLcidmember());
			if (checkResult == null) {
				apprespon.setResponError(respon, "17", lcmethod, reqCode, lcParam, LogNmsub);
			}else{
				apprespon.setResponSuccess(respon, "04", lcmethod, reqCode, lcParam, LogNmsub);		
			}		
		} catch (Exception e) {
			apprespon.setResponError(respon, "11", lcmethod, reqCode, lcParam, LogNmsub);
			 e.printStackTrace();
		}
//		return lcrespon;
	}

	
	public void OrderCancel(GetparamDetail lcdata, String lcmethod, int reqCode, String lcParam, ResponServ respon, String LogNm) throws Exception{
		String LogNmsub = "HOrderCancel";
		try {
			checkregis.orderCancel(lcdata,respon);
//			System.out.println(respon.getRsltcode());
			if (respon.getRsltcode().equals("22")) {
				apprespon.setResponSuccess(respon, respon.getRsltcode(), lcmethod, reqCode, lcParam, LogNmsub);
			}else{
				apprespon.setResponError(respon, respon.getRsltcode(), lcmethod, reqCode, lcParam, LogNmsub);		
			}		
		} catch (Exception e) {
			apprespon.setResponError(respon, "11", lcmethod, reqCode, lcParam, LogNmsub);
			// e.printStackTrace();
		}
//		return lcrespon;
	}

	
	public void GetdataOrder(GetparamDetail lcdata, String lcmethod, int reqCode, String lcParam, ResponServ respon, String LogNm) throws Exception{
		String LogNmsub = "HGetdataOrder";
		try {
			checkregis.GetdataOrder(lcdata,respon);
			if (respon.getRsltcode().equals("25")) {
				apprespon.setResponSuccess(respon, respon.getRsltcode(), lcmethod, reqCode, lcParam, LogNmsub);
			}else{
				apprespon.setResponError(respon, respon.getRsltcode(), lcmethod, reqCode, lcParam, LogNmsub);		
			}		
		} catch (Exception e) {
			apprespon.setResponError(respon, "11", lcmethod, reqCode, lcParam, LogNmsub);
			 e.printStackTrace();
		}
//		return lcrespon;
	}

	
}
