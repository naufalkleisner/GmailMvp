package com.nbs.starter.data.api.model.error;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ghiyatshanif on 2/20/17.
 */

public class ApiError {

    @SerializedName("statusCode")
    private int statusCode;
    @SerializedName("name")
    private String name;
    @SerializedName("message")
    private String message;
    @SerializedName("status")
    private int status;
    @SerializedName("code")
    private String code;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "ApiError{" +
                "statusCode=" + statusCode +
                ", name='" + name + '\'' +
                ", message='" + message + '\'' +
                ", status=" + status +
                ", code='" + code + '\'' +
                '}';
    }
}
