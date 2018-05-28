package be.dashmon.implement;

import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import javax.transaction.Transactional;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

//import be.dashmon.domain.DetailTrans;
import be.dashmon.domain.GetparamDetail;
import be.dashmon.domain.OrderHead;
//import be.dashmon.domain.HeadTrans;
import be.dashmon.domain.Register;
import be.dashmon.domain.ResponServ;
import be.dashmon.repository.RegRepo;
import be.dashmon.repository.SetOrderRepository;
import be.dashmon.service.RegService;
import be.dashmon.service.SetOrderService;

@Service
@PropertySource(value="classpath:application.properties")
public class SetOrderImpl implements SetOrderService {
	@Autowired
	private RegRepo Regrepo;
	
	@Autowired
	private SetOrderRepository setorder;
	
	@Autowired
	JdbcTemplate jTemplate;
	

	Properties prop = new Properties();
	String propFileName = "application.properties";
	InputStream inputStream;

	public void getProper() throws IOException{
	inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
	prop.load(inputStream);
	}
	
	@Override
	public Register findByLctknAndLcid(String lctoken,Long lcid) {		
		return this.Regrepo.findByApptknAndId(lctoken,lcid);
	}

	@Override
	public OrderHead findByLcinvoorderAndLcidmember(String lcinvoorder, int lcidmember) {
		return this.setorder.findByInvoorderAndIdmember(lcinvoorder,lcidmember);
	}

	@Override
	public void orderCancel(GetparamDetail lcdata, ResponServ respon) {
		int SuccessStat =0;
		String sql ="UPDATE tbm_h_order SET stat = ?,grouped = ? WHERE idmember = ? AND id = ?";
		Object[] args = new Object[]{5,5,lcdata.getLcidmember(),lcdata.getLcitemid()};
	
				try{
					SuccessStat = jTemplate.update(sql, args);
					respon.setRsltcode("22");
				}catch(Exception e){
					SuccessStat = 0;
					respon.setRsltcode("23");
				}
	}

	@Override
	public void saveOrder(OrderHead lcdata, ResponServ respon) {
		try {
		this.setorder.save(lcdata);
		respon.setRsltcode("05");		
		} catch (Exception e) {
			respon.setRsltcode("19");;
		}
	}

