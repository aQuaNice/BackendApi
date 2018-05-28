package be.dashmon.domain;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "tbm_h_order")
public class OrderHead {
	private Long id;
	private Long idmember;
	private String invoorder;
	private String descorder;
	private String detadd;
	private float longadd;
	private float latadd;
	private String areaadd;
	private Date tglord;
	private Date tglexp;
	private float ordtotal;
	private int stat;
	private Member member;
	
	@Transient
	public String getLctoken() {
		return lctoken;
	}

	public void setLctoken(String lctoken) {
		this.lctoken = lctoken;
	}

	@NotBlank
	private String lctoken;

//	private Member member;	
	private Set<OrderDetail> orderDetail;
	
	 	public OrderHead() {

	    }

	    public OrderHead(String name) {
	        this.invoorder = name;
	    }

//	    public OrderHead(String name, Member member) {
//	        this.invoorder = name;
//	        this.member = member;
//	    }
//	
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getIdmember() {
		return idmember;
	}
	public void setIdmember(Long idmember) {
		this.idmember = idmember;
	}
	public String getInvoorder() {
		return invoorder;
	}
	public void setInvoorder(String invoorder) {
		this.invoorder = invoorder;
	}
	public String getDescorder() {
		return descorder;
	}
	public void setDescorder(String descorder) {
		this.descorder = descorder;
	}
	public String getDetadd() {
		return detadd;
	}
	public void setDetadd(String detadd) {
		this.detadd = detadd;
	}
	public float getLongadd() {
		return longadd;
	}
	public void setLongadd(float longadd) {
		this.longadd = longadd;
	}
	public float getLatadd() {
		return latadd;
	}
	public void setLatadd(float latadd) {
		this.latadd = latadd;
	}
	public String getAreaadd() {
		return areaadd;
	}
	public void setAreaadd(String areaadd) {
		this.areaadd = areaadd;
	}
	public Date getTglord() {
		return tglord;
	}
	public void setTglord(Date tglord) {
		this.tglord = tglord;
	}
	public Date getTglexp() {
		return tglexp;
	}
	public void setTglexp(Date tglexp) {
		this.tglexp = tglexp;
	}
	public float getOrdtotal() {
		return ordtotal;
	}
	public void setOrdtotal(float ordtotal) {
		this.ordtotal = ordtotal;
	}
	public int getStat() {
		return stat;
	}
	public void setStat(int stat) {
		this.stat = stat;
	}
	
	@ManyToOne
    @JoinColumn(name="idmember",insertable = false, updatable = false)
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}

//	@OneToMany(mappedBy = "orderhead",fetch=FetchType.LAZY, cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
	@OneToMany(fetch=FetchType.LAZY, cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
	@JoinColumn(name="idhead")
	@NotNull
	public Set<OrderDetail> getOrderDetail() {
		
		return orderDetail;
	}

	public void setOrderDetail(Set<OrderDetail> orderDetail) {
		this.orderDetail = orderDetail;
	}	
	
}
