package com.example.pizza;

import com.example.pizza.auth.MyUserDetailsService;
import com.example.pizza.auth.WhoAmIController;
import com.example.pizza.order.OrderRestController;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import static org.springframework.http.HttpMethod.GET;

@EnableWebSecurity
public class ResourceServerSecurityConfig extends WebSecurityConfigurerAdapter {

    // generated via https://dinochiesa.github.io/jwt/
    private final String symmetricKey = "Undoubtedly-Daring-Orbit-6888-40";

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http.authorizeRequests()
                .antMatchers(GET, OrderRestController.GREETING_ENDPOINT).hasAuthority("gast")
                .antMatchers(GET, OrderRestController.GREETING_ENDPOINT).hasRole("USER")
                .antMatchers(GET, WhoAmIController.ME_ENDPOINT).authenticated()
                .antMatchers("/**").denyAll()
            .and().oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
            .csrf().disable(); // only for testing
        // @formatter:on
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new MyUserDetailsService();
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        var symmetricKeyBytes = symmetricKey.getBytes();
        SecretKey key = new SecretKeySpec(symmetricKeyBytes, 0, symmetricKeyBytes.length, "AES");
        return NimbusJwtDecoder.withSecretKey(key).build();
    }
}
