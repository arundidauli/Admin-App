package com.wingshield.technologies.adminapp.product;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wingshield.technologies.adminapp.R;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.SlideInBottomAnimationAdapter;

public class ProductActivity extends AppCompatActivity {
    private static String TAG = ProductActivity.class.getSimpleName();
    private Context context;
    private List<Product> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        context = ProductActivity.this;
        productList = new ArrayList<>();
        TextView title = findViewById(R.id.txt_title);

        Intent intent = getIntent();
        if (intent.getStringExtra("title") != null) {
            title.setText(intent.getStringExtra("title"));
        }
        setRecyclerView();
    }

    private void setRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.rv_panel);
        // PanelAdapter panelAdapter = new PanelAdapter(context, productList);
        // RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(context, 3);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        SlideInBottomAnimationAdapter alphaInAnimationAdapter = new SlideInBottomAnimationAdapter(new ProductAdapter(context, productList));
        alphaInAnimationAdapter.setDuration(1000);
        alphaInAnimationAdapter.setInterpolator(new AccelerateDecelerateInterpolator());
        alphaInAnimationAdapter.setFirstOnly(false);
        recyclerView.setAdapter(new ScaleInAnimationAdapter(alphaInAnimationAdapter));
        recyclerView.setAdapter(alphaInAnimationAdapter);
    }
}