package com.hurontg.mars.config;

import javax.inject.Inject;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.UserIdSource;
import org.springframework.social.security.AuthenticationNameUserIdSource;
import org.springframework.social.security.SpringSocialConfigurer;

import com.hurontg.mars.security.AuthenticationFailureHandlerImpl;
import com.hurontg.mars.security.AuthenticationService;
import com.hurontg.mars.security.AuthenticationSuccessHandlerImpl;
import com.hurontg.mars.security.LogoutSuccessHandlerImpl;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {	         
  
	@Inject
	AuthenticationService authService;
	
	@Inject
	AuthenticationFailureHandlerImpl authFailureHandler;
	
	@Inject
	AuthenticationSuccessHandlerImpl authSuccessHandler;
	
	@Inject
	LogoutSuccessHandlerImpl logoutHandler;
	
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
    	.csrf()
    		.disable()
    	.authorizeRequests()
      	.antMatchers("/k/**", "/commontext/**").permitAll()
      	.antMatchers("/s/xad/**").hasRole("ADMINISTRATOR")
      	.antMatchers("/signup").hasRole("ANONYMOUS")      	
      	.antMatchers("/**").hasRole("USER")
      	.anyRequest().authenticated()
      	.and()
      .formLogin()
      	.loginProcessingUrl("/login")
        .loginPage("/k/loginform")
        .failureHandler(authFailureHandler)
        .successHandler(authSuccessHandler)        
        .and()
       .logout()
       	.invalidateHttpSession(true)
       	.logoutSuccessHandler(logoutHandler)
       	.deleteCookies("JSESSIONID")
       	.and()      
      .exceptionHandling().accessDeniedPage("/k/accessDenied")
      	.and()
			.apply(getSpringSocialConfigurer())
      	.and()
      .sessionManagement()
      	.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
       	.sessionFixation()       
      	.migrateSession()
      	.maximumSessions(1)
       	.expiredUrl("/k/login_sessionexpired");
  }
  
  @Override
  public void configure(WebSecurity web) throws Exception {
       web
       	.ignoring()       
          .antMatchers("/resources/**");
  }
  
  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth
    	.userDetailsService(authService)
    	.passwordEncoder(new BCryptPasswordEncoder())
    		;    	
  }

  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
      return super.authenticationManagerBean();
  }
	
	@Bean
	public UserIdSource userIdSource() {
		return new AuthenticationNameUserIdSource();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

	@Bean
	public TextEncryptor textEncryptor() {
		return Encryptors.noOpText();
	}

	private SpringSocialConfigurer getSpringSocialConfigurer() {
		SpringSocialConfigurer ssc = new SpringSocialConfigurer();
		ssc
			.signupUrl("/k/registration/social")
			.postLoginUrl("/iuj");
		return ssc;
	}
}
