package be.dashmon.implement;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import be.dashmon.domain.GetparamDetail;
import be.dashmon.domain.LoginCheck;
import be.dashmon.domain.Register;
import be.dashmon.domain.ResponServ;
import be.dashmon.repository.LogRepo;
import be.dashmon.repository.RegRepo;
import be.dashmon.service.LogService;

@Service
public class LogImpl implements LogService {



	@Autowired
	private LogRepo Logrepo;
	
	@Autowired
	JdbcTemplate jTemplate;
	
	@Override
    public int updateToken(LoginCheck doMember) {
		int lcresult = 0;
		try {
			this.Logrepo.save(doMember);
			lcresult = 1;
		} catch (Exception e) {
//			e.printStackTrace();
			lcresult = 0;
		}
		
        return lcresult;
    }

	
	@Override
	public LoginCheck findByUseremailAndUserpass(String lcemail, String lcpass) {
		
//		int lcresult = 0;
//		try {
//			System.out.println(this.Regrepo.findByLcemail(doMember));
//			lcresult = 1;
//		} catch (Exception e) {
//			e.printStackTrace();
//			lcresult = 0;
//		}
		
		return this.Logrepo.findByUseremailAndUserpass(lcemail,lcpass);
		
//        return doMember;
//		Regrepo.findByLcemail(LcEmail);
//		return 0;
	}


	@Override
	public LoginCheck findByApptkn(String doLctoken) {
		
		return this.Logrepo.findByApptkn(doLctoken);
	}

	@Transactional
	@Override
	public void updatePass(LoginCheck auth,ResponServ respon, int lctype) {
		String sql ="";	
		Object[] args = new Object[]{};
	
		if(lctype == 1){
			 sql ="UPDATE tbm_member SET validat = ?,onlog = ?,apptkn = ?,passcode = ?  WHERE notlp = ?";	
			 args = new Object[]{auth.getValidat(),auth.getOnlog(),auth.getApptkn(),auth.getPasscode(),auth.getNotlp()};
		}else{		
		sql ="UPDATE tbm_member SET validat = ?,onlog = ?,apptkn = ?  WHERE notlp = ? AND passcode = ?";
//		String sql ="UPDATE tbm_member SET validat = ?,onlog = ?,apptkn = ?,passcode = ?  WHERE notlp = ?";
		args = new Object[]{auth.getValidat(),auth.getOnlog(),auth.getApptkn(),auth.getNotlp(),auth.getPasscode()};
//		Object[] args = new Object[]{auth.getLcvalid(),auth.getLcon(),auth.getLctkn(),auth.getLcpasscode(),auth.getLcnotlp()};
		}
		
		
		try{
					jTemplate.update(sql, args);
					respon.setRsltcode("04");
				}catch(Exception e){
//					SuccessStat = 0;
					respon.setRsltcode("13");
//					e.printStackTrace();
					
				}
//		return respon;
	}


	@Override
	public LoginCheck findByNotlpAndPasscode(String lcnotlp, String lcpasscode) {
		// TODO Auto-generated method stub
		return this.Logrepo.findByNotlpAndPasscode(lcnotlp,lcpasscode);
	}


	@Override
	public LoginCheck findByNotlpAndApptkn(String lcnotlp, String lctkn) {
		// TODO Auto-generated method stub
		return this.Logrepo.findByNotlpAndApptkn(lcnotlp,lctkn);
	}


	@Override
	public void setPass(LoginCheck auth, ResponServ respon) {
		String sql ="UPDATE tbm_member SET userpass = ? WHERE notlp = ? AND apptkn = ?";
		Object[] args = new Object[]{auth.getUserpass(),auth.getNotlp(),auth.getApptkn()};
				try{
					jTemplate.update(sql, args);
					respon.setRsltcode("04");
				}catch(Exception e){
//					SuccessStat = 0;
					respon.setRsltcode("13");
//					e.printStackTrace();
					
				}
		
	}


	@Override
	public LoginCheck findByNotlpAndUserpass(String lcnotlp, String lcpass) {
		// TODO Auto-generated method stub
		return this.Logrepo.findByNotlpAndUserpass(lcnotlp,lcpass);
	}


	@Override
	public LoginCheck findByNotlp(String lcnotlp) {
		// TODO Auto-generated method stub
		return this.Logrepo.findByNotlp(lcnotlp);
	}
	
//	@Override
//	public int updatePass(LoginCheck auth) {
//		// TODO Auto-generated method stub
//		return 0;
//	}


//	@Override
//	public LoginCheck findByLcemailAndLcpassAndLcuser(String lcemail, String lcpass, String lcuser) {
//		// TODO Auto-generated method stub
//		return null;
//	}


}
