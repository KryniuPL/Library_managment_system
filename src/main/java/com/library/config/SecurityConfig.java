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
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;



import javax.sql.DataSource;

/**
 * Zarządza bezpieczeństwem aplikacji
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig  extends WebSecurityConfigurerAdapter {

   /**
    * Zapytanie wyszukujące użytkownika o podanym loginie
    */
   public static final String DEF_USERS_BY_USERNAME_QUERY="SELECT User.username,User.password,active from User where User.username=?";
   /**
    * Zapytanie pobierające rolę użytkownika
    */
   public static final String DEF_AUTHORITIES_BY_USERNAME_QUERY="select User.username, Role.name from User,Role,User_Role where User.userID=User_Role.user_userID and User_Role.roles_roleID=Role.roleID and username= ?";

   @Autowired
   DataSource dataSource;

   @Autowired
   private BCryptPasswordEncoder bCryptPasswordEncoder;

   /**
    * Ustawia dostęp do widoków
    *
    * @param httpSecurity
    */
   protected void configure(HttpSecurity httpSecurity) throws Exception
   {
      httpSecurity.
              authorizeRequests()
              .antMatchers("/").permitAll()
              .antMatchers("/login").permitAll()
              .antMatchers("/register").permitAll()
              .antMatchers("/allbooks").permitAll()
              .antMatchers("/web-resources/libstyle.css").permitAll()
              .antMatchers("/css/registration.css").permitAll()
              .antMatchers("/bookborrow").hasAuthority("ADMIN")
              .antMatchers("/addbook").hasAuthority("ADMIN")
              .antMatchers("/home").hasAnyAuthority("ADMIN","USER").anyRequest()
              .authenticated().and().csrf().disable().formLogin()
              .loginPage("/login").failureUrl("/login?error=true")
              .defaultSuccessUrl("/home")
              .usernameParameter("username")
              .passwordParameter("password")
              .and().logout()
              .logoutSuccessUrl("/").and().exceptionHandling()
              .accessDeniedPage("/accessdenied");
   }

   /**
    * Autoryzacja użytkownika
    *
    * @param authorization
    */
   @Override
   protected void configure(AuthenticationManagerBuilder authorization) throws Exception
   {
      authorization.inMemoryAuthentication()
              .withUser("user").password("password").roles("ADMIN");
      authorization.jdbcAuthentication()
              .usersByUsernameQuery(DEF_USERS_BY_USERNAME_QUERY)
              .authoritiesByUsernameQuery(DEF_AUTHORITIES_BY_USERNAME_QUERY)
              .dataSource(dataSource)
              .passwordEncoder(new BCryptPasswordEncoder());
   }
}