package com.mobilsoftlab.mealapp.view.meals;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.mobilsoftlab.mealapp.R;
import com.mobilsoftlab.mealapp.network.io.swagger.client.model.Category;
import com.mobilsoftlab.mealapp.network.io.swagger.client.model.Meal;
import com.mobilsoftlab.mealapp.view.category.CategoryActivity;
import com.mobilsoftlab.mealapp.view.category.CategoryAdapter;
import com.mobilsoftlab.mealapp.view.category.CategoryPresenter;
import com.mobilsoftlab.mealapp.view.recipe.RecipeActivity;

import java.util.ArrayList;
import java.util.List;

public class MealsFragment extends Fragment implements MealsScreen {
    private FirebaseAnalytics mFirebaseAnalytics;

    private RecyclerView recyclerViewMeals;
    private SwipeRefreshLayout swipeRefreshLayoutMeals;
    private List<Meal> mealList;
    private MealAdapter mealAdapter;

    private String category = "Seafood";

    @Override
    public void onAttach(final Context context) {
        super.onAttach(context);

        category = getActivity().getIntent().getStringExtra(CategoryActivity.KEY_CATEGORY);
        MealsPresenter.getInstance().attachScreen(this);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(getContext());
    }

    @Override
    public void onDetach() {
        MealsPresenter.getInstance().detachScreen();
        super.onDetach();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.category_layout, container, false);
        recyclerViewMeals = (RecyclerView) view.findViewById(R.id.recyclerView);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewMeals.setLayoutManager(llm);

        mealList = new ArrayList<>();
        mealAdapter = new MealAdapter(getContext(), mealList);
        recyclerViewMeals.setAdapter(mealAdapter);

        swipeRefreshLayoutMeals = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayoutCategories);

        swipeRefreshLayoutMeals.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                MealsPresenter.getInstance().refreshMeals(category);
            }
        });

        mealAdapter.setOnItemClickListener(new MealAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Intent intent = new Intent(getActivity(), RecipeActivity.class);
                if (mealList.isEmpty()) {
                    mealList = MealsPresenter.getInstance().getPrevResult();
                }
                Meal meal = mealList.get(position);
                intent.putExtra(MealsActivity.KEY_MEAL_ID, meal.getIdMeal());
                Bundle bundle = new Bundle();
                bundle.putString(FirebaseAnalytics.Param.ITEM_ID, meal.getIdMeal());
                bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, meal.getStrMeal());
                bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "meal");
                mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
                startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (MealsPresenter.getInstance().getPrevResult() == null) {
            MealsPresenter.getInstance().refreshMeals(category);
        } else {
            showMeals(MealsPresenter.getInstance().getPrevResult());
        }
        MealsPresenter.getInstance().refreshMeals(category);
    }

    public void showMeals(List<Meal> meals) {
        if (swipeRefreshLayoutMeals != null) {
            swipeRefreshLayoutMeals.setRefreshing(false);
        }

        mealList.clear();
        mealList.addAll(meals);
        mealAdapter.notifyDataSetChanged();

        if (mealList.isEmpty()) {
            recyclerViewMeals.setVisibility(View.GONE);
        } else {
            recyclerViewMeals.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void showNetworkError(String errorMsg) {
        if (swipeRefreshLayoutMeals != null) {
            swipeRefreshLayoutMeals.setRefreshing(false);
        }
        Toast.makeText(getContext(), errorMsg, Toast.LENGTH_LONG).show();
    }
}
