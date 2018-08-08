package com.lords.pases.interfaces;

import java.sql.ResultSet;

public interface AsyncTaskCallback {
    void onTaskCompleted(ResultSet r);
}
