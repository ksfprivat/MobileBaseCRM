package ru.zintur.mobilebase.schema;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "CONFIG".
 */
public class Config {

    private Long id;
    private String state;
    private String user;
    private String password;
    private String version;
    private String uptime;
    private String evolution;

    public Config() {
    }

    public Config(Long id) {
        this.id = id;
    }

    public Config(Long id, String state, String user, String password, String version, String uptime, String evolution) {
        this.id = id;
        this.state = state;
        this.user = user;
        this.password = password;
        this.version = version;
        this.uptime = uptime;
        this.evolution = evolution;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getUptime() {
        return uptime;
    }

    public void setUptime(String uptime) {
        this.uptime = uptime;
    }

    public String getEvolution() {
        return evolution;
    }

    public void setEvolution(String evolution) {
        this.evolution = evolution;
    }

}