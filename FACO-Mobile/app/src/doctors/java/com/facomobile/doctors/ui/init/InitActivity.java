package com.facomobile.doctors.ui.init;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.facomobile.R;
import com.facomobile.doctors.utilities.CustomNavUtils;

public class InitActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init);

        Button btnSignUp = findViewById(R.id.btn_sign_up);
        btnSignUp.setOnClickListener(view -> CustomNavUtils.launchSignUp(this));

        TextView tvLogin = findViewById(R.id.tv_login);
        tvLogin.setOnClickListener(view -> CustomNavUtils.launchLogin(this));
    }
}
