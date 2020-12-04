package com.helha.main_menu_activity;

import android.content.SharedPreferences;
import android.graphics.Shader;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import android.security.keystore.KeyNotYetValidException;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import model.Accounts;
import model.Jar;
import model.JarAdapter;
import repository.AccountRepository;

/**
 * A simple {@link Fragment} subclass.
 */
public class EspaceFragment extends Fragment {

    private final List<Jar> jars = new ArrayList<>();
    private final AccountRepository accountRepository = new AccountRepository();
    private String userToken;
    public EspaceFragment() {
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
        View view = inflater.inflate(R.layout.fragment_espace, container, false);

        SharedPreferences Pref = getBaseContext().getSharedPreferences(, 0);
        String password = pref.getString("KEY_PASSWORD","Default Password");

        final TextView tvValueAccount = view.findViewById(R.id.tv_value_account);

        accountRepository.get(userToken).observe(EspaceFragment.this.getViewLifecycleOwner(), new Observer<Accounts>() {
            @Override
            public void onChanged(Accounts accounts) {
                tvValueAccount.setText(accounts.getBalance()+"€");
            }
        });


       GridView gvJar = view.findViewById(R.id.gv_jars);

       //valeur à changer avec la db
        //************** START *************
      //  jars.add(new Jar("Test",200));
       // jars.add(new Jar("Test",200));
       // jars.add(new Jar("Test",200));
        //************** END ***************
        JarAdapter jarAdapter = new JarAdapter(
                getContext(),
                R.id.gv_jars,
                jars
        );

        gvJar.setAdapter(jarAdapter);

        /*gvJar.setOnClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });*/
        return view;
    }
}