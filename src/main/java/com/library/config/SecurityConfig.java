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

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig  extends WebSecurityConfigurerAdapter {


   public static final String DEF_USERS_BY_USERNAME_QUERY="select username,password,true"+"from user where username=?";
   public static final String DEF_AUTHORITIES_BY_USERNAME_QUERY="select User.username, Role.name from User,Role,User_Role where User.userID=User_Role.user_userID and User_Role.roles_roleID=Role.roleID and username= ?";

   @Autowired
   DataSource dataSource;

   @Autowired
   private BCryptPasswordEncoder bCryptPasswordEncoder;


   protected void configure(HttpSecurity httpSecurity) throws Exception
   {

      httpSecurity.
              authorizeRequests()
              .antMatchers("/").permitAll()
              .antMatchers("/login").permitAll()
              .antMatchers("/register").permitAll()
              .antMatchers("/addbook").permitAll()
              .antMatchers("/allbooks").permitAll()
              .antMatchers("/admin/**").hasAuthority("ADMIN").anyRequest()
              .authenticated().and().csrf().disable().formLogin()
              .loginPage("/login").failureUrl("/login?error=true")
              .defaultSuccessUrl("/home")
              .usernameParameter("username")
              .passwordParameter("password")
              .and().logout()
              .logoutSuccessUrl("/").and().exceptionHandling()
              .accessDeniedPage("/access-denied");


   }
   @Override
   protected void configure(AuthenticationManagerBuilder authorization) throws Exception
   {
      authorization.jdbcAuthentication()
              .usersByUsernameQuery(DEF_USERS_BY_USERNAME_QUERY)
              .authoritiesByUsernameQuery(DEF_AUTHORITIES_BY_USERNAME_QUERY)
              .dataSource(dataSource)
              .passwordEncoder(new BCryptPasswordEncoder());
   }


}