//package be.dashmon.configuration;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.annotation.PostConstruct;
//
//import org.apache.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.provisioning.JdbcUserDetailsManager;
//import org.springframework.stereotype.Component;

//import be.dashmon.domain.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.provisioning.JdbcUserDetailsManager;
//import org.springframework.stereotype.Component;
//import javax.annotation.PostConstruct;
//
//@Component
//public class DataInitializer {
//	 static Logger logger = Logger.getLogger(DataInitializer.class.getName());
//	 
//	 @Autowired
//	    DatabaseConfig databaseConfig;
	 
//	 @PostConstruct  //need to trigger this to fill up first Data
//	 public void initData(){
//	        logger.info("Init Data Invoked");
	        
	        /*harus di taro disini sebab spring-security akan menginisialiasi spring security lebih dahulu, baru kemudian mengeksekusi @PostConstruct untuk pengisian data*/
//	        JdbcUserDetailsManager userDetailsService = new JdbcUserDetailsManager();
//	        userDetailsService.setDataSource(databaseConfig.getDataSource());
//	        org.springframework.security.crypto.password.PasswordEncoder encoder = new BCryptPasswordEncoder();
	        
	        
//	        if(!userDetailsService.userExists("user")) {
//	            List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
//	            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
//	            UserDetails userDetails = new User("user", encoder.encode("password"), authorities);
//	            userDetailsService.createUser(userDetails);
//	        }
	        
//	        if(!userDetailsService.userExists("administrator")){
//	            List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
//	            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
//	            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
//	            UserDetails userDetails = new User("administrator",encoder.encode("admin123"),authorities);
//	            userDetailsService.createUser(userDetails);
//	        }
//	     }
//}
