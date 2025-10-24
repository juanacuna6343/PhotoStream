package com.example.instagramapp.data.model;

public class MediaItem {
    private String id;
    private String media_type;
    private String media_url;
    private String thumbnail_url;
    private String caption;
    private String timestamp;

    public String getId() { return id; }
    public String getMedia_type() { return media_type; }
    public String getMedia_url() { return media_url; }
    public String getThumbnail_url() { return thumbnail_url; }
    public String getCaption() { return caption; }
    public String getTimestamp() { return timestamp; }
}