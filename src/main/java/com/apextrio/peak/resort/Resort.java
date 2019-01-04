package com.apextrio.peak.resort;

import com.apextrio.peak.team.Team;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

//TODO: Maybe push changes up to Heroku??

@Entity
public class Resort {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String nick;
    private float latitude;
    private float longitude;
    private int widgetId;
    private String otsUrl;
    private String website;
    @OneToMany(mappedBy = "resort")
    //This fixes a bug when getting resort info via AJAX. Solution found at https://stackoverflow.com/questions/3325387/infinite-recursion-with-jackson-json-and-hibernate-jpa-issue
    @JsonManagedReference
    public List<Team> teams;

    public long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getNick() {
        return this.nick;
    }

    public float getLatitude() {
        return this.latitude;
    }

    public float getLongitude() {
        return this.longitude;
    }

    public int getWidgetId() {
        return this.widgetId;
    }

    public String getOtsUrl() {
        return this.otsUrl;
    }

    public String getWebsite() {
        return this.website;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public void setWidgetId(int widgetId) {
        this.widgetId = widgetId;
    }

    public void setOtsUrl(String otsUrl) {
        this.otsUrl = otsUrl;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String toString() {
        return this.name;
    }
}
