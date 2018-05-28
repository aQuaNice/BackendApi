package be.dashmon.domain;

import org.json.simple.JSONObject;

public class ReturnMessage {
	
	
	private String rsltcode;
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
	public JSONObject getRsltdata() {
		return rsltdata;
	}
	public void setRsltdata(JSONObject rsltdata) {
		this.rsltdata = rsltdata;
	}
	private String rsltmsg;
	private JSONObject rsltdata;

}
