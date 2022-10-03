package com.SocialMedia.config;
import com.SocialMedia.service.CustomAuthProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Autowired
    private CustomAuthProvider customAuthProvider;

    public WebSecurityConfig() {
        super();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/signup").permitAll()
                .antMatchers("/friend/**").permitAll()
                .antMatchers("/user/**").permitAll()
                .antMatchers("/post/**").permitAll()
                .antMatchers("/comment/**").permitAll()
                .antMatchers("/reaction/**").permitAll()

                .antMatchers("/api/**").permitAll().and().authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll()
                .and()
                .httpBasic()
                .and()
                .authenticationProvider(customAuthProvider);
        return http.build();
    }

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers("/login").permitAll()
//                .antMatchers("/signup").permitAll()
//                .antMatchers("/user/**").authenticated()
//                .antMatchers("/post/**").authenticated()
//                .antMatchers("/comment/**").authenticated()
//                .antMatchers("/reaction/**").authenticated()
//                .antMatchers("/friend/**").authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/login")
//                .permitAll()
//                .and()
//                .logout()
//                .permitAll()
//                .and()
//                .httpBasic()
//                .and()
//                .authenticationProvider(customAuthProvider);
//        return http.build();
//    }

}
