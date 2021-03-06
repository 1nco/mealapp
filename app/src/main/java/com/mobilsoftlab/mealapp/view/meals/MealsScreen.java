package com.mobilsoftlab.mealapp.view.meals;

import com.mobilsoftlab.mealapp.network.io.swagger.client.model.Meal;

import java.util.List;

public interface MealsScreen {
    void showMeals(List<Meal> meals);

    void showNetworkError(String errorMsg);
}
