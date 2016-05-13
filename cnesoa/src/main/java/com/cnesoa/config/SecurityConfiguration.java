package com.cnesoa.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by Maxime on 22/04/2016.
 */

@Configuration
@ComponentScan("com.cnesoa")
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationListener authenticationListener;

    @Bean
    AuthenticationListener authenticationListener(){
        return new AuthenticationListener();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected  void configure(HttpSecurity httpSecurity) throws Exception{
        httpSecurity
                .authorizeRequests()
                .antMatchers("/console/**").permitAll()
                .antMatchers("/", "/images/**", "/css/**", "/js/**", "/webjars/**", "/logerror", "/customerror",
                        "/tmpls/**", "/jquery.min.map",
                        "/event.json.php").permitAll();


        httpSecurity.formLogin()
                .loginPage("/login").failureHandler(authenticationListener)
                .usernameParameter("username").permitAll()
                .and()
                .logout().logoutUrl("/logout").deleteCookies("remember-me").logoutSuccessUrl("/").permitAll().and()
                .exceptionHandling().accessDeniedPage("/403").and()
                .rememberMe();
        httpSecurity.authorizeRequests().anyRequest().fullyAuthenticated();
        httpSecurity.csrf().disable();
        httpSecurity.headers().frameOptions().disable();

    }


}
