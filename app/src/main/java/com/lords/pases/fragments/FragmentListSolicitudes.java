package com.lords.pases.fragments;


import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.loopwiki.qrsacnner.R;
import com.lords.pases.adapters.AdapterSoli;
import com.lords.pases.entidades.Solicitud;
import com.lords.pases.interfaces.AsyncTaskCallback;
import com.lords.pases.util.GenericAsyncDBTask;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentListSolicitudes extends Fragment {

    ArrayList <String> optionsSpinner;
    ArrayList<Solicitud> listaSolis;
    RecyclerView rvSolis;
    AdapterSoli adapterSoli;
    Spinner s1;
    ArrayAdapter <String> adapterFiltro;
    Dialog dialogSeeMore;
    private  String matri;
    public FragmentListSolicitudes() {
        // Required empty public constructor
    }

    public void setMatri(String matri) {
        this.matri = matri;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onStart() {
        super.onStart();
        LoadData();
        showPopup();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_list_solicitudes, container, false);

        //leer elementos
        rvSolis=view.findViewById(R.id.rv_solis);
        s1= view.findViewById(R.id.spinner_filtro_solis);

        dialogSeeMore= new Dialog(getContext());
        dialogSeeMore.setContentView(R.layout.custompopup);
        TextView txtclose= dialogSeeMore.findViewById(R.id.txtclose);
        txtclose.setText("X");

        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogSeeMore.dismiss();
            }
        });
        dialogSeeMore.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));



        listaSolis= new ArrayList<>();
        optionsSpinner= new ArrayList<>();
        optionsSpinner.add("Todas");
        optionsSpinner.add("Aceptadas");
        optionsSpinner.add("Rechazadas");
        optionsSpinner.add("Pendientes");
        adapterFiltro= new ArrayAdapter<>(view.getContext(), android.R.layout.simple_spinner_item,optionsSpinner);

        adapterSoli= new AdapterSoli(listaSolis,view.getContext());


        rvSolis.setAdapter(adapterSoli);
        rvSolis.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false));
        s1.setAdapter(adapterFiltro);



        s1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:

                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    default:
                        break;

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        return view;
    }



    private void LoadData() {
        try {
            String stmt = "exec consultarMaestro '"+matri+"'";
            GenericAsyncDBTask gdbc = new GenericAsyncDBTask(getActivity(), new AsyncTaskCallback() {
                @Override
                public void onTaskCompleted(ResultSet r) {
                    try {
                        listaSolis.clear();//limpamos por si se vuele a crear el fragment
                        while (r.next()){
                            Solicitud auxSolicitud  = new Solicitud();
                            auxSolicitud.setId(r.getInt("ID"));
                            auxSolicitud.setMotivo(r.getString("Motivo"));
                            auxSolicitud.setFechaCreada(r.getString(3));
                            auxSolicitud.setDias_solicitado(r.getDate(3));
                            auxSolicitud.setHorapedidaSalida(r.getTime(5));
                            auxSolicitud.setHoraPedidaRegreso(r.getTime(4));
                            auxSolicitud.setEstado(r.getString(7));
                            auxSolicitud.setRespuesta(r.getString(8)==null ?"":r.getString(8));
                            auxSolicitud.setSalida(r.getString(10)==null ?"":r.getString(10));
                            auxSolicitud.setRegreso(  r.getString(9)==null ?"":r.getString(9));

                            listaSolis.add(auxSolicitud);
                            adapterSoli.notifyDataSetChanged();
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            });
            gdbc.execute(stmt);
        } catch (Exception ex) {
            ex.printStackTrace();

        }
    }


    public void showPopup() {
        dialogSeeMore.show();
    }



}

