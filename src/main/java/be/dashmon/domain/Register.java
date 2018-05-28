package be.dashmon.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;
//import javax.validation.constraints.Size;
@Entity
@Table(name = "tbm_member")
public class Register {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	@Column(name="id")
	private long id;
	@Column(name="userid")
	@NotBlank()
	private String userid;

	
	@Column(name="passcode")
	private String passcode;
	

	@Column(name="notlp")
	@NotBlank()
	private String notlp;

	@Column(name="useremail")
//	@NotBlank()
	private String useremail;
	
	@Column(name="userpass")
	private String userpass;

	@Column(name="gcmcode")
//	@NotBlank()
	private String gcmcode;

	public String getAppver() {
		return appver;
	}

	public void setAppver(String appver) {
		this.appver = appver;
	}

	private String appver;
	
	@Column(name="validat")
	private int validat;

	@Column(name="apptkn")
	private String apptkn;
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getPasscode() {
		return passcode;
	}

	public void setPasscode(String passcode) {
		this.passcode = passcode;
	}

	public String getNotlp() {
		return notlp;
	}

	public void setNotlp(String notlp) {
		this.notlp = notlp;
	}

	public String getUseremail() {
		return useremail;
	}

	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}

	public String getUserpass() {
		return userpass;
	}

	public void setUserpass(String userpass) {
		this.userpass = userpass;
	}

	public String getGcmcode() {
		return gcmcode;
	}

	public void setGcmcode(String gcmcode) {
		this.gcmcode = gcmcode;
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
}
