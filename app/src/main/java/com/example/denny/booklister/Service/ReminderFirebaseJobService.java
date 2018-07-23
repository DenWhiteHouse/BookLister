package com.example.denny.booklister.Service;


import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class ReminderFirebaseJobService extends com.firebase.jobdispatcher.JobService {
    // Since JobService run on the MainThread we make it works on Background with Async
    private AsyncTask mBackgroundTask;

    @Override
    public boolean onStartJob(final com.firebase.jobdispatcher.JobParameters jobParameters) {
        mBackgroundTask = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {
                Context context =ReminderFirebaseJobService.this;
                ReminderTasks.executeTask(context,ReminderTasks.ACTION_REMINDER);
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                jobFinished(jobParameters,false);
            }
        };

        mBackgroundTask.execute();
        return true;
    }

    @Override
    public boolean onStopJob(com.firebase.jobdispatcher.JobParameters jobParameters) {
        //if the Job met wrongs condition it's called onStop like a busy thread
        if(mBackgroundTask != null) mBackgroundTask.cancel(true);
        return true;
    }
}
