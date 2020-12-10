package com.helha.mymoneymanager.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

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
    private TextView tvValueAccount;

    EditText etName;
    EditText etDescription;
    EditText etBalance;
    EditText etGoal;
    ProgressBar pgGoal;
    TextView txtPercentage;
    String currentJar = null ;
    JarRepository jarRepository = new JarRepository();

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

        //Déclaration et initialisation
        Accounts accounts;
        AccountRepository accountRepository = new AccountRepository();
        tvValueAccount = view.findViewById(R.id.tv_value_account);
        GridView gvJar = view.findViewById(R.id.gv_jars);
        final JarAdapter jarAdapter = new JarAdapter(getContext(), R.id.gv_jars, jars);
        gvJar.setAdapter(jarAdapter);


        //Actualisation du getBalance de mon compte.
        accountRepository.get(getSharedToken()).observe(this.getViewLifecycleOwner() , new Observer<Accounts>() {
            @Override
            public void onChanged(Accounts accounts) {
                tvValueAccount.setText(accounts.getBalance()+"€");
            }
        });

        //Chargement de mes jars
        jarRepository.query(getSharedToken()).observe(this.getViewLifecycleOwner(), new Observer<List<Jar>>() {
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
                final Dialog fbDialogue = new Dialog(getActivity(), android.R.style.Theme_Black_NoTitleBar);
                fbDialogue.getWindow().setBackgroundDrawable(new ColorDrawable(Color.argb(245, 254, 254, 254)));
                View viewOneJar = inflater.inflate(R.layout.fragment_about_one_jar, container, false);
                fbDialogue.setContentView(viewOneJar);
                fbDialogue.setCancelable(true);

                etName = viewOneJar.findViewById(R.id.etName);
                etDescription = viewOneJar.findViewById(R.id.etDescription);
                etBalance = viewOneJar.findViewById(R.id.etBalance);
                etGoal = viewOneJar.findViewById(R.id.etGoal);
                pgGoal = viewOneJar.findViewById(R.id.pgGoal);
                txtPercentage = viewOneJar.findViewById(R.id.txtPercentage);

                currentJar = jars.get(position).getJar_id();
                etName.setText(jars.get(position).getName());
                etDescription.setText(jars.get(position).getDescription());
                etBalance.setText(Double.toString(jars.get(position).getBalance()));
                etGoal.setText(Double.toString(jars.get(position).getMax()));
                int max = (int)jars.get(position).getMax();
                pgGoal.setMax(max);
                int prog = (int) jars.get(position).getBalance();
                pgGoal.setProgress(prog);
                int percent = (int) ((jars.get(position).getBalance()/jars.get(position).getMax())*100);
                txtPercentage.setText(percent +"%");
                fbDialogue.show();

            }
        });

        return view;
    }

    public String getSharedToken()
    {
        //Reception du SHARED PREFERENCE disponible et recopie du userToken dans le fragment.
        SharedPreferences preferences = this.getActivity().getSharedPreferences("USERTOKENSHARED", Context.MODE_PRIVATE);
        return preferences.getString("TOKEN", "No Token");
    }

    public void updateJar(Jar newUpdatedJar)
    {
        jarRepository.update(getSharedToken(), newUpdatedJar).observe(this.getViewLifecycleOwner(), new Observer<Jar>() {
            @Override
            public void onChanged(Jar jar) {
                Log.i("Jar", "Updated: "+ jar );
            }
        });
    }

    public void deleteJar()
    {
        jarRepository.delete(getSharedToken(),currentJar).observe(this.getViewLifecycleOwner(), new Observer<Jar>() {
            @Override
            public void onChanged(Jar jar) {
                Log.i("Jar", "Deleted: "+ jar);
            }
        });
    }
}

