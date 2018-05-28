//package be.dashmon.controller;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.validation.Valid;
//
//import org.json.simple.JSONObject;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import be.dashmon.domain.HeadTrans;
//import be.dashmon.domain.LoginCheck;
//import be.dashmon.domain.Register;
//import be.dashmon.domain.ResponServ;
//import be.dashmon.handler.OrderClassHandler;
//import be.dashmon.service.ResponService;
//import be.dashmon.util.Utility;
//
//@RestController
//public class OrderTransController_ {
//	@Autowired
//	Utility myutil;
//	
//	@Autowired
//	ResponService apprespon;
//
//	@Autowired
//	private HttpServletRequest request;
//
//	@Autowired
//	private HttpServletResponse response;
//
//	@Autowired
//	OrderClassHandler orderhandler;
//	
//	@RequestMapping(value = "/app-setorder", method = RequestMethod.POST, headers = "content-type=application/json")
////	public String AppSetOrder(@RequestBody JSONObject lcParam) throws Exception {
//	public String AppSetOrder(@Valid @RequestBody HeadTrans lcParam,BindingResult bindingResult) throws Exception {
//
//		ObjectMapper mapper = new ObjectMapper();
//		ResponServ respon = new ResponServ();
//		String LogNm = "Order";
//		
//		if (bindingResult.hasErrors()) {
//			apprespon.setResponError(respon, "10", request.getMethod(), response.getStatus(), mapper.writeValueAsString(lcParam),LogNm);
//			return mapper.writeValueAsString(respon);

//		}
//		
////		ObjectMapper mapper = new ObjectMapper();
////		ResponServ respon = new ResponServ();
////		String LogNm = "Order";
////		System.out.println("this array:"+lcParam);		
//
////		try{
////			apphandler.setPassChange(auth, request.getMethod(), response.getStatus(), mapper.writeValueAsString(auth), respon,LogNm);
////		} catch (Exception e) {
////			apprespon.setResponError(respon, "11", request.getMethod(), response.getStatus(), mapper.writeValueAsString(auth),LogNm);
////			e.printStackTrace();
////		}
//		return mapper.writeValueAsString(lcParam);
//	}
//}
//
////app-setorder