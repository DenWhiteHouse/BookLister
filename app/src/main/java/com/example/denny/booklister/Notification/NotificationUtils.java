package com.example.denny.booklister.Notification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;

import com.example.denny.booklister.MainActivity;
import com.example.denny.booklister.R;
import com.example.denny.booklister.Service.ReminderIntentService;
import com.example.denny.booklister.Service.ReminderTasks;

public class NotificationUtils {
    private static int NOTIFICATION_REMINDER_INTENT_ID= 1456;
    private static String NOTIFICATION_REMINDER_CHANNEL= "reminder_channel";
    private static final int ACTION_SETEXT_PENDING_INTENT_ID = 1;
    private static final int ACTION_IGNORE_PENDING_INTENT_ID = 14;

    //clear notification
    public static void clearAllNotifications(Context context){
        NotificationManager notificationManager =(NotificationManager)
        context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancelAll();
    }

    //Notification
    public static void remindUserBecauseCharging(Context context){
        NotificationManager notificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);
        //From Android Oreo on must be defined a notification channel
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel mChannel = new NotificationChannel(
                    NOTIFICATION_REMINDER_CHANNEL,
                    "notification_main",
                    NotificationManager.IMPORTANCE_HIGH
            );
            notificationManager.createNotificationChannel(mChannel);
        }

        // Create the Notification
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context,NOTIFICATION_REMINDER_CHANNEL)
                .setColor(ContextCompat.getColor(context, R.color.colorPrimary))
                .setSmallIcon(R.drawable.ic_add_white_24dp)
                .setLargeIcon(largeIcon(context))
                .setContentTitle(context.getString(R.string.notification_title))
                .setContentText(context.getString(R.string.notification_description))
                .setContentIntent(contentIntent(context))
                .addAction(setTextAction(context))
                .addAction(ignoreReminderAction(context))
                .setAutoCancel(true);

        //Check for previous version of Andreid (<Oreo)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN
                && Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            notificationBuilder.setPriority(NotificationCompat.PRIORITY_HIGH);
        }
        notificationManager.notify(NOTIFICATION_REMINDER_INTENT_ID, notificationBuilder.build());
    }


    //Notification Actions method

    private static NotificationCompat.Action setTextAction(Context context) {
        // COMPLETED (12) Create an Intent to launch WaterReminderIntentService
        Intent setTextIntent = new Intent(context, ReminderIntentService.class);
        // COMPLETED (13) Set the action of the intent to designate you want to increment the water count
        setTextIntent.setAction(ReminderTasks.ACTION_EDIT_TEXT);
        // COMPLETED (14) Create a PendingIntent from the intent to launch WaterReminderIntentService
        PendingIntent setTextPendingIntent = PendingIntent.getService(
                context,
                ACTION_SETEXT_PENDING_INTENT_ID,
                setTextIntent,
                PendingIntent.FLAG_CANCEL_CURRENT);
        // COMPLETED (15) Create an Action for the user to tell us they've had a glass of water
        NotificationCompat.Action setText = new NotificationCompat.Action(R.drawable.ic_add_white_24dp,
                context.getString(R.string.notification_action_YES),
                setTextPendingIntent);
        // COMPLETED (16) Return the action
        return setText;
    }



    private static NotificationCompat.Action ignoreReminderAction(Context context) {
        // COMPLETED (6) Create an Intent to launch WaterReminderIntentService
        Intent ignoreReminderIntent = new Intent(context, ReminderIntentService.class);
        // COMPLETED (7) Set the action of the intent to designate you want to dismiss the notification
        ignoreReminderIntent.setAction(ReminderTasks.ACTION_DISMISS_NOTIFICATION);
        // COMPLETED (8) Create a PendingIntent from the intent to launch WaterReminderIntentService
        PendingIntent ignoreReminderPendingIntent = PendingIntent.getService(
                context,
                ACTION_IGNORE_PENDING_INTENT_ID,
                ignoreReminderIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        // COMPLETED (9) Create an Action for the user to ignore the notification (and dismiss it)
        NotificationCompat.Action ignoreReminderAction = new NotificationCompat.Action(R.drawable.ic_add_white_24dp,
                context.getString(R.string.notification_action_NO),
                ignoreReminderPendingIntent);
        // COMPLETED (10) Return the action
        return ignoreReminderAction;
    }



    //create the PendingIntent
    private static PendingIntent contentIntent(Context contet){
        Intent startMainActivity = new Intent(contet, MainActivity.class);
        //wrap the explicit intent into a Pending Intent
        return  PendingIntent.getActivity(contet,NOTIFICATION_REMINDER_INTENT_ID,startMainActivity,PendingIntent.FLAG_UPDATE_CURRENT);
    }

    //generate the bitmap fot the notification
    private static Bitmap largeIcon(Context contet){
        Resources res = contet.getResources();
        Bitmap largeIcon = BitmapFactory.decodeResource(res, R.drawable.ic_add_white_24dp);
        return  largeIcon;
    }

}
