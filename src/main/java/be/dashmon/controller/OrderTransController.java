package be.dashmon.controller;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import antlr.Parser;
//import be.dashmon.domain.Book;
//import be.dashmon.domain.BookCategory;
//import be.dashmon.domain.DetailTrans;
//import be.dashmon.domain.DetailTrans;
import be.dashmon.domain.GetparamDetail;
//import be.dashmon.domain.HeadTrans;
import be.dashmon.domain.LoginCheck;
import be.dashmon.domain.Mainitem;
import be.dashmon.domain.OrderHead;
import be.dashmon.domain.ResponServ;
import be.dashmon.handler.OrderClassHandler;
//import be.dashmon.repository.BookCategoryRepository;
import be.dashmon.repository.SetOrderRepository;
import be.dashmon.service.DetOrderserv;
import be.dashmon.service.LiveScoreService;
import be.dashmon.service.ResponService;
import be.dashmon.service.SetOrderService;
import be.dashmon.util.Utility;

@RestController
public class OrderTransController {
	@Autowired
	Utility myutil;
	
	@Autowired
	ResponService apprespon;

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private HttpServletResponse response;

	@Autowired
	OrderClassHandler orderhandler;
	
    @Autowired
    private SetOrderRepository setOrderRepository;
    
    @Autowired
    OrderClassHandler OrderHandler;
    

	@Autowired
	SetOrderService checkregis;
	
	@Autowired
	DetOrderserv detorderserv;
	
	@Autowired
	private SimpMessagingTemplate template;

	@Autowired
	LiveScoreService service;
	
	@RequestMapping(value = "/app-setorder", method = RequestMethod.POST, headers = "content-type=application/json")
	public String AppSetorder(@Valid @RequestBody OrderHead lcParam,BindingResult bindingResult) throws Exception {	
		template.convertAndSend("/live/order", service.getScore());
		ObjectMapper mapper = new ObjectMapper();
		ResponServ respon = new ResponServ();
		String LogNm = "CAppSetorder";

		if (bindingResult.hasErrors()) {			
			apprespon.setResponError(respon, "10", request.getMethod(), response.getStatus(), mapper.writeValueAsString(lcParam),LogNm);
			return mapper.writeValueAsString(respon);
		}
		
		
		
		
		GetparamDetail lcCheck = new GetparamDetail();
		lcCheck.setLcidmember(lcParam.getIdmember());
		lcCheck.setLctoken(lcParam.getLctoken());

		System.err.println("param from client :"+ mapper.writeValueAsString(lcParam));

		try{
			orderhandler.checkMember(lcCheck,request.getMethod(), response.getStatus(), mapper.writeValueAsString(lcParam), respon,LogNm);
		} catch (Exception e) {
			apprespon.setResponError(respon, "11", request.getMethod(), response.getStatus(), mapper.writeValueAsString(lcParam),LogNm);
//			
			e.printStackTrace();
			return mapper.writeValueAsString(respon);
		}
		
		 if(respon.getRsltcode().equals("17")){
			apprespon.setResponError(respon, "19", request.getMethod(), response.getStatus(), mapper.writeValueAsString(lcParam),LogNm);
			return mapper.writeValueAsString(respon);	 
		 }

		 
//		System.out.println(mapper.writeValueAsString(lcParam));
		try{
				orderhandler.OrderProccess(lcParam,request.getMethod(), response.getStatus(), mapper.writeValueAsString(lcParam), respon,LogNm);
		} catch (Exception e) {
				apprespon.setResponError(respon, "11", request.getMethod(), response.getStatus(), mapper.writeValueAsString(lcParam),LogNm);
				return mapper.writeValueAsString(respon);
////				e.printStackTrace();
		}
		
		return mapper.writeValueAsString(respon);
	}
	
	
	
