package it.unipr.mobdev.easythoraxus.network;

import android.content.Context;
import android.content.AsyncTaskLoader;
import android.util.Log;

import java.io.IOException;

import it.unipr.mobdev.easythoraxus.models.StepDescriptor;
import it.unipr.mobdev.easythoraxus.utils.Global;
import it.unipr.mobdev.easythoraxus.utils.QueryUtils;

public class StepLoader extends AsyncTaskLoader<StepDescriptor> {

    private static final String TAG = StepLoader.class.getName();

    private String mUrl = Global.STEP_URL + Global.n_step;

    public StepLoader(Context context) {
        super(context);
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
            Log.e(TAG, "Connessione stabilita" + mUrl);
        } catch (IOException e) {
            Log.e(TAG, "Problem making the HTTP request.", e);
        }

        StepDescriptor step = QueryUtils.parserStep(jsonResponse);
        return step;

    }
}
