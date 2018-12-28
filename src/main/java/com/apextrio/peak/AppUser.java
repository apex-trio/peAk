package com.apextrio.peak;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    private String password;
    private String bio;
    private String firstName;
    private String lastName;
    @ManyToMany(mappedBy = "users")
    private Set<Team> groups = new HashSet<>();
}
