package be.dashmon.service;

import be.dashmon.domain.LoginCheck;
import be.dashmon.domain.Register;
import be.dashmon.domain.ResponServ;



public interface LogService {
//	int saveRegis(LoginCheck doMember);
//	int findByLcemail(String LcEmail);
//	Register findByLcemail(String doMember);
//	LoginCheck findByLcemailAndLcpass(String doMember,String doPass);
	int updateToken(LoginCheck doMember);
	LoginCheck findByApptkn(String doLctoken);
//	int saveRegis(Register doMember);
	
	
//	LoginCheck findByLcemailAndLcpassAndLcuser(String lcemail, String lcpass, String lcuser);
//	LoginCheck findByLcemailAndLcpassAndLcuser(String lcemail, String lcpass, String lcuser);
	LoginCheck findByUseremailAndUserpass(String lcemail, String lcpass);
//	int updatePass(LoginCheck auth);
	void updatePass(LoginCheck auth, ResponServ respon,int lctype);
	void setPass(LoginCheck auth, ResponServ respon);
	LoginCheck findByNotlpAndPasscode(String lcnotlp, String lcpasscode);
	LoginCheck findByNotlpAndApptkn(String lcnotlp, String lctkn);
	LoginCheck findByNotlpAndUserpass(String lcnotlp, String lcpass);
	LoginCheck findByNotlp(String lcnotlp);
}


//package be.dashmon.service;
//
//import org.springframework.stereotype.Service;
//
//@Service
//public class RegisService {
//	
//	
//	
//	s
//	
//}

