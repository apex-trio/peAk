package com.apextrio.peak;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String difficulty;
    private int capacity;
    private Date date;
    @ManyToOne
    private Resort resort;
    @ManyToMany
    @JoinTable(
            name = "team_members",
            joinColumns = {@JoinColumn(name = "team_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")}
    )
    private Set<AppUser> users;
}
