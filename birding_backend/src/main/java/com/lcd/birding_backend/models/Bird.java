package com.lcd.birding_backend.models;

public class Bird {

    private String comName;
    private String sciName;
    private String locName;
    private String obsDt;
    private int howMany;
    private double lat;
    private double lng;

    public Bird(String comName, String sciName, String locName, String obsDt, int howMany, double lat, double lng) {
        this.comName = comName;
        this.sciName = sciName;
        this.locName = locName;
        this.obsDt = obsDt;
        this.howMany = howMany;
        this.lat = lat;
        this.lng = lng;
    }

    public Bird() {
    }

    public String getComName() {
        return comName;
    }

    public void setComName(String comName) {
        this.comName = comName;
    }

    public String getSciName() {
        return sciName;
    }

    public void setSciName(String sciName) {
        this.sciName = sciName;
    }

    public String getLocName() {
        return locName;
    }

    public void setLocName(String locName) {
        this.locName = locName;
    }

    public String getObsDt() {
        return obsDt;
    }

    public void setObsDt(String obsDt) {
        this.obsDt = obsDt;
    }

    public int getHowMany() {
        return howMany;
    }

    public void setHowMany(int howMany) {
        this.howMany = howMany;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }
}
