package com.helha.mymoneymanager.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.helha.mymoneymanager.R;

import java.util.ArrayList;
import java.util.List;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;
import model.transaction.TransactionItem;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecevoirFragment extends Fragment {

    private LinearLayout linearLayout = null;

    EditText qrValue;
    Button generateBtn;
    ImageView qrImage;

    public RecevoirFragment() {
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
        View view = inflater.inflate(R.layout.fragment_recevoir, container, false);

        qrValue = (EditText) view.findViewById(R.id.et_qrInput);
        generateBtn = (Button) view.findViewById(R.id.btn_generate);
        qrImage = (ImageView) view.findViewById(R.id.iv_qrPlaceHolder);
        generateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = getAccountID() +"\n"
                            + qrValue.getText().toString() +"\n"
                            + getReceiverName();
                if(data.isEmpty())
                {
                    qrValue.setError("Valeur requise");
                }
                else {
                    QRGEncoder qrgEncoder = new QRGEncoder(data, null, QRGContents.Type.TEXT, 500);
                    Bitmap qrBits = qrgEncoder.getBitmap();
                    qrImage.setImageBitmap(qrBits);

                }
            }
        });
        return view;
    }

    public String getAccountID()
    {
        //Reception du SHARED PREFERENCE disponible et recopie du userToken dans le fragment.
        SharedPreferences preferences = this.getActivity().getSharedPreferences("USERTOKENSHARED", Context.MODE_PRIVATE);
        return preferences.getString("USERID", "No Token");
    }

    public String getReceiverName()
    {
        //Reception du SHARED PREFERENCE disponible et recopie du userToken dans le fragment.
        SharedPreferences preferences = this.getActivity().getSharedPreferences("USERTOKENSHARED", Context.MODE_PRIVATE);
        return preferences.getString("USERNAME", "No Token");
    }
}