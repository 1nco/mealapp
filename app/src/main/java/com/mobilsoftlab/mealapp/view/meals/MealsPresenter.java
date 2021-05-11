package com.mobilsoftlab.mealapp.view.meals;

import androidx.room.Room;

import com.mobilsoftlab.mealapp.MealApplication;
import com.mobilsoftlab.mealapp.interactor.meal.GetMealEvent;
import com.mobilsoftlab.mealapp.interactor.meal.MealInteractor;
import com.mobilsoftlab.mealapp.model.category.CategoryItem;
import com.mobilsoftlab.mealapp.model.category.CategoryListDatabase;
import com.mobilsoftlab.mealapp.model.meal.MealItem;
import com.mobilsoftlab.mealapp.model.meal.MealListDatabase;
import com.mobilsoftlab.mealapp.network.io.swagger.client.model.Category;
import com.mobilsoftlab.mealapp.network.io.swagger.client.model.Meal;
import com.mobilsoftlab.mealapp.view.Presenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MealsPresenter extends Presenter<MealsScreen> {

    private static MealsPresenter instance = null;
    private Executor networkExecutor;
    private MealInteractor mealsInteractor;
    private List<Meal> prevResult = null;

    private MealsPresenter() {
        networkExecutor = Executors.newFixedThreadPool(1);
        mealsInteractor = new MealInteractor();
    }

    public static MealsPresenter getInstance() {
        if (instance == null) {
            instance = new MealsPresenter();
        }
        return instance;
    }

    @Override
    public void attachScreen(MealsScreen screen) {
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

    public void refreshMeals(final String mealsQuery) {
        networkExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mealsInteractor.getMeals(mealsQuery);
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(final GetMealEvent event) {
        if (event.getThrowable() != null) {
            event.getThrowable().printStackTrace();
            if (screen != null) {
                screen.showNetworkError(event.getThrowable().getMessage());
            }
        } else {
            if (screen != null) {
                prevResult = event.getMeals();
                refreshDb();
                screen.showMeals(event.getMeals());
            }
        }
    }

    public void deleteAllItemsFromDb() {
        MealListDatabase db = Room.databaseBuilder(MealApplication.getAppContext(), MealListDatabase.class, "meals-db").build();
        for (MealItem mealItem: db.mealItemDao().getAll()) {
            db.mealItemDao().deleteItem(mealItem);
        }
    }

    public void refreshDb() {
        networkExecutor.execute(new Runnable() {
            @Override
            public void run() {
                addItemsToDb();
            }
        });
    }

    public void addItemsToDb() {
        deleteAllItemsFromDb();
        MealListDatabase db = Room.databaseBuilder(MealApplication.getAppContext(), MealListDatabase.class, "meals-db").build();
        for (Meal meal: prevResult) {
            MealItem mealItem = new MealItem();
            mealItem.id = meal.getIdMeal();
            mealItem.name = meal.getStrMeal();
            mealItem.thumbnail = meal.getStrMealThumb();
            db.mealItemDao().insertAll(mealItem);
        }
    }


    public List<Meal> getPrevResult() {
        return prevResult;
    }
}
