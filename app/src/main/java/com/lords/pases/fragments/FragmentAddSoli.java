package com.lords.pases.fragments;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.loopwiki.qrsacnner.R;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentAddSoli extends Fragment {


    Button send,setDate,setTimeStart,setTimeEnd;
    DatePickerDialog dpd;
    TimePickerDialog tpd;
    public FragmentAddSoli() {
        // Required empty public constructor
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment_add_soli, container, false);

        send=view.findViewById(R.id.btn_send_soli);
        setDate= view.findViewById(R.id.btn_set_date);
        setTimeStart= view.findViewById(R.id.btn_set_time_start);
        setTimeEnd= view.findViewById(R.id.btn_set_time_end);




        dpd= new DatePickerDialog(getContext());
        TimePickerDialog.OnTimeSetListener timeListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                Toast.makeText(getContext(),"hora:"+ hourOfDay+" minuto:"+minute,Toast.LENGTH_LONG).show();
            }
        };

        tpd= new TimePickerDialog(getContext(), timeListener, Calendar.HOUR,Calendar.MINUTE,false);
        setTimeStart.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                //dpd.show();
                tpd.show();
            }
        });

        setTimeEnd.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                //dpd.show();
                tpd.show();
            }
        });
        setDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dpd.show();
            }
        });

        dpd.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Toast.makeText(getContext(),"Año"+ year+"mes"+month+"day"+dayOfMonth,Toast.LENGTH_LONG).show();
            }
        });
        return view;
    }

    public static Fragment newInstance() {
        return new FragmentAddSoli();
    }

}
