package com.example.denny.booklister.Service;

import android.content.Context;
import android.content.Intent;

import com.example.denny.booklister.Book.AddBookView;
import com.example.denny.booklister.MainActivity;
import com.example.denny.booklister.Notification.NotificationUtils;

public class ReminderTasks {
    public static final String ACTION_BACKGROUND = "action-background";
    public static final String ACTION_EDIT_TEXT = "action-edit-text";
    public static final String ACTION_DISMISS_NOTIFICATION = "dismiss-notification";
    private static Context mContext;

    public ReminderTasks(Context context){
        mContext =context;
    }


    public static void executeTask(Context context,String action){
        if (ACTION_DISMISS_NOTIFICATION.equals(action)) {
            NotificationUtils.clearAllNotifications(context);
        } else if (ACTION_EDIT_TEXT.equals(action)) {
            {
            Intent intent = new Intent(context,AddBookView.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
            NotificationUtils.clearAllNotifications(context);
        }}
        else{
            MainActivity.editMainTextInBackgroud();
            }
        }
}
