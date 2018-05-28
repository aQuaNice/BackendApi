package be.dashmon.handler;

import java.io.InputStream;
import java.util.Properties;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import be.dashmon.domain.LoginCheck;
import be.dashmon.domain.Register;
import be.dashmon.domain.ResponServ;
import be.dashmon.log.Logapp;
import be.dashmon.service.LogService;
import be.dashmon.service.RegService;
import be.dashmon.service.ResponService;
import be.dashmon.util.Utility;
@Component
public class LoginRegisHandler {

	@Autowired
	Utility myutil;

	@Autowired
	Logapp mylog;

	@Autowired
	ResponService apprespon;

	@Autowired
	RegService regisServ;

	@Autowired
	LogService loggisServ;
	Properties prop = new Properties();
	String propFileName = "application.properties";
	InputStream inputStream;

	
	public void setRegisterPhone(Register auth, String lcmethod, int reqCode, String lcParam, ResponServ respon, String LogNm) throws Exception {
		myutil.setPass(auth);
//		auth.setLctkn(auth.getUserpass());
		String LogNmsub ="HsetRegister";
		myutil.SetPasscodeReg(auth);
		ObjectMapper mapper = new ObjectMapper();
		
		inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
		prop.load(inputStream);
		String lcappver = prop.getProperty("appver");		
		auth.setAppver(lcappver);
//		auth.setUserpasscode("1586");
		try {			
			regisServ.saveRegis(auth,respon);
			if(respon.getRsltcode().equals("00")){				
				//System.out.println("status sms send :"+respon.getRsltcode());
				String lcdata = mapper.writeValueAsString(regisServ.findByNotlp(auth.getNotlp()));
//				myutil.SendSmsSayur(auth.getNotlp(),auth.getPasscode(),respon);
				myutil.SendSmsSayur(auth.getNotlp(),auth.getPasscode(),respon);
				myutil.FormatDatabefore(respon,lcdata);
				apprespon.setResponSuccess(respon, respon.getRsltcode(), lcmethod, reqCode, lcParam, LogNmsub);				
			}else{
				apprespon.setResponError(respon, respon.getRsltcode(), lcmethod, reqCode, lcParam, LogNmsub);
			}
		} catch (Exception e) {
			apprespon.setResponError(respon, "11", lcmethod, reqCode, lcParam, LogNmsub);
			 e.printStackTrace();
		}
	}

//	public static final String ACCOUNT_SID = "AC15aae5704dc1bfe3dbeb849dcbd1ebda";
//	public static final String AUTH_TOKEN = "10d38453d93ca24ec792cb458c2e3c33";
//	public void setRegister(Register auth, String lcmethod, int reqCode, String lcParam, ResponServ respon, String LogNm) throws Exception {
//		myutil.setPass(auth);
//		auth.setLctkn(auth.getUserpass());
//		String LogNmsub ="HsetRegister";
//		try {
//			int lcresult = 0; //regisServ.saveRegis(auth);
//			if (lcresult < 1) {
//				apprespon.setResponError(respon, "13", lcmethod, reqCode, lcParam, LogNmsub);
//			}
//			String lcSend = myutil.toEmail(auth);
//			if (lcSend.equals("0")) {
//				System.err.println("email status send:" + lcSend);
//				apprespon.setResponError(respon, "15", lcmethod, reqCode, lcParam, LogNmsub);
//			} else {
//				apprespon.setResponSuccess(respon, "00", lcmethod, reqCode, lcParam, LogNmsub);
//			}
//		} catch (Exception e) {
//			apprespon.setResponError(respon, "11", lcmethod, reqCode, lcParam, LogNmsub);
//			// e.printStackTrace();
//		}
//	}
	

