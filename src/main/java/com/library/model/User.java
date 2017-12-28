package com.library.model;

import java.util.Date;

public class User {


    private final Long id;
    private final String message;
    private final Date time;
    private Double latitude;
    private Double longitude;

    public User(String message, Date time) {
        this(message, time, null, null);
    }

    public User(String message, Date time, Double longitude, Double latitude) {
        this.id = null;
        this.message = message;
        this.time = time;
        this.longitude = longitude;
        this.latitude = latitude;
    }
    public long getId() {
        return id;
    }
    public String getMessage() {
        return message;
    }
    public Date getTime() {
        return time;
    }
    public Double getLongitude() {
        return longitude;
    }
    public Double getLatitude() {
        return latitude;
    }
}