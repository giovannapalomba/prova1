package it.unipr.mobdev.easythoraxus.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import it.unipr.mobdev.easythoraxus.utils.Global;
import it.unipr.mobdev.easythoraxus.R;

public class InsertPatientDataActivity extends AppCompatActivity {

    private EditText age;
    private EditText height;
    private EditText weight;
    private Spinner gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insert_patient_data);
        getSupportActionBar().setTitle("Dati paziente");
        age = (EditText) findViewById(R.id.insert_age);
        gender = (Spinner) findViewById(R.id.insert_gender);
        height = (EditText) findViewById(R.id.insert_height);
        weight = (EditText) findViewById(R.id.insert_weight);

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(InsertPatientDataActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.genders));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gender.setAdapter(myAdapter);

        gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Global.gender = gender.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    public void next(View view) {
        Global.age = age.getText().toString();
        Global.height = height.getText().toString();
        Global.weight = weight.getText().toString();
        //controllo che siano tutti inseriti (gender è già inizializzato a M di default)
        if (Global.height.length() > 0 && Global.age.length() > 0 && Global.weight.length() > 0) {
            if ((Integer.parseInt(Global.age) > 110) || (Integer.parseInt(Global.height) > 210) || (Integer.parseInt(Global.weight) > 300)) {
                Toast.makeText(this, "Ricontrollare i dati.", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(InsertPatientDataActivity.this, MainListActivity.class);
                startActivity(intent);
            }
        } else
            Toast.makeText(this, "Inserire tutti i dati.", Toast.LENGTH_SHORT).show();
    }
}
