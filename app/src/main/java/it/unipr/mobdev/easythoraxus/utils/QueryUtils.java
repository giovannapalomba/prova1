package it.unipr.mobdev.easythoraxus.utils;

import android.util.Log;

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

import it.unipr.mobdev.easythoraxus.models.DiagnosisDescriptor;
import it.unipr.mobdev.easythoraxus.models.ProcedureDescriptor;
import it.unipr.mobdev.easythoraxus.models.StepDescriptor;


public final class QueryUtils {

    private static final String TAG = QueryUtils.class.getSimpleName();

    //costruttore privato, contiene variabili e metodi statici, non c'è bisogno di un oggetto
    private QueryUtils() {
    }

    public static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(TAG, "Problem building the URL ", e);
        }
        return url;
    }


    public static String makeHttpRequest(String surl) throws IOException {
        String jsonResponse = "";

        URL url = createUrl(surl);

        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();


            //se la richiesta ha successo (codice 200),
            //legge l'inputStream e parsa la risposta

            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(TAG, "Problem retrieving the JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {

                inputStream.close();
            }
        }
        return jsonResponse;
    }


     //Restituisce la stringa che sarà poi convertita in JSON

    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    public static ArrayList<ProcedureDescriptor> parserProcedure(String jsonFile) {
        ArrayList<ProcedureDescriptor> procedureDescriptors = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(jsonFile);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String nome = jsonObject.optString("nome");
                String descrizione = jsonObject.optString("descrizione");
                ProcedureDescriptor procedureDescriptor = new ProcedureDescriptor(nome, descrizione);
                procedureDescriptors.add(procedureDescriptor);
            }
        } catch (JSONException e) {
            Log.e(TAG, "Problem parsing the procedure JSON results", e);
        }
        return procedureDescriptors;
    }

    public static StepDescriptor parserStep(String jsonFile) {
        StepDescriptor step = new StepDescriptor();
        //ArrayList<String> info = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(jsonFile);
            step.setQuestion(jsonObject.optString("domanda"));
            JSONArray jsonArray = new JSONArray(jsonObject.optString("risposte"));
            ArrayList<String> answers = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                answers.add(jsonArray.getString(i));
                //info.add(answer);
            }
            step.setAnswers(answers);
            step.setDescription(jsonObject.optString("descrizione"));
            step.setLast_question(jsonObject.optString("finale"));
        } catch (JSONException e) {
            Log.e(TAG, "Problem parsing the info JSON results", e);
        }
        return step;
    }

    public static DiagnosisDescriptor parserDiagnosis(String jsonFile) {
        //ArrayList<String> diagnosi = new ArrayList<>();
        DiagnosisDescriptor diagnosis = new DiagnosisDescriptor();
        try {
            JSONArray jsonArray = new JSONArray(jsonFile);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String id = jsonObject.optString("id");
                if (id.equals("1")) {
                    diagnosis.setName(jsonObject.optString("nome"));
                    diagnosis.setDescription(jsonObject.optString("descrizione"));
                }
            }
        } catch (JSONException e) {
            Log.e(TAG, "Problem parsing the procedure JSON results", e);
        }
        return diagnosis;
    }
}







