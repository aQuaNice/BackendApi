package be.dashmon.util;

import java.io.IOException;
import java.io.InputStream;
import java.security.SecureRandom;
//import java.io.IOException;
//import java.io.UnsupportedEncodingException;
//import java.security.InvalidAlgorithmParameterException;
//import java.security.InvalidKeyException;
//import java.security.NoSuchAlgorithmException;
//import java.security.spec.InvalidKeySpecException;
//
//import javax.crypto.BadPaddingException;
//import javax.crypto.IllegalBlockSizeException;
//import javax.crypto.NoSuchPaddingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import be.dashmon.controller.EmailHtmlSender;
import be.dashmon.controller.EmailStatus;
import be.dashmon.domain.LoginCheck;
import be.dashmon.domain.Register;
import be.dashmon.domain.ResponServ;
import be.dashmon.service.RegService;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@Component
public class Utility {

	@Autowired
	HexCoder lcEncrypt;

	String key = "ezeon8547";

	@Autowired
	private EmailHtmlSender sendemail;

	@Autowired
	private RegService validCheck;

	public static final String ACCOUNT_SID = "AC15aae5704dc1bfe3dbeb849dcbd1ebda";
	public static final String AUTH_TOKEN = "10d38453d93ca24ec792cb458c2e3c33";
	
	Properties prop = new Properties();
	String propFileName = "application.properties";
	InputStream inputStream;
	
	public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

	OkHttpClient client = new OkHttpClient();

	public void NoPhoneCheck(String lcPhone, ResponServ respon) {
		String regex = "^[0-9]{10,13}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(lcPhone);
		String lccheck = "";
		if (!matcher.matches()) {
			lccheck = "12";
		}
		try {			
			Register doMember = validCheck.findByNotlp(lcPhone);			
			//Register doMember = validCheck.findByLcnotlp(lcPhone);
			if (doMember == null) {
				lccheck = "16";
			} else {
				lccheck = "14";
			}
		} catch (Exception e) {
			lccheck = "16";
		}
		respon.setRsltcode(lccheck);
//		return lccheck;
	}
	
	
	
	
	
	public void FormatData(ResponServ respon, String lcdata) throws ParseException{
		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(lcdata);
		JSONObject lclist = new JSONObject();
//		lclist.put("lcid", json.get("lcid"));
//		lclist.put("lcuser", json.get("lcuser"));
//		lclist.put("lcnotlp", json.get("lcnotlp"));
//		lclist.put("lcvalid", json.get("lcvalid"));
//		lclist.put("lctkn", json.get("lctkn"));
		lclist.put("id", json.get("id"));
		lclist.put("userid", json.get("userid"));
		lclist.put("notlp", json.get("notlp"));
		lclist.put("validat", json.get("validat"));
		lclist.put("apptkn", json.get("apptkn"));
		lclist.put("appver", json.get("appver"));

		respon.setRsltdata(lclist);
	}

	
	public void FormatDatabefore(ResponServ respon, String lcdata) throws ParseException{
		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(lcdata);
		JSONObject lclist = new JSONObject();
		
//		System.out.println();
		lclist.put("id", json.get("id"));
		lclist.put("userid", json.get("userid"));
		lclist.put("notlp", json.get("notlp"));
		lclist.put("validat", json.get("validat"));
		lclist.put("apptkn", null);
		lclist.put("appver", json.get("appver"));	
		
//		lclist.put("lcid", json.get("id"));
//		lclist.put("lcuser", json.get("userid"));
//		lclist.put("lcnotlp", json.get("notlp"));
//		lclist.put("lcvalid", json.get("validat"));
//		lclist.put("lctkn", null);				
		respon.setRsltdata(lclist);
	}
	public void setToken(LoginCheck auth) throws Exception {
		Date begin = new Date();
		String dateFormat = new SimpleDateFormat("yyyyMMddHHmmss").format(begin);
		String tkn = lcEncrypt.encrypt(dateFormat, auth.getNotlp() + auth.getPasscode() + auth.getUserid());
		auth.setApptkn(tkn);
	}

	public void setPass(Register auth) throws Exception {
		int noOfCAPSAlpha = 1;
		int noOfDigits = 1;
		int noOfSplChars = 1;
		int minLen = 8;
		int maxLen = 8;

		// for (int i = 0; i < 10; i++) {
		char[] pswd = lcEncrypt.generatePswd(minLen, maxLen, noOfCAPSAlpha, noOfDigits, noOfSplChars);
		// System.out.println("Len = " + pswd.length + ", " + new String(pswd));
		// }
		// Date begin = new Date();
		// String dateFormat = new
		// SimpleDateFormat("yyyyMMddHHmmss").format(begin);
		//
		// String pas=lcEncrypt.encrypt(dateFormat,auth.getUserid());
		auth.setUserpass(new String(pswd));
		// return enc;
		// return randomStr.substring(0, 8);
	}

	public void encPass(LoginCheck auth) throws Exception {
		Date begin = new Date();
		String dateFormat = new SimpleDateFormat("yyyyMMddHHmmss").format(begin);
		String pas = lcEncrypt.encrypt(auth.getNotlp(), auth.getUserpass());
		// String pas=lcEncrypt.encrypt(auth.getNotlp(),auth.getPasscode());
		auth.setUserpass(pas);
		// return enc;
	}

