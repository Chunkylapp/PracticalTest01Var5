package ro.pub.cs.systems.eim.practicaltest01var05;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import java.security.Provider;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class PracticalTest01Var05Service extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channel_id = "my_channel_01";
            NotificationChannel channel = new NotificationChannel(channel_id, "Channel human readable title", NotificationManager.IMPORTANCE_HIGH);
            ((NotificationManager) getSystemService(NOTIFICATION_SERVICE)).createNotificationChannel(channel);

            Notification notification = new Notification.Builder(this, channel_id)
                    .setContentTitle("Testing notification")
                    .setSmallIcon(R.drawable.ic_launcher_foreground)
                    .setContentText("This is a test notification that will be displayed when the service is running")
                    .setAutoCancel(true)
                    .build();

            startForeground(1, notification);
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("PracticalTest01Var05", "onStartCommand() method was invoked");

        MessageSender messageSender = new MessageSender(this, Objects.requireNonNull(intent.getStringExtra("data")));
        messageSender.start();

        return START_REDELIVER_INTENT;
    }

}
