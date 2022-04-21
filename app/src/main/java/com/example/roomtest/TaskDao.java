package com.example.roomtest;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TaskDao {
   // @Query("SELECT * FROM tasks WHERE task LIKE :vipul AND last_name LIKE :last")
    @Query("SELECT * FROM tasks WHERE task LIKE :vipul AND `desc` LIKE 'vipul meeting today'")
    /*@Query("SELECT * FROM tasks " +
            "WHERE task LIKE '% :vipul %'")*/
    //List<Tasks> getAll(String vipul);
    public abstract List<Tasks>  getAll(String vipul);

    @Query("SELECT * FROM tasks ")
    List<Tasks> getSome();

    @Insert
    void insert(Tasks tasks);

    @Delete
    void delete(Tasks tasks);

    @Update
    void update(Tasks tasks);



}
