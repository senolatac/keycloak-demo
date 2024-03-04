package com.nar.keycloakdemo.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

/*@Configuration
@EnableWebSecurity
@EnableMethodSecurity*/
@RequiredArgsConstructor
public class SecurityWithJWT {

    private final JwtAuthConverter jwtAuthConverter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http.authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/swagger-ui/**", "/v3/api-docs/**")
                .permitAll()

                .anyRequest()
                .authenticated());
        // http.oauth2ResourceServer(t -> t.jwt(jwtConfigurer -> jwtConfigurer.jwtAuthenticationConverter(jwtAuthConverter)));
        http.oauth2ResourceServer(t -> t.jwt(Customizer.withDefaults()));
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }

    @Bean
    public DefaultMethodSecurityExpressionHandler mSecurity() {
        DefaultMethodSecurityExpressionHandler defaultMethodSecurityExpressionHandler = new DefaultMethodSecurityExpressionHandler();
        defaultMethodSecurityExpressionHandler.setDefaultRolePrefix("");
        return defaultMethodSecurityExpressionHandler;
    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtAuthenticationConverter jac = new JwtAuthenticationConverter();
        JwtGrantedAuthoritiesConverter jgac = new JwtGrantedAuthoritiesConverter();
        jgac.setAuthorityPrefix("");
        jgac.setAuthoritiesClaimName("roles");
        jac.setJwtGrantedAuthoritiesConverter(jgac);
        return jac;
    }
}
