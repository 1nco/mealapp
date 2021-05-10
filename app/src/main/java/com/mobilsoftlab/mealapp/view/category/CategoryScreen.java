package com.mobilsoftlab.mealapp.view.category;

import com.mobilsoftlab.mealapp.network.io.swagger.client.model.Category;

import java.util.List;

public interface CategoryScreen {
    void showCategories(List<Category> categories);

    void showNetworkError(String errorMsg);
}
