package it.unipr.mobdev.easythoraxus.network;

import android.content.Context;
import android.content.AsyncTaskLoader;

import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;

import it.unipr.mobdev.easythoraxus.models.ProcedureDescriptor;
import it.unipr.mobdev.easythoraxus.utils.Global;
import it.unipr.mobdev.easythoraxus.utils.QueryUtils;

public class ProcedureLoader extends AsyncTaskLoader<ArrayList<ProcedureDescriptor>> {

    private static final String TAG = ProcedureLoader.class.getName();

    private String mUrl = Global.PROCEDURE_URL;

    public ProcedureLoader(Context context) {
        super(context);
        //mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    //thread in background
    @Override
    public ArrayList<ProcedureDescriptor> loadInBackground() {

        if (mUrl == null) {
            return null;
        }
        String jsonResponse = null;
        // richiesta connessione, estrazione json
        try {
            jsonResponse = QueryUtils.makeHttpRequest(mUrl);
            Log.e(TAG, "Connessione stabilita");
        } catch (IOException e) {
            Log.e(TAG, "Problem making the HTTP request.", e);
        }
        // parsing risposta
        ArrayList<ProcedureDescriptor> procedureDescriptor = QueryUtils.parserProcedure(jsonResponse);
        return procedureDescriptor;
    }
}
