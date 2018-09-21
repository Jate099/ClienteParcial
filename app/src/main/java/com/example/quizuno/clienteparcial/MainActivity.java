package com.example.quizuno.clienteparcial;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.net.Socket;

public class MainActivity extends AppCompatActivity implements Receptor.OnMessage {

    EditText et_usuario;
    EditText et_contraseña;
    Button btn_registrarse;
    Button btn_ingresar;

    String user;
    String contra;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Cliente client = new Cliente(this);
        client.start();

        et_usuario = findViewById(R.id.et_usuario);
        et_contraseña = findViewById(R.id.et_contra);
        btn_registrarse = findViewById(R.id.btn_registrarse);
        btn_ingresar = findViewById(R.id.btn_ingresar);



        btn_registrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                user = et_usuario.getText().toString();
                contra = et_contraseña.getText().toString();
                client.enviar();
            }
        });


    }

    @Override
    public void OnRecieve(final String mensaje) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this, mensaje,Toast.LENGTH_LONG).show();
            }
        });

    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getContra() {
        return contra;
    }

    public void setContra(String contra) {
        this.contra = contra;
    }
}
