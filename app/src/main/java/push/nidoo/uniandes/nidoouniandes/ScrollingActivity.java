package push.nidoo.uniandes.nidoouniandes;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.google.firebase.iid.FirebaseInstanceId;

public class ScrollingActivity extends AppCompatActivity {


    private BroadcastReceiver myReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            writeEvent(intent);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        registerReceiver(myReceiver, new IntentFilter(Catcher.INTENT_FILTER));

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        EditText view = (EditText)findViewById(R.id.editText1);
        view.setText("Token: \""+FirebaseInstanceId.getInstance().getToken()+"\"");
        Log.e("Token", "\""+FirebaseInstanceId.getInstance().getToken()+"\"");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Token: "+FirebaseInstanceId.getInstance().getToken(), Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }


    public void writeEvent(Intent intent){
        EditText view = (EditText)findViewById(R.id.editText1);
        Snackbar.make(view, "Push Recibido", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
        try {
            String data=intent.getStringExtra("data");
            view.append("\nnew ");
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
}
