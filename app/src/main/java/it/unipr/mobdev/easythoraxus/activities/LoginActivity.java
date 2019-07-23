package it.unipr.mobdev.easythoraxus.activities;

import android.content.Intent;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import it.unipr.mobdev.easythoraxus.R;

public class LoginActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private Button login;

    private static final String TAG = LoginActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Autenticazione");

        username = (EditText) findViewById(R.id.insert_username);
        password = (EditText) findViewById(R.id.insert_password);
        login = (Button) findViewById(R.id.btnlogin);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(username.getText().toString(), password.getText().toString());
            }
        });

    }

    public void validate(String username, String password) {
        if ((username.equals("adm1")) && (password.equals("adm1"))) {
            Intent intent = new Intent(LoginActivity.this, InsertPatientDataActivity.class);
            startActivity(intent);
        } else
            Toast.makeText(this, "Errore di accesso.", Toast.LENGTH_SHORT).show();
    }

}
