package com.mobilsoftlab.mealapp.view.recipe;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.mobilsoftlab.mealapp.R;
import com.mobilsoftlab.mealapp.network.io.swagger.client.model.MealDetails;

import com.mobilsoftlab.mealapp.view.meals.MealsActivity;

public class RecipeFragment extends Fragment implements RecipeScreen {
    private TextView tvName;
    private TextView tvIngredients;
    private TextView tvInstructions;
    private ImageView imageView;

    private MealDetails recipe;

    private String mealId = "";

    @Override
    public void onAttach(final Context context) {
        super.onAttach(context);

        mealId = getActivity().getIntent().getStringExtra(MealsActivity.KEY_MEAL_ID);
        RecipePresenter.getInstance().attachScreen(this);
    }

    @Override
    public void onDetach() {
        RecipePresenter.getInstance().detachScreen();
        super.onDetach();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recipe_layout, container, false);

        recipe = new MealDetails();
        RecipePresenter.getInstance().refreshRecipe(mealId);

        tvName = view.findViewById(R.id.recipe_name);
        tvIngredients = view.findViewById(R.id.recipe_ing);
        tvInstructions = view.findViewById(R.id.recipe_inst);
        imageView = view.findViewById(R.id.recipe_picture);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (RecipePresenter.getInstance().getPrevResult() == null) {
            RecipePresenter.getInstance().refreshRecipe(mealId);
        } else {
            showRecipe(RecipePresenter.getInstance().getPrevResult());
        }
        RecipePresenter.getInstance().refreshRecipe(mealId);
    }

    public void showRecipe(MealDetails mealDetails) {

        recipe = null;
        recipe = mealDetails;

        setData();
    }

    public void setData() {
        tvName.setText(recipe.getStrMeal());
        Glide.with(getContext()).load(recipe.getStrMealThumb()).into(imageView);
        tvIngredients.setText(getIngredients());
        tvInstructions.setText(recipe.getStrInstructions());
    }

    @Override
    public void showNetworkError(String errorMsg) {
        Toast.makeText(getContext(), errorMsg, Toast.LENGTH_LONG).show();
    }

    public String getIngredients() {
        String ingredientsString = "";
        ingredientsString += buildIngString(recipe.getStrIngredient1(), recipe.getStrMeasure1());
        ingredientsString += buildIngString(recipe.getStrIngredient2(), recipe.getStrMeasure2());
        ingredientsString += buildIngString(recipe.getStrIngredient3(), recipe.getStrMeasure3());
        ingredientsString += buildIngString(recipe.getStrIngredient4(), recipe.getStrMeasure4());
        ingredientsString += buildIngString(recipe.getStrIngredient5(), recipe.getStrMeasure5());
        ingredientsString += buildIngString(recipe.getStrIngredient6(), recipe.getStrMeasure6());
        ingredientsString += buildIngString(recipe.getStrIngredient7(), recipe.getStrMeasure7());
        ingredientsString += buildIngString(recipe.getStrIngredient8(), recipe.getStrMeasure8());
        ingredientsString += buildIngString(recipe.getStrIngredient9(), recipe.getStrMeasure9());
        ingredientsString += buildIngString(recipe.getStrIngredient10(), recipe.getStrMeasure10());
        ingredientsString += buildIngString(recipe.getStrIngredient11(), recipe.getStrMeasure11());
        ingredientsString += buildIngString(recipe.getStrIngredient12(), recipe.getStrMeasure12());
        ingredientsString += buildIngString(recipe.getStrIngredient13(), recipe.getStrMeasure13());
        ingredientsString += buildIngString(recipe.getStrIngredient14(), recipe.getStrMeasure14());
        ingredientsString += buildIngString(recipe.getStrIngredient15(), recipe.getStrMeasure15());
        ingredientsString += buildIngString(recipe.getStrIngredient16(), recipe.getStrMeasure16());
        ingredientsString += buildIngString(recipe.getStrIngredient17(), recipe.getStrMeasure17());
        ingredientsString += buildIngString(recipe.getStrIngredient18(), recipe.getStrMeasure18());
        ingredientsString += buildIngString(recipe.getStrIngredient19(), recipe.getStrMeasure19());
        ingredientsString += buildIngString(recipe.getStrIngredient20(), recipe.getStrMeasure20());
        return ingredientsString;
    }

    public String buildIngString(String ing, String measure) {
        if (ing != null && !ing.isEmpty()) {
         return measure + " " + ing + "\n";
        } else {
            return "";
        }
    }
}
