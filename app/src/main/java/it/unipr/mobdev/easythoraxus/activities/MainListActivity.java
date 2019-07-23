package it.unipr.mobdev.easythoraxus.activities;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.lang.reflect.Array;
import java.util.ArrayList;

import android.app.LoaderManager;
import android.content.Loader;

import it.unipr.mobdev.easythoraxus.models.ChronoDescriptor;
import it.unipr.mobdev.easythoraxus.models.ProcedureDescriptor;
import it.unipr.mobdev.easythoraxus.utils.Global;
import it.unipr.mobdev.easythoraxus.adapters.MyListAdapter;
import it.unipr.mobdev.easythoraxus.network.ProcedureLoader;
import it.unipr.mobdev.easythoraxus.R;

public class MainListActivity extends AppCompatActivity implements MyListAdapter.ItemClickListener, LoaderManager.LoaderCallbacks<ArrayList<ProcedureDescriptor>> {

    MyListAdapter adapter;
    public static final String TAG = MainListActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_activity);
        getSupportActionBar().setTitle("Procedure disponibili");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //inizializzo loader
        LoaderManager loaderManager = getLoaderManager();
        loaderManager.initLoader(Global.LOADER_ID, null, this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_history:
                Intent intent = new Intent(MainListActivity.this, HistoryActivity.class);
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public Loader<ArrayList<ProcedureDescriptor>> onCreateLoader(int i, Bundle bundle) {
        return new ProcedureLoader(this);
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<ProcedureDescriptor>> loader, ArrayList<ProcedureDescriptor> procedureDescriptor) {
        // nascondo icona caricamento, perchè è terminato
        View loadingIndicator = findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.GONE);

        if (procedureDescriptor != null && !procedureDescriptor.isEmpty()) {
            update(procedureDescriptor);
        }
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<ProcedureDescriptor>> loader) {
        //adapter.clear();
    }

    private void update(ArrayList<ProcedureDescriptor> procedureDescriptor) {
        RecyclerView recyclerView = findViewById(R.id.procedure);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyListAdapter(this, procedureDescriptor);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(View view, int position) {
        //ripulisco i vecchi valori, così da non salvare male
        /*Global.step_time.clear();
        Global.time_start = null;
        Global.to_save.clear();*/
        Global.step_time.clear();
        Global.time_start = null;
        Global.n_step = 0;


        Global.chosen_procedure = adapter.getItem(position);
        //Global.to_save.add(Global.chosen_procedure.getName());

        Long tsLong = System.currentTimeMillis();
        //devo salvarlo perchè mi serve alla fine per trovare tempo totale della procedura
        Global.time_start = tsLong;

        Global.current_time_log = new ChronoDescriptor();
        Global.current_time_log.setName(Global.chosen_procedure.getName());
        Global.current_time_log.setTs_start(tsLong);


        Intent intent = new Intent(MainListActivity.this, StepActivity.class);
        startActivity(intent);
    }

}