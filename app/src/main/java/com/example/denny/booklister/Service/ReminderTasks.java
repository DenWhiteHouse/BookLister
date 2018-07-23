package com.example.denny.booklister.Service;

import android.content.Context;
import android.widget.TextView;
import android.widget.Toast;

import com.example.denny.booklister.MainActivity;

public class ReminderTasks {
    public static final String ACTION_EDIT_TEXT = "action-edit-text";
    private static Context mContext;

    public ReminderTasks(Context context){
        mContext =context;
    }


    public static void executeTask(){
        MainActivity.showBackGroundToast();
    }
}
