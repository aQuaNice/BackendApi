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
public class Register___ {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	@Column(name="id")
	private long id;

	@Column(name="userid")
	@NotBlank()
	private String lcuser;

	
	@Column(name="passcode")
	private String lcpasscode;
	

	@Column(name="notlp")
	@NotBlank()
	private String lcnotlp;

	@Column(name="useremail")
//	@NotBlank()
	private String lcemail;
	
	@Column(name="userpass")
	private String lcpass;

	@Column(name="gcmcode")
//	@NotBlank()
	private String lcgcm;

	
	@Column(name="validat")
	private int lcvalid;

	@Column(name="apptkn")
	private String lctkn;

//	public long getLcid() {
//		return lcid;
//	}
//
//	public void setLcid(long lcid) {
//		this.lcid = lcid;
//	}

//	public String getLcuser() {
//		return lcuser;
//	}
//
//	public void setLcuser(String lcuser) {
//		this.lcuser = lcuser;
//	}

//	public String getLcemail() {
//		return lcemail;
//	}

//	public void setLcemail(String lcemail) {
//		this.lcemail = lcemail;
//	}

//	public String getLcpass() {
//		return lcpass;
//	}
//
//	public void setLcpass(String lcpass) {
//		this.lcpass = lcpass;
//	}
//
//	public String getLcgcm() {
//		return lcgcm;
//	}
//
//	public void setLcgcm(String lcgcm) {
//		this.lcgcm = lcgcm;
//	}
//
//	public int getLcvalid() {
//		return lcvalid;
//	}

//	public void setLcvalid(int lcvalid) {
//		this.lcvalid = lcvalid;
//	}
//
//	public String getLctkn() {
//		return lctkn;
//	}
//
//	public void setLctkn(String lctkn) {
//		this.lctkn = lctkn;
//	}


//	public String getLcpasscode() {
//		return lcpasscode;
//	}
//
//	public void setLcpasscode(String lcpasscode) {
//		this.lcpasscode = lcpasscode;
//	}

	
//	public String getLcnotlp() {
//		return lcnotlp;
//	}
//
//	public void setLcnotlp(String lcnotlp) {
//		this.lcnotlp = lcnotlp;
//	}


	

    
}
