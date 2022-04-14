package com.bsf.resource.service;

import com.bsf.resource.config.AppConfig;
import com.bsf.resource.config.keycloak.KeycloakProperties;
import com.bsf.resource.service.contract.IAuthenticationFacade;
import com.bsf.resource.web.dto.request.AuthRequest;
import com.bsf.resource.web.dto.request.TokenRefreshRequest;
import com.bsf.resource.web.dto.response.AuthResponse;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthService implements IAuthenticationFacade {

    @Autowired
    AppConfig appConfig;

    private final RestTemplate restTemplate;
//    private final KeycloakProperties keycloakProperties;

//    public AuthService(RestTemplate restTemplate, KeycloakProperties keycloakProperties) {
//        this.restTemplate = restTemplate;
//        this.keycloakProperties = keycloakProperties;
//    }

    public AuthService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * @param auth
     * @return AuthResponse
     */
    public AuthResponse auth(AuthRequest auth) {

        /*
         * { 'client_id': 'your_client_id', 'username': 'your_username', 'password':
         * 'your_password', 'grant_type': 'password' }
         */
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> request = new LinkedMultiValueMap<>();
        request.add("client_id", appConfig.getAdminClientId());
        request.add("username", auth.getUsername());
        request.add("password", auth.getPassword());
        request.add("client_secret", appConfig.getAdminClientSecret());
        request.add("grant_type", CredentialRepresentation.PASSWORD);
        request.add("url", appConfig.getUserAuthorizationUri());

        //// log.info("request:: >>>>>>>>>>>>>> {}", request);
        ResponseEntity<AuthResponse> result = restTemplate.postForEntity(appConfig.getUserAuthorizationUri(),
                new HttpEntity<>(request, headers), AuthResponse.class);

        return result.getBody();
    }


    /**
     * @param refreshToken
     * @return AuthResponse
     */
    public AuthResponse refresh(TokenRefreshRequest refreshToken) {

        /*
         * { 'client_id': 'your_client_id', 'refresh_token':
         * refresh_token_from_previous_request, 'grant_type': 'refresh_token' }
         */
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> request = new LinkedMultiValueMap<>();
        request.add("client_id", appConfig.getAdminClientId());
        request.add("refresh_token", refreshToken.getRefreshToken());
        request.add("client_secret", appConfig.getAdminClientSecret());
        request.add("grant_type", "refresh_token");

        ResponseEntity<AuthResponse> result = restTemplate.postForEntity(appConfig.getUserAuthorizationUri(),
                new HttpEntity<>(request, headers), AuthResponse.class);

        return result.getBody();
    }

    /**
     * @return Authentication
     */
    @Override
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
