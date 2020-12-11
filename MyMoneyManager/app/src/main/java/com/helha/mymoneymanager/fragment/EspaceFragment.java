package com.helha.mymoneymanager.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;

import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.helha.mymoneymanager.R;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import model.accounts.Accounts;
import model.jar.Jar;
import model.jar.JarAdapter;
import repository.AccountRepository;
import repository.JarRepository;

/**
 * A simple {@link Fragment} subclass.
 */
public class EspaceFragment extends Fragment {

    private static Jar currentJar;
    private static String userToken;
    private static LifecycleOwner myActivity;
    private static Dialog fbDialogue;

    public EspaceFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroyView() {

        FragmentManager fm = getFragmentManager();

        Fragment xmlFragment = fm.findFragmentById(R.id.fragment_aboutOneJar);
        if (xmlFragment != null) {
            fm.beginTransaction().remove(xmlFragment).commit();
        }

        super.onDestroyView();
    }


    @Override
    public void onInflate(Activity activity, AttributeSet attrs,
                          Bundle savedInstanceState) {

        FragmentManager fm = getFragmentManager();
        if (fm != null) {
            fm.beginTransaction().remove(this).commit();
        }

        super.onInflate(getActivity(), attrs, savedInstanceState);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {

        final List<Jar> jars = new ArrayList<>();
        View view = inflater.inflate(R.layout.fragment_espace, container, false);
        SharedPreferences preferences = getActivity().getSharedPreferences("USERTOKENSHARED", Context.MODE_PRIVATE);
        userToken = preferences.getString("TOKEN", "No Token");;
        myActivity = this.getViewLifecycleOwner();

        final TextView tvValueAccount;
        JarRepository jarRepository = new JarRepository();
        //Déclaration et initialisation
        Accounts accounts;
        AccountRepository accountRepository = new AccountRepository();
        tvValueAccount = view.findViewById(R.id.tv_value_account);
        GridView gvJar = view.findViewById(R.id.gv_jars);
        final JarAdapter jarAdapter = new JarAdapter(getContext(), R.id.gv_jars, jars);
        gvJar.setAdapter(jarAdapter);



        //Actualisation du getBalance de mon compte.
        accountRepository.get(userToken).observe(this.getViewLifecycleOwner() , new Observer<Accounts>() {
            @Override
            public void onChanged(Accounts accounts) {
                tvValueAccount.setText(accounts.getBalance()+"€");
            }
        });

        //Chargement de mes jars
        jarRepository.query(userToken).observe(this.getViewLifecycleOwner(), new Observer<List<Jar>>() {
            @Override
            public void onChanged(List<Jar> loadedJars) {
                jars.addAll(loadedJars);
                jarAdapter.notifyDataSetChanged();
            }
        });

        //ClickListener pour ouvrir une fenêtre pour chaque jar
        gvJar.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                currentJar = jars.get(position);
                fbDialogue = new Dialog(getActivity(), android.R.style.Theme_Black_NoTitleBar);
                fbDialogue.getWindow().setBackgroundDrawable(new ColorDrawable(Color.argb(245, 254, 254, 254)));
                View viewOneJar = inflater.inflate(R.layout.dialoginespaceforonejar, container, false);
                fbDialogue.setContentView(viewOneJar);
                fbDialogue.setCancelable(true);
                fbDialogue.show();
            }
        });

        return view;
    }

    public Jar getCurrentJar() {
        return currentJar;
    }


    public String getUserTokenStatic() {
        return userToken;
    }

    public static LifecycleOwner getMyActivity() {
        return myActivity;
    }

    public void closeDial()
    {
        fbDialogue.hide();
        EspaceFragment fg = new EspaceFragment();
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.syntheseFragment, fg)
                .commit();
    }

}

