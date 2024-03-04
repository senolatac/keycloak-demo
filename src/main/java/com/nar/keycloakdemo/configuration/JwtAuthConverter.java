package com.nar.keycloakdemo.configuration;

import com.nimbusds.jose.shaded.gson.internal.LinkedTreeMap;
import jakarta.annotation.Nonnull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class JwtAuthConverter implements Converter<Jwt, AbstractAuthenticationToken> {
    @Override
    public AbstractAuthenticationToken convert(@Nonnull Jwt jwt) {
        Collection<GrantedAuthority> grantedAuthorities = extractRoles(jwt);
        return new JwtAuthenticationToken(jwt, grantedAuthorities);
    }

    private Collection<GrantedAuthority> extractRoles(Jwt jwt) {
        List<String> clientRoles = new ArrayList<>(jwt.getClaim("roles"));
        if (CollectionUtils.isEmpty(clientRoles)) {
            return new ArrayList<>();
        }
        return clientRoles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    private Collection<GrantedAuthority> extractRolesOld(Jwt jwt) {
        Map<String, Object> resourceAccess = jwt.getClaim("resource_access");
        if (resourceAccess == null || resourceAccess.isEmpty()) {
            return new ArrayList<>();
        }
        LinkedTreeMap<String, List<String>> clientRoleMap = (LinkedTreeMap<String, List<String>>) resourceAccess.get("demo-rest-api");
        List<String> clientRoles = new ArrayList<>(clientRoleMap.get("roles"));
        return clientRoles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }
}
