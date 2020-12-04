package com.helha.main_menu_activity;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import model.Jar;
import model.JarAdapter;

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

        //valeur à modifié avec celle de la db
        //Modifie value max par la donnée max dans jar
        // ******************* START ******************
       // goals.add(new Jar("test goal",10,100));
       // goals.add(new Jar("oui",50,100));
        // ******************* END *******************

        JarAdapter goalAdapter = new JarAdapter(
          getContext(),
          R.id.lv_goals,
          goals
        );

        lvGoal.setAdapter(goalAdapter);

        return view;
    }
}