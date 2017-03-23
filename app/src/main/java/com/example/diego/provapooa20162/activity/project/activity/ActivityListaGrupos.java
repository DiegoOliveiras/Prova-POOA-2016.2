package com.example.diego.provapooa20162.activity.project.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.diego.provapooa20162.R;
import com.example.diego.provapooa20162.activity.project.adapter.GrupoAdapter;
import com.example.diego.provapooa20162.activity.project.model.Grupo;
import com.example.diego.provapooa20162.activity.project.model.Pessoa;

import java.util.ArrayList;

public class ActivityListaGrupos extends AppCompatActivity {

    private ImageButton btnNovoGrupo, btnSair;
    private TextView txtUsuario;
    private int idp;
    private Pessoa p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_grupos);

        Intent intent = getIntent();

        idp = Integer.parseInt((String)intent.getSerializableExtra("id"));
        p = Pessoa.findById(Pessoa.class, idp);

        txtUsuario = (TextView) findViewById(R.id.txtUsuario);
        txtUsuario.setText("Logado como: "+p.getNome());

        btnNovoGrupo = (ImageButton) findViewById(R.id.btnNovoGrupo);
        btnNovoGrupo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(ActivityListaGrupos.this, ActivityNovoGrupo.class);
                it.putExtra("id", p.getId().toString());
                startActivity(it);
                finish();
            }
        });

        btnSair = (ImageButton) findViewById(R.id.btnSair);
        btnSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sair();
            }
        });

        ListView lista = (ListView) findViewById(R.id.lista_grupos);
        final ArrayList<Grupo> grupos;
        grupos = (ArrayList<Grupo>) Grupo.find(Grupo.class, "gerente = ?", p.getId().toString());
        //grupos = p.getGrupos();
        ArrayAdapter adapter = new GrupoAdapter(this, grupos);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(ActivityListaGrupos.this, ActivityGrupo.class);
                intent.putExtra("idg",grupos.get(i).getId().toString());
                intent.putExtra("idp",p.getId().toString());

                startActivity(intent);
                finish();
            }
        });

        lista.setAdapter(adapter);
    }

    public void onBackPressed() {
        sair();
    }

    public void sair(){
        AlertDialog.Builder builder = new AlertDialog.Builder(ActivityListaGrupos.this);
        builder.setMessage("Deseja desconectar-se?")
                .setCancelable(false)
                .setPositiveButton("Sim", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id){
                        Intent it = new Intent(ActivityListaGrupos.this, ActivityLogin.class);
                        startActivity(it);
                        finish();
                    }
                })
                .setNegativeButton("Não", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id){}
                });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