	@Override
	public void GetdataOrder(GetparamDetail lcdata, ResponServ respon) {
		JSONArray lsResult = new JSONArray();
		JSONObject lcResult = new JSONObject();	
		try {
			this.getProper();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		String lctype = "grouped = "+lcdata.getLcstat();
		
		// TODO Auto-generated catch block
//		String lctype = "stat < 3";
//		if(lcdata.getLcstat() == 1){
//			lctype = "stat >= 3 && <=4";			
//		}
		
		
		String sql ="SELECT * FROM tbm_h_order WHERE idmember = ? AND "+lctype+" AND stat < 5 ORDER BY tglord LIMIT ?";
		Object[] args = new Object[]{lcdata.getLcidmember(),5};
						try{
				List<OrderHead> checklog = jTemplate.query(sql, args, new RowMapper<OrderHead>() {
//					@Override
					public OrderHead mapRow(ResultSet result, int rowNum) throws SQLException {
//						System.out.println("data from dbs :"+result.getString("invoorder"));
						OrderHead objList = new OrderHead();		
						objList.setInvoorder(result.getString("invoorder"));
						objList.setId(result.getLong("id"));
						objList.setTglexp(result.getDate("tglexp"));
						objList.setTglord(result.getDate("tglord"));
						objList.setOrdtotal(result.getFloat("ordtotal"));
						objList.setStat(result.getInt("stat"));
						return objList;
					}
		        });	
				
				
//				String lcstat = prop.getProperty("message0");
//				System.out.println(lcstat);
				
				for (OrderHead aList : checklog) {	
					String lcstat = prop.getProperty("message"+aList.getStat());
					lcResult = new JSONObject();
					lcResult.put("lcid", aList.getId());
					lcResult.put("lcinvoorder", aList.getInvoorder());
					lcResult.put("lcstat", lcstat);
					lcResult.put("lcstatcd", aList.getStat());
					lcResult.put("lcordtotal", aList.getOrdtotal());
					lcResult.put("lctglord", aList.getTglord());
					lcResult.put("lctglexp", aList.getTglexp());	
					lsResult.add(lcResult);
		        }
				
				
//				System.out.println("List Of DATA :"+lsResult);
				JSONObject lcData = new JSONObject();

				lcData.put("item", lsResult);
				respon.setRsltdata(lcData);
				respon.setRsltcode("25");
			}catch(Exception e){
				respon.setRsltcode("24");
			}
		
	}

	
	
//	@Transactional
//	@Override
	
//	public void saveOrder(OrderHead lcdata,ResponServ respon) {
//		try {
//			this.setorder.save(lcdata);
//			respon.setRsltcode("05");		
//		} catch (Exception e) {
//			respon.setRsltcode("19");;
//		}
//	}

//	@Override
//	public HeadTrans findByLcinvoorderAndLcidmember(String lcinvoorder, Long lcidmember) {
//		return this.setorder.findByLcinvoorderAndLcidmember(lcinvoorder,lcidmember);
//	}
//
//
//	@Override
//	public void orderCancel(GetparamDetail lcdata,ResponServ respon) {
//		int SuccessStat =0;
//		String sql ="UPDATE tbm_h_order SET stat = ? WHERE idmember = ? AND id = ?";
//		Object[] args = new Object[]{4,lcdata.getLcidmember(),lcdata.getLcitemid()};
//	
//				try{
//					SuccessStat = jTemplate.update(sql, args);
//					respon.setRsltcode("22");
//				}catch(Exception e){
//					SuccessStat = 0;
//					respon.setRsltcode("23");
//				}
////		return respon;
//	}
// 
//	
//	@Override
//	public void GetdataOrder(GetparamDetail lcdata, ResponServ respon) {
//		JSONArray lsResult = new JSONArray();
//		JSONObject lcResult = new JSONObject();	
//		try {
//			this.getProper();
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		String sql ="SELECT * FROM tbm_h_order WHERE idmember = ? AND stat = ? ORDER BY tglord LIMIT ?";
//		Object[] args = new Object[]{lcdata.getLcidmember(),lcdata.getLcstat(),5};
//						try{
//				List<HeadTrans> checklog = jTemplate.query(sql, args, new RowMapper<HeadTrans>() {
////					@Override
//					public HeadTrans mapRow(ResultSet result, int rowNum) throws SQLException {
////						System.out.println("data from dbs :"+result.getString("invoorder"));
//						HeadTrans objList = new HeadTrans();		
//						objList.setLcinvoorder(result.getString("invoorder"));
//						objList.setLcid(result.getLong("id"));
//						objList.setLctglexp(result.getDate("tglexp"));
//						objList.setLctglord(result.getDate("tglord"));
//						objList.setLcordtotal(result.getFloat("ordtotal"));
//						objList.setLcstat(result.getInt("stat"));
//						return objList;
//					}
//		        });	
//				
//				
////				String lcstat = prop.getProperty("message0");
////				System.out.println(lcstat);
//				
//				for (HeadTrans aList : checklog) {	
//					String lcstat = prop.getProperty("message"+lcdata.getLcstat());
//					lcResult = new JSONObject();
//					lcResult.put("lcid", aList.getLcid());
//					lcResult.put("lcinvoorder", aList.getLcinvoorder());
//					lcResult.put("lcstat", lcstat);
//					lcResult.put("lcstatcd", aList.getLcstat());
//					lcResult.put("lcordtotal", aList.getLcordtotal());
//					lcResult.put("lctglord", aList.getLctglord());
//					lcResult.put("lctglexp", aList.getLctglexp());	
//					lsResult.add(lcResult);
//		        }
//				
//				
////				System.out.println("List Of DATA :"+lsResult);
//				JSONObject lcData = new JSONObject();
//
//				lcData.put("item", lsResult);
//				respon.setRsltdata(lcData);
//				respon.setRsltcode("25");
//			}catch(Exception e){
//				respon.setRsltcode("24");
//			}
////			
//	}
//
//	@Override
//	public OrderHead findByLcinvoorderAndLcidmember(String lcinvoorder, Long lcidmember) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public void saveOrder(OrderHead lcdata, ResponServ respon) {
//		// TODO Auto-generated method stub
//		
//	}
}
