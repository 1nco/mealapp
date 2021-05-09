package com.mobilsoftlab.mealapp.view.category;

import com.mobilsoftlab.mealapp.di.Network;
import com.mobilsoftlab.mealapp.interactor.category.CategoryInteractor;
import com.mobilsoftlab.mealapp.interactor.category.GetCategoryEvent;
import com.mobilsoftlab.mealapp.view.Presenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.concurrent.Executor;

import javax.inject.Inject;

public class CategoryPresenter extends Presenter<CategoryScreen> {
    Executor networkExecutor;
    CategoryInteractor categoryInteractor;

    @Inject
    public CategoryPresenter(@Network Executor networkExecutor, CategoryInteractor categoryInteractor) {
        this.networkExecutor = networkExecutor;
        this.categoryInteractor = categoryInteractor;
    }

    @Override
    public void attachScreen(CategoryScreen screen) {
        super.attachScreen(screen);
        EventBus.getDefault().register(this);
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
                screen.showCategories(event.getCategories());
            }
        }
    }
}
