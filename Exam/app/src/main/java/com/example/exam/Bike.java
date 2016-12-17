package com.example.exam;

public class Bike {
    private long id = -1;
    private int bikeShareId = -1;
    private double latitude = 0.0;
    private double longitude = 0.0;
    private String name = null;
    private int numBikes = 0;
    private String address = null;
    public Bike(int bikeShareId, double latitude, double longitude,
                String name, int numBikes, String address) {

        setBikeShareId(bikeShareId);
        setLatitude(latitude);
        setLongitude(longitude);
        setName(name);
        setNumBikes(numBikes);
        setAddress(address);
    }
    public long getId() { return id; }
    public int getBikeShareId() { return bikeShareId; }
    public String getName() { return name; }
    public double getLatitude() { return latitude; }
    public double getLongitude() { return longitude; }
    public int getNumBikes() { return numBikes; }
    public String getAddress() { return address; }
    public void setId(long id) { this.id = id; }
    public void setBikeShareId(int id) { this.bikeShareId = id; }
    public void setName(String name) { this.name = name; }
    public void setLatitude(double latitude) { this.latitude = latitude; }
    public void setLongitude(double longitude) { this.longitude = longitude; }
    public void setNumBikes(int numBikes) { this.numBikes = numBikes; }
    public void setAddress(String address) { this.address = address; }
    public String toString() {
        return name + " (" + address + ")";
    }
}
