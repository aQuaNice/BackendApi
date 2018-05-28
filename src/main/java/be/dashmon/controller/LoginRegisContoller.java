package be.dashmon.controller;

import java.security.SecureRandom;
import java.util.Random;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.databind.ObjectMapper;

import be.dashmon.domain.LoginCheck;
import be.dashmon.domain.Register;
import be.dashmon.domain.ResponServ;
import be.dashmon.handler.LoginRegisHandler;
import be.dashmon.handler.MainClassHandler;
import be.dashmon.service.LiveScoreService;
import be.dashmon.service.ResponService;
import be.dashmon.util.Utility;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class LoginRegisContoller {

	@Autowired
	Utility myutil;

	@Autowired
	LoginRegisHandler apphandler;

	@Autowired
	ResponService apprespon;

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private HttpServletResponse response;

	@Autowired
	private SimpMessagingTemplate template;

	@Autowired
	LiveScoreService service;
	//
	// private static final Random RANDOM = new SecureRandom();
	// /** Length of password. @see #generateRandomPassword() */
	// public static final int PASSWORD_LENGTH = 8;

	@RequestMapping(value = "/member-regis", method = RequestMethod.POST, headers = "content-type=application/json")
	public String AppMemberReg(@Valid @RequestBody Register auth, BindingResult bindingResult) throws Exception {
		template.convertAndSend("/live/regis", service.getScore());
		ObjectMapper mapper = new ObjectMapper();
		ResponServ respon = new ResponServ();
		String LogNm = "CAppMemberReg";
		if (bindingResult.hasErrors()) {
			apprespon.setResponError(respon, "10", request.getMethod(), response.getStatus(),
					mapper.writeValueAsString(auth), LogNm);
			return mapper.writeValueAsString(respon);
		}		
		myutil.NoPhoneCheck(auth.getNotlp(),respon);		
		if (!respon.getRsltcode().isEmpty()) {
			if (respon.getRsltcode().equals("14")) {
				apprespon.setResponError(respon, respon.getRsltcode(), request.getMethod(), response.getStatus(),mapper.writeValueAsString(auth), LogNm);
				return mapper.writeValueAsString(respon);
			}
		}
		try {
			apphandler.setRegisterPhone(auth, request.getMethod(), response.getStatus(),mapper.writeValueAsString(auth), respon, LogNm);

		} catch (Exception e) {
			apprespon.setResponError(respon, "11", request.getMethod(), response.getStatus(),mapper.writeValueAsString(auth), LogNm);
			// e.printStackTrace();
		}
		return mapper.writeValueAsString(respon);
	}

	@RequestMapping(value = "/app-validpass", method = RequestMethod.POST, headers = "content-type=application/json")
	public String AppValidPass(@Valid @RequestBody LoginCheck auth, BindingResult bindingResult) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		ResponServ respon = new ResponServ();
		String LogNm = "CAppValidPassCode";
		if (bindingResult.hasErrors()) {
			apprespon.setResponError(respon, "10", request.getMethod(), response.getStatus(),mapper.writeValueAsString(auth), LogNm);
		}
		myutil.PassCodeCheck(auth,respon);
		if (!respon.getRsltcode().isEmpty()) {
			if (respon.getRsltcode().equals("17")) {
				apprespon.setResponError(respon, respon.getRsltcode(), request.getMethod(), response.getStatus(),
						mapper.writeValueAsString(auth), LogNm);
				return mapper.writeValueAsString(respon);
			}
		}
		try {
			apphandler.setPassCode(auth, request.getMethod(), response.getStatus(), mapper.writeValueAsString(auth),respon, LogNm);
		} catch (Exception e) {
			apprespon.setResponError(respon, "11", request.getMethod(), response.getStatus(),mapper.writeValueAsString(auth), LogNm);
			e.printStackTrace();
		}
		return mapper.writeValueAsString(respon);
	}

	
	@RequestMapping(value = "/member-setpass", method = RequestMethod.POST, headers = "content-type=application/json")
	public String AppSetPass(@Valid @RequestBody LoginCheck auth, BindingResult bindingResult) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		ResponServ respon = new ResponServ();
		String LogNm = "CAppSetPass";
		if (bindingResult.hasErrors()) {
			apprespon.setResponError(respon, "10", request.getMethod(), response.getStatus(),mapper.writeValueAsString(auth), LogNm);
			return mapper.writeValueAsString(respon);
		}

		try {
			apphandler.setPass(auth, request.getMethod(), response.getStatus(), mapper.writeValueAsString(auth), respon,LogNm);

		} catch (Exception e) {
			apprespon.setResponError(respon, "11", request.getMethod(), response.getStatus(),mapper.writeValueAsString(auth), LogNm);
			e.printStackTrace();
		}
		return mapper.writeValueAsString(respon);
	}

	 @RequestMapping(value = "/member-login", method = RequestMethod.POST,headers = "content-type=application/json")
	 public String AppMemberLogin(@Valid @RequestBody LoginCheck auth,BindingResult bindingResult) throws Exception {
	 ObjectMapper mapper = new ObjectMapper();
	 ResponServ respon = new ResponServ();
	 String LogNm = "CAppMemberLogin";
	 if (bindingResult.hasErrors()) {
	 apprespon.setResponError(respon, "10", request.getMethod(),response.getStatus(), mapper.writeValueAsString(auth),LogNm);
	 return mapper.writeValueAsString(respon);
	 }
	 try{
	 apphandler.setLogin(auth, request.getMethod(), response.getStatus(),mapper.writeValueAsString(auth), respon,LogNm);
	 } catch (Exception e) {
	 apprespon.setResponError(respon, "11", request.getMethod(),response.getStatus(), mapper.writeValueAsString(auth),LogNm);
	 e.printStackTrace();
	 }
	
	 return mapper.writeValueAsString(respon);
	 }

	// @RequestMapping(value = "/member-chpass", method = RequestMethod.POST,
	// headers = "content-type=application/json")
	// public String AppChangePass(@Valid @RequestBody LoginCheck
	// auth,BindingResult bindingResult) throws Exception {
	// ObjectMapper mapper = new ObjectMapper();
	// ResponServ respon = new ResponServ();
	// String LogNm = "CAppChangePass";
	// if (bindingResult.hasErrors()) {
	// apprespon.setResponError(respon, "10", request.getMethod(),
	// response.getStatus(), mapper.writeValueAsString(auth),LogNm);
	// return mapper.writeValueAsString(respon);
	// }
	// try{
	// apphandler.setPassChange(auth, request.getMethod(), response.getStatus(),
	// mapper.writeValueAsString(auth), respon,LogNm);
	// } catch (Exception e) {
	// apprespon.setResponError(respon, "11", request.getMethod(),
	// response.getStatus(), mapper.writeValueAsString(auth),LogNm);
	// e.printStackTrace();
	// }
	// return mapper.writeValueAsString(respon);
	// }
	
	 @RequestMapping(value = "/member-getpass", method = RequestMethod.POST,
	 headers = "content-type=application/json")
	 public String AppGetdPass(@Valid @RequestBody LoginCheck
	 auth,BindingResult bindingResult) throws Exception {
	 ObjectMapper mapper = new ObjectMapper();
	 ResponServ respon = new ResponServ();
	 String LogNm = "AppGetdPass";
	 if (bindingResult.hasErrors()) {
	 apprespon.setResponError(respon, "10", request.getMethod(),response.getStatus(), mapper.writeValueAsString(auth),LogNm);
	 }
	 myutil.NoPhoneCheck(auth.getNotlp(),respon);
		if (!respon.getRsltcode().isEmpty()) {
			if (respon.getRsltcode().equals("16")) {
				apprespon.setResponError(respon, "26", request.getMethod(), response.getStatus(),
						mapper.writeValueAsString(auth), LogNm);
				return mapper.writeValueAsString(respon);
			}
		}	 
	 try{
	 apphandler.genPassCode(auth, request.getMethod(), response.getStatus(),mapper.writeValueAsString(auth), respon,LogNm);
	 } catch (Exception e) {
	 apprespon.setResponError(respon, "11", request.getMethod(),response.getStatus(), mapper.writeValueAsString(auth),LogNm);
//	 e.printStackTrace();
	 }
	 return mapper.writeValueAsString(respon);
	 }
}