package com.lords.pases.util;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;

import com.lords.pases.MainPases;
import com.lords.pases.interfaces.AsyncTaskCallback;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GenericAsyncDBTask   extends AsyncTask<String,ResultSet,ResultSet> {

    ProgressDialog dialog;
    Activity activity;
    public ResultSet x;
    private AsyncTaskCallback listener;

    public GenericAsyncDBTask(Activity activity,AsyncTaskCallback listener ) {
        this.activity=activity;
        dialog= new ProgressDialog(activity);
        dialog.setTitle("Un momento...");
        dialog.setMessage("Verificando los datos en la NASA");
        this.listener=listener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        showDialog();
    }

    @Override
    protected void onPostExecute(ResultSet s) {
        super.onPostExecute(s);
        stopDialog();

        listener.onTaskCompleted(s);

    }

    @Override
    protected ResultSet doInBackground(String... strings) {
        try {
            return Conector.CONN().createStatement().executeQuery(strings[0]);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    private void showDialog(){
        dialog.show();
    }
    private void stopDialog(){
        if (dialog.isShowing()) {
            dialog.dismiss();
        }
    }

}
