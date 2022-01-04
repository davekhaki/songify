package com.songify.api.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.songify.api.config.AuthenticationConstants;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {
    public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }
    @Override protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader(AuthenticationConstants.HEADER_STRING);
        if (header == null || !header.startsWith(AuthenticationConstants.TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }
        UsernamePasswordAuthenticationToken authentication = getAuthentication(request);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }
    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(AuthenticationConstants.HEADER_STRING);
        if (token != null) {
            // parse the token.
            String user = JWT.require(Algorithm.HMAC512(AuthenticationConstants.SECRET.getBytes()))
                    .build()
                    .verify(token.replace(AuthenticationConstants.TOKEN_PREFIX, ""))
                    .getSubject();
            String role = JWT.require(Algorithm.HMAC512(AuthenticationConstants.SECRET.getBytes()))
                    .build()
                    .verify(token.replace(AuthenticationConstants.TOKEN_PREFIX, ""))
                    .getClaim("role").asString();
            if (user != null) {
                List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
                list.add(new SimpleGrantedAuthority(role));
                return new UsernamePasswordAuthenticationToken(user, null,/* new ArrayList<> */ list);
            }
            return null;
        }
        return null;
    }
}
