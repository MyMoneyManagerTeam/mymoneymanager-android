package com.helha.main_menu_activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import java.nio.file.Files;

public class Home_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        String userToken = getIntent().getStringExtra(Sign_In_Activity.KEY_EXTRA_TOKEN);
        Log.i("test", "onCreate: "+ userToken);

        SharedPreferences pref = getBaseContext().getSharedPreferences("KEY_TOKEN", 0);
        SharedPreferences.Editor editor;
        editor = pref.edit();
        editor.putString("KEY_TOKEN",userToken);
        editor.commit();

        FragmentManager manager = getSupportFragmentManager();
        final FragmentTransaction t = manager.beginTransaction();
        final EspaceFragment espaceFragment = new EspaceFragment();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_);

        final DrawerLayout drawerLayout = findViewById(R.id.drawerLayout);

        findViewById(R.id.imageMenu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        NavigationView navigationView = findViewById(R.id.navigationView);
        navigationView.setItemIconTintList(null);

        NavController navController = Navigation.findNavController(this, R.id.navHostFragment);
        NavigationUI.setupWithNavController(navigationView, navController);

        final TextView textTitle = findViewById(R.id.textTitle);

        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
                textTitle.setText(destination.getLabel());
            }
        });
    }
}