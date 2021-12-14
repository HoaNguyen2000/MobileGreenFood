package com.example.mobilegreenfood.model;

public class User {
    private int id;
    private String name;
    private String email;
    private String roles;
    private String api_token;

    public User() {
    }

    public User(String api_token) {
        this.api_token = api_token;
    }

    public User(int id, String name, String email, String roles, String api_token) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.roles = roles;
        this.api_token = api_token;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getApi_token() {
        return api_token;
    }

    public void setApi_token(String api_token) {
        this.api_token = api_token;
    }
}
