package de.cook2gether.backend.model.Request;

import lombok.Data;

@Data
public class AuthenticationRequest {

    private String emailAddress;
    private String password;
}
