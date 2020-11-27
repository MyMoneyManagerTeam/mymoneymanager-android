package com.helha.main_menu_activity;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import model.Goal;
import model.GoalAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class GoalFragment extends Fragment {

    private final List<Goal> goals = new ArrayList<>();

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
        goals.add(new Goal("test goal",10,100));
        goals.add(new Goal("oui",50,100));
        // ******************* END *******************

        GoalAdapter goalAdapter = new GoalAdapter(
          getContext(),
          R.id.lv_goals,
          goals
        );

        lvGoal.setAdapter(goalAdapter);

        return view;
    }
}