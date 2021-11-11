package edu.ieu.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText etMail;
    private Button btnEjecutar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etMail = findViewById(R.id.etMail);
        btnEjecutar = findViewById(R.id.boton_ejecutar);

        SharedPreferences preferences = getSharedPreferences("datos", Context.MODE_PRIVATE);
        etMail.setText(preferences.getString("email",""));

        btnEjecutar.setOnClickListener(view -> {
            guardarYSalir();
        });
    }

    private void guardarYSalir()
    {
        SharedPreferences preferences = getSharedPreferences("datos", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        String email = etMail.getText().toString();
        //LLAVE KEY -> VALOR
        editor.putString("email", email);
        editor.commit();
        finish();
    }
}