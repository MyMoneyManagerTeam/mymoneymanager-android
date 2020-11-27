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

public class JarAdapter extends ArrayAdapter<Jar>{
    public JarAdapter(@NonNull Context context, int resource, @NonNull List<Jar> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View inflatedView = convertView;

        if(inflatedView == null)
        {
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            inflatedView = layoutInflater.inflate(R.layout.list_items_jar,parent,false);
        }

        final Jar jars = getItem(position);
        populateView(inflatedView, jars);

        return inflatedView;
    }

    private void populateView(View inflatedView, Jar jars) {

        TextView tvName = inflatedView.findViewById(R.id.tv_name_jar);
        TextView tvValue = inflatedView.findViewById(R.id.tv_value_jar);

        tvName.setText(jars.getName());
        tvValue.setText(jars.getValue()+"€");
    }
}
