package com.mobilsoftlab.mealapp.view.category;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Toast;

import androidx.annotation.NonNull;

import androidx.fragment.app.Fragment;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.mobilsoftlab.mealapp.MealApplication;
import com.mobilsoftlab.mealapp.R;

import com.mobilsoftlab.mealapp.network.io.swagger.client.model.Category;
import com.mobilsoftlab.mealapp.view.meals.MealsActivity;


import java.util.ArrayList;

import java.util.List;


public class CategoryFragment extends Fragment implements CategoryScreen {
    private FirebaseAnalytics mFirebaseAnalytics;

    private RecyclerView recyclerView;
    private List<Category> categories;
    private CategoryAdapter categoryAdapter;

    private SwipeRefreshLayout swipeRefreshLayoutCategories;

    @Override
    public void onAttach(final Context context) {
        super.onAttach(context);
        CategoryPresenter.getInstance().attachScreen(this);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(getContext());
    }

    @Override
    public void onDetach() {
        CategoryPresenter.getInstance().detachScreen();
        super.onDetach();
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.category_layout, container, false);

        recyclerView = root.findViewById(R.id.recyclerView);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);

        categories = new ArrayList<>();
        categoryAdapter = new CategoryAdapter(getContext(), categories);
        recyclerView.setAdapter(categoryAdapter);
        swipeRefreshLayoutCategories = (SwipeRefreshLayout) root.findViewById(R.id.swipeRefreshLayoutCategories);


        swipeRefreshLayoutCategories.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                CategoryPresenter.getInstance().refreshCategories();
            }
        });

        categoryAdapter.setOnItemClickListener(new CategoryAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Intent intent = new Intent(getActivity(), MealsActivity.class);
                if (categories.isEmpty()) {
                    categories = CategoryPresenter.getInstance().getPrevResult();
                }
                Category category = categories.get(position);
                intent.putExtra(CategoryActivity.KEY_CATEGORY, category.getStrCategory());
                Bundle bundle = new Bundle();
                bundle.putString(FirebaseAnalytics.Param.ITEM_ID, category.getIdCategory());
                bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, category.getStrCategory());
                bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "category");
                mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
                startActivity(intent);
            }
        });
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (CategoryPresenter.getInstance().getPrevResult() == null) {
            CategoryPresenter.getInstance().refreshCategories();
        } else {
            showCategories(CategoryPresenter.getInstance().getPrevResult());
        }
        CategoryPresenter.getInstance().refreshCategories();
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
