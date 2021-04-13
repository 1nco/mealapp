package com.mobilsoftlab.mealapp;

import com.mobilsoftlab.mealapp.view.ViewModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ViewModule.class)
public interface MealApplicationComponent {

    void inject(MainActivity mainActivity);

}
