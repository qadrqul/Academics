package com.example.academics.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(new String[]{"/createpost","/savepost"}).authenticated()
                .antMatchers("/admin").hasAuthority("ROLE_ADMIN") // .hasRole(ADMIN) // hasRole will automatically add prefix ROLE_
                .anyRequest().permitAll()
                .and()
                .formLogin()
                /*.loginPage("/mylogin") // should set up page on controller
                .usernameParameter("username") // on html <input name="username" ...
                .passwordParameter("password") // on html <input name="password" ...
                .successForwardUrl("/createpost")*/ // url to go on successful login
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .permitAll()
                .and()

        ;
    }

}
