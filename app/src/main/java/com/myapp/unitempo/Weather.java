package com.myapp.unitempo;

public class Weather {
    private String city;
    private String description;
    private double temperature;
    private String icon;


    public Weather(String city, String description, double temperature, String icon) {
        this.city = city;
        this.description = description;
        this.temperature = temperature;
        this.icon = icon;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
