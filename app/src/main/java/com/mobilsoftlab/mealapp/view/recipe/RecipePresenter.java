package com.mobilsoftlab.mealapp.view.recipe;

import com.mobilsoftlab.mealapp.interactor.meal.GetMealEvent;
import com.mobilsoftlab.mealapp.interactor.recipe.GetRecipeEvent;
import com.mobilsoftlab.mealapp.interactor.recipe.RecipeInteractor;
import com.mobilsoftlab.mealapp.network.io.swagger.client.model.MealDetails;
import com.mobilsoftlab.mealapp.view.Presenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class RecipePresenter extends Presenter<RecipeScreen> {
    private static RecipePresenter instance = null;
    private Executor networkExecutor;
    private RecipeInteractor recipeInteractor;
    private MealDetails prevResult = null;

    private RecipePresenter() {
        networkExecutor = Executors.newFixedThreadPool(1);
        recipeInteractor = new RecipeInteractor();
    }

    public static RecipePresenter getInstance() {
        if (instance == null) {
            instance = new RecipePresenter();
        }
        return instance;
    }

    @Override
    public void attachScreen(RecipeScreen screen) {
        super.attachScreen(screen);
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    public void detachScreen() {
        EventBus.getDefault().unregister(this);
        super.detachScreen();
    }

    public void refreshRecipe(final String recipeQuery) {
        networkExecutor.execute(new Runnable() {
            @Override
            public void run() {
                recipeInteractor.getRecipe(recipeQuery);
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(final GetRecipeEvent event) {
        if (event.getThrowable() != null) {
            event.getThrowable().printStackTrace();
            if (screen != null) {
                screen.showNetworkError(event.getThrowable().getMessage());
            }
        } else {
            if (screen != null) {
                prevResult = event.getRecipe();
                screen.showRecipe(event.getRecipe());
            }
        }
    }

    public MealDetails getPrevResult() {
        return prevResult;
    }
}
