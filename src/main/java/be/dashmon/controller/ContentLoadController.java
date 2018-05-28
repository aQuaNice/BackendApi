package be.dashmon.controller;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import be.dashmon.domain.GetItem;
import be.dashmon.domain.LoginCheck;
import be.dashmon.domain.Register;
import be.dashmon.domain.ResponServ;
import be.dashmon.handler.MainClassHandler;

@RestController
public class ContentLoadController {
	

	@Autowired
	MainClassHandler mainhandler;
	
	Properties prop = new Properties();
	String propFileName = "application.properties";
	InputStream inputStream;


	@RequestMapping(value = "/app-getcon")
	public ModelAndView AppgetCont(HttpServletRequest request,HttpServletResponse response){
//		String browserType = request.getHeader("User-Agent");
//		ModelAndView modelAndView;// = new ModelAndView("main");		
		if(request.getHeader("User-Agent").indexOf("Mobile") != -1){
		    //you're in mobile land
			System.err.println("Mobile app "+request.getHeader("User-Agent"));
			JSONArray lcdata = mainhandler.getmenu();
			
			return new ModelAndView("main","lcmnu",lcdata);
		  }else{
			  System.err.println("Desktop or other "+request.getHeader("User-Agent"));
			  return new ModelAndView("main");
		    //nope, this is probably a desktop
		  }
		
//		System.out.println(browserType);
//		return modelAndView;
	}

	
	
	@RequestMapping(value = "/app-getjs",method=RequestMethod.GET)
	public ModelAndView AppgetJs(HttpServletRequest request,HttpServletResponse response) throws IOException{
//		String browserType = request.getHeader("User-Agent");
//		ModelAndView modelAndView;// = new ModelAndView("main");	
//		response.setContentType("text/html");
//		  PrintWriter pw = response.getWriter();
//		response.setHeader("Content-Length", String.valueOf(150000));

//		response.setHeader("Content-Length", String.valueOf(150000));
		InputStream is = getClass().getClassLoader().getResourceAsStream("templates/appjs.html");
		
		int length = is.available();
		
//		response.setHeader("Content-Length", String.valueOf(89619));
//		response.setContentLength(length);
		response.setCharacterEncoding("utf-8");  
		response.setContentType("text/html"); 
		System.out.println("test data : " + length);
//		File file =new File("appjs.html");
//		double bytes = file.length();
		
//		System.out.println(bytes);
		
		if(request.getHeader("User-Agent").indexOf("Mobile") != -1) {
		    //you're in mobile land
			System.err.println("Mobile app "+request.getHeader("User-Agent"));
			return new ModelAndView("appjs");
		  } else {
			  System.err.println("Desktop or other "+request.getHeader("User-Agent"));
			  return new ModelAndView("appjs"); //404/
		    //nope, this is probably a desktop
		  }
		
//		System.out.println(browserType);
//		return modelAndView;
	}
	
	@RequestMapping(value = "/app-getmenu")
	public JSONArray AppgetMenu(HttpServletRequest request,HttpServletResponse response) throws JsonProcessingException{
//		String browserType = request.getHeader("User-Agent");
//		ModelAndView modelAndView;// = new ModelAndView("main");		
		ObjectMapper mapper = new ObjectMapper();
		if(request.getHeader("User-Agent").indexOf("Mobile") != -1){
		    //you're in mobile land
			System.err.println("Mobile app "+request.getHeader("User-Agent"));
			JSONArray lcdata = mainhandler.getmenu();
			
			
			  return lcdata;
			//return mapper.writeValueAsString(lcdata);
			
//			return new ModelAndView("main","lcmnu",lcdata);
		  }else{
			  System.err.println("load data menu ");
			  System.err.println("Desktop or other "+request.getHeader("User-Agent"));
			  JSONArray lcdata = mainhandler.getmenu();
				
			  return lcdata;
			 // return mapper.writeValueAsString(lcdata);
//			  return new ModelAndView("main");
		    //nope, this is probably a desktop
		  }
		
//		System.out.println(browserType);
//		return modelAndView;
	}
	
	@RequestMapping(value = "/app-getbanner")
	public JSONArray AppgetBanner(HttpServletRequest request,HttpServletResponse response) throws JsonProcessingException{
//		String browserType = request.getHeader("User-Agent");
//		ModelAndView modelAndView;// = new ModelAndView("main");		
		ObjectMapper mapper = new ObjectMapper();
		if(request.getHeader("User-Agent").indexOf("Mobile") != -1){
		    //you're in mobile land
			System.err.println("Mobile app "+request.getHeader("User-Agent"));
			JSONArray lcdata = mainhandler.getBanner();
			
			//return mapper.writeValueAsString(lcdata);
			
//			return new ModelAndView("main","lcmnu",lcdata);
			  return lcdata;
			  
		  }else{
			  System.err.println("Desktop or other "+request.getHeader("User-Agent"));
			  JSONArray lcdata = mainhandler.getBanner();
				
			  return lcdata;
			 // return mapper.writeValueAsString(lcdata);
//			  return new ModelAndView("main");
		    //nope, this is probably a desktop
		  }
		
//		System.out.println(browserType);
//		return modelAndView;
	}

	
	@RequestMapping(value = "/app-getitem", method = RequestMethod.POST, headers = "content-type=application/json")
	public JSONArray AppgetItm(@Valid @RequestBody GetItem param,BindingResult bindingResult) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		
		if (bindingResult.hasErrors()) {
			System.out.println("error param");
		}
		JSONArray lcdata = mainhandler.getitem(param.getItemtype(),param.getLclimit(),param.getOffset()); 	
		return lcdata;
	}
	@RequestMapping(value = "/app-update", method = RequestMethod.POST, headers = "content-type=application/json")
	public String AppgetUpdate(@RequestBody LoginCheck param) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		JSONObject jo_data = new JSONObject();
		JSONArray ja_data = new JSONArray();
		inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
		prop.load(inputStream);
		String lcstat = prop.getProperty("appver");	
//		jo_data.put("rsltcode", "01");		

		System.out.println("app-update || Start");
		if (!lcstat.equals(param.getAppver())) {
			param.setAppver(lcstat);
			System.out.println(mapper.writeValueAsString(param));
		}
//		ja_data.add(jo_data);
//		System.out.println(ja_data);		
//		System.out.println(ja_data);
//		JSONArray lcdata = mainhandler.getitem(param.getItemtype(),param.getLclimit(),param.getOffset()); 	
		return mapper.writeValueAsString(param);
	}

	
	
//	@RequestMapping(value = "/app-menu")
//	public ModelAndView AppgetMenu(HttpServletRequest request,HttpServletResponse response){
//		if(request.getHeader("User-Agent").indexOf("Mobile") != -1) {
//			System.err.println("Mobile app "+request.getHeader("User-Agent"));
//			return new ModelAndView("appjs");
//		  } else {
//			  System.err.println("Desktop or other "+request.getHeader("User-Agent"));
//			  return new ModelAndView("appjs"); //404/
//		  }		
//	}

}
