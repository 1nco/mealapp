package com.mobilsoftlab.mealapp.model.meal;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

public interface MealItemDao {


    @Query("SELECT * FROM MealItem")
    List<MealItem> getAll();

    @Insert
    void insertAll(MealItem... mealItems);

    @Update
    void update(MealItem mealItem);

    @Delete
    void deleteItem(MealItem mealItem);
}
