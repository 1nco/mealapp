package com.mobilsoftlab.mealapp;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.mobilsoftlab.mealapp.model.meal.MealItem;
import com.mobilsoftlab.mealapp.model.meal.MealListDatabase;
import com.mobilsoftlab.mealapp.network.io.swagger.client.api.MealApi;
import com.mobilsoftlab.mealapp.network.io.swagger.client.model.CategoryListFilter;

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
public class MealsTests {

    private MealListDatabase db;
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.mobilsoftlab.mealapp", appContext.getPackageName());
    }

    @Test
    public void meals_list_call_test() {
        MealApi mealApi = new MealApi();
        try {
            CategoryListFilter categoryListFilter = mealApi.getMealsByCategory("Seafood");
            assert categoryListFilter != null;
            assert !categoryListFilter.getMeals().isEmpty();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, MealListDatabase.class).build();
    }

    @Test
    public void room_category_test() {
        MealItem mealItem = new MealItem();
        mealItem.id = "1";
        mealItem.name = "name";
        mealItem.thumbnail = "thumb";
        db.mealItemDao().insertAll(mealItem);
        List<MealItem> mealItems = db.mealItemDao().getAll();
        assert !mealItems.isEmpty();
        assert mealItems.get(0).id.equals("1");
    }

    @Test
    public void updateDb() throws Exception {
        MealItem mealItem = new MealItem();
        mealItem.id = "2";
        mealItem.name = "name";
        mealItem.thumbnail = "thumb";
        db.mealItemDao().insertAll(mealItem);
        mealItem.name = "name-update-test";

        db.mealItemDao().update(mealItem);

        List<MealItem> getItem = db.mealItemDao().getAll();

        assertEquals(getItem.get(0).name, mealItem.name);

    }

    @Test
    public void deleteItemDb() throws Exception {
        int size = db.mealItemDao().getAll().size();
        MealItem mealItem = new MealItem();
        mealItem.id = "3";
        mealItem.name = "name";
        mealItem.thumbnail = "thumb";
        db.mealItemDao().insertAll(mealItem);
        db.mealItemDao().deleteItem(mealItem);


        assertEquals(db.mealItemDao().getAll().size(), size);
    }
}