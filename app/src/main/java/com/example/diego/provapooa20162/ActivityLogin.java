package com.example.diego.provapooa20162;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class ActivityLogin extends AppCompatActivity {

    private EditText edtEmail, edtSenha;
    private Button btnConectar, btnNovaConta;
    private ImageView flaticonLogo;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        btnConectar = (Button) findViewById(R.id.btnConectar);
        btnConectar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(ActivityLogin.this, ActivityListaGrupos.class);
                startActivity(it);
            }
        });

        btnNovaConta = (Button) findViewById(R.id.btnNovaConta);
        btnNovaConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(ActivityLogin.this, ActivityNovaConta.class);
                startActivity(it);
            }
        });

        flaticonLogo = (ImageView) findViewById(R.id.flaticonLogo);
        flaticonLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(ActivityLogin.this, ActivityFlaticonCredits.class);
                startActivity(it);
            }
        });

    }
}
