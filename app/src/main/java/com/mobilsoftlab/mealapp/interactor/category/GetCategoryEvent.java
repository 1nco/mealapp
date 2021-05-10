package com.mobilsoftlab.mealapp.interactor.category;

import com.mobilsoftlab.mealapp.network.io.swagger.client.model.Category;

import java.util.List;

public class GetCategoryEvent {

    private List<Category> categories;
    private Throwable throwable;


    public GetCategoryEvent() {
    }

    public GetCategoryEvent(List<Category> categories, Throwable throwable) {

        this.categories = categories;
        this.throwable = throwable;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
