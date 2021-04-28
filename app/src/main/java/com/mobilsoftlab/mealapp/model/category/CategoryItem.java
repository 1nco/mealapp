package com.mobilsoftlab.mealapp.model.category;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity(tableName = "CategoryItem")
public class CategoryItem {

    @ColumnInfo(name = "id")
    public String id;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "thumbnail")
    public String thumbnail;

    @ColumnInfo(name = "description")
    public String description;
}
