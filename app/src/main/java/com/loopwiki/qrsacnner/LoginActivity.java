package com.loopwiki.qrsacnner;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.VideoView;
import com.lords.pases.MainPases;
import com.lords.pases.interfaces.AsyncTaskCallback;
import com.lords.pases.util.GenericAsyncDBTask;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginActivity extends AppCompatActivity {
    VideoView bg;
    EditText etUser, etPassword;
    Button button;

    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sharedPref= getSharedPreferences("myPref", Context.MODE_PRIVATE);

        editor=sharedPref.edit();

        String matri =sharedPref.getString("matri",null);
        if (matri!=null){
            Intent i = new Intent(LoginActivity.this, MainPases.class);
            i.putExtra("matri",matri);
            startActivity(i);
        }

        // Save your string in SharedPref




        button = findViewById(R.id.button_login);
        etUser = findViewById(R.id.et_user);
        etPassword = findViewById(R.id.et_pwd);
        bg = findViewById(R.id.bgVideoView);


        String path = "android.resource://" + getPackageName() + "/" + R.raw.bg;
        bg.setVideoURI(Uri.parse(path));


        bg.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
                mp.setVolume(0, 0);
            }
        });


        bg.start();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login(etUser.getText().toString(), etPassword.getText().toString());
            }
        });


    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        bg.start();
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private void login(final String user1, String pass) {
        try {
            String stmt = "exec logUser '" + user1 + "','" + pass + "' ";
            GenericAsyncDBTask gdbc = new GenericAsyncDBTask(LoginActivity.this, new AsyncTaskCallback() {
                @Override
                public void onTaskCompleted(ResultSet r) {
                    try {
                        if (r.next()) {

                            editor.putString("matri", user1);
                            editor.commit();
                            Intent i = new Intent(LoginActivity.this, MainPases.class);
                            i.putExtra("matri",user1);
                            startActivity(i);
                            makeMsk("Bienvenido :)");
                        } else {
                            makeMsk("Ha habido algun error con la autentificación La NASA no te deja entrar :c");
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            });
            gdbc.execute(stmt);


        } catch (Exception ex) {
            ex.printStackTrace();
            makeMsk(ex.getMessage());
        }
    }

    private void makeMsk(String msj) {
        Toast.makeText(getBaseContext(), msj, Toast.LENGTH_LONG).show();
    }




}
