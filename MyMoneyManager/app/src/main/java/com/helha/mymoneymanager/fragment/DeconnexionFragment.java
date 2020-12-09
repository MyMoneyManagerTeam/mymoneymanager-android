package com.helha.mymoneymanager.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.helha.mymoneymanager.R;
import com.helha.mymoneymanager.SignInActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class DeconnexionFragment extends Fragment {


    public DeconnexionFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_deconnexion, container, false);

        clearToken();
        goToSignIn();

        return view;
    }

    public void clearToken()
    {
        //Je clear USERTOKENSHARED pour me deconncter et je renvoie l'utilisateur à la page de connexion
        SharedPreferences preferences = this.getActivity().getSharedPreferences("USERTOKENSHARED", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();
    }

    public void goToSignIn()
    {
        getActivity().finishAffinity();
        Intent intent = new Intent(getActivity(), SignInActivity.class);

        //J'empêche le retour quand je serai sur la page de connexion
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}