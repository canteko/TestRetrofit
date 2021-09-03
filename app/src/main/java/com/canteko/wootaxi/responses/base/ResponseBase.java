
package com.canteko.wootaxi.responses.base;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseBase {

    @SerializedName("status")
    @Expose
    private boolean status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("config")
    @Expose
    private Config config;
    @SerializedName("serverDate")
    @Expose
    private String serverDate;
    @SerializedName("error")
    @Expose
    private String error;

    /**
     * No args constructor for use in serialization
     *
     */
    public ResponseBase() {
    }

    /**
     *
     * @param serverDate
     * @param message
     * @param config
     * @param status
     * @param error
     */
    public ResponseBase(boolean status, String message, Config config, String serverDate, String error) {
        super();
        this.status = status;
        this.message = message;
        this.config = config;
        this.serverDate = serverDate;
        this.error = error;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public ResponseBase withStatus(boolean status) {
        this.status = status;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResponseBase withMessage(String message) {
        this.message = message;
        return this;
    }

    public Config getConfig() {
        return config;
    }

    public void setConfig(Config config) {
        this.config = config;
    }

    public ResponseBase withConfig(Config config) {
        this.config = config;
        return this;
    }

    public String getServerDate() {
        return serverDate;
    }

    public void setServerDate(String serverDate) {
        this.serverDate = serverDate;
    }

    public ResponseBase withServerDate(String serverDate) {
        this.serverDate = serverDate;
        return this;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String toString() {
        Gson gson = new GsonBuilder().create();
        return gson.toJson(this);
    }
}
