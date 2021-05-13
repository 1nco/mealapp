package com.mobilsoftlab.mealapp;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.mobilsoftlab.mealapp.network.io.swagger.client.api.MealApi;
import com.mobilsoftlab.mealapp.network.io.swagger.client.model.MealList;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class RecipeTests {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.mobilsoftlab.mealapp", appContext.getPackageName());
    }

    @Test
    public void recipe_call_test() {
        MealApi mealApi = new MealApi();
        try {
            MealList mealList = mealApi.getMealById("52772");
            assert mealList != null;
            assert !mealList.getMeals().isEmpty();
            assert mealList.getMeals().get(0) != null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}