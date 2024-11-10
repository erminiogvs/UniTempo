package com.myapp.unitempo;

import java.util.List;

public class WeatherResponse {
    private Results results;

    public Results getResults() {
        return results;
    }

    public void setResults(Results results) {
        this.results = results;
    }

    public static class Results {
        private String city;
        private double temp;
        private String description;
        private String currently;
        private List<Forecast> forecast;

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public double getTemp() {
            return temp;
        }

        public void setTemp(double temp) {
            this.temp = temp;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getCurrently() {
            return currently;
        }

        public void setCurrently(String currently) {
            this.currently = currently;
        }

        public List<Forecast> getForecast() {
            return forecast;
        }

        public void setForecast(List<Forecast> forecast) {
            this.forecast = forecast;
        }
    }

    public static class Forecast {
        private String date;
        private String weekday;
        private double max;
        private double min;
        private String description;
        private String condition;
        private String conditionSlug;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getWeekday() {
            return weekday;
        }

        public void setWeekday(String weekday) {
            this.weekday = weekday;
        }

        public double getMax() {
            return max;
        }

        public void setMax(double max) {
            this.max = max;
        }

        public double getMin() {
            return min;
        }

        public void setMin(double min) {
            this.min = min;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getCondition() {
            return condition;
        }

        public void setCondition(String condition) {
            this.condition = condition;
        }

        public String getConditionSlug() {
            return conditionSlug;
        }

        public void setConditionSlug(String conditionSlug) {
            this.conditionSlug = conditionSlug;
        }

        public String getIconUrl() {
            return "https://assets.hgbrasil.com/weather/icons/conditions/" + conditionSlug + ".svg";
        }
    }
}
