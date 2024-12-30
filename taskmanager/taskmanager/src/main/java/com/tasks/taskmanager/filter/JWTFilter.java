package com.tasks.taskmanager.filter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.tasks.taskmanager.services.JWTService;
import com.tasks.taskmanager.services.UserDetailsSer;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JWTFilter extends OncePerRequestFilter {

	@Autowired
	public JWTService jwtservice;

	@Autowired
	private ApplicationContext context;

	//
	// when all the requests (other than login and register) hit with the token we
	// will validate the token by extracting the username from token and
//	validate the token and give authentication and will filter it here.
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String authHeader = request.getHeader("Authorization");
		String token = null, username = null;

		if (authHeader != null && authHeader.startsWith("Bearer ")) {
			token = authHeader.substring(7);
			username = jwtservice.extractUserName(token);
			

		}

		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = context.getBean(UserDetailsSer.class).loadUserByUsername(username);

			if (jwtservice.validateToken(token, userDetails)) {

				UsernamePasswordAuthenticationToken authtoken = new UsernamePasswordAuthenticationToken(userDetails,
						null, userDetails.getAuthorities());

				authtoken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authtoken);
			}
		}

		filterChain.doFilter(request, response);
	}

}
