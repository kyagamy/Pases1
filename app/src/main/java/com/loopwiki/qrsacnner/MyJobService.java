package com.loopwiki.qrsacnner;

import android.util.Log;

import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;

class MyJobService extends JobService {
    @Override
    public boolean onStartJob(JobParameters job) {
        Log.d("TAG", "Performing long running task in scheduled job");
// TODO(developer): add long running task here.
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters job) {
        return false;
    }
}
