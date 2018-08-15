package com.loopwiki.qrsacnner;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.ArrayMap;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.lords.pases.interfaces.AsyncTaskCallback;
import com.lords.pases.util.Conector;
import com.lords.pases.util.GenericAsyncDBTask;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    Button button1;
    EditText etNombre, etApellido, etphono, etPassword, etPasswordCheck;
    Spinner spinerArea;
    ArrayList<String> arrayListAreas;
    Map<Integer, String> mapita;
    ArrayAdapter arrayAdapterArea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        etNombre = findViewById(R.id.et_registro_name);
        etApellido = findViewById(R.id.et_reg_last_name);
        etphono = findViewById(R.id.et_reg_fono);
        etPassword = findViewById(R.id.editText_reg_pass);
        etPasswordCheck = findViewById(R.id.editText_reg_pass2);
        spinerArea = findViewById(R.id.spinner_area);
        button1 = findViewById(R.id.btn_send_register);


        arrayListAreas = new ArrayList<>();
        arrayAdapterArea = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, arrayListAreas);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            mapita = new ArrayMap<>();
        }
        spinerArea.setAdapter(arrayAdapterArea);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (etApellido.getText().toString().equals("") || etNombre.getText().toString().equals("") || etPassword.getText().toString().equals("") || etPasswordCheck.getText().toString().equals("") || etphono.getText().toString().equals("")) {
                    Toast.makeText(getBaseContext(), "Se deben de llenar todos los campos", Toast.LENGTH_LONG).show();
                } else if (!etPassword.getText().toString().equals(etPasswordCheck.getText().toString())) {
                    Toast.makeText(getBaseContext(), "Las contraseña no coinciden", Toast.LENGTH_LONG).show();
                } else {
                    register();
                }
            }
        });

        loadDataSpinners();


    }


    private void loadDataSpinners() {
        try {
            String stmtArea = "exec llamarAreas";
            GenericAsyncDBTask async = new GenericAsyncDBTask(this, new AsyncTaskCallback() {
                @Override
                public void onTaskCompleted(ResultSet r) throws SQLException {
                    while (r.next()) {
                        arrayListAreas.add(r.getString("area"));
                        mapita.put(r.getInt("idArea"), r.getString("area"));
                        arrayAdapterArea.notifyDataSetChanged();
                    }
                }
            });
            async.execute(stmtArea);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }


    public void register() {
        try {
            String stmt = "exec crearTrabajador '" + etNombre.getText().toString() + "','" + etApellido.getText().toString() + "','" + etphono.getText().toString() + "',3,'" + spinerArea.getSelectedItemId() + "','" + etPassword.getText().toString() + "'";
            GenericAsyncDBTask gdbc = new GenericAsyncDBTask(this, new AsyncTaskCallback() {
                @Override
                public void onTaskCompleted(ResultSet r) {
                    try {
                        String msj;
                        if (r.next()) {
                            msj =  Conector.msj(r);;
                        } else {
                            msj = "Ha ocurrido un error";
                        }
                        Toast.makeText(getBaseContext(), msj, Toast.LENGTH_LONG).show();

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            });
            gdbc.execute(stmt);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }

}
