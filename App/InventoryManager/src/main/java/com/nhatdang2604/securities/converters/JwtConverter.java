package com.nhatdang2604.securities.converters;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimNames;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.stereotype.Component;

import com.nhatdang2604.securities.properties.JwtConverterProperties;

@Component
public class JwtConverter implements Converter<Jwt, AbstractAuthenticationToken> {

    private final JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
    private final JwtConverterProperties properties;

    public JwtConverter(JwtConverterProperties properties) {
        this.properties = properties;
    }

    @Override
    public AbstractAuthenticationToken convert(Jwt jwt) {
        
        Collection<GrantedAuthority> converted = jwtGrantedAuthoritiesConverter.convert(jwt);
        System.out.println(converted);
        System.out.println(null == converted);

        if (null == converted) {
            return null;
        }

        Stream<GrantedAuthority> authorityStream = Stream.concat(
            converted.stream(),
            extractResourceRoles(jwt).stream()
        );
        Collection<GrantedAuthority> authorities = authorityStream
            .collect(Collectors.toSet());
        
        return new JwtAuthenticationToken(jwt, authorities, getPrincipalClaimName(jwt));
    }

    private Collection <? extends GrantedAuthority> extractResourceRoles(Jwt jwt) {
        Map<String, Object> resourceAccess = jwt.getClaim("resource_access");
        if (null == resourceAccess) {
            System.out.println("Empty resource access");
            return Collections.emptySet();
        }

        for (String key : resourceAccess.keySet()) {
            System.out.println("Key: " + key);
        }

        @SuppressWarnings("unchecked")
        Map<String, Object> resource = (Map<String, Object>) resourceAccess.get(properties.getResourceId());
        if (null == resource) {
            System.out.println("Empty resource");
            return Collections.emptySet();
        }

        for (String key : resource.keySet()) {
            System.out.println("Key: " + key);
        }

        @SuppressWarnings("unchecked")
        Collection<String> resourceRoles = (Collection<String>) resource.get("roles");
        if (null == resourceRoles) {
            System.out.println("Empty resource roles");
            return Collections.emptySet();
        }

        for (String role : resourceRoles) {
            System.out.println("Role: " + role);
        }


        System.out.println("Finished extracting resource roles");
        return resourceRoles.stream()
            .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
            .collect(Collectors.toSet());
    }
    
    private String getPrincipalClaimName(Jwt jwt) {
        String claimName = properties.getPrincipalAttribute();
        if (claimName == null) {
            claimName = JwtClaimNames.SUB;
        }
        
        return jwt.getClaim(claimName);
    }
}
