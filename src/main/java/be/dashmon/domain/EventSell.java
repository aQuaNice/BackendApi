//package be.dashmon.domain;
//
//import java.util.Set;
//
//import javax.persistence.CascadeType;
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.OneToMany;
//import javax.persistence.Table;
//import javax.validation.constraints.NotNull;
//
//@Entity
//@Table(name = "tbm_m_eve")
//public class EventSell {
//	
//	
//	private Long id;
//	private String desc;
//	private int evencut;
//	private int stat;
//	private Set<Product> product;
//
//	
////	public EventSell(){
////    }
////    public EventSell(String name) {
////        this.desc = name;
////    }
//
// 	@Id
//    @GeneratedValue(strategy = GenerationType.AUTO)		
//
//	public Long getId() {
//		return id;
//	}
//	public void setId(Long id) {
//		this.id = id;
//	}
//	public String getDesc() {
//		return desc;
//	}
//	public void setDesc(String desc) {
//		this.desc = desc;
//	}
//	public int getEvencut() {
//		return evencut;
//	}
//	public void setEvencut(int evencut) {
//		this.evencut = evencut;
//	}
//	public int getStat() {
//		return stat;
//	}
//	public void setStat(int stat) {
//		this.stat = stat;
//	}
//	
//	 	@OneToMany(mappedBy = "eventSell", cascade = CascadeType.ALL)
//	 
//		public Set<Product> getProduct() {
//			return product;
//		}
//		public void setProduct(Set<Product> product) {
//			this.product = product;
//		}
//
////		 @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
//
//}
