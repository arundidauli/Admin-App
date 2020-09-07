package com.wingshield.technologies.adminapp.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.wingshield.technologies.adminapp.R;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.SlideInBottomAnimationAdapter;

public class MainActivity extends AppCompatActivity {
    private static String TAG=MainActivity.class.getSimpleName();
    private Context context;
    private List<PanelItems> panelItemsList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context=MainActivity.this;
        panelItemsList=new ArrayList<>();
        setRecyclerView();
    }

    private void setRecyclerView() {
        RecyclerView recyclerView=findViewById(R.id.rv_panel);
        PanelAdapter panelAdapter = new PanelAdapter(context, panelItemsList);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(context, 3);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        SlideInBottomAnimationAdapter alphaInAnimationAdapter = new SlideInBottomAnimationAdapter(new PanelAdapter(context, panelItemsList));
        alphaInAnimationAdapter.setDuration(1000);
        alphaInAnimationAdapter.setInterpolator(new AccelerateDecelerateInterpolator());
        alphaInAnimationAdapter.setFirstOnly(false);
        recyclerView.setAdapter(new ScaleInAnimationAdapter(alphaInAnimationAdapter));
        recyclerView.setAdapter(alphaInAnimationAdapter);
    }
}