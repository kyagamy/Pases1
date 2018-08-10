package com.lords.pases.interfaces;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface AsyncTaskCallback {
    void onTaskCompleted(ResultSet r) throws SQLException;
}
