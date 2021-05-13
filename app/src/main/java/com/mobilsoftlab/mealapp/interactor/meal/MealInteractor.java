package com.mobilsoftlab.mealapp.interactor.meal;


import com.mobilsoftlab.mealapp.network.io.swagger.client.api.MealApi;
import com.mobilsoftlab.mealapp.network.io.swagger.client.model.CategoryListFilter;


import org.greenrobot.eventbus.EventBus;

public class MealInteractor {
    MealApi mealApi;


    public MealInteractor() {
        this.mealApi = new MealApi();
    }

    public void getMeals(String query) {

        GetMealEvent event = new GetMealEvent();
        try {
            CategoryListFilter response = mealApi.getMealsByCategory(query);
            event.setMeals(response.getMeals());
            EventBus.getDefault().post(event);
        } catch (Exception e) {
            event.setThrowable(e);
            EventBus.getDefault().post(event);
        }
    }
}
