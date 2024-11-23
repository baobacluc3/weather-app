// WeatherModel.java
package com.example.weatherapp1;

public class WeatherModel {
    private String location;
    private double temperature;
    private int humidity;
    private String description;

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public double getTemperature() { return temperature; }
    public void setTemperature(double temperature) { this.temperature = temperature; }

    public int getHumidity() { return humidity; }
    public void setHumidity(int humidity) { this.humidity = humidity; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}