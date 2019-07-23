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
        getSupportActionBar().setTitle(Global.selected_line_title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ts_inizio = (TextView) findViewById(R.id.tsinizio);
        ts_fine = (TextView) findViewById(R.id.tsfine);
        tps = (TextView) findViewById(R.id.tps);
        String[] tokens = Global.selected_line.split(",");
        ts_inizio.setText(tokens[1]);
        ts_fine.setText(tokens[2]);

        StringBuilder show_tps = new StringBuilder();
        int tot_step = Integer.parseInt(tokens[3]);
        for (int i = 1; i <= tot_step; i++){
            show_tps.append("StepActivity ");
            show_tps.append(i);
            show_tps.append(": ");
            show_tps.append(tokens[3+i]);
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
