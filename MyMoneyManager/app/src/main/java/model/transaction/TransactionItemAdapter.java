package model.transaction;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.helha.mymoneymanager.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TransactionItemAdapter extends ArrayAdapter<TransactionItem>
{

    public TransactionItemAdapter(@NonNull Context context, int resource, @NonNull List<TransactionItem> objects) {
        super(context, resource, objects);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
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

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void populateView(View inflatedView, TransactionItem transactionItem) {

        TextView tvDate = inflatedView.findViewById(R.id.tv_date_transaction);
        TextView tvDescription = inflatedView.findViewById(R.id.tv_description_transaction);
        TextView tvDebiteur = inflatedView.findViewById(R.id.tv_debiteur_transaction);
        TextView tvCrediteur = inflatedView.findViewById(R.id.tv_crediteur_transaction);
        TextView tvSomme = inflatedView.findViewById(R.id.tv_somme_transaction);

        String date = formatDate(transactionItem.getTransactionDate());

        Log.i("date", "populateView: "+ date);

        tvDate.setText(date);
        tvDescription.setText(transactionItem.getDescription());
        tvDebiteur.setText("De: " + transactionItem.getEmitterName());
        tvCrediteur.setText("Vers : " + transactionItem.getReceiverName());
        tvSomme.setText(transactionItem.getAmount() + "€");
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    String formatDate(String date)
    {
        String dateString = date.substring(0,10);
        LocalDate dateLocalDate = LocalDate.parse(dateString);

        String month = dateLocalDate.getMonth().toString();
        int day = dateLocalDate.getDayOfMonth();

        String dateReturn = day + "\n" + month.substring(0,3);

        return dateReturn;
    }
}
