package com.wingshield.technologies.adminapp.home;

import android.content.Context;
import android.content.Intent;
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
