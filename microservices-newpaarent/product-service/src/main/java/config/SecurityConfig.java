package config;

import com.microservice.productservice.security.JwtAuthenticationEntryPoint;
import com.microservice.productservice.security.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.SecurityBuilder;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {
    @Autowired
    private JwtAuthenticationFilter filter;
    @Autowired
    private JwtAuthenticationEntryPoint point;

    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
      http.csrf(csrf-> csrf.disable()).cors(cors-> cors.disable())
              .authorizeHttpRequests(
                      auth-> auth.requestMatchers("/api/**").authenticated().requestMatchers("/auth/login").permitAll()
                      .anyRequest().authenticated()).exceptionHandling(ex-> ex.authenticationEntryPoint(point)).sessionManagement(session-> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

                      http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
    return http.build();

    }
}