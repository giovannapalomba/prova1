package it.unipr.mobdev.prova;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class InsertPatientDataActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insert_patient_data);
    }

    public void next(View view){
        Intent intent = new Intent(InsertPatientDataActivity.this, MainListActivity.class);
        startActivity(intent);
    }
}
