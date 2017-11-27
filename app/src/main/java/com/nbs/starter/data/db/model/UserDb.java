package com.nbs.starter.data.db.model;

import com.nbs.starter.data.api.model.User;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

// Sample entity model

@Entity(nameInDb = "user")
public class UserDb {

    @Id(autoincrement = true)
    private Long id;

    @Property(nameInDb = "name")
    private String name;

    @Property(nameInDb = "created_at")
    private String createdAt;

    @Property(nameInDb = "updated_at")
    private String updatedAt;


    @Generated(hash = 1483682345)
    public UserDb(Long id, String name, String createdAt, String updatedAt) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @Generated(hash = 334083576)
    public UserDb() {
    }
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }



//    mapper method to map User object to userDb
    public static UserDb map(User user){
        UserDb userDb = new UserDb();

        userDb.setId(user.getId());
        userDb.setCreatedAt(user.getCreatedAt());
        userDb.setName(user.getName());
        userDb.setUpdatedAt(user.getUpdatedAt());

        return userDb;
    }
}