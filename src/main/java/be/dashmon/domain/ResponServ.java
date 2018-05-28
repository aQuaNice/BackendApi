package be.dashmon.domain;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class ResponServ {

	private String rsltcode;
	private String rslttoken;
	private JSONObject rsltdata;
	
public JSONObject getRsltdata() {
		return rsltdata;
	}
	public void setRsltdata(JSONObject lsResult) {
		this.rsltdata = lsResult;
	}
	//	public JSONObject getRsltdata() {
//		return rsltdata;
//	}
//	public void setRsltdata(String string) {
//		this.rsltdata = string;
//	}
	public String getRslttoken() {
		return rslttoken;
	}
	public void setRslttoken(String rslttoken) {
		this.rslttoken = rslttoken;
	}
	public String getRsltcode() {
		return rsltcode;
	}
	public void setRsltcode(String rsltcode) {
		this.rsltcode = rsltcode;
	}
	public String getRsltmsg() {
		return rsltmsg;
	}
	public void setRsltmsg(String rsltmsg) {
		this.rsltmsg = rsltmsg;
	}
	private String rsltmsg;
}
