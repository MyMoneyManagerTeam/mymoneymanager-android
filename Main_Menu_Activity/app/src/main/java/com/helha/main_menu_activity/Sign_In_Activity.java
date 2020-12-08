package com.helha.main_menu_activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import api.User.LoginRequest;
import model.User;
import repository.AuthenticateRepository;

public class Sign_In_Activity extends AppCompatActivity {

    public static final String KEY_EXTRA_TOKEN = String.valueOf(R.string.token);
    Button validationButton;
    EditText et_username;
    EditText et_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        final LoadingDialog loadingDialog = new LoadingDialog(Sign_In_Activity.this);
        validationButton = findViewById(R.id.btn_validation);
        et_username = findViewById(R.id.et_username);
        et_password = findViewById(R.id.et_password);

        final AuthenticateRepository authenticateRepository = new AuthenticateRepository();



        validationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                authenticateRepository.authenticate(new LoginRequest(et_username.getText().toString(),et_password.getText().toString())).observe(Sign_In_Activity.this, new Observer<User>() {
                    @Override
                    public void onChanged(User user) {
                        if(user != null)
                        {
                            //accès à l'activité Home
                            GoToActivityHome(user);
                            authenticateRepository.testToken(user.getJWTBearer());
                        }
                        else
                        {
                            Toast toast = Toast.makeText(Sign_In_Activity.this.getApplicationContext(),"Username/password incorrext",Toast.LENGTH_LONG);
                            toast.show();

                        }
                    }
                });
            }
        });
    }

    private void GoToActivityHome(User user) {
        Intent intent = new Intent(Sign_In_Activity.this, Home_Activity.class);
        intent.putExtra(KEY_EXTRA_TOKEN, user.getJWTBearer());
        startActivity(intent);
    }



}