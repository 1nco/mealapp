package com.mobilsoftlab.mealapp;

import com.mobilsoftlab.mealapp.network.io.swagger.client.api.MealApi;
import com.mobilsoftlab.mealapp.network.io.swagger.client.model.CategoryList;

import org.junit.Test;

public class CategoryUnitTests {
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
}
