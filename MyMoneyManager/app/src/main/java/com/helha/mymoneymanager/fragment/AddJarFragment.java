package com.helha.mymoneymanager.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.helha.mymoneymanager.R;

import org.xml.sax.Parser;

import model.jar.Jar;
import repository.JarRepository;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddJarFragment extends Fragment implements View.OnClickListener {
    private EditText et_name_jar;
    private EditText et_description_jar;
    private EditText et_balance_jar;
    private Button btn_create_jar;

    public AddJarFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_add_jar, container, false);
        et_name_jar = view.findViewById(R.id.etNameJar);
        et_description_jar = view.findViewById(R.id.etDescriptionJar);
        et_balance_jar = view.findViewById(R.id.etBalanceJar);
        btn_create_jar = view.findViewById(R.id.btn_create_jar);

        btn_create_jar.setOnClickListener(this);


        return view;
    }

    @Override
    public void onClick(View v) {
        Jar newJar = new Jar(null,null,et_description_jar.getText().toString(),et_name_jar.getText().toString(), Integer.parseInt(et_balance_jar.getText().toString()),0);

        JarRepository jarRepository = new JarRepository();
        jarRepository.create(getToken(),newJar).observe(getViewLifecycleOwner(), new Observer<Jar>() {
            @Override
            public void onChanged(Jar jar) {
                Log.i("addJar", "onChanged: "+jar);
            }
        });
    }

    private String getToken()
    {
        //Reception du SHARED PREFERENCE disponible et recopie du userToken dans le fragment.
        SharedPreferences preferences = this.getActivity().getSharedPreferences("USERTOKENSHARED", Context.MODE_PRIVATE);
        return preferences.getString("TOKEN", "No user ID");
    }


}