package com.wingshield.technologies.adminapp.startup;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.wingshield.technologies.adminapp.R;
import com.wingshield.technologies.adminapp.home.MainActivity;
import com.wingshield.technologies.adminapp.utils.Constant;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignInActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;
    @BindView(R.id.et_user_id)
    TextInputEditText user_id;
    @BindView(R.id.et_password)
    TextInputEditText password;
    @BindView(R.id.button_login)
    Button login_button;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        ButterKnife.bind(this);
        FirebaseApp.initializeApp(this);
        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        login_button.setOnClickListener(view -> {
            if (TextUtils.isEmpty(user_id.getText().toString())) {
                user_id.setError("User Id");
                user_id.requestFocus();
            } else if (TextUtils.isEmpty(password.getText().toString())) {
                password.setError("Password");
                password.requestFocus();
            } else {
                progressDialog.show();
                progressDialog.setMessage(Constant.PLEASE_WAIT);
                UserLoginMethod(user_id.getText().toString(), password.getText().toString());
            }


        });

    }

    private void UserLoginMethod(String User_id, String password) {
        // authenticate the user
        firebaseAuth.signInWithEmailAndPassword(User_id, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    progressDialog.dismiss();
                    Toast.makeText(SignInActivity.this, "Logged in Successfully", Toast.LENGTH_SHORT).show();
                    FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.putExtra(Constant.USER_ID, Objects.requireNonNull(firebaseUser).getUid());
                    intent.putExtra(Constant.EMAIL_ID, Objects.requireNonNull(firebaseUser).getEmail());
                    intent.putExtra(Constant.PROFILE_PICTURE, Objects.requireNonNull(firebaseUser).getPhotoUrl());
                    startActivity(intent);
                    finish();

                } else {
                    Toast.makeText(SignInActivity.this, "Please check your email id and password", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }

            }
        });

    }


    @Override
    public void onBackPressed() {
        SignInActivity.super.finish();
    }

}