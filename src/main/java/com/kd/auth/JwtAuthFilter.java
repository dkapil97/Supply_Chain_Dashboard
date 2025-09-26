package com.kd.auth;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
@Component
public class JwtAuthFilter extends OncePerRequestFilter{

	private final JwtUtil jwtUtil;
	private final UserServiceImple userServiceImple;

	public JwtAuthFilter(JwtUtil jwtUtil, UserServiceImple userServiceImple) {
		this.jwtUtil = jwtUtil;
		this.userServiceImple = userServiceImple;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String header = request.getHeader("Authorization");
		String token=null;
		
		if(StringUtils.hasText(header) && header.startsWith("Bearer ")) {
			token=header.substring(7);
		}
		
		if(token!=null) {
			try {
				 String username = jwtUtil.getUsername(token);
	                var userDetails = userServiceImple.loadUserByUsername(username);
	                
	                var auth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
	                auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
	                SecurityContextHolder.getContext().setAuthentication(auth);
			} catch (Exception e) {
				response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid or expired token");
                return;
			}
		}
		filterChain.doFilter(request, response);
		
	}

}
