package com.helha.main_menu_activity;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import model.TransactionHistory;
import model.TransactionHistoryAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class HistoriqueFragment extends Fragment {

    private final List<TransactionHistory> transactions = new ArrayList<>();

    public HistoriqueFragment() {
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
        View view =inflater.inflate(R.layout.fragment_historique, container, false);

        ListView lvTransaction = view.findViewById(R.id.lv_historique);

        //Valeur à modifié avec les valeurs en db
        //ATTENTION : transformé la date au format suivant : X \n LLL     => X jour  | L mois lettre
        //*********** START *************
        transactions.add(new TransactionHistory("9\n sep","transfert test","moi","lui", (float) 10.0));
        transactions.add(new TransactionHistory("10\n sep","transfert test2","moi","lui",(float) 10.0));
        //*********** END ***************
        TransactionHistoryAdapter transactionHistoryAdapter = new TransactionHistoryAdapter(
                getContext(),
                R.id.lv_historique,
                transactions
        );

        lvTransaction.setAdapter(transactionHistoryAdapter);
        return view;
    }
}