package com.wingshield.technologies.adminapp.home;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.wingshield.technologies.adminapp.R;
import com.wingshield.technologies.adminapp.utils.Constant;
import com.wingshield.technologies.adminapp.utils.Prefs;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.SlideInBottomAnimationAdapter;

public class MainActivity extends AppCompatActivity {
    private static String TAG = MainActivity.class.getSimpleName();
    private Context context;
    private List<PanelItems> panelItemsList;
    @BindView(R.id.txt_user)
    TextView user;
    @BindView(R.id.img_notification)
    ImageView notification_icon;
    @BindView(R.id.img_logout)
    ImageView logout_icon;
    @BindView(R.id.user_image)
    CircleImageView profile_picture;
    private Boolean exit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MainActivity.this;
        ButterKnife.bind(this);
        initView();
        setRecyclerView();
    }

    void initView() {
        panelItemsList = new ArrayList<>();
        Intent intent = getIntent();
        if (intent.getStringExtra(Constant.USER_ID) != null && intent.getStringExtra(Constant.EMAIL_ID) != null) {
            Prefs.getInstance(context).SetValue(Constant.USER_ID, intent.getStringExtra(Constant.USER_ID));
            Prefs.getInstance(context).SetValue(Constant.EMAIL_ID, intent.getStringExtra(Constant.EMAIL_ID));
            user.setText(Prefs.getInstance(context).GetValue(Constant.EMAIL_ID));
        }
        if (intent.getStringExtra(Constant.PROFILE_PICTURE) != null) {
            Glide.with(context).load(intent.getStringExtra(Constant.PROFILE_PICTURE))
                    .into(profile_picture);
        }

        notification_icon.setOnClickListener(view -> {
            Open_Notification();
        });

        logout_icon.setOnClickListener(view -> {
            Logout();
        });
    }

    private void Open_Notification() {

    }

    private void setRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.rv_panel);
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

    void Logout() {
        new AlertDialog.Builder(context)
                .setMessage("Are you sur want to exit?")
                .setCancelable(true)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseAuth.getInstance().signOut();
                        Prefs.getInstance(context).ClearAll();
                        finishAffinity();
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        }).show();
    }

    @Override
    public void onBackPressed() {

        if (exit) {
            finish(); // finish activity
        } else {
            Toast.makeText(this, "Press Back again to Exit.",
                    Toast.LENGTH_SHORT).show();
            exit = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    exit = false;
                }
            }, 2 * 1000);

        }
    }
}