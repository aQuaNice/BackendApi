package be.dashmon.domain;

import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "tbm_member")
public class Member {
	
	private Long id;
	private String userid;
	private String notlp;
	private String passcode;
	private String useremail;
	private String userpass;
	private String gcmcode;
	private int validat;
	private String apptkn;
	private int onlog;
	private Set<OrderHead> orderHead;
	
	public Member(){
    }
    public Member(String name) {
        this.userid = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    
    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public int getOnlog() {
		return onlog;
	}
	public void setOnlog(int onlog) {
		this.onlog = onlog;
	}
	
	@OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
		public Set<OrderHead> getOrderHead() {
			return orderHead;
		}
		public void setOrderHead(Set<OrderHead> orderHead) {
			this.orderHead = orderHead;
		}

	
	
//	private Set<Product> products;
//    public Member(){
//    }
//
//    public Member(String name) {
//        this.desc = name;
//    }

	
//	private Set<OrderHead> orderHead;
//	public Member(){
//    }

//    public Member(String name) {
//        this.lcuser = name;
//    }
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	
//	public long getLcid(){
//		return lcid;
//	}
//
//	public void setLcid(long lcid) {
//		this.lcid = lcid;
//	}
//
//
////	@OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
////	public Set<OrderHead> getOrderHead() {
////		return orderHead;
////	}
////
////	public void setOrderHead(Set<OrderHead> orderHead) {
////		this.orderHead = orderHead;
////	}
//
//	
//	public String getLcuser() {
//		return lcuser;
//	}
//
//	public void setLcuser(String lcuser) {
//		this.lcuser = lcuser;
//	}
//
//	public String getLcnotlp() {
//		return lcnotlp;
//	}
//
//	public void setLcnotlp(String lcnotlp) {
//		this.lcnotlp = lcnotlp;
//	}
//
//	public String getLcpasscode() {
//		return lcpasscode;
//	}
//
//	public void setLcpasscode(String lcpasscode) {
//		this.lcpasscode = lcpasscode;
//	}
//
//	public String getLcuseremail() {
//		return lcuseremail;
//	}
//
//	public void setLcuseremail(String lcuseremail) {
//		this.lcuseremail = lcuseremail;
//	}
//
//	public String getLcuserpass() {
//		return lcuserpass;
//	}
//
//	public void setLcuserpass(String lcuserpass) {
//		this.lcuserpass = lcuserpass;
//	}
//
//	public String getLcgcmcode() {
//		return lcgcmcode;
//	}
//
//	public void setLcgcmcode(String lcgcmcode) {
//		this.lcgcmcode = lcgcmcode;
//	}
//
//	public int getLcvalidat() {
//		return lcvalidat;
//	}
//
//	public void setLcvalidat(int lcvalidat) {
//		this.lcvalidat = lcvalidat;
//	}
//
//	public String getLcapptkn() {
//		return lcapptkn;
//	}
//
//	public void setLcapptkn(String lcapptkn) {
//		this.lcapptkn = lcapptkn;
//	}
//
//	public int getLconlog() {
//		return lconlog;
//	}
//
//	public void setLconlog(int lconlog) {
//		this.lconlog = lconlog;
//	}
//	
//	@Column(name="id")
//	private long lcid;
//
//	@Column(name="userid")
//	@NotBlank()
//	private String lcuser;
//	
//	@Column(name="notlp")
//	@NotBlank()
//	private String lcnotlp;
//	
//	@Column(name="passcode")
//	@NotBlank()
//	private String	lcpasscode;
//	
//	@Column(name="useremail")
//	@NotBlank()
//	private String	lcuseremail;
//	
//	@Column(name="userpass")
//	@NotBlank()
//	private String	lcuserpass;
//	
//	@Column(name="gcmcode")
//	@NotBlank()
//	private String	lcgcmcode;
//
//	@Column(name="validat")
//	@NotBlank()
//	private int	lcvalidat;
//
//	@Column(name="apptkn")
//	@NotBlank()
//	private String	lcapptkn;
//	
//	@Column(name="onlog")
//	@NotBlank()
//	private int	lconlog;
}
