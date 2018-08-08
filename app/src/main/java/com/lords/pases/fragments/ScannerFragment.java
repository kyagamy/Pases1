package com.lords.pases.fragments;


import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.loopwiki.qrsacnner.MainActivity;
import com.loopwiki.qrsacnner.R;
import com.loopwiki.qrsacnner.ScannerActivity;

import static android.content.Context.CLIPBOARD_SERVICE;

/**
 * A simple {@link Fragment} subclass.
 */
public class ScannerFragment extends Fragment {


    public ScannerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_scanner, container, false);

        Button buttonBarCodeScan = view.findViewById(R.id.buttonScan2);
        buttonBarCodeScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new IntentIntegrator(getActivity()).setCaptureActivity(ScannerActivity.class).initiateScan();
            }
        });
        return view;

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //We will get scan results here
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        //check for null
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(getContext(), "Se ha cancelao", Toast.LENGTH_LONG).show();
            } else {
                //show dialogue with result
                showResultDialogue(result.getContents());
            }
        } else {
            // This is important, otherwise the result will not be passed to the fragment
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    //method to construct dialogue with scan results
    public void showResultDialogue(final String result) {
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(getContext(), android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(getContext());
        }
        builder.setTitle("Resultado UwU")
                .setMessage("Esa wea decia  " + result)
                .setPositiveButton("Copiar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete
                        ClipboardManager clipboard = (ClipboardManager)getActivity().getSystemService(CLIPBOARD_SERVICE);
                        ClipData clip = ClipData.newPlainText("Scan Result", result);
                        clipboard.setPrimaryClip(clip);
                        Toast.makeText(getContext(), "Result copied to clipboard", Toast.LENGTH_SHORT).show();

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                        dialog.dismiss();
                    }
                })
                .show();
    }
}
