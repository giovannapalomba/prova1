package it.unipr.mobdev.prova;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private Button login;

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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



    public void validate(String username, String password){
        if ((username.equals("adm1")) && (password.equals("adm1"))){
            Intent intent = new Intent(MainActivity.this, InsertPatientDataActivity.class);
            startActivity(intent);

        }
        else
            Toast.makeText(this, "Errore di accesso", Toast.LENGTH_SHORT).show();

    }
}
