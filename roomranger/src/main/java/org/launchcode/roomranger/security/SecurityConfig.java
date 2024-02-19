package org.launchcode.roomranger.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private  JwtRequestFilter jwtRequestFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // New way to disable CSRF while adopting the functional style
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers("/authenticate").permitAll()
                        .requestMatchers("/manager/**").hasRole("Manager")
                        .requestMatchers("/roomAttendant/**").hasRole("RoomAttendant")
                        .requestMatchers(HttpMethod.POST, "/rooms").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.POST, "/tasks").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.PUT, "/tasks/**").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.POST, "/leave-requests").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.PUT, "/leave-requests/**").hasRole("MANAGER")
                        // Room Attendant authorization rules
                        .requestMatchers(HttpMethod.GET, "/tasks").hasRole("ROOMATTENDANT")
                        .requestMatchers(HttpMethod.PUT, "/tasks/**").hasRole("ROOMATTENDANT")
                        .requestMatchers(HttpMethod.POST, "/leave-requests").hasRole("ROOMATTENDANT")
                        .requestMatchers(HttpMethod.PUT, "/leave-requests/**").hasRole("ROOMATTENDANT")
                        .anyRequest().authenticated())
                .sessionManagement((session) -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

}