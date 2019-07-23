package it.unipr.mobdev.easythoraxus.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import android.app.LoaderManager;
import android.content.Loader;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import it.unipr.mobdev.easythoraxus.models.DiagnosisDescriptor;
import it.unipr.mobdev.easythoraxus.network.DiagnosisLoader;
import it.unipr.mobdev.easythoraxus.utils.Global;
import it.unipr.mobdev.easythoraxus.R;

public class DiagnosisActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<DiagnosisDescriptor> {

    private TextView diagnosi;
    private TextView description;

    private static final int LOADER_ID = 1;
    private static final String TAG = DiagnosisActivity.class.getSimpleName();

    private String REQUEST_URL = "http://10.0.2.2:3000/diagnosi";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diagnosis);
        getSupportActionBar().setTitle("Diagnosi");
        //saveToFile();

        Log.e(TAG, Global.current_time_log.getName() + " " + Global.current_time_log.getTs_start() + " " + Global.current_time_log.getTs_end() +
                " " + Global.current_time_log.getDuration() + Global.current_time_log.getTime_per_step());

        LoaderManager loaderManager = getLoaderManager();
        loaderManager.initLoader(LOADER_ID, null, this);

       // Global.selected_option = null; //azzero l'opzione scelta

        diagnosi = (TextView) findViewById(R.id.diagnosis);
        description = (TextView) findViewById(R.id.description);
    }


    @Override
    public Loader<DiagnosisDescriptor> onCreateLoader(int i, Bundle bundle) {
        return new DiagnosisLoader(this, REQUEST_URL);
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
        //this.finish();
        Intent intent = new Intent(DiagnosisActivity.this, InsertPatientDataActivity.class);
        startActivity(intent);

    }

    public void saveToFile() {

        ArrayList<String> to_save = Global.to_save;
        ArrayList<String> step_time = Global.step_time;
        //String msg = "";
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < to_save.size(); i++) {

            output.append(to_save.get(i));
            output.append(',');

        }
        for (int i = 0; i < step_time.size(); i++) {
            output.append(step_time.get(i));
            if (i == (step_time.size() - 1))
                output.append('\n');
            else
                output.append(',');
        }

        Log.i(TAG, "String builder: " + output);

        /*String str = "Collo,1562853747,1562863969,3,01.24,05.12,03.72\n" +
                "Polmoni,1562940241517,1562940257748,6,03.19,02.04,02.15,04.30,02.27,02.26\n" +
                "Pleura,1562940686333,1562940700875,6,02.81,02.16,02.05,03.00,02.14,02.36\n" +
                "Diaframma,1562952200147,1562952215318,6,03.94,02.70,01.96,02.10,02.07,02.38\n" +
                "Polmoni,1562952632881,1562952656632,6,02.95,02.87,02.81,02.45,03.33,09.32";*/

        File file;
        FileOutputStream outputStream;
        try {
            file = new File("/data/data/it.unipr.mobdev.prova/files", Global.filename);
            outputStream = new FileOutputStream(file, true); //APPEND to file
            //outputStream = new FileOutputStream(file);
            outputStream.write(output.toString().getBytes());
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            Log.e(TAG, "IOEXCEPTION");
        }
    }
}
