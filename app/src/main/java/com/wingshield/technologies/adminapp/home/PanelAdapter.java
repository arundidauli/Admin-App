package com.wingshield.technologies.adminapp.home;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wingshield.technologies.adminapp.R;
import com.wingshield.technologies.adminapp.category.CategoryActivity;
import com.wingshield.technologies.adminapp.product.ProductActivity;

import java.util.List;

/**
 * Created by Arun Kumar on 14/8/20.
 * Copyright (c) 2020  Wingshield Technologies
 */
public class PanelAdapter extends RecyclerView.Adapter<PanelAdapter.MyViewHolder> {
    private Context context;
    private List<PanelItems> panelItems;


    public PanelAdapter(Context context, List<PanelItems> panelItems) {
        this.context = context;
        this.panelItems = panelItems;
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
        holder.ll_root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (position == 2) {
                    Intent intent = new Intent(context, CategoryActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.putExtra("title", "Product Category");
                    context.startActivity(intent);
                } else {
                    Intent intent = new Intent(context, ProductActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.putExtra("title", "Product");
                    context.startActivity(intent);
                }


            }
        });


    }

    @Override
    public int getItemCount() {
        return 9;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView txt_location, txt_name_age, txt_online;
        private ImageView img_user, img_status;
        private LinearLayout ll_root;

        MyViewHolder(View view) {
            super(view);
            ll_root = view.findViewById(R.id.ll_root);


        }
    }
}
