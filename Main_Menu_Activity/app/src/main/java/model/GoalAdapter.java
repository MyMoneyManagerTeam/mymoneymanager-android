package model;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.helha.main_menu_activity.R;

import java.util.List;

public class GoalAdapter extends ArrayAdapter<Goal> {
    public GoalAdapter(@NonNull Context context, int resource, @NonNull List<Goal> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View inflatedView = convertView;

        if(inflatedView == null)
        {
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            inflatedView = layoutInflater.inflate(R.layout.list_items_goal,parent,false);
        }

        final Goal goals = getItem(position);
        populateView(inflatedView, goals);

        return inflatedView;

    }

    private void populateView(View inflatedView, Goal goals) {
        TextView tvName = inflatedView.findViewById(R.id.tv_title_goal);
        tvName.setText(goals.getName());

        ProgressBar pgGoal = inflatedView.findViewById(R.id.pg_goal);
        ObjectAnimator pgAnimator = ObjectAnimator.ofFloat(pgGoal,"Progress",10,goals.getValue());
        pgAnimator.setDuration(7000);
    }
}
