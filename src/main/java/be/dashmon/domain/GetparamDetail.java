package be.dashmon.domain;

public class GetparamDetail {

	public Long getLcitemid() {
		return lcitemid;
	}

	public void setLcitemid(Long lcitemid) {
		this.lcitemid = lcitemid;
	}
	private Long lcitemid;
	
//	public int getLcitemid() {
//		return lcitemid;
//	}
//	public void setLcitemid(int lcitemid) {
//		this.lcitemid = lcitemid;
//	}
	public int getLcstat() {
		return lcstat;
	}
	public void setLcstat(int lcstat) {
		this.lcstat = lcstat;
	}
	public Long getLcidmember() {
		return lcidmember;
	}

	public void setLcidmember(Long i) {
		this.lcidmember = i;
	}
	private Long lcidmember;
	private int lcstat;
	
	
	private String lctoken;
	public String getLctoken() {
		return lctoken;
	}
	public void setLctoken(String lctoken) {
		this.lctoken = lctoken;
	}
}
