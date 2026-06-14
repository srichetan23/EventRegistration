package com.bmt.MyStore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean 
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		
		return http
				.authorizeHttpRequests(auth-> auth
						.requestMatchers("/").permitAll()
						.requestMatchers("/contact").permitAll()
						.requestMatchers("/store/**").permitAll()
						.requestMatchers("/register").permitAll()
						.requestMatchers("/login").permitAll()
						.requestMatchers("/logout").permitAll()
						.requestMatchers("/adminLogin").permitAll()
						.requestMatchers("/h2-console/**").permitAll()
						.requestMatchers("/css/**", "/js/**", "/images/**", "/webjars/**").permitAll()
						.anyRequest().authenticated()
	)
	.formLogin(form->form
			.loginPage("/login") // Tell Spring to use our login.html
		    .loginProcessingUrl("/login") // Spring handles POST here
		    .defaultSuccessUrl("/eventRegPage", true) // After successful login
		    .permitAll()
	)
	.logout(config -> config.logoutSuccessUrl("/"))
	// Allow the H2 console (uses frames + its own POSTs) to work in dev.
	.csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**"))
	.headers(headers -> headers.frameOptions(frame -> frame.sameOrigin()))
	.build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		
		return new BCryptPasswordEncoder();
	}
}
