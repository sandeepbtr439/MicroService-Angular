package com.microservice.productservice.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.parser.Authorization;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;


import java.io.IOException;


public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private static final Logger log = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
    @Autowired
    private UserDetailService userDetailService;
    @Autowired
    private JwtHelper jwtHelper

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //Authorization = Bearer djnjenfe -- THIS IS THE FORMAT OF TOKEN , IT STARTS WITH Bearer
        String requestHeader = request.getHeader("Authorization");
        log.info("Header: {}", requestHeader);
        String userName = null;
        String token = null;
        if (requestHeader != null && requestHeader.startsWith("Bearer")) {    //Since token starts with Bearerfjdngjfngjf
            //if its null, it means token is not sent in "request header"
            token = requestHeader.substring(7);
            try {
                userName = this.jwtHelper.getUsernameFromToken(token);
            } catch (IllegalArgumentException e) {
                log.info("Illegal argument when fetching a user name!!");
                e.printStackTrace();
            } catch (ExpiredJwtException ee) {
                log.info("The token is expired");
            } catch (MalformedJwtException me) {
                log.info("mal formed token in place !!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            log.info("Invalid header value");
        }

        if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) { //check if
            // authentica is null or if it has been already authenticated and username is not null {
            UserDetails userDetails = this.userDetailService.loadByUserName(userName);
            Boolean validateToken = this.jwtHelper.validateToken(token, userDetails);
            if (validateToken) {
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                log.info("validation fails !!");

            }
        }
        filterChain.doFilter(request, response);
    }
}
