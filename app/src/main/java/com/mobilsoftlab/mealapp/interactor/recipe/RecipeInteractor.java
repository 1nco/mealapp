package com.mobilsoftlab.mealapp.interactor.recipe;

import com.mobilsoftlab.mealapp.interactor.meal.GetMealEvent;
import com.mobilsoftlab.mealapp.network.io.swagger.client.api.MealApi;
import com.mobilsoftlab.mealapp.network.io.swagger.client.model.CategoryListFilter;
import com.mobilsoftlab.mealapp.network.io.swagger.client.model.MealList;

import org.greenrobot.eventbus.EventBus;

public class RecipeInteractor {
    MealApi mealApi;


    public RecipeInteractor() {
        this.mealApi = new MealApi();
    }

    public void getRecipe(String query) {

        GetRecipeEvent event = new GetRecipeEvent();
        try {
            MealList mealList = mealApi.getMealById(query);
            event.setRecipe(mealList.getMeals().get(0));
            EventBus.getDefault().post(event);
        } catch (Exception e) {
            event.setThrowable(e);
            EventBus.getDefault().post(event);
        }
    }
}
