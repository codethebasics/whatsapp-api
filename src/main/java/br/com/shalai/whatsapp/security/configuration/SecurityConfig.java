package br.com.shalai.whatsapp.security.configuration;

import lombok.extern.java.Log;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Log
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests(authorize -> {
            authorize.antMatchers(getPublicAntMatchers()).hasAnyRole("ADMIN", "COMMON");
            authorize.antMatchers(getProtectedAntMatchers()).hasAnyRole("ADMIN", "COMMON");
            authorize.antMatchers(getPrivateAntMatchers()).hasRole("ADMIN");
        })
        .authorizeRequests().anyRequest().authenticated();

        http.csrf().disable();
        http.headers().frameOptions().disable();
    }

    public String[] getPublicAntMatchers() {
        return new String[] {
            "/api/v1/*/public/**",
            "/static/**",
            "/h2-console/**"
        };
    }

    public String[] getProtectedAntMatchers() {
        return new String[] {
            "/api/v1/*/protected/**"
        };
    }

    public String[] getPrivateAntMatchers() {
        return new String[] {
            "/api/v1/*/private/**"
        };
    }
}
