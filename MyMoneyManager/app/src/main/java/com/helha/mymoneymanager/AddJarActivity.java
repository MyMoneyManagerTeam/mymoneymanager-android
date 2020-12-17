package com.helha.mymoneymanager;

import android.app.AppComponentFactory;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import model.jar.Jar;
import repository.JarRepository;

public class AddJarActivity extends AppCompatActivity {
    private EditText et_name_jar;
    private EditText et_description_jar;
    private EditText et_balance_jar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_jar);

        et_name_jar = findViewById(R.id.etNameJar);
        et_description_jar = findViewById(R.id.etDescriptionJar);
        et_balance_jar = findViewById(R.id.etBalanceJar);

    }


    public void addJar(View v) {
        String newDescription = et_description_jar.getText().toString();
        String newName = et_name_jar.getText().toString();
        String newLimit = et_balance_jar.getText().toString();

        if((!newDescription.isEmpty()) && (!newName.isEmpty()) && (!newLimit.isEmpty())) {
            Jar newJar = new Jar(null, null, newDescription, newName, Float.parseFloat(newLimit), 0);
            JarRepository jarRepository = new JarRepository();
            jarRepository.create(getToken(), newJar).observe(this, new Observer<Jar>() {
                @Override
                public void onChanged(Jar jar) {
                    Log.i("addJar", "onChanged: " + jar);
                }
            });
            finish();
        }else{
            Toast toast = Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_LONG);
            toast.show();
        }
    }

    private String getToken()
    {
        //Reception du SHARED PREFERENCE disponible et recopie du userToken dans le fragment.
        SharedPreferences preferences = this.getSharedPreferences("USERTOKENSHARED", Context.MODE_PRIVATE);
        return preferences.getString("TOKEN", "No user ID");
    }
}
