package telran.spring.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import lombok.extern.slf4j.Slf4j;
import telran.spring.security.AuthorizationConfiguration;

@Configuration
@Slf4j
public class EmplAuthorizationConfiguration implements AuthorizationConfiguration {

	public void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.authorizeHttpRequests(requests -> requests.requestMatchers(HttpMethod.OPTIONS).permitAll().
				requestMatchers(HttpMethod.GET).authenticated()
				.anyRequest().hasRole("ADMIN"));
		log.info("Employees authorization configuration loaded");
	}

}
