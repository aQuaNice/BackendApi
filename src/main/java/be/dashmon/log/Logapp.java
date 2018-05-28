package be.dashmon.log;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.log4j.Appender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;
import org.springframework.stereotype.Component;
@Component
public class Logapp {
	
	Logger logger = Logger.getLogger("ProLog");
    Appender fh = null;
    SimpleDateFormat format = new SimpleDateFormat("dd-MM-YYYY");
    SimpleDateFormat formatDt = new SimpleDateFormat("dd-MM-YYYY HH:mm:ss");
    
    
    public void checkDir(){
    	
    	File f = new File("/api/log");
		
        if (f.exists() && f.isDirectory()) {
        System.out.println("Exists");
        //if the file is present then it will show the msg  
        }
        else{
        System.out.println("NOT Exists");
        
        f.mkdirs();
        //if the file is Not present then it will show the msg      
        }
    }
    
    public void setLogReg(String LogNm, String lcmethod,int lcResponcode,String Reqparam, String lcCode, String Resparam, String lclogtype,String lcLogmsg,int lclogdesc){
   	 logger.removeAllAppenders();	
   	checkDir();
		 try {
	        	 fh = new FileAppender(new SimpleLayout(),"/api/log/"+LogNm+"/"+LogNm+"_"+ format.format(Calendar.getInstance().getTime()) + ".log");
	        	 logger.addAppender(fh);          
	             fh.setLayout(new SimpleLayout());
	             
	            if( lclogdesc < 1){
		            logger.error("==="+formatDt.format(Calendar.getInstance().getTime())+"=======================Begin : "+LogNm+"==========Error==========");
		            logger.error("Log code : "+lclogtype);
		            logger.error("Log Msg : "+lcLogmsg);
		            logger.error("ReqMethod : "+lcmethod);
		            logger.error("Response : "+lcResponcode);
		            logger.error("ReqParam :"+Reqparam);
		            logger.error("ResParam : "+Resparam);
		            logger.error("===========================End : "+LogNm+"============================"+formatDt.format(Calendar.getInstance().getTime())+"===");
	             }else{
		            logger.info("==="+formatDt.format(Calendar.getInstance().getTime())+"=======================Begin : "+LogNm+"===========Success============");
		            logger.info("Log code : "+lclogtype);
		            logger.info("Log Msg : "+lcLogmsg);
		            logger.info("ReqMethod : "+lcmethod);
		            logger.info("Response : "+lcResponcode);
		            logger.info("ReqParam :"+Reqparam);
		            logger.info("ResParam : "+Resparam);
		            logger.info("===========================End : "+LogNm+"============================"+formatDt.format(Calendar.getInstance().getTime())+"===");
	             }
	            
	        } catch (SecurityException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
   	 
		 
		 
    }
    

    public void exeptionerror(String LogNm, String lcmethod,int lcResponcode,String Reqparam, String lcCode, String Resparam, String lclogtype,String lcLogmsg,int lclogdesc){
      	 logger.removeAllAppenders();	
   		 try {
   			checkDir();
   	        	 fh = new FileAppender(new SimpleLayout(),"/api/log/"+LogNm+"/"+LogNm+"_"+ format.format(Calendar.getInstance().getTime()) + ".log");
   	        	 logger.addAppender(fh);          
   	             fh.setLayout(new SimpleLayout());
   	             
   	            if( lclogdesc < 1){
   		            logger.error("==="+formatDt.format(Calendar.getInstance().getTime())+"=======================Begin : "+LogNm+"==========Error==========");
   		            logger.error("Log code : "+lclogtype);
   		            logger.error("Log Msg : "+lcLogmsg);
   		            logger.error("ReqMethod : "+lcmethod);
   		            logger.error("Response : "+lcResponcode);
   		            logger.error("ReqParam :"+Reqparam);
   		            logger.error("ResParam : "+Resparam);
   		            logger.error("===========================End : "+LogNm+"============================"+formatDt.format(Calendar.getInstance().getTime())+"===");
   	             }else{
   		            logger.info("==="+formatDt.format(Calendar.getInstance().getTime())+"=======================Begin : "+LogNm+"===========Success============");
   		            logger.info("Log code : "+lclogtype);
   		            logger.info("Log Msg : "+lcLogmsg);
   		            logger.info("ReqMethod : "+lcmethod);
   		            logger.info("Response : "+lcResponcode);
   		            logger.info("ReqParam :"+Reqparam);
   		            logger.info("ResParam : "+Resparam);
   		            logger.info("===========================End : "+LogNm+"============================"+formatDt.format(Calendar.getInstance().getTime())+"===");
   	             }
   	            
   	        } catch (SecurityException e) {
   	            e.printStackTrace();
   	        } catch (IOException e) {
   	            e.printStackTrace();
   	        }
      	 
   		 
   		 
       }

    
	 public void GetLogClient(String LogNm, String Reqparam){
    	 logger.removeAllAppenders();	
		 try {
			 checkDir();
	        	 fh = new FileAppender(new SimpleLayout(),"/api/log/"+LogNm+"/"+LogNm+"_"+ format.format(Calendar.getInstance().getTime()) + ".log");
	        	 logger.addAppender(fh);          
	             fh.setLayout(new SimpleLayout());
//	             logger.info(format);
		            logger.info("==="+formatDt.format(Calendar.getInstance().getTime())+"=======================Begin : "+LogNm+"===========Success============");
		            logger.info("Device Type : test");
		            logger.info("Log Data :"+Reqparam);
		            logger.info("Client Data : shandy");
		            logger.info("Device size : ");
		            logger.info("===========================End : "+LogNm+"============================"+formatDt.format(Calendar.getInstance().getTime())+"===");
//	            logger.info(GetString);
	        } catch (SecurityException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
    	 
     }
}
