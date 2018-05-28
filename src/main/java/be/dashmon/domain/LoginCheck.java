package be.dashmon.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "tbm_member")
public class LoginCheck {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)

	@Column(name="id")
	private long id;
	@Column(name="useremail")
//	@NotBlank()	
	private String useremail;
	
	@Column(name="userid")
//	@NotBlank()
	private String userid;
	@Column(name="notlp")
	@NotBlank()
	private String notlp;
	@Column(name="passcode")
	private String passcode;
	@Column(name="userpass")
	private String userpass;
	@Column(name="validat")
	private int validat;
	@Column(name="apptkn")
	private String apptkn;
	
	public String getAppver() {
		return appver;
	}
	public void setAppver(String appver) {
		this.appver = appver;
	}
	private String appver;
	@Column(name="onlog")
	private int onlog;	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUseremail() {
		return useremail;
	}
	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getNotlp() {
		return notlp;
	}
	public void setNotlp(String notlp) {
		this.notlp = notlp;
	}
	public String getPasscode() {
		return passcode;
	}
	public void setPasscode(String passcode) {
		this.passcode = passcode;
	}
	public String getUserpass() {
		return userpass;
	}
	public void setUserpass(String userpass) {
		this.userpass = userpass;
	}
	public int getValidat() {
		return validat;
	}
	public void setValidat(int validat) {
		this.validat = validat;
	}
	public String getApptkn() {
		return apptkn;
	}
	public void setApptkn(String apptkn) {
		this.apptkn = apptkn;
	}
	public int getOnlog() {
		return onlog;
	}
	public void setOnlog(int onlog) {
		this.onlog = onlog;
	}

	

}
