package com.example.instagramapp.data.model;

public class UserProfile {
    private String id;
    private String username;
    private String account_type;
    // Simulated field for profile picture; Basic Display API does not expose this directly.
    private String profile_picture_url;

    public String getId() { return id; }
    public String getUsername() { return username; }
    public String getAccount_type() { return account_type; }
    public String getProfile_picture_url() { return profile_picture_url; }
}