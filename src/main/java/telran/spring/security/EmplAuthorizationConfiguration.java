package telran.spring.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import telran.spring.security.AuthorizationConfiguration;

@Configuration
public class EmplAuthorizationConfiguration implements AuthorizationConfiguration {

	public void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.authorizeHttpRequests(custom -> custom.requestMatchers(HttpMethod.GET).
				authenticated().anyRequest().hasRole("ADMIN"));

	}

}
