package be.dashmon.domain;

public class GetItem {
	public String getLclimit() {
		return lclimit;
	}
	public void setLclimit(String lclimit) {
		this.lclimit = lclimit;
	}
	public String getOffset() {
		return offset;
	}
	public void setOffset(String offset) {
		this.offset = offset;
	}
	public String getItemtype() {
		return itemtype;
	}
	public void setItemtype(String itemtype) {
		this.itemtype = itemtype;
	}
	private String lclimit;
	private String offset;
	private String itemtype;
}
