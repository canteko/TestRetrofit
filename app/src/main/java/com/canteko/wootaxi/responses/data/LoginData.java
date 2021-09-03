
package com.canteko.wootaxi.responses.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginData {

    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("apiKey")
    @Expose
    private String apiKey;
    @SerializedName("isMaster")
    @Expose
    private boolean isMaster;

    /**
     * No args constructor for use in serialization
     * 
     */
    public LoginData() {
    }

    /**
     * 
     * @param apiKey
     * @param isMaster
     * @param id
     * @param email
     */
    public LoginData(String email, String id, String apiKey, boolean isMaster) {
        super();
        this.email = email;
        this.id = id;
        this.apiKey = apiKey;
        this.isMaster = isMaster;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LoginData withEmail(String email) {
        this.email = email;
        return this;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LoginData withId(String id) {
        this.id = id;
        return this;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public LoginData withApiKey(String apiKey) {
        this.apiKey = apiKey;
        return this;
    }

    public boolean isIsMaster() {
        return isMaster;
    }

    public void setIsMaster(boolean isMaster) {
        this.isMaster = isMaster;
    }

    public LoginData withIsMaster(boolean isMaster) {
        this.isMaster = isMaster;
        return this;
    }

}
