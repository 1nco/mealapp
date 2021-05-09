package com.mobilsoftlab.mealapp.view.category;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.mobilsoftlab.mealapp.MealApplication;
import com.mobilsoftlab.mealapp.R;
import com.mobilsoftlab.mealapp.network.io.swagger.client.ApiCallback;
import com.mobilsoftlab.mealapp.network.io.swagger.client.ApiClient;
import com.mobilsoftlab.mealapp.network.io.swagger.client.ApiException;
import com.mobilsoftlab.mealapp.network.io.swagger.client.api.MealApi;
import com.mobilsoftlab.mealapp.network.io.swagger.client.model.Category;
import com.mobilsoftlab.mealapp.network.io.swagger.client.model.CategoryList;
import com.mobilsoftlab.mealapp.network.io.swagger.client.model.CategoryListFilter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class CategoryFragment extends Fragment implements CategoryScreen {


    @Inject
    CategoryPresenter categoryPresenter;

    private CategoryViewModel categoryViewModel;

    private RecyclerView recyclerView;
    private TextView textView;
    private List<Category> categories;
    private CategoryAdapter categoryAdapter;

    private SwipeRefreshLayout swipeRefreshLayoutCategories;

    public CategoryFragment() {
        MealApplication.injector.inject(this);
    }

    @Override
    public void onAttach(final Context context) {
        super.onAttach(context);

        categoryPresenter.attachScreen(this);
    }

    @Override
    public void onDetach() {
        categoryPresenter.detachScreen();
        super.onDetach();
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        categoryViewModel =
                new ViewModelProvider(this).get(CategoryViewModel.class);
        View root = inflater.inflate(R.layout.category_layout, container, false);

        recyclerView = root.findViewById(R.id.recyclerView);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);

        categories = new ArrayList<>();
        categoryAdapter = new CategoryAdapter(getContext(), categories);
        recyclerView.setAdapter(categoryAdapter);


        swipeRefreshLayoutCategories.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                categoryPresenter.refreshCategories();
            }
        });
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        categoryPresenter.refreshCategories();
    }

    public void showCategories(List<Category> categoryList) {
        if (swipeRefreshLayoutCategories != null) {
            swipeRefreshLayoutCategories.setRefreshing(false);
        }

        categories.clear();
        categories.addAll(categoryList);
        categoryAdapter.notifyDataSetChanged();

        if (categories.isEmpty()) {
            recyclerView.setVisibility(View.GONE);
        } else {
            recyclerView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void showNetworkError(String errorMsg) {
        if (swipeRefreshLayoutCategories != null) {
            swipeRefreshLayoutCategories.setRefreshing(false);
        }
        Toast.makeText(getContext(), errorMsg, Toast.LENGTH_LONG).show();
    }


}
