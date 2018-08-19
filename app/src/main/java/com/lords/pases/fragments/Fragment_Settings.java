package com.lords.pases.fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.loopwiki.qrsacnner.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Settings extends Fragment {


    public Fragment_Settings() {
        // Required empty public constructor
    }
    SharedPreferences sharedPreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_, container, false);
        Button closeSession= view.findViewById(R.id.btn_session_close);
        SharedPreferences sharedPref= getActivity().getSharedPreferences("myPref", Context.MODE_PRIVATE);

        final SharedPreferences.Editor editor=sharedPref.edit();

        closeSession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              editor.putString("matri",null);
              editor.commit();
              getActivity().finish();
            }
        });
        return view;
    }

    public static Fragment newInstance() {
        return  new Fragment_Settings();
    }
}
