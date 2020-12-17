package com.helha.mymoneymanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import model.jar.Jar;
import repository.JarRepository;

public class JarManagerActivity extends AppCompatActivity {

    private JarRepository jarRepository = new JarRepository();
    private EditText etName;
    private EditText etDescription;
    private EditText etBalance;
    private EditText etGoal;
    private ProgressBar pgGoal;
    private TextView txtPercentage;
    private String userToken;
    private Jar currentJar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jar_manager);

        currentJar = (Jar) getIntent().getSerializableExtra("Jar");

        SharedPreferences preferences = this.getSharedPreferences("USERTOKENSHARED", Context.MODE_PRIVATE);
        userToken = preferences.getString("TOKEN", "No Token");;

        etName = findViewById(R.id.etName);
        etDescription = findViewById(R.id.etDescription);
        etBalance = findViewById(R.id.etBalance);
        etGoal = findViewById(R.id.etGoal);
        pgGoal = findViewById(R.id.pgGoal);
        txtPercentage = findViewById(R.id.txtPercentage);
        etName.setText(currentJar.getName());
        etDescription.setText(currentJar.getDescription());
        etBalance.setText(Double.toString(currentJar.getBalance()));
        etGoal.setText(Double.toString(currentJar.getMax()));
        pgGoal.setMax((int) currentJar.getMax());
        pgGoal.setProgress((int) currentJar.getBalance());
        double percentage = (currentJar.getBalance() / currentJar.getMax())*100;
        txtPercentage.setText((int)percentage +"%");

    }

    public void updateJar(View view)
    {
        String newDescription = etDescription.getText().toString();
        String newName = etName.getText().toString();
        String newLimit = etGoal.getText().toString();
        String newBalance = etBalance.getText().toString();

        if(!newDescription.isEmpty() && !newName.isEmpty() && !newLimit.isEmpty() && !newBalance.isEmpty()) {
            Jar newUpdatedJar = new Jar(currentJar.getJar_id(), currentJar.getOwner(), newDescription, newName, Float.parseFloat(newLimit), Float.parseFloat(newBalance));
            jarRepository.update(userToken, newUpdatedJar).observe(this, new Observer<Jar>() {
                @Override
                public void onChanged(Jar jar) {
                    Log.i("Jar", "Updated: " + jar);
                }
            });
            finish();
        }else{
            Toast toast = Toast.makeText(this, "Veuillez remplir correctement tous les champs", Toast.LENGTH_LONG);
            toast.show();
        }
    }

    public void deleteJar(View view)
    {
        JarRepository jarRepository = new JarRepository();
        jarRepository.delete(userToken,currentJar.getJar_id()).observe(this, new Observer<Jar>() {
            @Override
            public void onChanged(Jar jar) {
                Log.i("Jar", "Deleted: "+ jar.toString());
            }
        });

        finish();
    }
}