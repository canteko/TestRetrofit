
package com.canteko.wootaxi.responses.base;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Config {

    @SerializedName("trackingEnabled")
    @Expose
    private boolean trackingEnabled;
    @SerializedName("actualVersion")
    @Expose
    private String actualVersion;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("secondsToGetGeolocation")
    @Expose
    private long secondsToGetGeolocation;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Config() {
    }

    /**
     * 
     * @param phone
     * @param trackingEnabled
     * @param actualVersion
     * @param secondsToGetGeolocation
     */
    public Config(boolean trackingEnabled, String actualVersion, String phone, long secondsToGetGeolocation) {
        super();
        this.trackingEnabled = trackingEnabled;
        this.actualVersion = actualVersion;
        this.phone = phone;
        this.secondsToGetGeolocation = secondsToGetGeolocation;
    }

    public boolean isTrackingEnabled() {
        return trackingEnabled;
    }

    public void setTrackingEnabled(boolean trackingEnabled) {
        this.trackingEnabled = trackingEnabled;
    }

    public Config withTrackingEnabled(boolean trackingEnabled) {
        this.trackingEnabled = trackingEnabled;
        return this;
    }

    public String getActualVersion() {
        return actualVersion;
    }

    public void setActualVersion(String actualVersion) {
        this.actualVersion = actualVersion;
    }

    public Config withActualVersion(String actualVersion) {
        this.actualVersion = actualVersion;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Config withPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public long getSecondsToGetGeolocation() {
        return secondsToGetGeolocation;
    }

    public void setSecondsToGetGeolocation(long secondsToGetGeolocation) {
        this.secondsToGetGeolocation = secondsToGetGeolocation;
    }

    public Config withSecondsToGetGeolocation(long secondsToGetGeolocation) {
        this.secondsToGetGeolocation = secondsToGetGeolocation;
        return this;
    }

}
