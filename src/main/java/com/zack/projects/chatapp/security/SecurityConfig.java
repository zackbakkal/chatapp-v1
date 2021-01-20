package com.zack.projects.chatapp.security;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.zack.projects.chatapp.service.ApplicationUserService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private ApplicationUserService applicationUserService;
	
	@Autowired
	private final PasswordEncoder passwordEncoder;
	

	public SecurityConfig(ApplicationUserService applicationUserService, PasswordEncoder passwordEncoder) {
		super();
		this.applicationUserService = applicationUserService;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http
			.csrf().disable()
			.authorizeRequests()
			.antMatchers("/", "index", " /css/*", "/js/*", "/register", "/adduser").permitAll()
			.anyRequest()
			.authenticated()
			.and()
			.formLogin()
				.loginPage("/login")
				.permitAll()
				.defaultSuccessUrl("/chatapp", true)
				.passwordParameter("password")
				.usernameParameter("username")
			.and()
			.rememberMe().tokenValiditySeconds((int)TimeUnit.DAYS.toSeconds(21))
				.key("usesecuredkey")
				.rememberMeParameter("remember-me")
			.and()
			.logout()
				.logoutUrl("/logout")
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
				.clearAuthentication(true)
				.invalidateHttpSession(true)
				.deleteCookies("JSESSIONID", "remember-me")
				.logoutSuccessUrl("/login")
			;
	}

//	@Override
//	@Bean
//	protected UserDetailsService userDetailsService() {
//
//		List<UserDetails> userList = new ArrayList<>();
//
//		this.userRepository
//			.findAll()
//			.stream()
//			.forEach(user -> userList.add(
//					User.builder()
//					.username(user.getUserName())
//					.password(passwordEncoder.encode(user.getPassword()))
//					.authorities(USER.getGrantedAuthorities())
//					.build()));
//
//		this.adminRepository
//			.findAll()
//			.stream()
//			.forEach(admin -> userList.add(
//					User.builder()
//					.username(admin.getAdminUserName())
//					.password(passwordEncoder.encode(admin.getPassword()))
//					.authorities(ADMIN.getGrantedAuthorities())
//					.build()));
//
//		return new InMemoryUserDetailsManager(userList);
//
//	}
	
	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider daoAuthProvider = new DaoAuthenticationProvider();
		
		daoAuthProvider.setPasswordEncoder(passwordEncoder);
		daoAuthProvider.setUserDetailsService(applicationUserService);
		
		return daoAuthProvider;
		
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(daoAuthenticationProvider());
	}
}
