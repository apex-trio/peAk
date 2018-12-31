package com.apextrio.peak;

import javax.persistence.*;
import java.util.List;

//TODO: Write SQL file to create all the entries we'll want
//TODO: Maybe push changes up to Heroku??

@Entity
public class Resort {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String nick;
    private int widgetId;
    private String otsUrl;
    private String website;
    @OneToMany(mappedBy = "resort")
    public List<Team> groups;

    public long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getNick() {
        return this.nick;
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
