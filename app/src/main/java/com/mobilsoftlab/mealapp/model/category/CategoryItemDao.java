package com.mobilsoftlab.mealapp.model.category;


import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

public interface CategoryItemDao {

    @Query("SELECT * FROM CategoryItem")
    List<CategoryItem> getAll();

    @Insert
    void insertAll(CategoryItem... categoryItem);

    @Update
    void update(CategoryItem categoryItem);

    @Delete
    void deleteItem(CategoryItem categoryItem);
}
