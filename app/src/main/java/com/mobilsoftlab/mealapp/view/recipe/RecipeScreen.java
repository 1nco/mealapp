package com.mobilsoftlab.mealapp.view.recipe;

import com.mobilsoftlab.mealapp.network.io.swagger.client.model.MealDetails;


public interface RecipeScreen {
    void showRecipe(MealDetails recipe);

    void showNetworkError(String errorMsg);
}
