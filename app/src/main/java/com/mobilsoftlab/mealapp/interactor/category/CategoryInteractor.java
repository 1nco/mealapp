package com.mobilsoftlab.mealapp.interactor.category;
import com.mobilsoftlab.mealapp.network.io.swagger.client.api.MealApi;
import com.mobilsoftlab.mealapp.network.io.swagger.client.model.CategoryList;
import org.greenrobot.eventbus.EventBus;

public class CategoryInteractor {
    MealApi mealApi;


    public CategoryInteractor() {
        this.mealApi = new MealApi();
    }

    public void getCategories() {

        GetCategoryEvent event = new GetCategoryEvent();
        try {
            CategoryList response = mealApi.getCategories();
            event.setCategories(response.getCategories());
            EventBus.getDefault().post(event);
        } catch (Exception e) {
            event.setThrowable(e);
            EventBus.getDefault().post(event);
        }
    }
}
