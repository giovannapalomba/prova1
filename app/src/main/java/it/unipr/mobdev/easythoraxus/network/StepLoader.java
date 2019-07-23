package it.unipr.mobdev.easythoraxus.network;

import android.content.Context;
import android.content.AsyncTaskLoader;
import android.util.Log;

import java.io.IOException;

import it.unipr.mobdev.easythoraxus.models.StepDescriptor;
import it.unipr.mobdev.easythoraxus.utils.QueryUtils;

public class StepLoader extends AsyncTaskLoader<StepDescriptor> {

    private static final String TAG = StepLoader.class.getName();

    private String mUrl;

    public StepLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public StepDescriptor loadInBackground() {

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
        // info è un array, in ogni posizione c'è un'informazione: 0: domanda, 1: risp1,
        // 2: risp2, 3: descr, 4: finale (bool)
        //Log.i(TAG, jsonResponse);
        StepDescriptor step = QueryUtils.parserStep(jsonResponse);
        //Log.i(TAG, info.toString());
        return step;

    }
}
