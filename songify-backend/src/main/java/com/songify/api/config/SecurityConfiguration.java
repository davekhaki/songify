package com.songify.api.config;

import com.songify.api.filter.JWTAuthenticationFilter;
import com.songify.api.filter.JWTAuthorizationFilter;
import com.songify.api.model.Role;
import com.songify.api.service.impl.AuthenticationUserDetailServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AuthenticationUserDetailServiceImpl authenticationUserDetailServiceImpl;

    @Override protected void configure (HttpSecurity http) throws Exception{
        http.headers().frameOptions().sameOrigin();
        http.csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.POST, AuthenticationConstants.SIGN_UP_URL).permitAll()

//                .antMatchers("/**").permitAll() //allows all requests for the api

                .antMatchers("/v1/api/auth/**").permitAll() //allows login request
                .antMatchers("/v1/api/spotify/**").permitAll()// allows spotify auth
                .antMatchers("/ws/**").permitAll() //allows all websocket requests
                .antMatchers("/login").permitAll() //allows get bearer token request
                .antMatchers("/actuator/**").permitAll() //actuator (monitoring) requests.

                .antMatchers("/v1/api/users/**").hasRole("ADMIN")

                .anyRequest().authenticated()
                .and()
                .addFilter(new JWTAuthenticationFilter(authenticationManager()))
                .addFilter(new JWTAuthorizationFilter(authenticationManager()))

                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(authenticationUserDetailServiceImpl).passwordEncoder(bCryptPasswordEncoder);
    }

}
