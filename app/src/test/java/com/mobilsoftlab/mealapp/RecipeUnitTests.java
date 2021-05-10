package com.mobilsoftlab.mealapp;

import com.mobilsoftlab.mealapp.network.io.swagger.client.api.MealApi;
import com.mobilsoftlab.mealapp.network.io.swagger.client.model.CategoryListFilter;
import com.mobilsoftlab.mealapp.network.io.swagger.client.model.MealList;

import org.junit.Test;

public class RecipeUnitTests {
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
