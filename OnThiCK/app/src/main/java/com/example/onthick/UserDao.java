package com.example.onthick;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {

    @Insert
    void InsertUser(User user);

    @Query("Select * from users")
    List<User> getAllUser();

    @Delete
    void Delete_1_user (User user);

    @Query("UPDATE users SET `name_user` = :name , `adress_user` = :adress WHERE id_user = :id")
    void update(int id,String name,String adress);

}
