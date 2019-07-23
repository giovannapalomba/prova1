package it.unipr.mobdev.easythoraxus.network;

import android.content.Context;
import android.content.AsyncTaskLoader;
import android.util.Log;

import java.io.IOException;

import it.unipr.mobdev.easythoraxus.models.DiagnosisDescriptor;
import it.unipr.mobdev.easythoraxus.utils.Global;
import it.unipr.mobdev.easythoraxus.utils.QueryUtils;

public class DiagnosisLoader extends AsyncTaskLoader<DiagnosisDescriptor> {

    private static final String TAG = DiagnosisLoader.class.getName();

    private String mUrl = Global.DIAGNOSIS_URL;

    public DiagnosisLoader(Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public DiagnosisDescriptor loadInBackground() {

        if (mUrl == null) {
            return null;
        }

        String jsonResponse = null;
        try {
            jsonResponse = QueryUtils.makeHttpRequest(mUrl);
            Log.e(TAG, "Connessione stabilita");
        } catch (IOException e) {
            Log.e(TAG, "Problem making the HTTP request.", e);
        }

        //Log.i(TAG, jsonResponse);
        DiagnosisDescriptor diagnosi = QueryUtils.parserDiagnosis(jsonResponse);
        //Log.i(TAG, diagnosi.toString());
        return diagnosi;
    }
}
