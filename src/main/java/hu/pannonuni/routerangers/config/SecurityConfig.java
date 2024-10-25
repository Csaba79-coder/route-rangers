package hu.pannonuni.routerangers.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/optimize", "/*").permitAll()  // Engedélyezés hitelesítés nélkül a /optimize végpontra
                        .anyRequest().authenticated()  // Minden más végpont hitelesítést igényel
                )
                .csrf(AbstractHttpConfigurer::disable);  // A CSRF védelem kikapcsolása API hívásokhoz

        return http.build();
    }
}
