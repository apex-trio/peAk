package com.apextrio.peak;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private int difficulty;
    private String description;
    private int capacity;
    private String dateCreated;
    private String dateGoing;
    @ManyToOne
    //Same usage as reason in Resort.java
    @JsonBackReference
    public Resort resort;
    @ManyToMany
    @JoinTable(
            name = "team_members",
            joinColumns = {@JoinColumn(name = "team_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")}
    )
    //Same usage as reason in Resort.java
    @JsonManagedReference
    public Set<AppUser> users = new HashSet<>();

    public long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public int getDifficulty() {
        return this.difficulty;
    }

    public String getDescription() {
        return description;
    }

    public int getCapacity() {
        return this.capacity;
    }

    public String getDateCreated() {
        return this.dateCreated;
    }

    public String getDateGoing() {
        return this.dateGoing;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public void setDateGoing(String dateGoing) {
        this.dateGoing = dateGoing;
    }

    public String toString() {
        return this.name;
    }
}
