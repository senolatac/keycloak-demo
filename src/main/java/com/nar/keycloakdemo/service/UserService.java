package com.nar.keycloakdemo.service;

import com.nar.keycloakdemo.dto.UserRecord;
import com.nar.keycloakdemo.mapper.UserMapper;
import com.nar.keycloakdemo.util.KeycloakSecurityUtil;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.Keycloak;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final KeycloakSecurityUtil keycloakSecurityUtil;
    private final UserMapper userMapper;



    public List<UserRecord> getAllUsers(String realm) {
        Keycloak keycloakInstance = keycloakSecurityUtil.getKeycloakInstance();
        return keycloakInstance
                .realm(realm)
                .users()
                .list()
                .stream()
                .map(userMapper::mapToUserRecordFromUserRepresentation)
                .collect(Collectors.toList());
    }

    public UserRecord createUser(UserRecord userRecord, String realm) {
        Keycloak keycloakInstance = keycloakSecurityUtil.getKeycloakInstance();
        try (Response response = keycloakInstance.realm(realm).users().create(userMapper.mapToUserRepresentationFromUserRecord(userRecord))) {
        }
        return userRecord;
    }
}
