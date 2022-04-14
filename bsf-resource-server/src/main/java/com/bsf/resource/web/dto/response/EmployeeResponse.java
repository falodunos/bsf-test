package com.bsf.resource.web.dto.response;

import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
public class EmployeeResponse {
    private String firstName;
    private String lastName;
    private String email;
    private String telephone;

    public EmployeeResponse() {}
}