	public void decPass(LoginCheck auth) throws Exception {
		Date begin = new Date();
		String dateFormat = new SimpleDateFormat("yyyyMMddHHmmss").format(begin);

		String pas = lcEncrypt.decrypt(auth.getNotlp(), auth.getUserpass());
		auth.setUserpass(pas);
		// return enc;
	}

	public String toEmail(Register auth) throws Exception {

		JSONArray lcemail = new JSONArray();
		lcemail.add(auth.getUseremail());
		String array[] = new String[lcemail.size()];
		for (int k = 0; k < lcemail.size(); k++) {
			array[k] = (String) lcemail.get(k);
		}

		Context context = new Context();
		String title = "Hi " + auth.getUserid() + ",";
		String desc = "Thank you for creating a customer account at aQua Go.";
		context.setVariable("title", title);
		context.setVariable("desc", desc);
		context.setVariable("email", auth.getUseremail());
		context.setVariable("pass", auth.getUserpass());
		// context.setVariable("description", "Klick link di bawah ini untuk
		// validasi registrasi member aQua Go...");
		EmailStatus emailStatus = sendemail.send(array, "aQua Go", "email/email", context);
		return emailStatus.getStatus();
	}

	// public void SetPasscode(Register auth) throws Exception {
	// int noOfCAPSAlpha = 0;
	// int noOfDigits = 4;
	// int noOfSplChars = 0;
	// int minLen = 4;
	// int maxLen = 4;
	//
	// char[] pswd = lcEncrypt.generatePswd(minLen, maxLen,noOfCAPSAlpha,
	// noOfDigits, noOfSplChars);
	// auth.setLcpasscode(new String(pswd));
	// }
	public void SetPasscodeReg(Register auth) throws Exception {
		int noOfCAPSAlpha = 0;
		int noOfDigits = 4;
		int noOfSplChars = 0;
		int minLen = 4;
		int maxLen = 4;

		char[] pswd = lcEncrypt.generatePswd(minLen, maxLen, noOfCAPSAlpha, noOfDigits, noOfSplChars);
		auth.setPasscode(new String(pswd));
	}

	public void SetPasscode(LoginCheck auth) throws Exception {
		int noOfCAPSAlpha = 0;
		int noOfDigits = 4;
		int noOfSplChars = 0;
		int minLen = 4;
		int maxLen = 4;

		char[] pswd = lcEncrypt.generatePswd(minLen, maxLen, noOfCAPSAlpha, noOfDigits, noOfSplChars);
		auth.setPasscode(new String(pswd));
	}

	public void SendSms(String LcPhone, String passCode, ResponServ respon) throws Exception {
		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
		Message message = Message.creator(new PhoneNumber("+62" + LcPhone), // 6289685777475+auth.getNotlp()
				new PhoneNumber("+12138144411"), "Your verification number : " + passCode).create();
		// queued
		respon.setRsltcode("00");
		
		
	}
	

	
	public void SendSmsSayur(String LcPhone, String passCode, ResponServ respon) throws IOException{

		inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
		prop.load(inputStream);		
		String key = prop.getProperty("smskey");
		String smsmessage = prop.getProperty("smsmessage");
		String smsurl = prop.getProperty("smsurl");
		
		JSONObject json = new JSONObject();
		
		json.put("destination", LcPhone);
		json.put("text", smsmessage + " : "+passCode);
//        String json = smtpSend.bowlingJson("089685777475", "test mesabot");
        String postResponse = this.doPostRequest(smsurl, json.toString(),key);
        System.out.println(postResponse);
	
	
	}
	
	
	 String doPostRequest(String url, String json,String token) throws IOException{
         RequestBody body = RequestBody.create(JSON, json);
           Request request = new Request.Builder()
           	.addHeader("Authorization", "Bearer "+token)
           	 .header("User-Agent", "Mozilla/5.0 (Windows NT 5.1; rv:31.0) Gecko/20100101 Firefox/31.0")
           	 .header("accept:", "*/*")
           	 .header("Content-Type", "application/json")
               .url(url)
               .post(body)
               .build();
           Response response = client.newCall(request).execute();
           return response.body().string();
     }

	public void PassCodeCheck(LoginCheck auth, ResponServ respon) {
		String lccheck = "";
		System.err.println("this check :" +auth.getNotlp());
		System.err.println("this check :" +auth.getPasscode());

		Register doMember = validCheck.findByNotlpAndPasscode(auth.getNotlp(), auth.getPasscode());

		if (doMember == null) {
			lccheck = "17";
		} else {
			lccheck = "04";
		}
		respon.setRsltcode(lccheck);
//		return lccheck;
	}
	
	
	

	public String EmailCheck(String lcEmail) {
		String regex = "^(.+)@(.+)$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(lcEmail);
		String lccheck = "";

		if (!matcher.matches()) {
			lccheck = "12";
			return lccheck;
		}

		try {
			Register doMember = validCheck.findByUseremail(lcEmail);
			if (!doMember.toString().isEmpty()) {
				lccheck = "14";
				return lccheck;
			}
		} catch (Exception e) {
			lccheck = "";
			return lccheck;
		}
		return lccheck;
	}

}
