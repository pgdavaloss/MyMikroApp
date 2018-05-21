package com.etr.MyMikroApp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.List;
import java.util.Map;

import me.legrange.mikrotik.MikrotikApiException;
import me.legrange.mikrotik.ApiConnection;
import me.legrange.mikrotik.ResultListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    final String LOG_TAG = "mLog";

    //Button btnConnect;

    MyTask mt;

    Button btnConnect;


    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnConnect = (Button) findViewById(R.id.btnConnect);

        btnConnect.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        mt = new MyTask();
        mt.execute();
    }

    class MyTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //tvResult.setText("Begin");
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {

                List<Map<String, String>> result = null;

                try
                {
                    Log.d(LOG_TAG, "start");

                    ApiConnection con = ApiConnection.connect("IP");
                    Log.d(LOG_TAG, "start2");
                    con.login("login", "password");
                    if(con.isConnected())
                    {
                        //tvResult.setText("OK!");
                        Log.d(LOG_TAG, "isConnected");
                    }
                    result = con.execute("/interface/print");
                    for(Map<String, String> res : result)
                    {
                        Log.d(LOG_TAG, res.toString());
                    }
                    con.close();
                }
                catch (Exception e)
                {
                    Log.d(LOG_TAG, "error");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            //tvResult.setText("End");
        }
    }
}
