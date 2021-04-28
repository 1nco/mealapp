package com.mobilsoftlab.mealapp.model.meal;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity(tableName = "MealItem")
public class MealItem {

    @ColumnInfo(name = "id")
    public String id;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "thumbnail")
    public String thumbnail;
}