	@RequestMapping(value = "/app-getdetail", method = RequestMethod.POST, headers = "content-type=application/json")
	public String AppGetdetail(@RequestBody GetparamDetail lcParam) throws Exception {		
		ObjectMapper mapper = new ObjectMapper();
		ResponServ respon = new ResponServ();
		String LogNm = "CAppGetdetail";

//		System.out.println(lcParam.getLctoken());;
		try{
			orderhandler.checkMember(lcParam,request.getMethod(), response.getStatus(), mapper.writeValueAsString(lcParam), respon,LogNm);
		} catch (Exception e) {
			apprespon.setResponError(respon, "11", request.getMethod(), response.getStatus(), mapper.writeValueAsString(lcParam),LogNm);
			//e.printStackTrace();
		}
		
		if(respon.getRsltcode().equals("04")){
		orderhandler.OrderDetail(lcParam, request.getMethod(), response.getStatus(), mapper.writeValueAsString(lcParam),respon, LogNm);
		
//		System.out.println(mapper.writeValueAsString(respon.getRsltdata()));
		////respon.setRsltcode("22");
//		}else{
//		apprespon.setResponError(respon, "23", request.getMethod(), response.getStatus(), mapper.writeValueAsString(lcParam),LogNm);
//		//respon.setRsltcode("23");
		}

//		System.out.println(mapper.writeValueAsString(respon));
//		 List<DetailTrans> lcorderlast = detorderserv.Lcidhead(lcParam.getLcitemid());
		return mapper.writeValueAsString(respon);
	}

	@RequestMapping(value = "/app-ordercancel", method = RequestMethod.POST, headers = "content-type=application/json")
	public String AppOrderCancel(@RequestBody GetparamDetail lcParam) throws Exception {		
		ObjectMapper mapper = new ObjectMapper();
		ResponServ respon = new ResponServ();
		String LogNm = "CAppOrderCancel";
		
//		if (bindingResult.hasErrors()) {			
//			apprespon.setResponError(respon, "10", request.getMethod(), response.getStatus(), mapper.writeValueAsString(lcParam),LogNm);
//			return mapper.writeValueAsString(respon);
//		}
		try{
			orderhandler.checkMember(lcParam,request.getMethod(), response.getStatus(), mapper.writeValueAsString(lcParam), respon,LogNm);
		} catch (Exception e) {
			apprespon.setResponError(respon, "11", request.getMethod(), response.getStatus(), mapper.writeValueAsString(lcParam),LogNm);
			//e.printStackTrace();
		}
	
				
		if(respon.getRsltcode().equals("04")){
			orderhandler.OrderCancel(lcParam,request.getMethod(), response.getStatus(), mapper.writeValueAsString(lcParam), respon,LogNm);
//			respon.setRsltcode("22");
		}else{
			apprespon.setResponError(respon, "23", request.getMethod(), response.getStatus(), mapper.writeValueAsString(lcParam),LogNm);
//			respon.setRsltcode("23");
		}
		
		return mapper.writeValueAsString(respon);
	}
	
	@RequestMapping(value = "/app-getdata", method = RequestMethod.POST, headers = "content-type=application/json")
	public String AppGetData(@RequestBody GetparamDetail lcParam) throws Exception {		
		ObjectMapper mapper = new ObjectMapper();
		ResponServ respon = new ResponServ();
		String LogNm = "CAppGetData";
		
//		if (bindingResult.hasErrors()) {			
//			apprespon.setResponError(respon, "10", request.getMethod(), response.getStatus(), mapper.writeValueAsString(lcParam),LogNm);
//			return mapper.writeValueAsString(respon);
//		}
		try{
			orderhandler.checkMember(lcParam,request.getMethod(), response.getStatus(), mapper.writeValueAsString(lcParam), respon,LogNm);
		} catch (Exception e) {
			apprespon.setResponError(respon, "11", request.getMethod(), response.getStatus(), mapper.writeValueAsString(lcParam),LogNm);
			//e.printStackTrace();
		}
	
				
		if(respon.getRsltcode().equals("04")){
			orderhandler.GetdataOrder(lcParam,request.getMethod(), response.getStatus(), mapper.writeValueAsString(lcParam), respon,LogNm);
//			respon.setRsltcode("22");
//		}else{
//			apprespon.setResponError(respon, "23", request.getMethod(), response.getStatus(), mapper.writeValueAsString(lcParam),LogNm);
//			respon.setRsltcode("23");
		}
		
		return mapper.writeValueAsString(respon);
	}


}