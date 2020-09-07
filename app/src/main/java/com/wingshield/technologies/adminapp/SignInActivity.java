package com.wingshield.technologies.adminapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import com.wingshield.technologies.adminapp.home.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignInActivity extends AppCompatActivity {
    @BindView(R.id.et_user_id)
    TextInputEditText user_id;
    @BindView(R.id.et_password)
    TextInputEditText password;
    @BindView(R.id.button_login)
    Button login_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        ButterKnife.bind(this);
        login_button.setOnClickListener(view -> {
            if (TextUtils.isEmpty(user_id.getText().toString())){
                user_id.setError("User Id");
                user_id.requestFocus();
            }else if (TextUtils.isEmpty(password.getText().toString())) {
                password.setError("Password");
                password.requestFocus();
            }else {
                UserLoginMethod(user_id.getText().toString(),password.getText().toString());
            }


        });

    }

    private void UserLoginMethod(String user_id, String password) {

        startActivity(new Intent(getApplicationContext(), MainActivity.class));

    }
}