	public void setPassCode(LoginCheck auth, String lcmethod, int reqCode, String lcParam, ResponServ respon,String LogNm) throws Exception {		
		ObjectMapper mapper = new ObjectMapper();
		String LogNmsub = "HsetPassCode";				
		myutil.setToken(auth);
//		myutil.SetPasscode(auth); setValidat
		auth.setValidat(1);auth.setOnlog(1);
		LoginCheck checkResult = new LoginCheck();
		checkResult = loggisServ.findByNotlp(auth.getNotlp());
		if (checkResult == null) {
			apprespon.setResponError(respon, "17", lcmethod, reqCode, lcParam, LogNmsub);
		} else {
			try {
				loggisServ.updatePass(auth,respon,0);
			} catch (Exception e) {
				apprespon.setResponError(respon, "11", lcmethod, reqCode, lcParam, LogNmsub);
				// e.printStackTrace();
			}			
			if(respon.getRsltcode().equals("04")){
//				myutil.SendSmsSayur(auth.getNotlp(),auth.getPasscode(),respon);
				String lcdata = mapper.writeValueAsString(auth);
				myutil.FormatData(respon,lcdata);
				apprespon.setResponSuccess(respon, respon.getRsltcode(), lcmethod, reqCode, lcParam, LogNmsub);
			}else{
				apprespon.setResponError(respon, respon.getRsltcode(), lcmethod, reqCode, lcParam, LogNmsub);
			}
		}
		
		
		
	}
	
	
	public void genPassCode(LoginCheck auth, String lcmethod, int reqCode, String lcParam, ResponServ respon,String LogNm) throws Exception {		
		ObjectMapper mapper = new ObjectMapper();
		String LogNmsub = "HsetPassCode";				
		myutil.setToken(auth);
		myutil.SetPasscode(auth); 
		auth.setOnlog(1); auth.setValidat(1);auth.setOnlog(1);
		LoginCheck checkResult = new LoginCheck();
		checkResult = loggisServ.findByNotlp(auth.getNotlp());
		if (checkResult == null) {
			apprespon.setResponError(respon, "17", lcmethod, reqCode, lcParam, LogNmsub);
		} else {
			try {
				loggisServ.updatePass(auth,respon,1);
			} catch (Exception e) {
				apprespon.setResponError(respon, "11", lcmethod, reqCode, lcParam, LogNmsub);
				// e.printStackTrace();
			}			
			if(respon.getRsltcode().equals("04")){
//				myutil.SendSmsSayur(auth.getNotlp(),auth.getPasscode(),respon);
				String lcdata = mapper.writeValueAsString(auth);
				myutil.SendSmsSayur(auth.getNotlp(),auth.getPasscode(),respon);
				myutil.FormatDatabefore(respon,lcdata);
				apprespon.setResponSuccess(respon, respon.getRsltcode(), lcmethod, reqCode, lcParam, LogNmsub);
			}else{
				apprespon.setResponError(respon, respon.getRsltcode(), lcmethod, reqCode, lcParam, LogNmsub);
			}
		}
		
		
		
	}
	public void setPass(LoginCheck auth, String lcmethod, int reqCode, String lcParam, ResponServ respon,String LogNm) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		LoginCheck checkResult = new LoginCheck();
		String LogNmsub ="HsetPass";
		try {
			myutil.encPass(auth);
			checkResult = loggisServ.findByNotlpAndApptkn(auth.getNotlp(), auth.getApptkn());				
			if (checkResult == null) {
				apprespon.setResponError(respon, "16", lcmethod, reqCode, lcParam, LogNmsub);
			} else {
				checkResult.setUserpass(auth.getUserpass());
				loggisServ.setPass(checkResult,respon);		
				if(respon.getRsltcode().equals("04")){
					String lcdata = mapper.writeValueAsString(checkResult);
					myutil.FormatData(respon,lcdata);
					apprespon.setResponSuccess(respon, "03", lcmethod, reqCode, lcParam, LogNmsub);
				}

			}
		} 
		catch (Exception e) {	
			apprespon.setResponError(respon, "11", lcmethod, reqCode, lcParam, LogNmsub);
			 e.printStackTrace();
		}
			
		}



