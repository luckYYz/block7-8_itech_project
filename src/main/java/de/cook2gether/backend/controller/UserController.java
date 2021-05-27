package de.cook2gether.backend.controller;

import de.cook2gether.backend.model.User;
import de.cook2gether.backend.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    @GetMapping("user")
    public ResponseEntity<?> getUser(@RequestParam String emailAddress){
        Optional<User> user = userRepository.findByEmailAddress(emailAddress);
        if(user.isPresent()){
            return ResponseEntity.ok(user.get());
        }
        return ResponseEntity.ok("NoUserFound");
    }
}
