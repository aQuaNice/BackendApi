package be.dashmon.service;

import be.dashmon.domain.Register;
import be.dashmon.domain.ResponServ;



public interface RegService {
//	int saveRegis(Register doMember);
//	int findByLcemail(String LcEmail);
	Register findByUseremail(String doMember);
	Register findByNotlp(String doPhone);
//	Register findByLcemailAndLcpass(String doMember,String doPass);
	void saveRegis(Register auth, ResponServ respon);
	Register findByNotlpAndPasscode(String lcnotlp, String lcpasscode);
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

