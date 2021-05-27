package de.cook2gether.backend.controller;

import de.cook2gether.backend.model.User;
import de.cook2gether.backend.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Slf4j
public class RegistrationController {

    private final UserRepository userRepository;
    private final PasswordEncoder bCryptPasswordEncoder;

   @PostMapping("register")
   public ResponseEntity<?> register(@RequestBody User user){
       Optional<User> optionalUser = userRepository.findByEmailAddress(user.getEmailAddress());
       if(optionalUser.isEmpty()){
           user.setRoles("ROLE_USER");
           user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
           userRepository.save(user);

           return ResponseEntity.ok("success");
       }
       else return ResponseEntity.badRequest().body("failure");
   }
}
