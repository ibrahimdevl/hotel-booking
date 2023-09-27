package com.ibrahimdev.hotelbooking.config;

import com.ibrahimdev.hotelbooking.service.HotelUserDetailsService;
import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {


    @Bean
    public UserDetailsService detailsService() {
        return new HotelUserDetailsService();
    }

    @Autowired
    JwtRequestFilter jwtRequestFilter;

    @Qualifier("delegatedAuthenticationEntryPoint")
    private final DelegatedAuthenticationEntryPoint authEntryPoint;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(detailsService());

        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain springSecurityConfiguration(HttpSecurity http) throws Exception {


        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class).csrf(AbstractHttpConfigurer::disable)
                .exceptionHandling(exception -> exception.authenticationEntryPoint(authEntryPoint))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(requests ->
                        requests
                                .requestMatchers("/v3/api-docs/**",
                                        "/swagger-ui/**",
                                        "/swagger-ui.html").permitAll()
                                .requestMatchers(HttpMethod.POST, "/api/auth/signup").permitAll()
                                .requestMatchers(HttpMethod.POST, "/api/auth/signin").permitAll()

                                .requestMatchers(HttpMethod.POST,"/api/rooms/**").hasRole( "ADMIN")
                                .requestMatchers(HttpMethod.PUT,"/api/rooms/**").hasRole( "ADMIN")
                                .requestMatchers(HttpMethod.DELETE,"/api/rooms/**").hasRole( "ADMIN")
                                .requestMatchers("/api/rooms/all").hasAnyRole("HOTEL", "ADMIN")
                                .requestMatchers("/api/rooms/occupied").hasAnyRole("HOTEL", "ADMIN")
                                .requestMatchers("/api/rooms/available").hasAnyRole("HOTEL", "ADMIN")
                                .requestMatchers("/api/rooms/**").permitAll()

                                .requestMatchers(HttpMethod.DELETE,"/api/reservation").hasAnyRole("HOTEL", "ADMIN")
                                .requestMatchers("/api/reservation/all").hasAnyRole("HOTEL", "ADMIN")
                                .requestMatchers(HttpMethod.POST,"/api/reservation/update").hasAnyRole("HOTEL", "ADMIN")
                                .requestMatchers("/api/reservation").authenticated()

                                .requestMatchers(HttpMethod.POST,"/api/user/create").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/api/user/update/**").authenticated()
                                .requestMatchers(HttpMethod.DELETE, "/api/user/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/api/user/search").hasAnyRole("HOTEL", "ADMIN")
                                .requestMatchers(HttpMethod.GET, "/api/user/find").hasAnyRole("HOTEL", "ADMIN")
                                .requestMatchers(HttpMethod.GET, "/api/user/**").authenticated()

                                .requestMatchers( "/api/guest/**").hasAnyRole("HOTEL", "ADMIN")
                                .requestMatchers("/api/admin/**").hasRole("ADMIN")
                                .requestMatchers("/api/hotel/**").hasAnyRole("HOTEL", "ADMIN")
                                .anyRequest().authenticated()
                ).authenticationProvider(authProvider()
                );

        return http.build();
    }

}
