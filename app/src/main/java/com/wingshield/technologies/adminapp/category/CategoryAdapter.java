package com.wingshield.technologies.adminapp.category;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.wingshield.technologies.adminapp.R;

import java.util.List;

/**
 * Created by Arun Kumar on 14/8/20.
 * Copyright (c) 2020  Wingshield Technologies
 */
public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder> {
    private Context context;
    private List<ProductCategory> productCategoryList;


    public CategoryAdapter(Context context, List<ProductCategory> productCategoryList) {
        this.context = context;
        this.productCategoryList = productCategoryList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_category_item_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.txt_category_name.setText(productCategoryList.get(position).getCategory_name());
        Glide.with(context).load(productCategoryList.get(position).getCategory_image()).placeholder(R.mipmap.ic_launcher).into(holder.category_image);


        holder.img_edit.setOnClickListener(view -> {

        });
        holder.img_delete.setOnClickListener(view -> {

        });
    }

    @Override
    public int getItemCount() {
        return productCategoryList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView txt_category_name;
        private ImageView img_edit, img_delete, category_image;
        private RelativeLayout rl_layout;

        MyViewHolder(View view) {
            super(view);
            img_edit = view.findViewById(R.id.img_edit);
            img_delete = view.findViewById(R.id.img_delete);
            txt_category_name = view.findViewById(R.id.txt_category_name);
            category_image = view.findViewById(R.id.category_image);


        }
    }
}
