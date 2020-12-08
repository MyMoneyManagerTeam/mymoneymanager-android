package com.helha.mymoneymanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.helha.mymoneymanager.R;

public class ConnectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection_);
    }

    public void GoToActivitySign(View view) {
        Intent intent = new Intent(ConnectionActivity.this, SignInActivity.class);
        startActivity(intent);
    }
}