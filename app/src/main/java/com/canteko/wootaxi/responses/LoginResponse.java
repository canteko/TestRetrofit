package com.canteko.wootaxi.responses;

import com.canteko.wootaxi.responses.base.Config;
import com.canteko.wootaxi.responses.base.ResponseBase;
import com.canteko.wootaxi.responses.data.LoginData;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResponse extends ResponseBase {

    @SerializedName("data")
    @Expose
    private LoginData data;

    public LoginResponse() {
    }

    public LoginResponse(boolean status, String message, Config config, String serverDate, String error,LoginData data) {
        super(status, message, config, serverDate, error);
        this.data = data;
    }

    public LoginData getData() {
        return this.data;
    }

    public void setData(LoginData data) {
        this.data = data;
    }

    public LoginResponse withData(LoginData data){
        this.data = data;
        return this;
    }
}
