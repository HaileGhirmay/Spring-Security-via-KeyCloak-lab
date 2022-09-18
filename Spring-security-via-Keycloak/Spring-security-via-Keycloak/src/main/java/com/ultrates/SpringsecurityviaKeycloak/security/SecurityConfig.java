package com.ultrates.SpringsecurityviaKeycloak.security;


import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;  //this is from dependency pom file
import org.keycloak.adapters.springsecurity.KeycloakConfiguration;
import org.keycloak.adapters.springsecurity.authentication.KeycloakAuthenticationProvider;
import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;

@KeycloakConfiguration
@Import(KeycloakSpringBootConfigResolver.class)
@EnableWebSecurity
@EnableGlobalMethodSecurity(jsr250Enabled = true)

public class SecurityConfig extends KeycloakWebSecurityConfigurerAdapter {

    //build the AuthenticationManager  using the, you need the object of the provider,
    //set the grantedAuthoritiesMapper
    @Autowired
    public  void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        KeycloakAuthenticationProvider keycloakAuthenticationProvider =keycloakAuthenticationProvider();
        keycloakAuthenticationProvider.setGrantedAuthoritiesMapper(new SimpleAuthorityMapper());
    }


    //define the session authentication strategy.
    @Override
    protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
        return new RegisterSessionAuthenticationStrategy(new SessionRegistryImpl());
    }


    @Bean
    public SessionRegistry sessionRegistryBuilder(){
        return  new SessionRegistryImpl();
    }

    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    //this is the second overriden method  that accepts http
    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        super.configure(http);
        http
                .authorizeRequests()
//                .antMatchers("/admin/*").hasRole("admin")
//                .antMatchers("/user/*").hasRole("employee")
                .anyRequest().permitAll();
        http.csrf().disable();
    }
}


