package com.bsf.resource.web.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest {

    @NotNull(message = "Invalid username/password!")
    @Size(min = 3, message = "invalid username/password!")
    private String username;

    @NotNull(message = "Invalid username/password!")
    @Size(min = 3, message = "Invalid username/password!")
    private String password;
}
