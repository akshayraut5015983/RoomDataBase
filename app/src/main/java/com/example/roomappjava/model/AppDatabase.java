package com.example.roomappjava.model;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Task.class,TaskAk.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    public abstract TaskDao taskDao();
    public abstract TaskAkDao  taskAkDao();
}

