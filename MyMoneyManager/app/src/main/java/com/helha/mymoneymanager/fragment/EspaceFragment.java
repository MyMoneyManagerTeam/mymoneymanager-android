package com.helha.mymoneymanager.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.helha.mymoneymanager.JarManagerActivity;
import com.helha.mymoneymanager.R;

import java.util.ArrayList;
import java.util.List;

import model.accounts.Accounts;
import model.jar.Jar;
import model.jar.JarAdapter;
import repository.AccountRepository;
import repository.JarRepository;

/**
 * A simple {@link Fragment} subclass.
 */
public class EspaceFragment extends Fragment {

    private final List<Jar> jars = new ArrayList<>();
    private boolean shouldRefreshOnResume = false;
    private JarRepository jarRepository = new JarRepository();
    private JarAdapter jarAdapter;
    private GridView gvJar;
    private String userToken;

    public EspaceFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_espace, container, false);

        SharedPreferences preferences = getActivity().getSharedPreferences("USERTOKENSHARED", Context.MODE_PRIVATE);
        userToken = preferences.getString("TOKEN", "No Token");

        //Déclaration et initialisation
        Accounts accounts;
        AccountRepository accountRepository = new AccountRepository();

        final TextView tvValueAccount;

        tvValueAccount = view.findViewById(R.id.tv_value_account);
        gvJar = view.findViewById(R.id.gv_jars);

        jarAdapter = new JarAdapter(getContext(), R.id.gv_jars, jars);
        gvJar.setAdapter(jarAdapter);

        //Actualisation du getBalance de mon compte.
        accountRepository.get(userToken).observe(this.getViewLifecycleOwner() , new Observer<Accounts>() {
            @Override
            public void onChanged(Accounts accounts) {
                tvValueAccount.setText(accounts.getBalance()+"€");
            }
        });

        loadMySpaces();

        return view;
    }

    public void loadMySpaces(){

        gvJar.setAdapter(jarAdapter);

        //Chargement de mes jars
        jarRepository.query(userToken).observe(this.getViewLifecycleOwner(), new Observer<List<Jar>>() {
            @Override
            public void onChanged(List<Jar> loadedJars) {
                jars.clear();
                jars.addAll(loadedJars);
                jarAdapter.notifyDataSetChanged();
            }
        });

        //ClickListener pour ouvrir une fenêtre pour chaque jar
        gvJar.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Jar currentJar = jars.get(position);
                Intent intent = new Intent(getActivity(), JarManagerActivity.class);
                intent.putExtra("Jar", currentJar);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        // Check should we need to refresh the fragment
        if(shouldRefreshOnResume){
            Log.i("Jar","Je suis revenu aux fragments");
            shouldRefreshOnResume = false;
            loadMySpaces();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        shouldRefreshOnResume = true;
    }
}

