package it.unipr.mobdev.easythoraxus.activities;


import android.content.Intent;

import android.graphics.Color;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


import android.app.LoaderManager;
import android.content.Loader;

import it.unipr.mobdev.easythoraxus.models.StepDescriptor;
import it.unipr.mobdev.easythoraxus.utils.Global;
import it.unipr.mobdev.easythoraxus.R;
import it.unipr.mobdev.easythoraxus.network.StepLoader;


public class StepActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<StepDescriptor> {

    private TextView answer1;
    private TextView answer2;
    private TextView question;
    private TextView description;
    private int fine_domande = 0;

    //private static final int LOADER_ID = 1;
    private static final String TAG = StepActivity.class.getSimpleName();
    //private String REQUEST_URL = "http://10.0.2.2:3000/step/" + (Global.n_step + 1);

    //private String REQUEST_URL = "http://192.168.137.1:3000/step/" + Global.n_step;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.step_activity);

        Global.n_step += 1;
        setTitle();
        //Log.e(TAG, REQUEST_URL);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Get a reference to the LoaderManager, in order to interact with loaders.
        LoaderManager loaderManager = getLoaderManager();
        loaderManager.initLoader(Global.LOADER_ID, null, this);

        Global.selected_option = null; //azzero l'opzione scelta

        answer1 = (TextView) findViewById(R.id.answer1);
        answer2 = (TextView) findViewById(R.id.answer2);
        question = (TextView) findViewById(R.id.domanda);
        description = (TextView) findViewById(R.id.description);

        answer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answer1.setBackgroundResource(R.color.colorSelected);
                answer1.setTextColor(Color.WHITE);
                answer2.setBackgroundResource(R.drawable.tv_border);
                answer2.setTextColor(Color.DKGRAY);
                selectedOption(answer1.getText().toString());
            }
        });

        answer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answer1.setBackgroundResource(R.drawable.tv_border);
                answer1.setTextColor(Color.DKGRAY);
                answer2.setBackgroundResource(R.color.colorSelected);
                answer2.setTextColor(Color.WHITE);
                selectedOption(answer2.getText().toString());
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        //l'activity riparte quando chiudo quella successiva, dunque torno indietro di uno step_activity
        Global.n_step -= 1;
        setTitle();
        Global.selected_option = null; //azzero l'opzione scelta
        //se torno indietro devo ricalcolare l'intervallo dello step_activity
        Long tsLong = System.currentTimeMillis();
        Global.time_start = tsLong;
        Global.step_time.remove(Global.step_time.size() - 1); //elimino l'ultimo aggiunto
    }

    @Override
    public Loader<StepDescriptor> onCreateLoader(int i, Bundle bundle) {
        return new StepLoader(this);
    }

    @Override
    public void onLoadFinished(Loader<StepDescriptor> loader, StepDescriptor step) {
        View loadingIndicator = findViewById(R.id.loading_indicator2);
        loadingIndicator.setVisibility(View.GONE);

        if (step != null /*&& !step_activity.isEmpty()*/) {
            update(step);
        }
    }

    @Override
    public void onLoaderReset(Loader<StepDescriptor> loader) {
        //  adapter.clear();
    }

    public void setTitle() {
        getSupportActionBar().setTitle(Global.chosen_procedure.getName() + " - " + Global.n_step);
    }

    private void update(StepDescriptor step) {
        if (step.getLast_question().equals("1")) {
            Log.i(TAG, "Domande finite.");
            fine_domande = 1;
        }

        question.setText(step.getQuestion());
        answer1.setText(step.getAnswers().get(0));
        answer2.setText(step.getAnswers().get(1));
        description.setText(step.getDescription());
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void selectedOption(String selected_option) {
        Global.selected_option = selected_option;
        Log.i(TAG, "Opzione selezionata: " + Global.selected_option);
    }

    public void next(View v) {
        if (Global.selected_option != null) {

            Long tsLong = System.currentTimeMillis();
            Log.e(TAG, tsLong.toString());
            Long interval = tsLong - Global.time_start;
            Log.e(TAG, interval.toString()); //in secondi
            Date duration = new Date(interval);
            DateFormat formatter = new SimpleDateFormat("ss.SS"); //sec:centsec
            String durationFormatted = formatter.format(duration);
            Global.step_time.add(durationFormatted);
            Log.e(TAG, durationFormatted);
            Global.time_start = tsLong; //parte da 0 per il nuovo intervallo

            if (fine_domande == 1) {
                Global.current_time_log.setTime_per_step(Global.step_time);
                Global.current_time_log.setTs_end(tsLong);
                //Log.i(TAG, tsLong.toString());
                Intent intentDiagnosis = new Intent(StepActivity.this, DiagnosisActivity.class);
                startActivity(intentDiagnosis);
            } else {
                Log.i(TAG, "StepActivity n: " + Global.n_step);
                Intent intentNext = new Intent(StepActivity.this, StepActivity.class);
                startActivity(intentNext);
            }
        } else
            Toast.makeText(this, "Selezionare una risposta.", Toast.LENGTH_SHORT).show();
    }

}
