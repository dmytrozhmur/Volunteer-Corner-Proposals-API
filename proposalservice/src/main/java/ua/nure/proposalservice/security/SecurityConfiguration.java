package ua.nure.proposalservice.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ua.nure.proposalservice.component.handler.MissedAuthenticationPoint;
import ua.nure.proposalservice.component.handler.UnsuitableRoleHandler;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration {
    @Autowired
    private MissedAuthenticationPoint unauthorizedHandler;
    @Autowired
    private UnsuitableRoleHandler accessDeniedHandler;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthTokenFilter authTokenFilter() {
        return new AuthTokenFilter();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
                .and()
                .cors().and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/v1/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/v1/proposals/**").hasAnyRole("VOLUNTEER", "SUPERADMIN")
                .antMatchers(HttpMethod.POST, "/api/v1/volunteers/**").hasAnyRole("ADMIN", "SUPERADMIN")
                .antMatchers(HttpMethod.PUT, "/api/v1/**").hasAnyRole("VOLUNTEER", "SUPERADMIN")
                .and()
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler).and()
                .logout().permitAll();
        http.addFilterBefore(authTokenFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}