package be.dashmon.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbm_d_order")
public class OrderDetail {

	private Long id;
	private int idhead;
//	private String invoorder;
	private int iditm;
	private String qtyitm;
	private float itmprice;
	private int priceid;
	private int eventid;
	private int discid;
	private int stat;
	private Product product;
	private OrderHead orderhead;
	

	public OrderDetail() {

    }

    public OrderDetail(Long name) {
        this.id = name;
    }

    public OrderDetail(Long name, Product product) {
        this.id = name;
        this.product = product;
    }
    
    @Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getIdhead() {
		return idhead;
	}

	public void setIdhead(int idhead) {
		this.idhead = idhead;
	}

//	public String getInvoorder() {
//		return invoorder;
//	}
//
//	public void setInvoorder(String invoorder) {
//		this.invoorder = invoorder;
//	}

	public int getIditm() {
		return iditm;
	}

	public void setIditm(int iditm) {
		this.iditm = iditm;
	}

	public String getQtyitm() {
		return qtyitm;
	}

	public void setQtyitm(String qtyitm) {
		this.qtyitm = qtyitm;
	}

	public float getItmprice() {
		return itmprice;
	}

	public void setItmprice(float itmprice) {
		this.itmprice = itmprice;
	}

	public int getPriceid() {
		return priceid;
	}

	public void setPriceid(int priceid) {
		this.priceid = priceid;
	}

	public int getEventid() {
		return eventid;
	}

	public void setEventid(int eventid) {
		this.eventid = eventid;
	}

	public int getDiscid() {
		return discid;
	}

	public void setDiscid(int discid) {
		this.discid = discid;
	}

	public int getStat() {
		return stat;
	}

	public void setStat(int stat) {
		this.stat = stat;
	}
	@ManyToOne
    @JoinColumn(name="iditm",insertable = false, updatable = false)
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	 @ManyToOne(optional = false)
    @JoinColumn(name="idhead",insertable = false, updatable = false,referencedColumnName="id")
	public OrderHead getOrderhead() {
		return orderhead;
	}

	public void setOrderhead(OrderHead orderhead) {
		this.orderhead = orderhead;
	}
}
