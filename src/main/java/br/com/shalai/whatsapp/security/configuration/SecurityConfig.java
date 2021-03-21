package br.com.shalai.whatsapp.security.configuration;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import java.util.Arrays;

@Log
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private Environment environment;

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

    @Override
    public void configure(WebSecurity web) throws Exception {

        boolean isDevProfile = Arrays.stream(this.environment.getActiveProfiles())
            .anyMatch(p -> p.equalsIgnoreCase("dev"));

        if (isDevProfile) {
            web.ignoring()
                .antMatchers("/h2-console/**");
        }
    }

    public String[] getPublicAntMatchers() {
        return new String[] {
            "/api/v1/*/public/**",
            "/static/**"
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
