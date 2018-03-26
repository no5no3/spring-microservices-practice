package net.no5no3.microservices.router;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
//@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.httpBasic().disable();
//        http.authorizeRequests()
//                .antMatchers("/eureka/**").permitAll()
//                .antMatchers("”/api/account/authorize/**").permitAll()
//                .anyRequest().authenticated()
//                .and().formLogin()
//                .and().csrf().disable();
        http.httpBasic()
                .and().formLogin()
                .and().authorizeRequests()
                .antMatchers("/api/**"
                ).permitAll()
                .anyRequest().authenticated()
                .and().csrf().disable();
    }
}
