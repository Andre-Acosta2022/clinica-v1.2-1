package com.clinic.pago_service.Configuration;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
public class SecurityConfig {

    @Value("${ISSUER_URI}")
    private String issuerUri;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)  // Desactiva CSRF para API REST (no necesario)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/public/**", "/actuator/health", "/actuator/info").permitAll() // Endpoints públicos
                        .anyRequest().authenticated()  // El resto requiere autenticación
                )
                .cors(AbstractHttpConfigurer::disable) // Puedes configurar CORS según necesidad
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(withDefaults())) // Habilita JWT para OAuth2 Resource Server
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS)) // Sin sesión, stateless
                .build();
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        return JwtDecoders.fromIssuerLocation(issuerUri); // Obtiene configuraciones JWT según el issuer
    }

    @PostConstruct
    public void init() {
        // Para propagar contexto de seguridad a hilos hijos si usas programación multihilo
        SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);
    }
}
