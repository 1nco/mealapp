package com.mobilsoftlab.mealapp.model.meal;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(
        entities = {MealItem.class},
        version = 1
)
public abstract class MealListDatabase extends RoomDatabase {
    public abstract MealItemDao mealItemDao();
}
