package com.mobilsoftlab.mealapp;

import com.mobilsoftlab.mealapp.interactor.category.CategoryInteractor;
import com.mobilsoftlab.mealapp.view.ViewModule;
import com.mobilsoftlab.mealapp.view.category.CategoryActivity;
import com.mobilsoftlab.mealapp.view.category.CategoryFragment;
import com.mobilsoftlab.mealapp.view.category.CategoryPresenter;
import com.mobilsoftlab.mealapp.view.meals.MealsActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ViewModule.class})
public interface MealApplicationComponent {

    void inject(CategoryActivity categoryActivity);

    void inject(MealsActivity mealsActivity);

    void inject(CategoryFragment categoryFragment);

    void inject(CategoryInteractor categoryInteractor);

    void inject(CategoryPresenter categoryPresenter);

}
