package com.mobilsoftlab.mealapp.model.category;

import androidx.room.Database;
import androidx.room.RoomDatabase;
@Database(
        entities = {CategoryItem.class},
        version = 1
)
public abstract class CategoryListDatabase extends RoomDatabase {
    public abstract CategoryItemDao categoryItemDao();
}
