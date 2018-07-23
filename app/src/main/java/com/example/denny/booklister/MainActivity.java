package com.example.denny.booklister;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.denny.booklister.Book.AddBookView;
import com.example.denny.booklister.Notification.NotificationUtils;
import com.example.denny.booklister.Service.ReminderIntentService;
import com.example.denny.booklister.Service.ReminderTasks;
import com.example.denny.booklister.Service.ReminderUtilities;

public class MainActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {
    private static SharedPreferences sharedPreferences;
    private static TextView mainTextView;
    private static Context mContext;
    private static Button mNotificationButton;

    //I won't use the manifest, I'll we add a Dynamic Broadcast Receiver
    IntentFilter mChargingintentFiler;
    BroadcastReceiver mChargingBroadcastReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainTextView = findViewById(R.id.main_text);
        mNotificationButton =findViewById(R.id.notification_button);
        setSharedPreferences();
        mContext=getApplicationContext();

        //Inizialized the INTENT FILTER fo filtering charging status
        mChargingintentFiler = new IntentFilter();
        mChargingintentFiler.addAction(Intent.ACTION_POWER_CONNECTED);

        //inizialized the Broadcastreceiver with the custom one
        mChargingBroadcastReceiver = new CharchingBroadcastReceiver();

        //Learning Code for backgroud features

        //Trying Backgroud Task
        Intent intent = new Intent(this, ReminderIntentService.class);
        intent.setAction(ReminderTasks.ACTION_BACKGROUND);
        startService(intent);

        //Binding button for testing Notification
        mNotificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                testNotification(view);
            }
        });

        //set the Reminder with FirebaseJobService
        ReminderUtilities.scheduleReminder(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_book:
                // User action add a book to the Library
                Intent startAddBookActivity = new Intent(this, AddBookView.class);
                startActivity(startAddBookActivity);
                return true;

            case R.id.action_settings:
                // Selection of Settings
                Intent startSettingsActivity = new Intent(this, SettingsActvity.class);
                startActivity(startSettingsActivity);
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    private void setSharedPreferences(){
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
        if (s.equals("show_name")){
            mainTextView.setText(String.valueOf(sharedPreferences.getBoolean("show_name",true)));
        }
    }

    @Override
    protected void onDestroy() {
        PreferenceManager.getDefaultSharedPreferences(this).unregisterOnSharedPreferenceChangeListener(this);
        super.onDestroy();
    }

    synchronized public static void editMainTextInBackgroud(){
        mainTextView.setText("background");
    }

    public void testNotification(View view) {
        NotificationUtils.remindUserBecauseCharging(this);
    }

    public void updateMainTextBecauseOfBroadcastReceiver() {
        mainTextView.setText("boradcast receiver got and intent because charging status");
    }

    //Defyining my BroadcastReceiver
    private class CharchingBroadcastReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(Intent.ACTION_POWER_CONNECTED)){
                updateMainTextBecauseOfBroadcastReceiver();
            }
        }
    }

    //Remember that receivers must be registered on Resume and Unregistered onPause
    //Because the UI doesn't need to be updated when the UI is not in foreground
    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(mChargingBroadcastReceiver);
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(mChargingBroadcastReceiver,mChargingintentFiler);
        /* If you want to check the entry status of the phone
        based on the API (>23 or <23) can be used a BatteryManager or
        a stricky Intent to check the BatteryStatus when Resuming the Activity
         */
    }
}
