package com.bsf.resource.config.keycloak;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;


@Data
@Component
@Configuration
@ConfigurationProperties(prefix = "admin.keycloak")
public class KeycloakProperties {

   private String url;
   private String realm;
   private String clientId;
   private String clientSecret;
   private String id;
   private String username;
   private String userSecret;
   private String userAuthorizationUri;
}
