package de.cook2gether.backend.model.Request;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class AuthenticationResponse {
    private final String token;
}
