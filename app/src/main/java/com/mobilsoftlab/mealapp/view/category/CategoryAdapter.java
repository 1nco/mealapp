package com.mobilsoftlab.mealapp.view.category;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mobilsoftlab.mealapp.R;
import com.mobilsoftlab.mealapp.network.io.swagger.client.model.Category;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    private Context context;
    private List<Category> categories;

    public CategoryAdapter(Context context, List<Category> categories) {
        this.context = context;
        this.categories = categories;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.category_item, viewGroup, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Category category = categories.get(position);
        if (category.getStrCategoryThumb() != null) {
            Glide.with(context).load(category.getStrCategoryThumb()).into(holder.ivImage);
        }
        holder.tvName.setText(category.getStrCategory());
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }


    protected static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView ivImage;
        public TextView tvName;

        public ViewHolder(View itemView) {
            super(itemView);
            ivImage = itemView.findViewById(R.id.category_imageView);
            tvName = itemView.findViewById(R.id.category_name);
        }
    }
}
