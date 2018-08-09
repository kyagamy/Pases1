package com.lords.pases.fragments;


import android.content.Intent;
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

import com.loopwiki.qrsacnner.LoginActivity;
import com.loopwiki.qrsacnner.R;
import com.lords.pases.MainPases;
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
    public FragmentListSolicitudes() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        LoadData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_list_solicitudes, container, false);

        //leer elementos
        rvSolis=view.findViewById(R.id.rv_solis);
        s1= view.findViewById(R.id.spinner_filtro_solis);



        listaSolis= new ArrayList<>();
        optionsSpinner= new ArrayList<>();
        optionsSpinner.add("Todas");
        optionsSpinner.add("Aceptadas");
        optionsSpinner.add("Rechazadas");
        optionsSpinner.add("Pendientes");
        adapterFiltro= new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_spinner_item,optionsSpinner);

        adapterSoli= new AdapterSoli(listaSolis,view.getContext());
        //Solicitud solitest= new Solicitud();

       // listaSolis.add(solitest);
        //listaSolis.add(solitest);
        //consultar


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
            String stmt = "exec consultarMaestro '131313'";
            GenericAsyncDBTask gdbc = new GenericAsyncDBTask(getActivity(), new AsyncTaskCallback() {
                @Override
                public void onTaskCompleted(ResultSet r) {
                    try {
                        while (r.next()){
                            Solicitud s = new Solicitud();
                            s.setId(r.getInt("ID"));
                            s.setMotivo(r.getString("Motivo"));
                            s.setFechaCreada(r.getString(3));
                            s.setDias_solicitado(r.getDate(3));
                            s.setHorapedidaSalida(r.getTime(5));
                            s.setHoraPedidaRegreso(r.getTime(4));
                            s.setEstado(r.getString(7));
                            s.setRespuesta(r.getString(8));
                            s.setSalida(r.getString(10));
                            s.setRegreso(  r.getString(9));

                            listaSolis.add(s);
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



}

