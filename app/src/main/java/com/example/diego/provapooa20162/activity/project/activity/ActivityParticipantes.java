package com.example.diego.provapooa20162.activity.project.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.diego.provapooa20162.R;
import com.example.diego.provapooa20162.activity.project.adapter.PessoaAdapter;
import com.example.diego.provapooa20162.activity.project.adapter.TarefaAdapter;
import com.example.diego.provapooa20162.activity.project.model.Grupo;
import com.example.diego.provapooa20162.activity.project.model.Pessoa;
import com.example.diego.provapooa20162.activity.project.model.Tarefa;

import java.util.ArrayList;

public class ActivityParticipantes extends AppCompatActivity {
    private TextView txtNomeGrupo;

    private int idg;
    private String idp;
    private Grupo g;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participantes);

        Intent intent = getIntent();

        idp = (String)intent.getSerializableExtra("idp");
        idg = Integer.parseInt((String)intent.getSerializableExtra("idg"));
        g = Grupo.findById(Grupo.class, idg);

        txtNomeGrupo = (TextView) findViewById(R.id.txtNomeGrupo);
        txtNomeGrupo.setText(g.getNome()+": Participantes");

        ListView lista = (ListView) findViewById(R.id.lista_participantes);
        final ArrayList<Pessoa> pessoas;
        pessoas = (ArrayList<Pessoa>) Pessoa.find(Pessoa.class, "id = ?", g.getGerente().getId().toString());
        ArrayAdapter adapter = new PessoaAdapter(this, pessoas);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });

        lista.setAdapter(adapter);
    }

    public void onBackPressed() {
        Intent it = new Intent(ActivityParticipantes.this, ActivityGrupo.class);
        it.putExtra("idg",g.getId().toString());
        it.putExtra("idp",idp);
        startActivity(it);
        finish();
    }
}
