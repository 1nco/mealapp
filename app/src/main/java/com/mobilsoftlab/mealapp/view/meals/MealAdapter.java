package com.mobilsoftlab.mealapp.view.meals;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mobilsoftlab.mealapp.R;

import com.mobilsoftlab.mealapp.network.io.swagger.client.model.Meal;

import java.util.List;

public class MealAdapter extends RecyclerView.Adapter<MealAdapter.ViewHolder> {

    private Context context;
    private List<Meal> meals;

    private static ClickListener clickListener;

    public MealAdapter(Context context, List<Meal> meals) {
        this.context = context;
        this.meals = meals;
    }

    public MealAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.category_item, viewGroup, false);
        return new MealAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MealAdapter.ViewHolder holder, int position) {
        Meal meal = meals.get(position);
        if (meal.getStrMealThumb() != null) {
            Glide.with(context).load(meal.getStrMealThumb()).into(holder.ivImage);
        }
        holder.tvName.setText(meal.getStrMeal());
    }

    @Override
    public int getItemCount() {
        return meals.size();
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView ivImage;
        public TextView tvName;

        public ViewHolder(View itemView) {
            super(itemView);
            ivImage = itemView.findViewById(R.id.category_imageView);
            tvName = itemView.findViewById(R.id.category_name);
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            clickListener.onItemClick(getAdapterPosition(), v);
        }
    }

    public void setOnItemClickListener(ClickListener clickListener) {
        MealAdapter.clickListener = clickListener;
    }

    public interface ClickListener {
        void onItemClick(int position, View v);
    }
}
