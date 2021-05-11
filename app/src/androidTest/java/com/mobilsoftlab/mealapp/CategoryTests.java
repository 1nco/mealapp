package com.mobilsoftlab.mealapp;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.mobilsoftlab.mealapp.model.category.CategoryItem;
import com.mobilsoftlab.mealapp.model.category.CategoryListDatabase;
import com.mobilsoftlab.mealapp.network.io.swagger.client.api.MealApi;
import com.mobilsoftlab.mealapp.network.io.swagger.client.model.CategoryList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class CategoryTests {

    private CategoryListDatabase db;
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.mobilsoftlab.mealapp", appContext.getPackageName());
    }

    @Test
    public void category_call_test() {
        MealApi mealApi = new MealApi();
        try {
            CategoryList categoryList = mealApi.getCategories();
            assert categoryList != null;
            assert !categoryList.getCategories().isEmpty();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, CategoryListDatabase.class).build();
    }

    @Test
    public void room_category_test() {
        CategoryItem categoryItem = new CategoryItem();
        categoryItem.id = "1";
        categoryItem.name = "name";
        categoryItem.thumbnail = "thumb";
        categoryItem.description = "desc";
        db.categoryItemDao().insertAll(categoryItem);
        List<CategoryItem> categoryItemList = db.categoryItemDao().getAll();
        assert !categoryItemList.isEmpty();
        assert categoryItemList.get(0).id.equals("1");
    }

    @Test
    public void updateDb() throws Exception {
        CategoryItem categoryItem = new CategoryItem();
        categoryItem.id = "1";
        categoryItem.name = "name";
        categoryItem.thumbnail = "thumb";
        categoryItem.description = "desc";
        db.categoryItemDao().insertAll(categoryItem);
        categoryItem.name = "name-update-test";

        db.categoryItemDao().update(categoryItem);

        List<CategoryItem> getItem = db.categoryItemDao().getAll();

        assertEquals(getItem.get(0).name, categoryItem.name);

    }

    @Test
    public void deleteItemDb() throws Exception {
        CategoryItem categoryItem = new CategoryItem();
        int size = db.categoryItemDao().getAll().size();
        categoryItem.id = "1";
        categoryItem.name = "name";
        categoryItem.thumbnail = "thumb";
        categoryItem.description = "desc";
        db.categoryItemDao().insertAll(categoryItem);
        db.categoryItemDao().deleteItem(categoryItem);


        assertEquals(db.categoryItemDao().getAll().size(), size);
    }
}