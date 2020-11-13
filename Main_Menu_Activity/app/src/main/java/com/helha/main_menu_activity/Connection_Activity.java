package com.helha.main_menu_activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Connection_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection_);
    }

    public void GoToActivitySignIn(View view) {
        Intent intent = new Intent(Connection_Activity.this, Sign_In_Activity.class);

        startActivity(intent);
    }
}