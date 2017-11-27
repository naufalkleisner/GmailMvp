package com.nbs.starter.base.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ghiyatshanif on 4/17/17.
 */

public class ResponseModel {
    @SerializedName("code")
    private String code;
    @SerializedName("status")
    private int status;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
