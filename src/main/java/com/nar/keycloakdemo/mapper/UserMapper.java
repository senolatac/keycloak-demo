package com.nar.keycloakdemo.mapper;

import com.nar.keycloakdemo.dto.UserRecord;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "password", ignore = true)
    UserRecord mapToUserRecordFromUserRepresentation(UserRepresentation userRepresentation);

    UserRepresentation mapToUserRepresentationFromUserRecord(UserRecord userRecord);

    @AfterMapping
    default void convertNameToUpperCase(@MappingTarget UserRepresentation userRepresentation, UserRecord userRecord) {
        userRepresentation.setEnabled(true);
        userRepresentation.setEmailVerified(true);
        List<CredentialRepresentation> list = new ArrayList<>();
        CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
        credentialRepresentation.setTemporary(false);
        credentialRepresentation.setValue(userRecord.password());
        list.add(credentialRepresentation);
        userRepresentation.setCredentials(list);
    }
}
