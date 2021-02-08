package com.example.roomappjava.model;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TaskAkDao {

    @Query("SELECT * FROM taskAk")
    List<TaskAk> getAll();

    @Insert
    void insert(TaskAk task);



}