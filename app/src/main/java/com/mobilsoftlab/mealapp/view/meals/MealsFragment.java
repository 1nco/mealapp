package com.mobilsoftlab.mealapp.view.meals;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.mobilsoftlab.mealapp.R;
import com.mobilsoftlab.mealapp.network.io.swagger.client.model.Meal;
import com.mobilsoftlab.mealapp.view.category.CategoryActivity;

import java.util.ArrayList;
import java.util.List;

public class MealsFragment extends Fragment implements MealsScreen {
    private RecyclerView recyclerViewMeals;
    private SwipeRefreshLayout swipeRefreshLayoutArtists;
    private List<Meal> mealList;
    private MealAdapter artistsAdapter;

    private String category = "Seafood";

    @Override
    public void onAttach(final Context context) {
        super.onAttach(context);

        category = getActivity().getIntent().getStringExtra(CategoryActivity.KEY_CATEGORY);
        String headerName = getString(R.string.nav_category_meals);
        headerName = "Meals in " + category + "category";
        MealsPresenter.getInstance().attachScreen(this);
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
        artistsAdapter = new MealAdapter(getContext(), mealList);
        recyclerViewMeals.setAdapter(artistsAdapter);

        swipeRefreshLayoutArtists = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayoutCategories);

        swipeRefreshLayoutArtists.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                MealsPresenter.getInstance().refreshMeals(category);
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
        if (swipeRefreshLayoutArtists != null) {
            swipeRefreshLayoutArtists.setRefreshing(false);
        }

        mealList.clear();
        mealList.addAll(meals);
        artistsAdapter.notifyDataSetChanged();

        if (mealList.isEmpty()) {
            recyclerViewMeals.setVisibility(View.GONE);
//            tvEmpty.setVisibility(View.VISIBLE);
        } else {
            recyclerViewMeals.setVisibility(View.VISIBLE);
//            tvEmpty.setVisibility(View.GONE);
        }
    }

    @Override
    public void showNetworkError(String errorMsg) {
        if (swipeRefreshLayoutArtists != null) {
            swipeRefreshLayoutArtists.setRefreshing(false);
        }
        Toast.makeText(getContext(), errorMsg, Toast.LENGTH_LONG).show();
    }
}
