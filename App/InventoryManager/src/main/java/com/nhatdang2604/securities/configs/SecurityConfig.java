package com.nhatdang2604.securities.configs;

import com.nhatdang2604.securities.handlers.AuthenticationEntryPointHandler;
import lombok.RequiredArgsConstructor;

import org.keycloak.adapters.authorization.integration.jakarta.ServletPolicyEnforcerFilter;
import org.keycloak.representations.adapters.config.PolicyEnforcerConfig;
import org.keycloak.util.JsonSerialization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.web.authentication.BearerTokenAuthenticationFilter;
import org.springframework.security.web.SecurityFilterChain;

import java.io.IOException;
import java.io.InputStream;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Autowired
    private AuthenticationEntryPointHandler authenticationEntryPointHandler;

    @Value("${spring.security.oauth2.resourceserver.jwt.jwk-set-uri}")
    private String jwkSetUri;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
            .requestMatchers("api/v1/auth/access-denied")
                .permitAll()
            .anyRequest()
                .authenticated()
        );

        http.oauth2ResourceServer(oauth2 -> oauth2
                .jwt(Customizer.withDefaults())
                .authenticationEntryPoint(authenticationEntryPointHandler)
        );

        http.addFilterAfter(
                createPolicyEnforcerFilter(),
                BearerTokenAuthenticationFilter.class
        );

        return http.build();
    }

    private ServletPolicyEnforcerFilter createPolicyEnforcerFilter() {
        PolicyEnforcerConfig config;
        try {
            InputStream policyEnforcerAsStream = getClass().getResourceAsStream("/policy-enforcer.json");
            config = JsonSerialization.readValue(policyEnforcerAsStream, PolicyEnforcerConfig.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return new ServletPolicyEnforcerFilter(httpRequest -> config);
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withJwkSetUri(this.jwkSetUri).build();
    }
}
