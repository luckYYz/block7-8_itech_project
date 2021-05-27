package de.cook2gether.backend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import javax.persistence.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userID;
    @Column(name = "first_name")
    @NonNull
    private String firstName;
    @Column(name = "last_name")
    @NonNull
    private String lastName;
    @Column(name = "email_address")
    @NonNull
    private String emailAddress;
    @Column(name = "password")
    @NonNull
    private String password;
    @Column(name = "roles")
    private String roles;
    @Column(name = "region")
    private String region;
    @Column(name = "username")
    @NonNull
    private String username;
}
