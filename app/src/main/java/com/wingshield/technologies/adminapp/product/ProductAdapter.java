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
                .inflate(R.layout.panel_item_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {


    }

    @Override
    public int getItemCount() {
        return 9;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView txt_location, txt_name_age, txt_online;
        private ImageView img_user, img_status;
        private RelativeLayout rl_layout;

        MyViewHolder(View view) {
            super(view);


        }
    }
}
