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

public class AccountsAdapter extends ArrayAdapter<Accounts> {

    public AccountsAdapter(@NonNull Context context, int resource, @NonNull Accounts[] objects) {
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

        final Accounts accounts= getItem(position);
        populateView(inflatedView, accounts);

        return inflatedView;
    }

    private void populateView(View inflatedView, Accounts accounts) {
        TextView tv_value_account = inflatedView.findViewById(R.id.tv_value_account);

        tv_value_account.setText(accounts.getBalance()+"€");
    }
}
