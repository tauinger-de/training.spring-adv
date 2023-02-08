package com.example.pizza;

import com.example.pizza.auth.MyUserDetailsService;
import com.example.pizza.auth.WhoAmIController;
import com.example.pizza.order.OrderRestController;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.springframework.http.HttpMethod.GET;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http.authorizeRequests()
                .antMatchers(GET, OrderRestController.GREETING_ENDPOINT).hasAuthority("gast")
                .antMatchers(GET, OrderRestController.GREETING_ENDPOINT).hasRole("USER")
                .antMatchers(GET, WhoAmIController.ME_ENDPOINT).authenticated()
                .antMatchers("/**").denyAll()
            .and().httpBasic()
            .and().formLogin()
            .and().csrf().disable(); // only for testing
        // @formatter:on
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new MyUserDetailsService();
    }

    @Bean
    PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
