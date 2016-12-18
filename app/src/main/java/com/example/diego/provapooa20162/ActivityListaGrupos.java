package com.example.diego.provapooa20162;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class ActivityListaGrupos extends AppCompatActivity {

    private ImageButton btnNovoGrupo, btnSair;
    private AlertDialog alerta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_grupos);

        btnNovoGrupo = (ImageButton) findViewById(R.id.btnNovoGrupo);
        btnNovoGrupo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(ActivityListaGrupos.this, ActivityNovoGrupo.class);
                startActivity(it);
            }
        });

        btnSair = (ImageButton) findViewById(R.id.btnSair);
        btnSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ActivityListaGrupos.this);
                builder.setMessage("Confirma logout?")
                       .setCancelable(false)
                       .setPositiveButton("Sim", new DialogInterface.OnClickListener(){
                   public void onClick(DialogInterface dialog, int id){
                       Intent it = new Intent(ActivityListaGrupos.this, ActivityLogin.class);
                       startActivity(it);
                   }
                })
                       .setNegativeButton("Não", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id){}
                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }

    public void onBackPressed() {}
}
