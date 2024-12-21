package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.authentication.dao.DaoAuthenticationProvider;

import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import com.example.demo.jwt.AuthEntryPointJwt;
import com.example.demo.jwt.AuthTokenFilter;
import com.example.demo.service.CustomUserDetailsService;



@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class  SecurityConfig{
	
	/*
	 * @Autowired DataSource dataSource;
	 */
	
	@Autowired
	private AuthEntryPointJwt unauthorizedHandler;
	
	@Bean
	public AuthTokenFilter authenticationJwtTokenFilter() {
		return new AuthTokenFilter();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
		return builder.getAuthenticationManager();
	}
	
	
	@Bean
    public UrlBasedCorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("http://localhost:3000"); // Allowed origin
        config.addAllowedMethod("*"); // Allow all HTTP methods
        config.addAllowedHeader("*"); // Allow all headers
        config.setAllowCredentials(true); // Allow cookies or authorization headers

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
	
	
	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(request->
					request.requestMatchers("/api/v1/employee/signin").permitAll()
					.requestMatchers("/api/v1/employee/save/**").permitAll()
					.requestMatchers("/api/v1/employee/forgot-password").permitAll()
					.requestMatchers("/api/v1/employee/reset-password").permitAll()
					.anyRequest().authenticated());
		
		//as we are using JWT, session management must be state less
		http.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		
		http.exceptionHandling(exception -> exception.authenticationEntryPoint(unauthorizedHandler));
		
		//this is for H2 console
		http.headers(headers -> headers.frameOptions(frameOptions -> frameOptions.sameOrigin()));
		
		//this is also for H2 console
		http.csrf(csrf -> csrf.disable());
		
		 http.cors(cors -> cors.disable());
		
		http.authenticationProvider(authenticationProvider());
		
		//To add our custom filter before UsernamePasswordAuthenticationFilter, which is in built filter
		http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
		
		//http.httpBasic(Customizer.withDefaults());
		return http.build();
	}

	/*
	 * @Bean public UserDetailsService userDetailsService() { //UserDetails user1 =
	 * User.withUsername("Sumit").password(passwordEncoder().encode("Sumit")).roles(
	 * "USER").build(); //UserDetails user2 =
	 * User.withUsername("Shubham").password(passwordEncoder().encode("Shubham")).
	 * roles("ADMIN").build(); JdbcUserDetailsManager jdbcUserDetailsManager = new
	 * JdbcUserDetailsManager(dataSource);
	 * //jdbcUserDetailsManager.createUser(user1);
	 * //jdbcUserDetailsManager.createUser(user2); return jdbcUserDetailsManager;
	 * //return new InMemoryUserDetailsManager(user1,user2); }
	 */
	
	@Bean
	UserDetailsService userDetails() {
		return new CustomUserDetailsService();
	}
	
	@Bean
	BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetails());
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}
}






