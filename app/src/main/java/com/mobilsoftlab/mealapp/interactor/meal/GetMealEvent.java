package com.mobilsoftlab.mealapp.interactor.meal;

import com.mobilsoftlab.mealapp.network.io.swagger.client.model.Meal;

import java.util.List;

public class GetMealEvent {
    private List<Meal> meals;
    private Throwable throwable;


    public GetMealEvent() {
    }

    public GetMealEvent(List<Meal> categories, Throwable throwable) {

        this.meals = categories;
        this.throwable = throwable;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }

    public List<Meal> getMeals() {
        return meals;
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }
}
