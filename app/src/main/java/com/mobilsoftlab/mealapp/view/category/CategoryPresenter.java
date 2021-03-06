package com.mobilsoftlab.mealapp.view.category;

import androidx.room.Room;

import com.mobilsoftlab.mealapp.MealApplication;
import com.mobilsoftlab.mealapp.interactor.category.CategoryInteractor;
import com.mobilsoftlab.mealapp.interactor.category.GetCategoryEvent;
import com.mobilsoftlab.mealapp.model.category.CategoryItem;
import com.mobilsoftlab.mealapp.model.category.CategoryListDatabase;
import com.mobilsoftlab.mealapp.network.io.swagger.client.model.Category;
import com.mobilsoftlab.mealapp.view.Presenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class CategoryPresenter extends Presenter<CategoryScreen> {
    private static CategoryPresenter instance = null;

    Executor networkExecutor;
    CategoryInteractor categoryInteractor;

    private List<Category> prevResult = null;

    public CategoryPresenter() {
        this.networkExecutor = Executors.newFixedThreadPool(1);
        this.categoryInteractor = new CategoryInteractor();
    }

    public static CategoryPresenter getInstance() {
        if (instance == null) {
            instance = new CategoryPresenter();
        }
        return instance;
    }

    @Override
    public void attachScreen(CategoryScreen screen) {
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

    public void refreshCategories() {
        networkExecutor.execute(new Runnable() {
            @Override
            public void run() {
                categoryInteractor.getCategories();
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(final GetCategoryEvent event) {
        if (event.getThrowable() != null) {
            event.getThrowable().printStackTrace();
            if (screen != null) {
                screen.showNetworkError(event.getThrowable().getMessage());
            }
        } else {
            if (screen != null) {
                prevResult = event.getCategories();
                refreshDb();
                screen.showCategories(event.getCategories());
            }
        }
    }

    public void deleteAllItemsFromDb() {
        CategoryListDatabase db = Room.databaseBuilder(MealApplication.getAppContext(), CategoryListDatabase.class, "category-db").build();
        for (CategoryItem categoryItem: db.categoryItemDao().getAll()) {
            db.categoryItemDao().deleteItem(categoryItem);
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
        CategoryListDatabase db = Room.databaseBuilder(MealApplication.getAppContext(), CategoryListDatabase.class, "category-db").build();
        for (Category category: prevResult) {
            CategoryItem categoryItem = new CategoryItem();
            categoryItem.id = category.getIdCategory();
            categoryItem.name = category.getStrCategory();
            categoryItem.thumbnail = category.getStrCategoryThumb();
            categoryItem.description = category.getStrCategoryDescription();
            db.categoryItemDao().insertAll(categoryItem);
        }
    }


    public List<Category> getPrevResult() {
        return prevResult;
    }
}
