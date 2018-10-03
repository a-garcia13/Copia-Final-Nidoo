package push.nidoo.uniandes.nidoouniandes;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;


import java.net.URL;

import me.pushy.sdk.Pushy;
import me.pushy.sdk.util.exceptions.PushyException;

public class ScrollingActivity extends AppCompatActivity {


    private BroadcastReceiver myReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            writeEvent(intent);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        registerReceiver(myReceiver, new IntentFilter(PushReceiver.INTENT_FILTER));

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (!Pushy.isRegistered(getApplicationContext())) {
            new RegisterForPushNotificationsAsync().execute();
        }else{
            Pushy.listen(this);
        }
        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            // Request both READ_EXTERNAL_STORAGE and WRITE_EXTERNAL_STORAGE so that the
            // Pushy SDK will be able to persist the device token in the external storage
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
        }
    }


    public void writeEvent(Intent intent){
        EditText view = (EditText)findViewById(R.id.editText1);
        Snackbar.make(view, "Push Recibido", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
        try {
            String data=intent.getStringExtra("data");
            view.append("\npsh ");
            view.append(data);
        }catch (Exception e){
            Log.e("Exc",e.toString());
        }
    }

    @Override
    public void onDestroy(){
        unregisterReceiver(myReceiver);
        super.onDestroy();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private class RegisterForPushNotificationsAsync extends AsyncTask<Void, Void, Exception> {
        protected Exception doInBackground(Void... params) {
            try {
                // Assign a unique token to this device
                String deviceToken = Pushy.register(getApplicationContext());

                // Log it for debugging purposes
                Log.e("MyApp", "Pushy device token: " + deviceToken);

                // Send the token to your backend server via an HTTP GET request
                //new URL("https://backend.com/mt?t=" + deviceToken).openConnection();
            }
            catch (Exception exc) {
                // Return exc to onPostExecute
                return exc;
            }

            // Success
            return null;
        }

        @Override
        protected void onPostExecute(Exception exc) {
            // Failed?
            if (exc != null) {
                // Show error as toast message
                Toast.makeText(getApplicationContext(), exc.toString(), Toast.LENGTH_LONG).show();
                return;
            }

            // Succeeded, optionally do something to alert the user
        }
    }
}
