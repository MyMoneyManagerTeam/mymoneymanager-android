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
import android.widget.TextView;

import com.anychart.APIlib;
import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Cartesian;
import com.anychart.charts.Pie;
import com.anychart.core.Text;
import com.anychart.core.cartesian.series.Column;
import com.helha.mymoneymanager.R;

import java.util.ArrayList;
import java.util.List;

import model.accounts.Accounts;
import model.jar.Jar;
import model.jar.JarPieAdapter;
import repository.AccountRepository;
import repository.JarRepository;

/**
 * A simple {@link Fragment} subclass.
 */
public class SyntheseFragment extends Fragment {

    private AnyChartView ACV_pie;
    private double totalDispo;
    private double total;
    private double totalJars;
    private int nbJars;
    private TextView txtTotal;
    private TextView txtTotalJars;
    private TextView txtNbJars;
    private TextView txtTotalDispo;

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
        txtTotal = view.findViewById(R.id.txt_total);
        txtTotalJars = view.findViewById(R.id.txt_totalJars);
        txtNbJars = view.findViewById(R.id.txt_nbJar);
        txtTotalDispo = view.findViewById(R.id.txt_totalDispo);

        ACV_pie= view.findViewById(R.id.circle_graph);
        APIlib.getInstance().setActiveAnyChartView(ACV_pie);
        setupCircleGraph();

        AccountRepository accountRepository = new AccountRepository();
        accountRepository.get(getSharedToken()).observe(this.getViewLifecycleOwner(), new Observer<Accounts>() {
            @Override
            public void onChanged(Accounts accounts) {
                total = accounts.getBalance();
                totalDispo = accounts.getAvailableBalance();
                txtTotal.setText(total+"€");
                txtTotalDispo.setText(totalDispo+"€");
            }
        });

        return view;
    }

    public void setupCircleGraph(){
        final Pie pie = AnyChart.pie();
        final List<DataEntry> dataEntries = new ArrayList<>();
        JarRepository jarRepository = new JarRepository();
        jarRepository.query(getSharedToken()).observe(getViewLifecycleOwner(), new Observer<List<Jar>>() {
            @Override
            public void onChanged(List<Jar> jars) {
                for(Jar jar : jars)
                {
                    nbJars++;
                    totalJars+=jar.getBalance();
                    ValueDataEntry value = new ValueDataEntry(jar.getName(),jar.getBalance());
                    dataEntries.add(value);
                }
                txtTotalJars.setText(totalJars+"€");
                txtNbJars.setText(String.valueOf(nbJars));
                pie.data(dataEntries);
                pie.title("Répartition des jars");
                ACV_pie.setChart(pie);
            }
        });
    }

    public String getSharedToken() {
        //Reception du SHARED PREFERENCE disponible et recopie du userToken dans le fragment.
        SharedPreferences preferences = this.getActivity().getSharedPreferences("USERTOKENSHARED", Context.MODE_PRIVATE);
        return preferences.getString("TOKEN", "No Token");
    }
}