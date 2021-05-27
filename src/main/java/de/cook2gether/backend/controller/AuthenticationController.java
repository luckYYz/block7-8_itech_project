package de.cook2gether.backend.controller;

import de.cook2gether.backend.jwt.JwtUtils;
import de.cook2gether.backend.model.Request.AuthenticationRequest;
import de.cook2gether.backend.model.Request.AuthenticationResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final UserDetailsService userDetailsService;

    public AuthenticationController(AuthenticationManager authenticationManager, JwtUtils jwtUtils, UserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.userDetailsService = userDetailsService;
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmailAddress(), authenticationRequest.getPassword()));
        }
        catch (BadCredentialsException exception){
            throw new Exception("Invalid Credentials ", exception);
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmailAddress());

        return ResponseEntity.ok(new AuthenticationResponse(jwtUtils.generateToken(userDetails)));
    }
}
