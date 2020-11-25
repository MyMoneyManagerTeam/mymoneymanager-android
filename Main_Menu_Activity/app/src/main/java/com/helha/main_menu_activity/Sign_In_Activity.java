package com.helha.main_menu_activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

public class Sign_In_Activity extends AppCompatActivity {

    Button validationButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        final LoadingDialog loadingDialog = new LoadingDialog(Sign_In_Activity.this);
        validationButton = findViewById(R.id.btn_validation);

        validationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingDialog.startAlertDialog();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadingDialog.dismissDialog();
                        //Test sur les données de la base de donnée
                        //********** START ********
                        GoToActivityHome();
                        //*********** END *********
                    }
                }, 5000);
            }
        });
    }

    private void GoToActivityHome() {

        Intent intent = new Intent(Sign_In_Activity.this, Home_Activity.class);
        startActivity(intent);
    }
}