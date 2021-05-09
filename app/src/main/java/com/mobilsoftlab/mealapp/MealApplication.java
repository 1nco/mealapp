package com.mobilsoftlab.mealapp;

import android.app.Application;

import com.mobilsoftlab.mealapp.view.ViewModule;

public class MealApplication extends Application {

    public static MealApplicationComponent injector;

    @Override
    public void onCreate() {
        super.onCreate();
        injector = DaggerMealApplicationComponent.builder().viewModule(
                new ViewModule(this)
        ).build();
    }
}
