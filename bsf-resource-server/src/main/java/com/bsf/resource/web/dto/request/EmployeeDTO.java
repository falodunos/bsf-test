package com.bsf.resource.web.dto.request;

import lombok.Value;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public enum EmployeeDTO {;
    private interface FirstName {
        /**
         *
         * @return String
         */
        @NotBlank(message = "Please provide a valid request reference ")
        @Pattern(regexp="^[a-zA-Z]*$",message="first name must contain only letters")
        @Size(min = 3, message = "first name must be three or more letters")
        String getFirstName();
    }

    private interface LastName {
        /**
         *
         * @return String
         */
        @NotBlank(message = "Please provide a valid request reference ")
        @Pattern(regexp="^[a-zA-Z]*$",message="first name must contain only letters")
        @Size(min = 3, message = "first name must be three or more letters")
        String getLastName();
    }

    private interface Email {
        /**
         *
         * @return long
         */
        @NotBlank
        @javax.validation.constraints.Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}", message = "Invalid Email Address")
        String getEmail();
    }

    private interface Telephone {
        /**
         * @return String
         */
        @NotBlank(message = "Please provide a valid telephone number")
        String getTelephone();
    }

    public enum Request {
        ;

        @Value
        public static class Body implements FirstName, LastName, Email, Telephone {
            String firstName;
            String lastName;
            String email;
            String telephone;
        }
    }
}
