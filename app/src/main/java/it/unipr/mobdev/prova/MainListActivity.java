package it.unipr.mobdev.prova;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MainListActivity extends AppCompatActivity  implements MyListAdapter.ItemClickListener{


    MyListAdapter adapter;
    public static final String TAG = MainActivity.class.getSimpleName();

    final String jsonFile = null;

    /** URL to query the USGS dataset for earthquake information */
    private static final String REQUEST_URL = "http://10.0.2.2:3000/procedure"; // cleartext http traffic to localhost not permitted!
                //localhost è 10.0.2.2
    //private static final String REQUEST_URL = "http://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=2014-01-01&endtime=2014-12-01&minmagnitude=7";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_activity);

        // Create URL object
        URL url = QueryUtils.createUrl(REQUEST_URL);


        Connect connection = new Connect();
        connection.execute(url);

        // data to populate the RecyclerView with

        /*final String jsonFile = "{\"procedure\":[{\"nome\":\"collo\",\"abilitazione\":[\"Parma\",\"Milano\"]}," +
                "{\"nome\":\"polmoni\",\"abilitazione\":[\"Parma\",\"Milano\",\"Torino\"]}," +
                "{\"nome\":\"pleura\",\"abilitazione\":[\"Parma\",\"Milano\",\"Torino\"]}," +
                "{\"nome\":\"diaframma\",\"abilitazione\":[\"Parma\",\"Torino\"]}]}";*/
       /* String data = "";
        ArrayList<String> procedure = new ArrayList<>();
        try{JSONObject jsonRootObject= new JSONObject(jsonFile);
            JSONArray jsonArray = jsonRootObject.optJSONArray("procedure");
            for(int i=0; i < jsonArray.length(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String nome = jsonObject.optString("nome");
                procedure.add(nome);

            }
        } catch (JSONException e) {
            Log.e(TAG, "Problem parsing the earthquake JSON results", e);
        }*/

        /*procedure.add("Collo");
        procedure.add("Polmoni");
        procedure.add("Pleura");
        procedure.add("Diaframma");*/

        // set up the RecyclerView
        /*RecyclerView recyclerView = findViewById(R.id.procedure);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyListAdapter(this, procedure);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);*/
    }

    private class Connect extends AsyncTask<URL, Void, ArrayList<String>> {
        @Override
        protected ArrayList<String> doInBackground(URL... urls) {
            // Perform HTTP request to the URL and receive a JSON response back
            String jsonResponse = null;
            for(int i=0; i < urls.length; i++) {
                try {
                    jsonResponse = QueryUtils.makeHttpRequest(urls[i]);
                    Log.e(TAG, "Connessione stabilita");
                } catch (IOException e) {
                    Log.e(TAG, "Problem making the HTTP request.", e);
                }

            }
            ArrayList<String> procedure = QueryUtils.parser(jsonResponse);
            return procedure;
        }

        @Override
        protected void onPostExecute(ArrayList<String> procedure){
            update(procedure);
        }


    }


    /**
     * Returns new URL object from the given string URL.
     */

    private void update( ArrayList<String> procedure){
        RecyclerView recyclerView = findViewById(R.id.procedure);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyListAdapter(this, procedure);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(this, "You clicked " + adapter.getItem(position), Toast.LENGTH_SHORT).show();
        //all'inizio della procedure prendo timestamp
        ArrayList<String> timestamp = new ArrayList<>();
        Long tsLong = System.currentTimeMillis()/1000;
        timestamp.add(tsLong.toString());

        //TODO: al click manda richiesta al server che risponde con la domanda
    }

}