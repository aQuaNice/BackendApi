package be.dashmon.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//import be.dashmon.domain.HeadTrans;
import be.dashmon.service.ResponService;
@RestController
public class ErrorMessageController {
	@Autowired
	private HttpServletRequest request;

	@Autowired
	private HttpServletResponse response;
	
	@Autowired
	ResponService apprespon;
	
	@RequestMapping(value = "/app-cleinterror", method = RequestMethod.POST, headers = "content-type=application/json")
	public void AppSetorder(@RequestBody String lcParam) throws Exception {		
		String LogNm = "Cleinterror";
		
		apprespon.setCleintError(lcParam, LogNm);
		
//		System.out.println(lcParam);
//		apprespon.setCleintError(request.toString(),LogNm);
	}

}
