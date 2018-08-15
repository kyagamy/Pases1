package com.lords.pases.fragments;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.loopwiki.qrsacnner.R;
import com.lords.pases.interfaces.AsyncTaskCallback;
import com.lords.pases.util.Conector;
import com.lords.pases.util.GenericAsyncDBTask;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

public class FragmentDialogEdit extends DialogFragment {
    Button send, setDate, setTimeStart, setTimeEnd;
    EditText motivo;
    DatePickerDialog dpd;
    TimePickerDialog tpd, tpd2;

    String fecha, hora1, hora2,motivotxt;
    public  int idSolicitud;

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setHora1(String hora1) {
        this.hora1 = hora1;
    }

    public void setHora2(String hora2) {
        this.hora2 = hora2;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment_add_soli, container, false);

        send = view.findViewById(R.id.btn_send_soli);
        setDate = view.findViewById(R.id.btn_set_date);
        setTimeStart = view.findViewById(R.id.btn_set_time_start);
        setTimeEnd = view.findViewById(R.id.btn_set_time_end);
        motivo = view.findViewById(R.id.et_motivo);
        motivo.setText(motivotxt);



        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            dpd = new DatePickerDialog(getContext());
        }
        TimePickerDialog.OnTimeSetListener timeListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                hora1 = hourOfDay + ":" + minute;
                setTimeStart.setText(hora1);
            }
        };
        TimePickerDialog.OnTimeSetListener timeListener2 = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                hora2 = hourOfDay + ":" + minute;
                setTimeEnd.setText(hora2);
            }
        };


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            tpd = new TimePickerDialog(getContext(), timeListener, Calendar.HOUR, Calendar.MINUTE, false);
            tpd2 = new TimePickerDialog(getContext(), timeListener2, Calendar.HOUR, Calendar.MINUTE, false);
        }


        setTimeStart.setText("Hora de salida ("+ hora1+")");
        setTimeStart.setText("Hora de regreso ("+ hora2+")");
        setDate.setText("Fecha solicitada ("+fecha+")");
        setTimeStart.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                tpd.show();
            }
        });

        setTimeEnd.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                tpd2.show();
            }
        });
        setDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dpd.show();
            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            dpd.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    fecha = year + "-" + month + "-" + dayOfMonth;
                    setDate.setText(fecha);
                }
            });
        }

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fecha == null || hora1 == null || hora2 == null) {
                    Toast.makeText(getActivity().getBaseContext(), "Debes de llenar todos los campos", Toast.LENGTH_LONG).show();
                } else {
                    if (motivo.getText().toString().length() < 15) {
                        Toast.makeText(getActivity().getBaseContext(), "El motivo debe de tener almenos 15 caracteres", Toast.LENGTH_LONG).show();
                    } else {
                        edit(motivo.getText().toString());
                    }
                }

            }
        });

        return view;
    }


    public void edit(String motivo) {
        try {
            String stmt = "exec modificarSolicitudMaestro '" + motivo + "','" + fecha + "','" + hora1 + "','" + hora2 + "'," + idSolicitud;
            GenericAsyncDBTask gdbc = new GenericAsyncDBTask(getActivity(), new AsyncTaskCallback() {
                @Override
                public void onTaskCompleted(ResultSet r) {
                    try {
                        String msj;
                        if (r.next()) {
                            msj = Conector.msj(r);
                        } else {
                            msj = "Ha ocurrido un error";
                        }
                        Toast.makeText(getActivity().getBaseContext(), msj, Toast.LENGTH_LONG).show();

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            });
            gdbc.execute(stmt);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getActivity().getBaseContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }


}
