package com.rmrfroot.tasktracker222.configurers;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * this class has configured with the application.properties file by using
 * the Spring Security Oauth2 that allows a third party application to get
 * a limit access to the application such as HTTPS or HTTP requests.
 * @author Visoth Cheam
 */
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf()
                .disable()
                .authorizeRequests() //users request need to be authorized, such as access token or refresh token
                .anyRequest() //all requests that sent by users
                .authenticated() //need to be authenticated, such as signIN
                .and()
                .oauth2Login() //this use an oauth2login process that we can use MFA(multiple factors authentication)
                //which we can allow second authentication, such as send a text to a user to verify their signIN process,
                //but in our case we do not implement it yet.
                .defaultSuccessUrl("/",true) // default page after a user is successful signIN
                .and()
                .logout()
                .logoutSuccessUrl("https://" + System.getenv("SUBDOMAIN") + ".auth." + System.getenv("AWS_REGION") +
                        ".amazoncognito.com/logout?client_id=" + System.getenv("COGNITO_CLIENT_ID") +
                        "&logout_uri=http://localhost:8080/")
        ;
    }
}
