package com.rest.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
  private static final String ROLE_USER = "USER";
  private static final String ROLE_ADMIN = "ADMIN";
  private static final String ROLE_HR = "HR";

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    auth.inMemoryAuthentication().withUser("user").password(encoder.encode("password")).roles(ROLE_USER);
    auth.inMemoryAuthentication().withUser("admin").password(encoder.encode("password")).roles(ROLE_ADMIN);
    auth.inMemoryAuthentication().withUser("hr").password(encoder.encode("password")).roles(ROLE_HR);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
//    http.csrf().disable();

    http.authorizeRequests()
        .antMatchers("/allUsers")
        .hasAnyRole(ROLE_USER, ROLE_HR, ROLE_ADMIN);

    http.authorizeRequests()
        .antMatchers("/hrUser")
        .hasRole(ROLE_HR);

    http.authorizeRequests()
        .antMatchers("/adminUser")
        .hasRole(ROLE_ADMIN);

    http.httpBasic().authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED));

//    http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
  }
}
