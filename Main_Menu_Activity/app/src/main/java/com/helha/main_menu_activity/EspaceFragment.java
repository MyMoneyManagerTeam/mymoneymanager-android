package com.helha.main_menu_activity;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import model.Jar;
import model.JarAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class EspaceFragment extends Fragment {

    private final List<Jar> jars = new ArrayList<>();

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

        TextView tvValueAccount = view.findViewById(R.id.tv_value_account);
        //valeur à changer avec la db
        //valeur du compte global
        //************ START *******
        tvValueAccount.setText(200+"€");
        //************ END *********

       GridView gvJar = view.findViewById(R.id.gv_jars);

       //valeur à changer avec la db
        //************** START *************
        jars.add(new Jar("Test",200));
        jars.add(new Jar("Test",200));
        jars.add(new Jar("Test",200));
        //************** END ***************
        JarAdapter jarAdapter = new JarAdapter(
                getContext(),
                R.id.gv_jars,
                jars
        );

        gvJar.setAdapter(jarAdapter);
        return view;
    }
}