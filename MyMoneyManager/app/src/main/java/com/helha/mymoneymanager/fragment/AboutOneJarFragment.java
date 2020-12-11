package com.helha.mymoneymanager.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.helha.mymoneymanager.R;

import model.jar.Jar;
import repository.JarRepository;

/**
 * A simple {@link Fragment} subclass.
 */
public class AboutOneJarFragment extends EspaceFragment implements View.OnClickListener
{
    JarRepository jarRepository = new JarRepository();
    EditText etName;
    EditText etDescription;
    EditText etBalance;
    EditText etGoal;
    ProgressBar pgGoal;
    TextView txtPercentage;
    Jar currentJar;

    public AboutOneJarFragment() {
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
        View view = inflater.inflate(R.layout.fragment_about_one_jar, container, false);
        currentJar = getCurrentJar();

        etName = view.findViewById(R.id.etName);
        etDescription = view.findViewById(R.id.etDescription);
        etBalance = view.findViewById(R.id.etBalance);
        etGoal = view.findViewById(R.id.etGoal);
        pgGoal = view.findViewById(R.id.pgGoal);
        txtPercentage = view.findViewById(R.id.txtPercentage);
        etName.setText(currentJar.getName());
        etDescription.setText(currentJar.getDescription());
        etBalance.setText(Double.toString(currentJar.getBalance()));
        etGoal.setText(Double.toString(currentJar.getMax()));
        pgGoal.setMax((int) currentJar.getMax());
        pgGoal.setProgress((int) currentJar.getBalance());
        double percentage = (currentJar.getBalance() / currentJar.getMax())*100;
        txtPercentage.setText((int)percentage +"%");

        Button b = (Button) view.findViewById(R.id.btnDelete);
        b.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnDelete:
                deleteJar();
                closeDial();
                break;
            case R.id.btnSave:
                updateJar();
                break;
        }

    }

    public void updateJar()
    {
        Jar newUpdatedJar = new Jar(currentJar.getJar_id(), currentJar.getOwner(), etDescription.getText().toString(),etName.getText().toString(), (int) currentJar.getMax(), (int) currentJar.getBalance());
        jarRepository.update(getUserTokenStatic(), newUpdatedJar).observe(getMyActivity(), new Observer<Jar>() {
            @Override
            public void onChanged(Jar jar) {
                Log.i("Jar", "Updated: "+ jar );
            }
        });
    }

    public void deleteJar()
    {
        JarRepository jarRepository = new JarRepository();
        jarRepository.delete(getUserTokenStatic(),currentJar.getJar_id()).observe(getMyActivity(), new Observer<Jar>() {
            @Override
            public void onChanged(Jar jar) {
                Log.i("Jar", "Deleted: "+ jar.toString());
            }
        });
    }

}
