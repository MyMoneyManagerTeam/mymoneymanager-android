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
import android.widget.ListView;

import com.helha.mymoneymanager.R;

import java.util.ArrayList;
import java.util.List;

import model.jar.Jar;
import model.jar.JarAdapter;
import repository.JarRepository;

/**
 * A simple {@link Fragment} subclass.
 */
public class GoalFragment extends Fragment {

    private final List<Jar> goals = new ArrayList<>();

    public GoalFragment() {
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
        View view = inflater.inflate(R.layout.fragment_goal, container, false);
        ListView lvGoal = view.findViewById(R.id.lv_goals);
        final JarAdapter goalAdapter = new JarAdapter(getContext(), R.id.lv_goals, goals);
        lvGoal.setAdapter(goalAdapter);

        //Reception du SHARED PREFERENCE disponible et recopie du userToken dans le fragment.
        SharedPreferences preferences = this.getActivity().getSharedPreferences("USERTOKENSHARED", Context.MODE_PRIVATE);
        String userToken = preferences.getString("TOKEN", "No Token");

        JarRepository jarRepository = new JarRepository();
        jarRepository.query(userToken).observe(this.getViewLifecycleOwner(), new Observer<List<Jar>>() {
            @Override
            public void onChanged(List<Jar> jars) {
                    goals.addAll(jars);
                    goalAdapter.notifyDataSetChanged();

            }
        });
        return view;
    }
}