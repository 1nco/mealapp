package com.mobilsoftlab.mealapp;

import com.mobilsoftlab.mealapp.network.io.swagger.client.api.MealApi;
import com.mobilsoftlab.mealapp.network.io.swagger.client.model.CategoryListFilter;

import org.junit.Test;

public class MealsUnitTests {
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
}
