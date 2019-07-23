package it.unipr.mobdev.easythoraxus.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.app.LoaderManager;
import android.content.Loader;
import android.view.View;
import android.widget.TextView;

import it.unipr.mobdev.easythoraxus.models.DiagnosisDescriptor;
import it.unipr.mobdev.easythoraxus.network.DiagnosisLoader;
import it.unipr.mobdev.easythoraxus.utils.ChronoManager;
import it.unipr.mobdev.easythoraxus.utils.Global;
import it.unipr.mobdev.easythoraxus.R;

public class DiagnosisActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<DiagnosisDescriptor> {

    private TextView diagnosi;
    private TextView description;

    //private static final int LOADER_ID = 1;
    private static final String TAG = DiagnosisActivity.class.getSimpleName();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diagnosis_refactor);
        getSupportActionBar().setTitle("Diagnosi");

        //save chrono log to internal storage
        ChronoManager.getInstance(this).addLogToHead(Global.current_time_log);

       /* Log.e(TAG, Global.current_time_log.getName() + " " + Global.current_time_log.getTs_start() + " " + Global.current_time_log.getTs_end() +
                " " + Global.current_time_log.getDuration() + Global.current_time_log.getTime_per_step());
                */
        LoaderManager loaderManager = getLoaderManager();
        loaderManager.initLoader(Global.LOADER_ID, null, this);

       //Global.selected_option = null; //azzero l'opzione scelta
        diagnosi = (TextView) findViewById(R.id.diagnosis);
        description = (TextView) findViewById(R.id.description);
    }

    @Override
    public Loader<DiagnosisDescriptor> onCreateLoader(int i, Bundle bundle) {
        return new DiagnosisLoader(this);
    }

    @Override
    public void onLoadFinished(Loader<DiagnosisDescriptor> loader, DiagnosisDescriptor diagnosisDescriptor) {
        View loadingIndicator = findViewById(R.id.loading_indicator3);
        loadingIndicator.setVisibility(View.GONE);
        if (diagnosisDescriptor != null) {
            update(diagnosisDescriptor);
        }
    }

    @Override
    public void onLoaderReset(Loader<DiagnosisDescriptor> loader) {
        //  adapter.clear();
    }

    private void update(DiagnosisDescriptor diagnosisDescriptor) {
        diagnosi.setText(diagnosisDescriptor.getName());
        description.setText(diagnosisDescriptor.getDescription());
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void restart(View v) {
        Global.n_step = 0; //poi ricomincerò da 0
        Intent intent = new Intent(DiagnosisActivity.this, InsertPatientDataActivity.class);
        startActivity(intent);

    }

}
