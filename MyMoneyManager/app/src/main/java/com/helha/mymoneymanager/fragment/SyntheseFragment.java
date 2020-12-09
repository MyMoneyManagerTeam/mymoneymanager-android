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

import com.anychart.APIlib;
import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Cartesian;
import com.anychart.charts.Pie;
import com.anychart.core.cartesian.series.Column;
import com.helha.mymoneymanager.R;

import java.util.ArrayList;
import java.util.List;

import model.jar.Jar;
import repository.JarRepository;

/**
 * A simple {@link Fragment} subclass.
 */
public class SyntheseFragment extends Fragment {

    AnyChartView ACV_pie;
    AnyChartView ACV_bar;

    //Valeur à changer avec les données de la db
    //************ START ****************
    /*String[] months = {"janvier","fevrier","mars"};
    int[] earnings = {500,800,2000};*/

    double[] earningsJar = {100,200,300};
    String[] nameJar = {"vacance","vetement","nourriture"};
    //************ END ****************

    public SyntheseFragment() {
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
        View view = inflater.inflate(R.layout.fragment_synthese, container, false);

        /*ACV_bar= view.findViewById(R.id.bar_graph);
        APIlib.getInstance().setActiveAnyChartView(ACV_bar);
        setupBarGraph();*/

        ACV_pie= view.findViewById(R.id.circle_graph);
        APIlib.getInstance().setActiveAnyChartView(ACV_pie);
        setupCircleGraph();

        return view;
    }

    public void setupCircleGraph(){

        Pie pie = AnyChart.pie();
        List<DataEntry> dataEntries = new ArrayList<>();

        for(int i=0;i< 3;i++)
        {
            dataEntries.add(new ValueDataEntry(nameJar[i],earningsJar[i]));
        }

        pie.data(dataEntries);
        pie.title("Répartition des jars");
        ACV_pie.setChart(pie);
    }

   /* public void setupBarGraph()
    {
        Cartesian bar = AnyChart.column();
        List<DataEntry> dataEntriesBar = new ArrayList<>();

        for(int i=0;i< months.length;i++)
        {
            dataEntriesBar.add(new ValueDataEntry(months[i],earnings[i]));
        }

        Column column = bar.column(dataEntriesBar);

        bar.animation(true);

        bar.xAxis(0).title("Mois");
        bar.yAxis(0).title("Revenue du compte");

        ACV_bar.setChart(bar);
    }*/
}