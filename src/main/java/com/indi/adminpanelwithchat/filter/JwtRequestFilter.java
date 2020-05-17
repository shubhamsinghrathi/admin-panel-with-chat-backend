package com.indi.adminpanelwithchat.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.indi.adminpanelwithchat.service.AdminDetailService;
import com.indi.adminpanelwithchat.util.JwtUtils;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

	@Autowired
	private AdminDetailService adminDetailService;
	
	@Autowired
	private JwtUtils jwtTokenUtil;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		final String authorizationHeader = request.getHeader("Authorization");
		
		String username = null;
		String jwt = null;
		
		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
			jwt = authorizationHeader.substring(7);
			try {
				username = jwtTokenUtil.extractUsername(jwt);	
			} catch (Exception e) {
				username = null;
			}
		}
		
		/**
		 * this entire security context creation thing would have been done by spring security
		 * but we have to customize it because we want to authorize in case of valid jwt
		 */
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = this.adminDetailService.loadUserByUsername(username);
			
			if (jwtTokenUtil.validateToken(jwt, userDetails)) {
				UsernamePasswordAuthenticationToken upat = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				upat.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(upat);
			}
		}
		
		// making the chain to continue
		filterChain.doFilter(request, response);
	}
	
}
