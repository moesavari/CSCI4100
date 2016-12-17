package com.example.lab_04;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.os.BatteryManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private TextView batteryInfo;
    private String batteryHealth = "";
    private boolean isCharging;
    private String chargeMethod = "None";
    private int temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        batteryInfo = (TextView) findViewById(R.id.textViewBatteryInfo);

        this.registerReceiver(this.batteryinfoReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));

    }

    private BroadcastReceiver batteryinfoReceiver = new BroadcastReceiver() {

        public void onReceive(Context context, Intent intent){

            IntentFilter iFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
            Intent batteryStatus = context.registerReceiver(null, iFilter);

            int health = intent.getIntExtra(BatteryManager.EXTRA_HEALTH, 0);
            int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
            temp = intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, 0);
            int chargePlug = batteryStatus.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
            isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING ||
                    status == BatteryManager.BATTERY_STATUS_FULL;
            boolean usbCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_USB;
            boolean acCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_AC;

            if(health == BatteryManager.BATTERY_HEALTH_GOOD)
                batteryHealth = "Good";
            else if(health == BatteryManager.BATTERY_HEALTH_COLD)
                batteryHealth = "Cold";
            else if(health == BatteryManager.BATTERY_HEALTH_DEAD)
                batteryHealth = "DEAD";

            if(usbCharge)
                chargeMethod = "USB";
            else if(acCharge)
                chargeMethod = "AC";

            batteryInfo.setText("Battery Health: " + batteryHealth + "\n"
                                + "Is charging: " + isCharging + "\n"
                                + "Method of charging: " + chargeMethod + "\n"
                                + "Temperature: " + temp + "\n"
            );

            showNotification();

        }
    };

    public void showNotification() {
        PendingIntent pi = PendingIntent.getActivity(this, 0, new Intent(this, MainActivity.class), 0);
        Resources r = getResources();
        Notification notification = new NotificationCompat.Builder(this)
                .setTicker(r.getString(R.string.notification_title))
                .setSmallIcon(android.R.drawable.ic_menu_report_image)
                .setContentTitle(r.getString(R.string.notification_title))
                .setContentText("Battery Health: " + batteryHealth + "\n"
                        + "Is charging: " + isCharging + "\n"
                        + "Method of charging: " + chargeMethod + "\n"
                        + "Temperature: " + temp + "\n")
                .setContentIntent(pi)
                .setAutoCancel(true)
                .build();

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(0, notification);
    }
}
