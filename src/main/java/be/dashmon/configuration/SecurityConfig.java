//package be.dashmon.configuration;
//
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.context.annotation.Configuration;
////import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
////import org.springframework.security.config.annotation.web.builders.HttpSecurity;
////import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
////import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
////import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
////import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
////import org.springframework.security.core.GrantedAuthority;
////import org.springframework.security.core.authority.SimpleGrantedAuthority;
////import org.springframework.security.core.userdetails.User;
////import org.springframework.security.core.userdetails.UserDetails;
////import org.springframework.security.core.userdetails.UserDetailsService;
////import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.provisioning.JdbcUserDetailsManager;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
////import java.util.ArrayList;
////import java.util.List;
//
////import javax.activation.DataSource;
//import javax.sql.DataSource;
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter{
//	
////	 @Autowired
////	    DatabaseConfig databaseConfig;
//	 
//	@Autowired
//    private DataSource dataSource;
//	 
////	 @Autowired
////	    @Qualifier("customUserDetailsService")
////	    UserDetailsService userDetailsService;
//
//
//	 @Override
//	    protected void configure(HttpSecurity http)throws Exception{
////		  http.csrf().disable() //disable cross site request forgery (csrf) protection
//		 http.authorizeRequests()
////	                .antMatchers("/").permitAll() //izinkan semua
//	                .antMatchers("/merchant-report").permitAll() //izinkan semua
//	                .antMatchers("/css/**").permitAll() //izinkan semua
//	                .antMatchers("/js/**").permitAll() //izinkan semua
//	                .antMatchers("/fonts/**").permitAll() //izinkan semua
//	                .antMatchers("/login").permitAll() //izinkan semua (termasuk default spring-security login page
//	                .anyRequest().authenticated() //selain yang di atas harus authenticated
////	                .anyRequest().access("hasRole('ROLE_USER') or hasRole('ROLE_USER')")
//	                .and()
//	                .formLogin() //login config
//	                .loginPage("/login")
//	                .and()
//	                .logout() //logout config
//	                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//	                .logoutSuccessUrl("/")
//	                .and()
//	                .exceptionHandling().accessDeniedPage("/403");
////		  http.addFilterAfter(new CsrfTokenResponseHeaderBindingFilter(), CsrfFilter.class);
//	    }
//	 
//	 @Autowired
//		public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
//		 JdbcUserDetailsManager userDetailsService = new JdbcUserDetailsManager();
//		 userDetailsService.setDataSource(dataSource);
//		 org.springframework.security.crypto.password.PasswordEncoder encoder = new BCryptPasswordEncoder();
//		 auth.userDetailsService(userDetailsService).passwordEncoder(encoder);
//
//		}
//
//	 
////	 @Autowired
////	    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
////		 	JdbcUserDetailsManager userDetailsService = new JdbcUserDetailsManager();
////		 	 userDetailsService.setDataSource(dataSource);
////		 	 auth.userDetailsService(userDetailsService);
////	    }	 
//	 
//	 
//
////	    @Autowired
////	    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
////	        auth.inMemoryAuthentication().withUser("adi").password("adi123").roles("USER");
////	        auth.inMemoryAuthentication().withUser("administrator").password("admin123").roles("ADMIN");
////	    }
//	
////	@Autowired
////    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
////
////        JdbcUserDetailsManager userDetailsService = new JdbcUserDetailsManager();
////        userDetailsService.setDataSource(databaseConfig.getDataSource());
////        org.springframework.security.crypto.password.PasswordEncoder encoder = new BCryptPasswordEncoder();
////        auth.userDetailsService(userDetailsService).passwordEncoder(encoder);
////        auth.jdbcAuthentication().dataSource(databaseConfig.getDataSource());
////        
////    }
//
//}
