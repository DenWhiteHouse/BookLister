package com.example.denny.booklister.Service;

import android.content.Context;
import android.widget.TextView;
import android.widget.Toast;

import com.example.denny.booklister.MainActivity;
import com.example.denny.booklister.Notification.NotificationUtils;

public class ReminderTasks {
    public static final String ACTION_EDIT_TEXT = "action-edit-text";
    public static final String ACTION_DISMISS_NOTIFICATION = "dismiss-notification";
    private static Context mContext;

    public ReminderTasks(Context context){
        mContext =context;
    }


    public static void executeTask(Context context,String action){
        if (ACTION_DISMISS_NOTIFICATION.equals(action)) {
            NotificationUtils.clearAllNotifications(context);
        } else {
            MainActivity.showBackGroundToast();
        }
    }
}
