package com.lcd.birding_backend.models;

public class BirdPayload {
    private String speciesCode;
    private String comName;
    private String sciName;
    private String locId;
    private String locName;
    private String obsDt;
    private int howMany;
    private double lat;
    private double lng;
    private boolean obsValid;
    private boolean obsReviewed;
    private boolean locationPrivate;
    private String subId;

    public BirdPayload(String speciesCode, String comName, String sciName, String locId, String locName, String obsDt, int howMany, double lat, double lng, boolean obsValid, boolean obsReviewed, boolean locationPrivate, String subId) {
        this.speciesCode = speciesCode;
        this.comName = comName;
        this.sciName = sciName;
        this.locId = locId;
        this.locName = locName;
        this.obsDt = obsDt;
        this.howMany = howMany;
        this.lat = lat;
        this.lng = lng;
        this.obsValid = obsValid;
        this.obsReviewed = obsReviewed;
        this.locationPrivate = locationPrivate;
        this.subId = subId;
    }

    public BirdPayload() {
    }

    public String getSpeciesCode() {
        return speciesCode;
    }

    public void setSpeciesCode(String speciesCode) {
        this.speciesCode = speciesCode;
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

    public String getLocId() {
        return locId;
    }

    public void setLocId(String locId) {
        this.locId = locId;
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

    public boolean isObsValid() {
        return obsValid;
    }

    public void setObsValid(boolean obsValid) {
        this.obsValid = obsValid;
    }

    public boolean isObsReviewed() {
        return obsReviewed;
    }

    public void setObsReviewed(boolean obsReviewed) {
        this.obsReviewed = obsReviewed;
    }

    public boolean isLocationPrivate() {
        return locationPrivate;
    }

    public void setLocationPrivate(boolean locationPrivate) {
        this.locationPrivate = locationPrivate;
    }

    public String getSubId() {
        return subId;
    }

    public void setSubId(String subId) {
        this.subId = subId;
    }
}
