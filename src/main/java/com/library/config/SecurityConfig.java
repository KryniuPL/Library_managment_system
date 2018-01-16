package com.library.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import javax.sql.DataSource;


public class SecurityConfig  extends WebSecurityConfigurerAdapter {


   public static final String DEF_USERS_BY_USERNAME_QUERY="select username,password,true"+"from user where username=?";
   public static final String DEF_AUTHORITIES_BY_USERNAME_QUERY="select User.username, Role.name from User,Role,User_Role where User.userID=User_Role.user_userID and User_Role.roles_roleID=Role.roleID and username= ?";

   @Autowired
   DataSource dataSource;



   protected void configure(HttpSecurity httpSecurity) throws Exception
   {
      httpSecurity
              .csrf().disable()
              .authorizeRequests()
              .antMatchers("/login").permitAll()
              .antMatchers("/*")
              .hasAnyRole("USER","ADMIN")
              .anyRequest().hasAnyRole("USER","ADMIN")
              .and()
              .formLogin().loginPage("/login")
              .defaultSuccessUrl("/home",true)
              .usernameParameter("username")
              .passwordParameter("password")
              .and()
              .rememberMe().tokenValiditySeconds(2419200).key("iamKey")
              .and()
              .logout().logoutSuccessUrl("/login");


   }
   @Override
   protected void configure(AuthenticationManagerBuilder authorization) throws Exception
   {
      authorization.inMemoryAuthentication()
              .withUser("user").password("password").roles("USER").and()
              .withUser("admin").password("password").roles("USER","ADMIN");
      authorization.jdbcAuthentication()
              .dataSource(dataSource)
              .usersByUsernameQuery(DEF_USERS_BY_USERNAME_QUERY)
              .authoritiesByUsernameQuery(DEF_AUTHORITIES_BY_USERNAME_QUERY)
              .passwordEncoder(new BCryptPasswordEncoder());
   }


}