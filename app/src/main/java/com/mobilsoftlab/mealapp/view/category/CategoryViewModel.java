package com.mobilsoftlab.mealapp.view.category;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CategoryViewModel extends ViewModel {

    private MutableLiveData<String> text;

    public CategoryViewModel() {
        text = new MutableLiveData<>();
        text.setValue("This is the category fragment fragment");
    }

    public LiveData<String> getText() {
        return text;
    }
}
