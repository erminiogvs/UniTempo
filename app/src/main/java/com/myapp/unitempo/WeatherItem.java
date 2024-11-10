package com.myapp.unitempo;

public class WeatherItem {
    private String description;
    private String temperature;
    private String iconUrl;

    public WeatherItem(String description, String temperature, String iconUrl) {
        this.description = description;
        this.temperature = temperature;
        this.iconUrl = iconUrl;
    }

    public String getDescription() {
        return description;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getIconUrl() {
        return iconUrl;
    }
}
