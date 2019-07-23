package it.unipr.mobdev.easythoraxus.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import it.unipr.mobdev.easythoraxus.R;
import it.unipr.mobdev.easythoraxus.utils.Global;

public class ChronoDetailsActivity extends AppCompatActivity {

    private TextView ts_inizio;
    private TextView ts_fine;
    private TextView tps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chrono_details_activity);
        getSupportActionBar().setTitle(Global.selected_chrono.getName()+" - "+Global.selected_chrono.getDuration());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ts_inizio = (TextView) findViewById(R.id.tsinizio);
        ts_fine = (TextView) findViewById(R.id.tsfine);
        tps = (TextView) findViewById(R.id.tps);

        ts_inizio.setText(Global.selected_chrono.getTs_start().toString());
        ts_fine.setText(Global.selected_chrono.getTs_end().toString());
        StringBuilder show_tps = new StringBuilder();
        for (int i = 0; i < Global.selected_chrono.getTime_per_step().size(); i++){
            show_tps.append("Step ");
            show_tps.append(i+1);
            show_tps.append(": ");
            show_tps.append(Global.selected_chrono.getTime_per_step().get(i));
            show_tps.append('s');
            show_tps.append('\n');
        }
        tps.setText(show_tps.toString());

    }

    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
