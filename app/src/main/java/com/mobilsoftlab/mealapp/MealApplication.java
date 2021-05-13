package com.mobilsoftlab.mealapp;

import android.app.Application;
import android.content.Context;

import com.mobilsoftlab.mealapp.view.ViewModule;

public class MealApplication extends Application {

    public static MealApplicationComponent injector;
    private static Context context;


    @Override
    public void onCreate() {
        super.onCreate();
        MealApplication.context = getApplicationContext();
        injector = DaggerMealApplicationComponent.builder().viewModule(
                new ViewModule(this)
        ).build();
    }

    public static Context getAppContext() {
        return MealApplication.context;
    }
}
