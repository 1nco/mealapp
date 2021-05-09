package com.mobilsoftlab.mealapp.interactor.category;

import com.mobilsoftlab.mealapp.MealApplication;
import com.mobilsoftlab.mealapp.network.io.swagger.client.api.MealApi;
import com.mobilsoftlab.mealapp.network.io.swagger.client.model.Category;
import com.mobilsoftlab.mealapp.network.io.swagger.client.model.CategoryList;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import javax.inject.Inject;

public class CategoryInteractor {
    MealApi mealApi;

    @Inject
    public CategoryInteractor(/*MealApi mealApi*/) {
        this.mealApi = new MealApi();
        MealApplication.injector.inject(this);
    }

    public void getCategories() {

        GetCategoryEvent event = new GetCategoryEvent();
        try {
            CategoryList response = mealApi.getCategories();
//            if (response.code() != 200) {
//                throw new Exception("Result code is not 200");
//            }
//            event.setCode();
            event.setCategories(response.getCategories());
            EventBus.getDefault().post(event);
        } catch (Exception e) {
            event.setThrowable(e);
            EventBus.getDefault().post(event);
        }
    }
}
