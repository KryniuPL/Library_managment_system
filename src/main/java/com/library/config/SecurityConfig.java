package com.library.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

public class SecurityConfig  extends WebSecurityConfigurerAdapter {


   public static final String DEF_USERS_BY_USERNAME_QUERY="select username,password,true"+"from user where username=?";

   @Autowired
   DataSource dataSource;

   protected void configure(HttpSecurity httpSecurity) throws Exception
   {
      httpSecurity.authorizeRequests()
              .anyRequest().authenticated()
              .and()
              .formLogin()
              .loginPage("/login")
               .permitAll();
   }
   @Override
   protected void configure(AuthenticationManagerBuilder authorization) throws Exception
   {
      authorization.inMemoryAuthentication()
              .withUser("user").password("password").roles("USER").and()
              .withUser("admin").password("password").roles("USER","ADMIN");
      authorization.jdbcAuthentication()
                   .dataSource(dataSource)
                   .usersByUsernameQuery(DEF_USERS_BY_USERNAME_QUERY);
   }


}
