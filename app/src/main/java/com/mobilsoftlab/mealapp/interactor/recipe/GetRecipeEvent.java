package com.mobilsoftlab.mealapp.interactor.recipe;
import com.mobilsoftlab.mealapp.network.io.swagger.client.model.MealDetails;


public class GetRecipeEvent {
    private MealDetails recipe;
    private Throwable throwable;


    public GetRecipeEvent() {
    }

    public GetRecipeEvent(MealDetails mealDetails, Throwable throwable) {
        this.recipe = mealDetails;
        this.throwable = throwable;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }

    public MealDetails getRecipe() {
        return recipe;
    }

    public void setRecipe(MealDetails recipe) {
        this.recipe = recipe;
    }
}
