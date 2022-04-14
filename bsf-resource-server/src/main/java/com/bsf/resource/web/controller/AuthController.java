package com.bsf.resource.web.controller;

import com.bsf.resource.service.AuthService;
import com.bsf.resource.web.dto.request.AuthRequest;
import com.bsf.resource.web.dto.request.TokenRefreshRequest;
import com.bsf.resource.web.dto.response.AuthResponse;
import groovy.util.logging.Slf4j;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;

@CrossOrigin(origins = "*")
@Produces(MediaType.APPLICATION_JSON_VALUE)
@Consumes(MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping(value = "/bsf/auth/")
@Slf4j
@AllArgsConstructor
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    AuthService authService;

    /**
     * @param request
     * @return ResponseEntity
     */
    @PostMapping("token")
    public ResponseEntity<AuthResponse> userAuth(@Valid @RequestBody AuthRequest request) {
        logger.info("Auth Request {} " + request.toString());
        AuthResponse response = authService.auth(request);
        return ResponseEntity.ok().body(response);
    }


    /**
     * @param request
     * @return ResponseEntity
     */
    @PostMapping("token/refresh")
    public ResponseEntity<AuthResponse> tokenRefresh(@Valid @RequestBody TokenRefreshRequest request) {
        AuthResponse response = authService.refresh(request);
        return ResponseEntity.ok().body(response);
    }
}
