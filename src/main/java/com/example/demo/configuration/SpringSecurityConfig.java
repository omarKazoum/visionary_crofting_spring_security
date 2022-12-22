package com.example.demo.configuration;

import com.example.demo.Authorities;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtFilter;
import com.example.demo.security.JwtUtil;
import com.example.demo.service.UserDetailsService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletResponse;
import java.beans.JavaBean;

@Configuration
@EnableWebSecurity
@Data
@SuppressWarnings("deprecation")
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
    String lastName="essydeq";
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserDetailsService userDetailsService;
    @Autowired
    JwtFilter jwtFilter;
    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf()
                .disable()
                .cors()
                .and()
                .httpBasic().disable()
                .authorizeRequests()
                .antMatchers("/api/auth/**")
                .permitAll()
                .antMatchers("/api/user/**")
                .authenticated()
                .antMatchers("/api/v1/orders/**")
                .hasAuthority(Authorities.MANAGE_ORDERS)
                .antMatchers("/api/v1/products/**")
                .hasAuthority(Authorities.MANAGE_PRODUCTS)
                .and()
                .userDetailsService(userDetailsService)
                .exceptionHandling()
                .authenticationEntryPoint((req,resp,authExe)->{
                    resp.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Unauthorized");
                })
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                httpSecurity.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
    /*@Bean
    AuthenticationProvider authenticationProvider(){
        return new AuthenticationProvider() {
            @Override
            public Authentication authenticate(Authentication authentication) throws AuthenticationException {
                return null;
            }

            @Override
            public boolean supports(Class<?> authentication) {
                return false;
            }
        }
    }*/
}
