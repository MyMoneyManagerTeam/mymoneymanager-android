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

import model.transaction.TransactionItem;
import model.transaction.TransactionItemAdapter;
import model.transaction.Transactions;
import repository.TransactionRepository;

/**
 * A simple {@link Fragment} subclass.
 */
public class HistoriqueFragment extends Fragment {

    private final List<TransactionItem> transactions = new ArrayList<>();

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
        final ListView lvTransaction = view.findViewById(R.id.lv_historique);
        final TransactionItemAdapter transactionItemAdapter = new TransactionItemAdapter(getContext(), R.id.lv_historique, transactions);
        lvTransaction.setAdapter(transactionItemAdapter);
        final String userToken = getTokenShared();

        TransactionRepository transactionRepository = new TransactionRepository();
        transactionRepository.query(userToken).observe(this.getViewLifecycleOwner(), new Observer<Transactions>() {
            @Override
            public void onChanged(Transactions transactionHistories) {
                transactions.addAll(transactionHistories.getTransactionList());
                transactionItemAdapter.notifyDataSetChanged();
            }
        });

        return view;
    }

    public String getTokenShared()
    {
        //Reception du SHARED PREFERENCE disponible et recopie du userToken dans le fragment.
        SharedPreferences preferences = this.getActivity().getSharedPreferences("USERTOKENSHARED", Context.MODE_PRIVATE);
        return preferences.getString("TOKEN", "No history Token");
    }
}