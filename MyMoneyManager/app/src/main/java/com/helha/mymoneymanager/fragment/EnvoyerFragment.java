package com.helha.mymoneymanager.fragment;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.google.gson.Gson;
import com.google.zxing.Result;
import com.helha.mymoneymanager.R;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import model.transaction.TransactionItem;
import repository.TransactionRepository;

/**
 * A simple {@link Fragment} subclass.
 */
public class EnvoyerFragment extends Fragment {

    CodeScanner codeScanner;
    CodeScannerView codeScannerView;
    TextView resultdata;

    public EnvoyerFragment() {
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
        final Activity activity = getActivity();
        View view = inflater.inflate(R.layout.fragment_envoyer, container, false);

        codeScannerView = view.findViewById(R.id.scannerView);
        codeScanner = new CodeScanner(activity,codeScannerView);
        resultdata = view.findViewById(R.id.resultsQr);

        codeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull final Result result) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        // changer pour rentrer valeur dans la db
                        // RETRAIT ARGENT SUR LE COMPTE CREDITEUR
                        // AJOUT ARGENT SUR LE COMPTE DEBITEUR
                        //******* START *******

                        String[] qrCodeBrutResult = result.getText().split("\n");
                        String receiverId = qrCodeBrutResult[0];
                        double amount = Double.parseDouble(qrCodeBrutResult[1]);
                        String receiverName = qrCodeBrutResult[2];
                        resultdata.setText(receiverName +" " + amount + receiverId);

                        executeTransaction(receiverId,receiverName,amount);
                    }
                });
            }
        });
        return view;
    }

<<<<<<< Updated upstream
    private void executeTransaction(String receiverId, String receiverName, double amount) {
=======
    private void executeTransaction(String receiverId,final String receiverName, double amount) {
>>>>>>> Stashed changes
        TransactionItem newTransaction = new TransactionItem(null,getEmitterId(),receiverId, amount,null, "Description",getEmitterName(),receiverName);

        TransactionRepository transactionRepository = new TransactionRepository();
        transactionRepository.create(getToken(),newTransaction).observe(this.getViewLifecycleOwner(), new Observer<TransactionItem>() {
            @Override
            public void onChanged(TransactionItem transactionItem) {
                Log.i("transaction", transactionItem.toString());
            }
        });
    }

    private String getToken()
    {
        //Reception du SHARED PREFERENCE disponible et recopie du userToken dans le fragment.
        SharedPreferences preferences = this.getActivity().getSharedPreferences("USERTOKENSHARED", Context.MODE_PRIVATE);
        return preferences.getString("TOKEN", "No user ID");
    }

    private String getEmitterId()
    {
        //Reception du SHARED PREFERENCE disponible et recopie du userToken dans le fragment.
        SharedPreferences preferences = this.getActivity().getSharedPreferences("USERTOKENSHARED", Context.MODE_PRIVATE);
        return preferences.getString("USERID", "No user ID");
    }

    private String getEmitterName()
    {
        //Reception du SHARED PREFERENCE disponible et recopie du userToken dans le fragment.
        SharedPreferences preferences = this.getActivity().getSharedPreferences("USERTOKENSHARED", Context.MODE_PRIVATE);
        return preferences.getString("USERNAME", "No user Name");
    }

    @Override
    public void onResume() {
        super.onResume();
        requestForCamera();
    }

    private void requestForCamera() {
        Dexter.withActivity(getActivity()).withPermission(Manifest.permission.CAMERA).withListener(new PermissionListener() {
            @Override
            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                codeScanner.startPreview();
            }

            @Override
            public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
                Toast.makeText(getView().getContext(), "Camera Permission is Required", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                permissionToken.continuePermissionRequest();
            }
        }).check();
    }


}
