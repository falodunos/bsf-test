package com.bsf.resource.config.keycloak;

import lombok.extern.slf4j.Slf4j;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.keycloak.admin.client.KeycloakBuilder;

@Slf4j
@Configuration
public class KeycloakConfig {

    /**
     * @param keycloakProperties
     * @return Keycloak
     */
//   @Bean
   public Keycloak keyCloak(KeycloakProperties keycloakProperties) {
       log.info(".getUrl() " + keycloakProperties.getUrl());
       Keycloak keycloak = KeycloakBuilder.builder()
               .serverUrl(keycloakProperties.getUrl())
               .realm(keycloakProperties.getRealm())
               .grantType(OAuth2Constants.PASSWORD)
               .clientId(keycloakProperties.getClientId())
               .clientSecret(keycloakProperties.getClientSecret())
               .username(keycloakProperties.getUsername())
               .password(keycloakProperties.getUserSecret())
               .build();
       log.info("Keycloak AccessToken: " + keycloak.tokenManager().getAccessTokenString());
       return keycloak;
   }
}