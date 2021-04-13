package com.mobilsoftlab.mealapp;

import com.mobilsoftlab.mealapp.view.ViewModule;
import com.mobilsoftlab.mealapp.view.category.CategoryActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ViewModule.class)
public interface MealApplicationComponent {

    void inject(CategoryActivity categoryActivity);

}
