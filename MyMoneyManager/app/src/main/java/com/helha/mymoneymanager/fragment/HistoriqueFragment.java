package com.helha.mymoneymanager.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

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
        final String userToken = getTokenShared();
        TransactionRepository transactionRepository = new TransactionRepository();
        ListView lvTransaction = view.findViewById(R.id.lv_historique);

        //Valeur à modifié avec les valeurs en db
        //ATTENTION : transformé la date au format suivant : X \n LLL     => X jour  | L mois lettre
        //*********** START *************
       // transactions.add(new TransactionHistory("9\n sep","transfert test","moi","lui", (float) 10.0));
        //transactions.add(new TransactionHistory("10\n sep","transfert test2","moi","lui",(float) 10.0));
        //*********** END ***************

        transactionRepository.query(userToken).observe(this.getViewLifecycleOwner(), new Observer<Transactions>() {
            @Override
            public void onChanged(Transactions transactionHistories) {
                transactions.addAll(transactionHistories.getTransactionList());
            }
        });

        TransactionItemAdapter transactionItemAdapter = new TransactionItemAdapter(getContext(), R.id.lv_historique, transactions);
        lvTransaction.setAdapter(transactionItemAdapter);
        return view;
    }

    public String getTokenShared()
    {
        //Reception du SHARED PREFERENCE disponible et recopie du userToken dans le fragment.
        SharedPreferences preferences = this.getActivity().getSharedPreferences("USERTOKENSHARED", Context.MODE_PRIVATE);
        return preferences.getString("TOKEN", "No Token");
    }
}