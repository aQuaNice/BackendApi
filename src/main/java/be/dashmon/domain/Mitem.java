package be.dashmon.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "tbm_item")
public class Mitem {

//@Id
//@Column(name = "id")
//@TableGenerator(name = "TABLE_GEN", table = "SYS_SEQ",
//pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_VALUE", pkColumnValue = "LANGUAGE_SEQ")
//@GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN")
//private Integer id;
	
	@Id
	@GeneratedValue
	@Column(name="id")
	private int lcitid ;
	public int getLcitid() {
		return lcitid;
	}
	public void setLcitid(int lcitid) {
		this.lcitid = lcitid;
	}
	public String getLcitemicon() {
		return lcitemicon;
	}
	public void setLcitemicon(String lcitemicon) {
		this.lcitemicon = lcitemicon;
	}
	public String getLcdeschead() {
		return lcdeschead;
	}
	public void setLcdeschead(String lcdeschead) {
		this.lcdeschead = lcdeschead;
	}
	public String getLcitmsat() {
		return lcitmsat;
	}
	public void setLcitmsat(String lcitmsat) {
		this.lcitmsat = lcitmsat;
	}
	public float getLcitmqty() {
		return lcitmqty;
	}
	public void setLcitmqty(float lcitmqty) {
		this.lcitmqty = lcitmqty;
	}
	public String getLcitmvol() {
		return lcitmvol;
	}
	public void setLcitmvol(String lcitmvol) {
		this.lcitmvol = lcitmvol;
	}
	@Column(name="itemicon")
	private String lcitemicon;
	@Column(name="deschead")
	private String lcdeschead;
	@Column(name="itmsat")
	private String lcitmsat;
	@Column(name="itmqty")
	private float lcitmqty;
	@Column(name="itmvol")
	private String lcitmvol;
//	
//	  @OneToOne
//	@JoinColumn(name="id", 
//				insertable=false, updatable=false, 
//				nullable=false)
//	private DetailTrans detailTrans;
	
	
//	public Mitem() {
//		
//	}
//	
//	public Mitem(String lcdeschead) {
//		this.lcdeschead = lcdeschead;
//		
//	}



}
