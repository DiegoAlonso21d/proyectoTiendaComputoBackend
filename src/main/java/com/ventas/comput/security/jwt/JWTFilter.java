package com.ventas.comput.security.jwt;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ventas.comput.security.CustomUserDetailsService;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JWTFilter  extends OncePerRequestFilter{

	
	@Autowired
	private JWTUtil jwtUtil;
	
	@Autowired
	private CustomUserDetailsService customerUserDetailsService;
	
	
	Claims claims = null;
	
	private String username= null;
	
	
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

			
		if( request.getServletPath().matches("/usuarios/login|/usuarios/forgotPassword|/usuarios/register|/productos/get")) {
			filterChain.doFilter(request, response);
		}else
		{
			String authorizacionHeader = request.getHeader("Authorization");
			
			String token =  null;
			
			if(authorizacionHeader !=null && authorizacionHeader.startsWith("Bearer")) {
				token = authorizacionHeader.substring(7);
				username = jwtUtil.extractUserName(token);
				
				claims = jwtUtil.extactAllClaims(token);
				
				
				
				
			}
			
			
			
			if(username !=null && SecurityContextHolder.getContext().getAuthentication() == null) {
				
				UserDetails userDetails = customerUserDetailsService.loadUserByUsername(username);
				
				if(jwtUtil.validateToken(token, userDetails)) {
					
					UsernamePasswordAuthenticationToken usernamePasswordAutheenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
					
					
					new WebAuthenticationDetailsSource().buildDetails(request);
					
					 
					SecurityContextHolder.getContext().setAuthentication(usernamePasswordAutheenticationToken);
					
				}
				
				
			
				
				
			}
			
			
			
			
			filterChain.doFilter(request, response);
			
			
			
		}
		
		
		
	}

	
	
	
	
	
	
	
	
	
	
	public Boolean isAdmin() {
		return "administrador".equalsIgnoreCase((String) claims.get("role"));
	}
	
	
	
	
	
	public Boolean isUsuario() {
		return "usuario".equalsIgnoreCase((String) claims.get("role"));
	}
	
	
	public String getCurrentUser() {
		return username;
	}
	
	
	
	
	
	
	
	
	
	
	
}
