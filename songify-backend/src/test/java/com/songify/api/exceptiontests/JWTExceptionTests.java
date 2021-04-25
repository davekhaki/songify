package com.songify.api.exceptiontests;

import com.songify.api.exceptions.JWTException;
import com.songify.api.filter.JWTAuthenticationFilter;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class JWTExceptionTests {

    private final JWTAuthenticationFilter authenticationFilter = new JWTAuthenticationFilter(authentication -> null);

    @Test
    void testJwtException(){
    }
}
