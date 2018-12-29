package com.apextrio.peak;

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
    private String difficulty;
    private int capacity;
    private Date dateCreated;
    private Date dateGoing;
    @ManyToOne
    public Resort resort;
    @ManyToMany
    @JoinTable(
            name = "team_members",
            joinColumns = {@JoinColumn(name = "team_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")}
    )
    public Set<AppUser> users;

    public long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getDifficulty() {
        return this.difficulty;
    }

    public int getCapacity() {
        return this.capacity;
    }

    public Date getDateCreated() {
        return this.dateCreated;
    }

    public Date getDateGoing() {
        return this.dateGoing;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public void setDateGoing(Date dateGoing) {
        this.dateGoing = dateGoing;
    }

    public String toString() {
        return this.name;
    }
}
