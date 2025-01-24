package com.flipkart.bean;

// Represents a city entity.
public class City {
    public String getCityID() {
        return cityID;
    }

    public void setCityID(String cityID) {
        this.cityID = cityID;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    private String cityID;
    private String cityName;

    public City(String cityID, String cityName) {
        this.cityID = cityID;
        this.cityName = cityName;
    }
}
