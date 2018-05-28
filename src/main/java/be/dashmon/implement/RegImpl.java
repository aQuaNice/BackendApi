package be.dashmon.implement;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.dashmon.domain.Register;
import be.dashmon.domain.ResponServ;
import be.dashmon.repository.RegRepo;
import be.dashmon.service.RegService;

@Service
public class RegImpl implements RegService {

//	private RegRepo Regrepo;
	
	@Autowired
	private RegRepo Regrepo;
//	public RegImpl(RegRepo Regrepo) {
//		this.Regrepo = Regrepo;
//	}
	
	@Transactional
	@Override
	public void saveRegis(Register auth, ResponServ respon) {
		try {
		this.Regrepo.save(auth);
			respon.setRsltcode("00");
		} catch (Exception e) {
			respon.setRsltcode("13");
			
		}
		
	}

//    public int saveRegis(Register doMember) {
//		int lcresult = 0;
//		try {
//			this.Regrepo.save(doMember);
//			lcresult = 1;
//		} catch (Exception e) {
//			lcresult = 0;
//		}
//		
//        return lcresult;
//    }

	@Override
	public Register findByUseremail(String doMember) {
		
//		int lcresult = 0;
		
		
//		try {
//			System.out.println(this.Regrepo.findByLcemail(doMember));
//			lcresult = 1;
//		} catch (Exception e) {
//			e.printStackTrace();
//			lcresult = 0;
//		}
		
		return this.Regrepo.findByUseremail(doMember);
		
//        return doMember;
//		Regrepo.findByLcemail(LcEmail);
//		return 0;
	}

	@Override
	public Register findByNotlp(String doPhone) {
		// TODO Auto-generated method stub
		return this.Regrepo.findByNotlp(doPhone);
	}

	@Override
	public Register findByNotlpAndPasscode(String lcnotlp, String lcpasscode) {
		// TODO Auto-generated method stub
		return this.Regrepo.findByNotlpAndPasscode(lcnotlp,lcpasscode);
	}

	

}
