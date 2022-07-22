package com.idat.reservacita.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

@Configuration
@EnableResourceServer
public class ResourceConfig extends ResourceServerConfigurerAdapter{
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        super.configure(resources);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.anonymous().disable();
		
        http.csrf().disable();

        http.anonymous().and().authorizeRequests().antMatchers("/usuario/registrar").permitAll();

		http.authorizeRequests()
			.antMatchers("/cliente/**")
			.hasAnyRole("CLIENTE","ADMIN")
            .antMatchers("/hospital/**")
			.hasAnyRole("CLIENTE","ADMIN")
            .antMatchers("/usuario/**")
			.hasAnyRole("CLIENTE","ADMIN")
			.and()
			.exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
    }


}
