package push.nidoo.uniandes.nidoouniandes;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.design.widget.Snackbar;
import android.util.Log;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.HashMap;
//import com.google.firebase.firebase_core.*;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.google.firebase.messaging.RemoteMessage;
import com.google.firebase.messaging.FirebaseMessagingService;

import org.json.JSONObject;

public class Catcher extends FirebaseMessagingService {
    public static final String INTENT_FILTER = "INTENT_FILTER";

    public Catcher() {

    }

    @Override
    public void onDeletedMessages() {
        Log.e("onDeletedMessages", "onDeletedMessages");
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.e("MSG","MSG");
        HashMap<String,String> result= new HashMap<String,String>();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
        result.put("time",df.format(new Date()));

        if (remoteMessage.getNotification() != null) {
            result.put("msg",remoteMessage.getNotification().getBody());
            //Log.e("Not", "Message Notification Body: " + remoteMessage.getNotification().getBody());
        }

        if (remoteMessage.getData().size() > 0) {
            result.putAll(remoteMessage.getData());
            //Log.e("Data", "Message data payload: " + remoteMessage.getData());
        }
        Log.e("MessageReceived", result.toString());
        Intent intent = new Intent(INTENT_FILTER);
        try {
            intent.putExtra("data", (new JSONObject(result)).toString());
        }catch (Exception e){
            Log.e("Exc",e.toString());
        }

        sendBroadcast(intent);



        //Snackbar.make(view, "Token: " + FirebaseInstanceId.getInstance().getToken(), Snackbar.LENGTH_LONG)
        //        .setAction("Action", null).show();





    }
}
