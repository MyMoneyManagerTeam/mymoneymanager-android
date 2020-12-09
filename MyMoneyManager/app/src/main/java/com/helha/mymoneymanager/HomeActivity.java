package com.helha.mymoneymanager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.helha.mymoneymanager.R;

import repository.AccountRepository;

public class HomeActivity extends AppCompatActivity {

    private final AccountRepository accountRepository = new AccountRepository();
    private String userToken;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_);

        //Réception du token depuis Sign In Activity
        String userToken = getIntent().getStringExtra(SignInActivity.KEY_EXTRA_TOKEN);

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

        //POST new JAR sur l'API
        /*JarRepository jarRepository = new JarRepository();
        Jar newJar = new Jar(null,null, "DescriptionTest", "Nametest", 600, 500);
        jarRepository.create(user.getJWTBearer(),newJar).observe(SignInActivity.this, new Observer<Jar>() {
            @Override
            public void onChanged(Jar jar) {
                Log.i("Jar", jar.toString());
            }
        });*/
    }
}