package com.bsf.resource.web.dto.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author olugbenga.falodun
 */
@Data
public class TokenRefreshRequest {

    @NotNull(message = "Invalid Token")
    @Size(min = 150, message = "Invalid Token")
    private String refreshToken;
}
