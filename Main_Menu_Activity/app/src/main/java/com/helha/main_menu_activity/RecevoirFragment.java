package com.helha.main_menu_activity;

import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

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
                String data = qrValue.getText().toString();
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
}