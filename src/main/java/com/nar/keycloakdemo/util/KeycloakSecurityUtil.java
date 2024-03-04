package com.nar.keycloakdemo.util;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class KeycloakSecurityUtil {

    private Keycloak keycloak;

    @Value("${keycloak.client.server-url}")
    private String serverUrl;

    @Value("${keycloak.client.realm}")
    private String realm;

    @Value("${keycloak.client.client-id}")
    private String clientId;

    @Value("${keycloak.client.grant-type}")
    private String grantType;

    @Value("${keycloak.client.name}")
    private String name;

    @Value("${keycloak.client.password}")
    private String password;

    public Keycloak getKeycloakInstance() {
        if (null == keycloak) {
            keycloak = KeycloakBuilder.builder()
                    .serverUrl(serverUrl)
                    .clientId(clientId)
                    .realm(realm)
                    .grantType(grantType)
                    .username(name)
                    .password(password)
                    .build();
        }
        return keycloak;
    }

}
