package com.wingshield.technologies.adminapp.product;

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
public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder> {
    private Context context;
    private List<Product> productList;


    public ProductAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_item_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.txt_title.setText(productList.get(position).getProduct_name());
        Glide.with(context).load(productList.get(position).getProduct_image())
                .placeholder(R.mipmap.room)
                .into(holder.product_image);

        holder.img_edit.setOnClickListener(view -> {

        });
        holder.img_delete.setOnClickListener(view -> {

        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView txt_title, txt_location, txt_distance, txt_rating, txt_reviews;
        private ImageView img_edit, img_delete, product_image;
        private RelativeLayout rl_layout;

        MyViewHolder(View view) {
            super(view);
            txt_title = view.findViewById(R.id.txt_title);
            txt_location = view.findViewById(R.id.txt_location);
            txt_distance = view.findViewById(R.id.txt_distance);
            txt_rating = view.findViewById(R.id.txt_rating);
            txt_reviews = view.findViewById(R.id.txt_reviews);
            product_image = view.findViewById(R.id.product_image);
            img_edit = view.findViewById(R.id.img_edit);
            rl_layout = view.findViewById(R.id.rl_layout);
            img_delete = view.findViewById(R.id.img_delete);


        }
    }
}
