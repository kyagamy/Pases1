package com.lords.pases.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.loopwiki.qrsacnner.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Settings extends Fragment {


    public Fragment_Settings() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_, container, false);
        return view;
    }

    public static Fragment newInstance() {
        return  new Fragment_Settings();
    }
}
