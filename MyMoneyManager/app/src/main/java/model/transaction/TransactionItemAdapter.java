package model.transaction;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.helha.mymoneymanager.R;

import java.util.List;

public class TransactionItemAdapter extends ArrayAdapter<TransactionItem>
{

    public TransactionItemAdapter(@NonNull Context context, int resource, @NonNull List<TransactionItem> objects) {
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

        final TransactionItem transactionItem = getItem(position);
        populateView(inflatedView, transactionItem);

        return inflatedView;
    }

    private void populateView(View inflatedView, TransactionItem transactionItem) {

        TextView tvDate = inflatedView.findViewById(R.id.tv_date_transaction);
        TextView tvDescription = inflatedView.findViewById(R.id.tv_description_transaction);
        TextView tvDebiteur = inflatedView.findViewById(R.id.tv_debiteur_transaction);
        TextView tvCrediteur = inflatedView.findViewById(R.id.tv_crediteur_transaction);
        TextView tvSomme = inflatedView.findViewById(R.id.tv_somme_transaction);

        tvDate.setText(transactionItem.getTransactionDate());
        tvDescription.setText(transactionItem.getDescription());
        tvDebiteur.setText("De: " + transactionItem.getEmitterName());
        tvCrediteur.setText("Vers : " + transactionItem.getReceiverName());
        tvSomme.setText(transactionItem.getAmount() + "€");
    }
}
