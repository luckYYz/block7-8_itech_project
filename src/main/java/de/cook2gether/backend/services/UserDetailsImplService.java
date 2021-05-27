package de.cook2gether.backend.services;

import de.cook2gether.backend.model.User;
import de.cook2gether.backend.model.UserDetailsImpl;
import de.cook2gether.backend.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsImplService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    Logger logger = LoggerFactory.getLogger(UserDetailsImplService.class);

    @Override
    public UserDetails loadUserByUsername(String emailAddress) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmailAddress(emailAddress);

        if(user.isEmpty()){
            logger.error(String.valueOf(new UsernameNotFoundException("User " + emailAddress + " not found!")));
            throw new UsernameNotFoundException("User " + emailAddress + " not found!");
        }

        UserDetailsImpl userDetails = new UserDetailsImpl();
        userDetails.setUser(user.get());

        return userDetails;
    }
}
