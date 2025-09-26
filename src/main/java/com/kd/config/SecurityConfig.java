package com.kd.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.kd.auth.JwtAuthFilter;
import com.kd.auth.UserServiceImple;


@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

	private final UserServiceImple userServiceImple;
	private final JwtAuthFilter jwtAuthFilter;

	
	public SecurityConfig(UserServiceImple userServiceImple, JwtAuthFilter jwtAuthFilter) {
		this.userServiceImple = userServiceImple;
		this.jwtAuthFilter = jwtAuthFilter;
	}

	@Value("${app.cors.allowed-origins:http://localhost:5173}")
	private String allowedOrigins;
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
		httpSecurity
		  .cors().and()
		  .csrf().disable()
		  .sessionManagement()
		  .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		  .and()
		  .authorizeHttpRequests(auth-> auth
				  .requestMatchers("/api/auth/**").permitAll()
				  .requestMatchers("/swagger-ui/**","/v3/api-docs/**").permitAll()
				  .requestMatchers("/api/admin/**").hasRole("ADMIN")
	              .requestMatchers("/api/**").authenticated()
	              ).addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
		
		return httpSecurity.build();
	}
	
	@Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // CORS config
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of(allowedOrigins));
        configuration.setAllowedMethods(List.of("GET","POST","PUT","DELETE","OPTIONS"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
