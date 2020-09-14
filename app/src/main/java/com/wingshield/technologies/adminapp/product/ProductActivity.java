package com.wingshield.technologies.adminapp.product;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.wingshield.technologies.adminapp.R;
import com.wingshield.technologies.adminapp.utils.Constant;
import com.wingshield.technologies.adminapp.utils.Prefs;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.SlideInBottomAnimationAdapter;

public class ProductActivity extends AppCompatActivity {
    private static String TAG = ProductActivity.class.getSimpleName();
    private Context context;
    private List<Product> productList;
    private ProgressDialog progressDialog;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        context = ProductActivity.this;
        initView();
        GetProduct();

    }

    void initView() {
        progressDialog = new ProgressDialog(context);
        productList = new ArrayList<>();
        TextView title = findViewById(R.id.txt_title);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = firebaseDatabase.getReference(Prefs.getInstance(context).GetValue(Constant.USER_ID));
        Intent intent = getIntent();
        if (intent.getStringExtra("title") != null) {
            title.setText(intent.getStringExtra("title"));
        }
        FloatingActionButton floatingActionButton = findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(view -> {
            Intent i = new Intent(getApplicationContext(), AddProductActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
        });
    }

    private void setRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.rv_product);
        // PanelAdapter panelAdapter = new PanelAdapter(context, productList);
        // RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(context, 3);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);
        SlideInBottomAnimationAdapter alphaInAnimationAdapter = new SlideInBottomAnimationAdapter(new ProductAdapter(context, productList));
        alphaInAnimationAdapter.setDuration(1000);
        alphaInAnimationAdapter.setInterpolator(new AccelerateDecelerateInterpolator());
        alphaInAnimationAdapter.setFirstOnly(false);
        recyclerView.setAdapter(new ScaleInAnimationAdapter(alphaInAnimationAdapter));
        recyclerView.setAdapter(alphaInAnimationAdapter);
    }

    private void GetProduct() {
        progressDialog.show();
        progressDialog.setMessage(Constant.PLEASE_WAIT);
        ValueEventListener eventListener = new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                progressDialog.dismiss();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    if (ds.exists()) {
                        Product student = ds.getValue(Product.class);
                        productList.add(student);

                    }
                }
                setRecyclerView();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", databaseError.toException());
                progressDialog.dismiss();

            }
        };
        mDatabaseReference.child("product").addListenerForSingleValueEvent(eventListener);


    }
}