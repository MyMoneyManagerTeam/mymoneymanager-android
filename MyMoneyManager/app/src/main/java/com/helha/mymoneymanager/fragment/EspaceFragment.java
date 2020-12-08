package com.helha.mymoneymanager.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import com.helha.mymoneymanager.R;

import java.util.ArrayList;
import java.util.List;

import model.accounts.Accounts;
import model.jar.Jar;
import model.jar.JarAdapter;
import repository.AccountRepository;

/**
 * A simple {@link Fragment} subclass.
 */
public class EspaceFragment extends Fragment {

    private final List<Jar> jars = new ArrayList<>();
    private TextView tvValueAccount;
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
        View view = inflater.inflate(R.layout.fragment_espace, container, false);
        tvValueAccount = view.findViewById(R.id.tv_value_account);

        //Reception du SHARED PREFERENCE disponible et recopie du userToken dans le fragment.
        SharedPreferences preferences = this.getActivity().getSharedPreferences("USERTOKENSHARED", Context.MODE_PRIVATE);
        String userToken = preferences.getString("TOKEN", "No Token");
        Log.i("espace", "La récéption du token: "+ userToken);

        //Actualisation du getBalance de mon compte.
        Accounts accounts;
        AccountRepository accountRepository = new AccountRepository();
        accountRepository.get(userToken)
                .observe(this.getViewLifecycleOwner() , new Observer<Accounts>() {
                    @Override
                    public void onChanged(Accounts accounts) {
                        tvValueAccount.setText(accounts.getBalance()+"€");
                    }
                });

        //Gestion des jars
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