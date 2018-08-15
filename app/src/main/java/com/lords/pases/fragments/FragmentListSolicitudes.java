package com.lords.pases.fragments;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.loopwiki.qrsacnner.R;
import com.loopwiki.qrsacnner.ScannerActivity;
import com.lords.pases.adapters.AdapterSoli;
import com.lords.pases.entidades.Solicitud;
import com.lords.pases.interfaces.AsyncTaskCallback;
import com.lords.pases.util.Conector;
import com.lords.pases.util.GenericAsyncDBTask;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentListSolicitudes extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    ArrayList<String> optionsSpinner;
    ArrayList<Solicitud> listaSolis;
    RecyclerView rvSolis;
    AdapterSoli adapterSoli;
    Spinner s1;
    ArrayAdapter<String> adapterFiltro;
    Dialog dialogSeeMore;
    int currentid;


    private String matri;
    SwipeRefreshLayout mSwipeRefreshLayout;

    //elemets of pupup menú

    TextView tvmCreador, tvmFechaCreado, tvmFechaSolicita, tvmHorasSoli, tvmHorasCumplidas;
    EditText etmMotivo, etmRespuesta;
    Button estatus, openSalidaRegistro;

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
    public void onResume() {
        super.onResume();
        LoadData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_list_solicitudes, container, false);
        //leer elementos
        rvSolis = view.findViewById(R.id.rv_solis);
        s1 = view.findViewById(R.id.spinner_filtro_solis);

        //elementosModal
        dialogSeeMore = new Dialog(getContext());
        dialogSeeMore.setContentView(R.layout.custompopup);
        TextView txtclose = dialogSeeMore.findViewById(R.id.txtclose);
        txtclose.setText("X");
        tvmCreador = dialogSeeMore.findViewById(R.id.tv_creado_por);
        tvmFechaCreado = dialogSeeMore.findViewById(R.id.modal_fecha_creado);
        tvmFechaSolicita = dialogSeeMore.findViewById(R.id.tv_fechapedida);
        tvmHorasSoli = dialogSeeMore.findViewById(R.id.tv_horas1);
        tvmHorasCumplidas = dialogSeeMore.findViewById(R.id.tv_horas2);
        etmMotivo = dialogSeeMore.findViewById(R.id.editText_menu_motivo);
        etmRespuesta = dialogSeeMore.findViewById(R.id.editText_respuestamenu);
        estatus = dialogSeeMore.findViewById(R.id.btn_estatus);
        openSalidaRegistro = dialogSeeMore.findViewById(R.id.register_salida);

        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogSeeMore.dismiss();
            }
        });
        dialogSeeMore.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        listaSolis = new ArrayList<>();
        optionsSpinner = new ArrayList<>();
        optionsSpinner.add("Todas");
        optionsSpinner.add("Aceptadas");
        optionsSpinner.add("Rechazadas");
        optionsSpinner.add("Pendientes");
        adapterFiltro = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_spinner_item, optionsSpinner);
        adapterSoli = new AdapterSoli(listaSolis, view.getContext(), this);
        rvSolis.setAdapter(adapterSoli);
        rvSolis.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false));
        s1.setAdapter(adapterFiltro);


        //swipe rehrsh
        mSwipeRefreshLayout = view.findViewById(R.id.simpleSwipeRefreshLayout);

        mSwipeRefreshLayout.setOnRefreshListener(this);
//        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);


        mSwipeRefreshLayout.post(new Runnable() {

            @Override
            public void run() {

                if (mSwipeRefreshLayout != null) {
                    mSwipeRefreshLayout.setRefreshing(true);
                }
                // TODO Fetching data from server
                LoadData();
            }
        });


        s1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
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

        openSalidaRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new IntentIntegrator(getActivity()).setCaptureActivity(ScannerActivity.class).initiateScan();
            }
        });


        return view;
    }



    private void LoadData() {
        try {
            String stmt = "exec consultarMaestro '" + matri + "'";
            GenericAsyncDBTask gdbc = new GenericAsyncDBTask(getActivity(), new AsyncTaskCallback() {
                @Override
                public void onTaskCompleted(ResultSet r) {
                        try {
                            listaSolis.clear();//limpamos por si se vuele a crear el fragment
                        while (r.next()) {
                            Solicitud auxSolicitud = new Solicitud();
                            auxSolicitud.setId(r.getInt("ID"));
                            auxSolicitud.setMotivo(r.getString("Motivo"));
                            auxSolicitud.setFechaCreada(r.getString(3));
                            auxSolicitud.setDias_solicitado(r.getDate(3));
                            auxSolicitud.setHorapedidaSalida(r.getTime(5));
                            auxSolicitud.setHoraPedidaRegreso(r.getTime(4));
                            auxSolicitud.setEstado(r.getString(7) == null ? "" : r.getString(7));
                            auxSolicitud.setRespuesta(r.getString(8) == null ? "" : r.getString(8));
                            auxSolicitud.setSalida(r.getString(10) == null ? "" : r.getString(10));
                            auxSolicitud.setRegreso(r.getString(9) == null ? "" : r.getString(9));
                            listaSolis.add(auxSolicitud);
                            adapterSoli.notifyDataSetChanged();
                            mSwipeRefreshLayout.setRefreshing(false);
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


    public void showEditFragment(String fecha, String time1, String time2, int id, String motivo) {
        FragmentDialogEdit newFragment = new FragmentDialogEdit();

        newFragment.setFecha(fecha);
        newFragment.setHora1(time1);
        newFragment.setHora2(time2);
        newFragment.idSolicitud = id;
        newFragment.motivotxt = motivo;
        newFragment.show(getFragmentManager(), "Editar");


    }


    public void showPopup(String fechaCreada, String fechaPedida, String horas1, String horas2, String motivo, String respuesta, String estatus,int id) {


        tvmCreador.setText("Custom user");
        tvmFechaCreado.setText(fechaCreada);
        tvmFechaSolicita.setText(fechaPedida);
        tvmHorasSoli.setText(horas1);
        tvmHorasCumplidas.setText(horas2);
        etmRespuesta.setText(respuesta);
        etmMotivo.setText(motivo);
        this.estatus.setText(estatus);
        this.openSalidaRegistro.setVisibility(View.VISIBLE);//por si se oculto previamente
        if (estatus.equals("Aceptado")) {
            this.estatus.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_aprovado, 0, 0, 0);
        } else if (estatus.equals("Pendiente")) {
            this.estatus.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ico_caution, 0, 0, 0);
            this.openSalidaRegistro.setVisibility(View.GONE);
        } else {


            this.estatus.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_denied, 0, 0, 0);
        }




        dialogSeeMore.show();
        this.currentid=id;
    }


    public void delete(int id) {
        try {
            final String stmt = "exec eliminarSolicitud " + id;
            final GenericAsyncDBTask gdbc = new GenericAsyncDBTask(getActivity(), new AsyncTaskCallback() {
                @Override
                public void onTaskCompleted(ResultSet r) {
                    try {
                        String msj;
                        if (r.next()) {
                            msj = Conector.msj(r);
                            LoadData();
                        } else {
                            msj = "Ha ocurrido un error";
                        }
                        Toast.makeText(getActivity().getBaseContext(), msj, Toast.LENGTH_LONG).show();

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            });

            new AlertDialog.Builder(getActivity())
                    .setTitle("Confirmación")
                    .setMessage("¿Realmente deseas eleminar está solicitud?")
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            gdbc.execute(stmt);
                        }
                    })
                    .setNegativeButton(android.R.string.no, null).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getActivity().getBaseContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }


    @Override
    public void onRefresh() {
        LoadData();
    }
}

