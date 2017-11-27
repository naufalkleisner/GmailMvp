package com.nbs.starter.data.api.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.nbs.starter.base.model.ResponseModel;

/**
 * Created by Kleisner on 11/22/2017.
 */

public class Message extends ResponseModel implements Parcelable {
    /**
     * id : 1
     * isImportant : false
     * picture : https://api.androidhive.info/json/google.png
     * from : Google Alerts
     * subject : Google Alert - android
     * message : Now android supports multiple voice recogonization
     * timestamp : 10:30 AM
     * isRead : false
     */

    private int id;
    private boolean isImportant;
    private String picture;
    private String from;
    private String subject;
    private String message;
    private String timestamp;
    private boolean isRead;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isIsImportant() {
        return isImportant;
    }

    public void setIsImportant(boolean isImportant) {
        this.isImportant = isImportant;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public boolean isIsRead() {
        return isRead;
    }

    public void setIsRead(boolean isRead) {
        this.isRead = isRead;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeByte(this.isImportant ? (byte) 1 : (byte) 0);
        dest.writeString(this.picture);
        dest.writeString(this.from);
        dest.writeString(this.subject);
        dest.writeString(this.message);
        dest.writeString(this.timestamp);
        dest.writeByte(this.isRead ? (byte) 1 : (byte) 0);
    }

    public Message() {
    }

    protected Message(Parcel in) {
        this.id = in.readInt();
        this.isImportant = in.readByte() != 0;
        this.picture = in.readString();
        this.from = in.readString();
        this.subject = in.readString();
        this.message = in.readString();
        this.timestamp = in.readString();
        this.isRead = in.readByte() != 0;
    }

    public static final Parcelable.Creator<Message> CREATOR = new Parcelable.Creator<Message>() {
        @Override
        public Message createFromParcel(Parcel source) {
            return new Message(source);
        }

        @Override
        public Message[] newArray(int size) {
            return new Message[size];
        }
    };
}
