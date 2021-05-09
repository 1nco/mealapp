package com.mobilsoftlab.mealapp.interactor.category;

import com.mobilsoftlab.mealapp.network.io.swagger.client.model.Category;

import java.util.List;

public class GetCategoryEvent {
//    private int code;
    private List<Category> categories;
    private Throwable throwable;

    //<editor-fold desc="Constructors|Getters|Setters">

    public GetCategoryEvent() {
    }

    public GetCategoryEvent(/*int code,*/ List<Category> categories, Throwable throwable) {
//        this.code = code;
        this.categories = categories;
        this.throwable = throwable;
    }

//    public int getCode() {
//        return code;
//    }
//
//    public void setCode(int code) {
//        this.code = code;
//    }

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
