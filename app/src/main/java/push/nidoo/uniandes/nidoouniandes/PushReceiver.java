package push.nidoo.uniandes.nidoouniandes;

import me.pushy.sdk.Pushy;
import android.content.Intent;
import android.graphics.Color;
import android.content.Context;
import android.app.PendingIntent;
import android.media.RingtoneManager;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class PushReceiver extends BroadcastReceiver {
    public static final String INTENT_FILTER = "REPLAY_INTENT_FILTER";
    @Override
    public void onReceive(Context context, Intent intent) {
        HashMap<String,String> result= new HashMap<String,String>();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
        result.put("time",df.format(new Date()));

        String notificationTitle = "MyApp";
        String notificationText = "Test notification";

        if (intent.getStringExtra("message") != null) {
            notificationText = intent.getStringExtra("message");
            result.put("message",notificationText);
        }
        Intent replayIntent = new Intent(INTENT_FILTER);
        try {
            replayIntent .putExtra("data", (new JSONObject(result)).toString());
        }catch (Exception e){
            Log.e("Exc",e.toString());
        }

        context.sendBroadcast(replayIntent);

/*
        Intent intent = new Intent(INTENT_FILTER);
        try {
            intent.putExtra("data", (new JSONObject(result)).toString());
        }catch (Exception e){
            Log.e("Exc",e.toString());
        }
        sendBroadcast(intent);

*/




        // Attempt to extract the "message" property from the payload: {"message":"Hello World!"}
        /*if (intent.getStringExtra("message") != null) {
            notificationText = intent.getStringExtra("message");
        }

        // Prepare a notification with vibration, sound and lights
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setAutoCancel(true)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle(notificationTitle)
                .setContentText(notificationText)
                .setLights(Color.RED, 1000, 1000)
                .setVibrate(new long[]{0, 400, 250, 400})
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setContentIntent(PendingIntent.getActivity(context, 0, new Intent(context, ScrollingActivity.class), PendingIntent.FLAG_UPDATE_CURRENT));

        // Automatically configure a Notification Channel for devices running Android O+
        Pushy.setNotificationChannel(builder, context);

        // Get an instance of the NotificationManager service
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);

        // Build the notification and display it
        notificationManager.notify(1, builder.build());*/
    }
}
