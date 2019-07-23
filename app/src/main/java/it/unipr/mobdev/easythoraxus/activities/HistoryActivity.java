package it.unipr.mobdev.easythoraxus.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import it.unipr.mobdev.easythoraxus.adapters.ChronoAdapter;
import it.unipr.mobdev.easythoraxus.models.ChronoDescriptor;
import it.unipr.mobdev.easythoraxus.utils.ChronoManager;
import it.unipr.mobdev.easythoraxus.utils.Global;
import it.unipr.mobdev.easythoraxus.R;

public class HistoryActivity extends AppCompatActivity implements ChronoAdapter.ItemClickListener {

    private static final String TAG = HistoryActivity.class.getSimpleName();
    public static String filename = "time_history";
    ChronoAdapter adapter;
    ArrayList<String> to_show = new ArrayList<>();
    ArrayList<String> lines = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_activity);
        getSupportActionBar().setTitle("Cronologia");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        /*File file = new File("/data/data/it.unipr.mobdev.prova/files", filename);

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
                StringBuilder element = new StringBuilder();
                Log.e(TAG, line);
                String[] tokens = line.split(",");
                element.append(tokens[0]);
                element.append(" - ");
                Long total_time_ms = Long.parseLong(tokens[2]) - Long.parseLong(tokens[1]);
                Date duration = new Date(total_time_ms);
                DateFormat formatter = new SimpleDateFormat("ss.SS"); //sec.centsec
                String durationFormatted = formatter.format(duration);
                element.append(durationFormatted);
                element.append("s");
                to_show.add(element.toString());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        //Collections.reverse(to_show); //li inverto così sono in ordine dal più recente al meno recente
        //Collections.reverse(lines);//coerenza!
        //al posto di adapter ci va la lista di procedure - tempo tot
        ArrayList<ChronoDescriptor> chronoList = ChronoManager.getInstance(this).getLogList();
        Log.e(TAG, chronoList.toString());
        RecyclerView recyclerView = findViewById(R.id.chrono);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ChronoAdapter(this, chronoList);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onItemClick(View view, int position) {
       /* Global.selected_line = lines.get(position);
        Log.e(TAG, Global.selected_line);*/
        Global.selected_chrono = adapter.getItem(position);
        Intent intentNext = new Intent(HistoryActivity.this, ChronoDetailsActivity.class);
        startActivity(intentNext);
    }

}
