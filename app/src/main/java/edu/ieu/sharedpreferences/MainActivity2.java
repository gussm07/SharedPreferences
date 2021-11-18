package edu.ieu.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    private EditText etMail;
    private EditText etNombre;
    private EditText etDatosContacto;
    private Button btn_GuardarAgenda;
    private Button btn_Recuperar;
    private Button btnEjecutar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        etMail = findViewById(R.id.etMail);
        btnEjecutar = findViewById(R.id.btn_ejecutar);

        SharedPreferences preferences = getSharedPreferences("datos", Context.MODE_PRIVATE);
        //BIND -> ENLAZADO DE OBJETOS
        etMail.setText(preferences.getString("email",""));
        etDatosContacto = findViewById(R.id.etDatosContacto);
        etNombre = findViewById((R.id.etNombreABuscar));
        btn_GuardarAgenda = findViewById(R.id.btn_guardarAgenda1);
        btn_Recuperar = findViewById(R.id.btn_recuperar1);



        btnEjecutar.setOnClickListener(view -> {
            guardarYSalir();
        });

        btn_GuardarAgenda.setOnClickListener(view -> {
            guardarContacto();
        });

        btn_Recuperar.setOnClickListener(view -> {
            recuperarContacto();
        });


    }

    private void recuperarContacto() {
        String nombre = etNombre.getText().toString();
        SharedPreferences preferences = getSharedPreferences("agenda", Context.MODE_PRIVATE);
        String datos = preferences.getString(nombre, "");
        if(datos.equals("")){
            Toast.makeText(this, "No existe el nombre en la agenda", Toast.LENGTH_LONG).show();
        }else{
            etDatosContacto.setText(datos);
        }

    }

    private void guardarContacto() {
        String nombre = etNombre.getText().toString();
        String datos = etDatosContacto.getText().toString();
        SharedPreferences preferences = getSharedPreferences("agenda", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(nombre, datos);
        editor.commit();
        Toast.makeText(this, "Datos guardados", Toast.LENGTH_LONG).show();
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