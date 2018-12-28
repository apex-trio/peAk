package com.apextrio.peak;

import javax.persistence.*;
import java.util.List;

@Entity
public class Resort {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private float latitude;
    private float longitude;
    private String website;
    @OneToMany(mappedBy = "resort")
    private List<Team> groups;
}
