package model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.helha.main_menu_activity.R;

import java.util.List;

public class TransactionHistoryAdapter extends ArrayAdapter<TransactionHistory>
{

    public TransactionHistoryAdapter(@NonNull Context context, int resource, @NonNull List<TransactionHistory> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View inflatedView = convertView;

        if(inflatedView == null)
        {
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            inflatedView = layoutInflater.inflate(R.layout.list_items_history_transaction,parent,false);
        }

        final TransactionHistory transactionHistory = getItem(position);
        populateView(inflatedView, transactionHistory);

        return inflatedView;
    }

    private void populateView(View inflatedView, TransactionHistory transactionHistory) {

        TextView tvDate = inflatedView.findViewById(R.id.tv_date_transaction);
        TextView tvDescription = inflatedView.findViewById(R.id.tv_description_transaction);
        TextView tvDebiteur = inflatedView.findViewById(R.id.tv_debiteur_transaction);
        TextView tvCrediteur = inflatedView.findViewById(R.id.tv_crediteur_transaction);
        TextView tvSomme = inflatedView.findViewById(R.id.tv_somme_transaction);

        tvDate.setText(transactionHistory.getDate());
        tvDescription.setText(transactionHistory.getDescription());
        tvDebiteur.setText("De: " +transactionHistory.getDebiteur());
        tvCrediteur.setText("Vers : " +transactionHistory.getCrediteur());
        tvSomme.setText(transactionHistory.getSomme().toString() + "€");
    }
}
