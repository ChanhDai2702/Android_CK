package com.example.onthick;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "users")
public class User implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id_user;

    private String name_user;
    private String adress_user;

    public User() {
    }

    public User(String name_user, String adress_user) {
        this.name_user = name_user;
        this.adress_user = adress_user;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getName_user() {
        return name_user;
    }

    public void setName_user(String name_user) {
        this.name_user = name_user;
    }

    public String getAdress_user() {
        return adress_user;
    }

    public void setAdress_user(String adress_user) {
        this.adress_user = adress_user;
    }
}
