package be.dashmon.domain;

public class Banner {
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getGrouped() {
		return grouped;
	}
	public void setGrouped(int grouped) {
		this.grouped = grouped;
	}
	public int getStat() {
		return stat;
	}
	public void setStat(int stat) {
		this.stat = stat;
	}
	private int id;
	private String desc;
	private String image;
	private int grouped;
	private int stat;

}
