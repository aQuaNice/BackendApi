package be.dashmon.handler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import be.dashmon.domain.Banner;
import be.dashmon.domain.Mainitem;
import be.dashmon.domain.Mainmenu;

@Component
public class MainClassHandler {
	
	@Autowired
	private JdbcTemplate jTemplate;

	
		public JSONArray getmenu(){
//			JSONArray objMenu = null;
			JSONObject jo_data = new JSONObject();
			JSONArray ja_data = new JSONArray();
			String sql = "SELECT * FROM tbm_menu where stat = ? order by ordstat";
			List<Mainmenu> listMenu = jTemplate.query(sql, new Object[] {1}, new RowMapper<Mainmenu>() {
				public Mainmenu mapRow(ResultSet result, int rowNum) throws SQLException {
					Mainmenu objMember = new Mainmenu();
					objMember.setId(result.getInt("id"));
					objMember.setDesc(result.getString("desc"));
					objMember.setMnuicon(result.getString("mnuicon"));
					objMember.setAddesc(result.getString("adddesc"));
					return objMember;
				}
			});
			
			for (Mainmenu aMember : listMenu) {
				jo_data = new JSONObject();
				jo_data.put("id", aMember.getId());
				jo_data.put("desc", aMember.getDesc());
				jo_data.put("mnuicon", aMember.getMnuicon());
				jo_data.put("addesc", aMember.getAddesc());
//				jo_data.put("type", aMember.getType());
//				jo_data.put("icon", aMember.getIcon());
//				jo_data.put("url", aMember.getUrl());
				ja_data.add(jo_data);
			}
			
			System.out.println(ja_data);
			return ja_data;
		}
		
		
		public JSONArray getBanner(){
//			JSONArray objMenu = null;
			JSONObject jo_data = new JSONObject();
			JSONArray ja_data = new JSONArray();
			String sql = "SELECT * FROM tbm_m_banner where stat = ? and grouped = ?";
			List<Banner> listMenu = jTemplate.query(sql, new Object[] {1,1}, new RowMapper<Banner>() {
				public Banner mapRow(ResultSet result, int rowNum) throws SQLException {
					Banner objMember = new Banner();
					objMember.setId(result.getInt("id"));
					objMember.setDesc(result.getString("desc"));
					objMember.setImage(result.getString("image"));
//					objMember.setUrl(result.getString("url"));
					return objMember;
				}
			});
			
			for (Banner aBanner : listMenu) {
				jo_data = new JSONObject();
				jo_data.put("id", aBanner.getId());
				jo_data.put("desc", aBanner.getDesc());
				jo_data.put("mnuicon", aBanner.getImage());
//				jo_data.put("type", aMember.getType());
//				jo_data.put("icon", aMember.getIcon());
//				jo_data.put("url", aMember.getUrl());
				ja_data.add(jo_data);
			}
			
			return ja_data;
		}
		
		public JSONArray getitem(String lctype,String lclimit, String lcoffset){
//			JSONArray objMenu = null;
			JSONObject jo_data = new JSONObject();
			JSONArray ja_data = new JSONArray();
			String sql = "SELECT * FROM listeventedit WHERE idmenu = "+lctype+" limit "+lclimit+" offset "+lcoffset;			
			List<Mainitem> listItem = jTemplate.query(sql, new Object[] {}, new RowMapper<Mainitem>() {
				public Mainitem mapRow(ResultSet result, int rowNum) throws SQLException {
					Mainitem objlist = new Mainitem();
					objlist.setId(result.getInt("id"));
					objlist.setIdmenu(result.getInt("idmenu"));
					objlist.setDeschead(result.getString("deschead"));
					objlist.setDesccont(result.getString("desccont"));
					objlist.setItemicon(result.getString("itemicon"));
					objlist.setItmprice(result.getInt("itmprice"));
					objlist.setItmqty(result.getInt("itmqty"));
					objlist.setItmsat(result.getString("itmsat"));
					objlist.setItmvol(result.getString("itmvol"));
					objlist.setEventuse(result.getInt("eventuse"));
					objlist.setEvenid(result.getInt("evenstat"));
					
					objlist.setEvenstat(result.getInt("evenstat"));
					objlist.setEvendesc(result.getString("evendesc"));
					objlist.setEvenset(result.getFloat("evenset"));
					objlist.setEvendprice(result.getFloat("evendprice")); 
					return objlist;
				}
			});
			
			for (Mainitem aList : listItem) {
				jo_data = new JSONObject();
				jo_data.put("iditm", aList.getId());
				jo_data.put("idmenu", aList.getIdmenu());
				jo_data.put("deschead", aList.getDeschead());
				jo_data.put("desccont", aList.getDesccont());
				jo_data.put("itemicon", aList.getItemicon());
				jo_data.put("itmprice", aList.getItmprice());
				jo_data.put("itmqty", aList.getItmqty());
				jo_data.put("itmsat", aList.getItmsat());
				jo_data.put("itmvol", aList.getItmvol());				
				jo_data.put("itmevid", aList.getEvenid());
				jo_data.put("itmevuse", aList.getEventuse());
				jo_data.put("itmdvdesc", aList.getEvendesc());
				jo_data.put("itmevset", aList.getEvenset());
				jo_data.put("itmevprice", aList.getEvendprice());
//				jo_data.put("mnuicon", aMember.getMnuicon());

				ja_data.add(jo_data);
			}
			
			return ja_data;
		}

}