//	public void setLogin(LoginCheck auth, String lcmethod, int reqCode, String lcParam, ResponServ respon, String LogNm)
//			throws Exception {
//		LoginCheck checkResult = new LoginCheck();
//		String LogNmsub ="HsetLogin";
//		try {
//
//			if (auth.getApptkn() != null && !auth.getApptkn().isEmpty()) {//jika param login yg di kirim mengandung token
//				System.err.println("jika param login yg di kirim mengandung token");
//				String lcToken = auth.getApptkn().toString();
//				if (lcToken.trim().length() == 0) {
//					apprespon.setResponError(respon, "17", lcmethod, reqCode, lcParam, LogNmsub);
//				} else {
//					checkResult = loggisServ.findByLctkn(auth.getApptkn());
//					if (checkResult == null) { //check apa token yg di kirim iu ada di dalam database
//						apprespon.setResponError(respon, "17", lcmethod, reqCode, lcParam, LogNmsub);
//					} else { // jika valid maka user telah mengubah password sebelumnya oleh karna itu pass yg di rubah harus di encript untuk validasi
//						
//						System.err.println("jika valid maka user telah mengubah password sebelumnya oleh karna itu pass yg di rubah harus di encript untuk validasi");
//						myutil.encPass(auth);
//						checkResult = loggisServ.findByLcemailAndLcpass(auth.getUseremail(), auth.getUserpass());
//						if (checkResult == null) {
//							apprespon.setResponError(respon, "16", lcmethod, reqCode, lcParam, LogNmsub);
//						} else {
//							respon.setRslttoken(checkResult.getApptkn());
//							apprespon.setResponSuccess(respon, "01", lcmethod, reqCode, lcParam, LogNmsub);
//						}
//					}
//				}
//			} else {//jika param login yg di kirim tidak mengandung token (bisa saja baru regis/user login di tempat lain) 
//				checkResult = loggisServ.findByLcemailAndLcpass(auth.getUseremail(), auth.getUserpass()); // lakukan pengechekan biasa untuk yg baru regis
//				if (checkResult == null) {//bisa di asumsikan kondisi di mana user login di tempat lain
//					
//					System.err.println("bisa di asumsikan kondisi di mana user login di tempat lain");
//					
//					myutil.encPass(auth); //maka coba encript pass untuk validasi
//					checkResult = loggisServ.findByLcemailAndLcpass(auth.getUseremail(), auth.getUserpass());
//					if (checkResult == null) {
//						apprespon.setResponError(respon, "16", lcmethod, reqCode, lcParam, LogNmsub);
//					}else{
//						respon.setRslttoken(checkResult.getApptkn());
//						checkResult.setOnlog(1);
//						apprespon.setResponSuccess(respon, "01", lcmethod, reqCode, lcParam, LogNmsub);
//						
//						int lcresult = 0;
////						try{
//						 lcresult = loggisServ.updateToken(checkResult);
////						}catch (Exception e) {
////						e.printStackTrace();
////						}
//						
//						if (lcresult < 1) {
//							apprespon.setResponError(respon, "13", lcmethod, reqCode, lcParam, LogNmsub);
//						}
//
//					}
//					
//					
//				} else {//kondisi di mana user baru login dan harus ubah password
//					
////					System.err.println("kondisi di mana user baru login dan harus ubah password");
//					if(checkResult.getValidat() == 1){
//						System.err.println("kondisi di mana user login dari tempat lain dengan menggunakan pass encript buat login");
//						apprespon.setResponError(respon, "16", lcmethod, reqCode, lcParam, LogNmsub);
//					}else{
//						System.err.println("kondisi di mana user baru login dan harus ubah password");
//						apprespon.setResponSuccess(respon, "02", lcmethod, reqCode, lcParam, LogNmsub);
//						checkResult.setLcon(1);
//						int lcresult = loggisServ.updateToken(checkResult);
//						if (lcresult < 1) {
//							apprespon.setResponError(respon, "13", lcmethod, reqCode, lcParam, LogNmsub);
//						}	
//					}
//					
//					
//				}
//				
//			}
//
//		} catch (Exception e) {
//			apprespon.setResponError(respon, "11", lcmethod, reqCode, lcParam, LogNmsub);
//			 e.printStackTrace();
//		}
//	}

	public void setLogin(LoginCheck auth, String lcmethod, int reqCode, String lcParam, ResponServ respon, String LogNm)
			throws Exception {
		LoginCheck checkResult = new LoginCheck();
		String LogNmsub ="HsetLogin";
		try {

			if (auth.getApptkn() != null && !auth.getApptkn().isEmpty()) {//jika param login yg di kirim mengandung token
				System.err.println("jika param login yg di kirim mengandung token");
				String lcToken = auth.getApptkn().toString();
				if (lcToken.trim().length() == 0) {
					apprespon.setResponError(respon, "17", lcmethod, reqCode, lcParam, LogNmsub);
				} else {
					checkResult = loggisServ.findByApptkn(auth.getApptkn());
					if (checkResult == null) { //check apa token yg di kirim iu ada di dalam database
						apprespon.setResponError(respon, "17", lcmethod, reqCode, lcParam, LogNmsub);
					} else { // jika valid maka user telah mengubah password sebelumnya oleh karna itu pass yg di rubah harus di encript untuk validasi
						
						System.err.println("jika valid maka user telah mengubah password sebelumnya oleh karna itu pass yg di rubah harus di encript untuk validasi");
						myutil.encPass(auth);
						checkResult = loggisServ.findByNotlpAndUserpass(auth.getNotlp(), auth.getUserpass());
						if (checkResult == null) {
							apprespon.setResponError(respon, "16", lcmethod, reqCode, lcParam, LogNmsub);
						} else {
							respon.setRslttoken(checkResult.getApptkn());
							apprespon.setResponSuccess(respon, "01", lcmethod, reqCode, lcParam, LogNmsub);
						}
					}
				}
			} else {//jika param login yg di kirim tidak mengandung token (bisa saja baru regis/user login di tempat lain) 
				checkResult = loggisServ.findByNotlpAndUserpass(auth.getNotlp(), auth.getUserpass()); // lakukan pengechekan biasa untuk yg baru regis
				if (checkResult == null) {//bisa di asumsikan kondisi di mana user login di tempat lain
					ObjectMapper mapper = new ObjectMapper();
					
					System.err.println("bisa di asumsikan kondisi di mana user login di tempat lain");
					
					myutil.encPass(auth); //maka coba encript pass untuk validasi
					checkResult = loggisServ.findByNotlpAndUserpass(auth.getNotlp(), auth.getUserpass());
					
					
					if (checkResult == null) {
						apprespon.setResponError(respon, "16", lcmethod, reqCode, lcParam, LogNmsub);
					}else{
						respon.setRslttoken(checkResult.getApptkn());
						checkResult.setOnlog(1);
						
						int lcresult = 0;
//						try{
						lcresult = loggisServ.updateToken(checkResult);
						
							String lcdata = mapper.writeValueAsString(checkResult);
//							FormatData
							myutil.FormatData(respon,lcdata);							
//							
							apprespon.setResponSuccess(respon, "01", lcmethod, reqCode, lcParam, LogNmsub);
//						
						if (lcresult < 1) {
							apprespon.setResponError(respon, "13", lcmethod, reqCode, lcParam, LogNmsub);
						}

					}
					
					
				} else {//kondisi di mana user baru login dan harus ubah password
					
//					System.err.println("kondisi di mana user baru login dan harus ubah password");
					if(checkResult.getValidat() == 1){
						System.err.println("kondisi di mana user login dari tempat lain dengan menggunakan pass encript buat login");
						apprespon.setResponError(respon, "16", lcmethod, reqCode, lcParam, LogNmsub);
					}else{
						System.err.println("kondisi di mana user baru login dan harus ubah password");
						apprespon.setResponSuccess(respon, "02", lcmethod, reqCode, lcParam, LogNmsub);
						checkResult.setOnlog(1);
						int lcresult = loggisServ.updateToken(checkResult);
						if (lcresult < 1) {
							apprespon.setResponError(respon, "13", lcmethod, reqCode, lcParam, LogNmsub);
						}	
					}
					
					
				}
				
			}

		} catch (Exception e) {
			apprespon.setResponError(respon, "11", lcmethod, reqCode, lcParam, LogNmsub);
			 e.printStackTrace();
		}
	}
	public void setPassChange(LoginCheck auth, String lcmethod, int reqCode, String lcParam, ResponServ respon,
		String LogNm) throws Exception {
//		ObjectMapper mapper = new ObjectMapper();
		LoginCheck checkResult = new LoginCheck();
		String LogNmsub ="HsetPassChange";
		try {
			checkResult = loggisServ.findByApptkn(auth.getUserpass());
			if (checkResult == null) {
				String lcToken = auth.getApptkn().toString();
				if (lcToken.trim().length() == 0) {
					apprespon.setResponError(respon, "10", lcmethod, reqCode, lcParam, LogNmsub);
				} else {
					myutil.encPass(auth);
					checkResult = loggisServ.findByUseremailAndUserpass(auth.getUseremail(), auth.getUserpass());
					if (checkResult == null) {
						apprespon.setResponError(respon, "16", lcmethod, reqCode, lcParam, LogNmsub);
					} else {
						checkResult.setUserpass(auth.getApptkn());
						myutil.encPass(checkResult);
						int lcresult = loggisServ.updateToken(checkResult);

						if (lcresult < 1) {
							apprespon.setResponError(respon, "18", lcmethod, reqCode, lcParam, LogNmsub);
						}
						respon.setRslttoken(checkResult.getApptkn());

						if (checkResult.getValidat() == 0) {
							apprespon.setResponSuccess(respon, "02", lcmethod, reqCode, lcParam, LogNmsub);
						} else {
							apprespon.setResponSuccess(respon, "03", lcmethod, reqCode, lcParam, LogNmsub);
						}
					}
				}
			} else {
				String lcToken = auth.getApptkn().toString();
				if (lcToken.trim().length() == 0) {
					apprespon.setResponError(respon, "10", lcmethod, reqCode, lcParam, LogNmsub);
				} else {
					checkResult.setUserpass(auth.getApptkn());
					myutil.encPass(checkResult);
					myutil.setToken(checkResult);
					checkResult.setOnlog(1);
					checkResult.setValidat(1);
					int lcresult = loggisServ.updateToken(checkResult);
					if (lcresult < 1) {
						apprespon.setResponError(respon, "18", lcmethod, reqCode, lcParam, LogNmsub);
					}
					respon.setRslttoken(checkResult.getApptkn());
					if (checkResult.getValidat() == 0) {
						apprespon.setResponSuccess(respon, "02", lcmethod, reqCode, lcParam, LogNmsub);
					} else {
						apprespon.setResponSuccess(respon, "03", lcmethod, reqCode, lcParam, LogNmsub);
					}
				}
			}
		} catch (Exception e) {
			apprespon.setResponError(respon, "11", lcmethod, reqCode, lcParam, LogNmsub);
			// e.printStackTrace();
		}
	}
	
	
	

}
