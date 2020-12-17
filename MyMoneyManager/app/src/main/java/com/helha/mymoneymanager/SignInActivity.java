package com.helha.mymoneymanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.VideoView;


import apiServices.LoginRequest;
import model.User;
import repository.AuthenticateRepository;

public class SignInActivity extends AppCompatActivity {

    public static final String KEY_EXTRA_TOKEN = String.valueOf(R.string.token);
    Button validationButton;
    EditText et_username;
    EditText et_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);



        /*String pkgName = getApplicationContext().getPackageName();
        int resID = getApplicationContext().getResources().getIdentifier("mountainvideo", "raw", pkgName);
        Uri uri = Uri.parse("android.resource://" + this.getApplicationContext().getPackageName() + "/"+ resID);

        VideoView simpleVideoView = (VideoView) findViewById(R.id.videoView); // initiate a video view
        simpleVideoView.setVideoURI(uri);
        simpleVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });
        simpleVideoView.start();*/

        validationButton = findViewById(R.id.btn_validation);
        et_username = findViewById(R.id.et_username);
        et_password = findViewById(R.id.et_password);
        final AuthenticateRepository authenticateRepository = new AuthenticateRepository();

        validationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                authenticateRepository.authenticate(new LoginRequest(et_username.getText().toString(), et_password.getText().toString())).observe(SignInActivity.this, new Observer<User>() {
                    @Override
                    public void onChanged(User user) {
                        if (user != null) {

                            //accès à l'activité Home
                            GoToActivityHome(user);
                            Log.i("connexion", "onChanged: c'est ok");
                            authenticateRepository.testToken(user.getJWTBearer());
                            storeToken(user);
                        } else {
                            Toast toast = Toast.makeText(SignInActivity.this.getApplicationContext(), "Mail/Password incorrect", Toast.LENGTH_LONG);
                            toast.show();
                        }
                    }
                });
            }
        });
    }

    private void GoToActivityHome(User user) {
        Intent intent = new Intent(SignInActivity.this, HomeActivity.class);
        startActivity(intent);
    }

    private void storeToken(User user){
        //Je stocke dans l'endroit USERTOKENSHARED LE TOKEN => userToken pour communiquer avec mes fragments
        SharedPreferences preferences = this.getBaseContext().getSharedPreferences("USERTOKENSHARED", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("TOKEN", user.getJWTBearer());
        editor.putString("USERID",user.getId());
        editor.putString("USERNAME",user.getLastName());
        editor.putString("MAIL",user.getMail());
        Log.i("qrcode", "SignInActivity: "+user.getLastName());
        editor.apply();
    }



}