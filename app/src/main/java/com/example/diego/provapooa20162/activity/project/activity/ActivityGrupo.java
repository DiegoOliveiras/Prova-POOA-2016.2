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
import android.widget.Toast;

import com.example.diego.provapooa20162.R;
import com.example.diego.provapooa20162.activity.project.adapter.GrupoAdapter;
import com.example.diego.provapooa20162.activity.project.adapter.TarefaAdapter;
import com.example.diego.provapooa20162.activity.project.model.Grupo;
import com.example.diego.provapooa20162.activity.project.model.Pessoa;
import com.example.diego.provapooa20162.activity.project.model.Tarefa;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ActivityGrupo extends AppCompatActivity {

    private ImageButton btnNovaTarefa, btnParticipantes, btnApagar;
    private TextView txtNomeGrupo, txtDescricao;

    private int idg;
    private String idp;
    private Grupo g;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grupo);

        Intent intent = getIntent();

        idg = Integer.parseInt((String)intent.getSerializableExtra("idg"));
        idp = (String)intent.getSerializableExtra("idp");
        g = Grupo.findById(Grupo.class, idg);

        txtNomeGrupo = (TextView) findViewById(R.id.txtNomeGrupo);
        txtNomeGrupo.setText("Grupo: "+g.getNome());

        txtDescricao = (TextView) findViewById(R.id.txtDescricao);
        txtDescricao.setText(""+g.getDescricao());

        btnNovaTarefa = (ImageButton) findViewById(R.id.btnNovaTarefa);
        btnNovaTarefa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(ActivityGrupo.this, ActivityNovaTarefa.class);
                it.putExtra("idg", g.getId().toString());
                it.putExtra("idp", idp);
                startActivity(it);
                finish();
            }
        });

        btnParticipantes = (ImageButton) findViewById(R.id.btnParticipantes);
        btnParticipantes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(ActivityGrupo.this, ActivityParticipantes.class);
                it.putExtra("idg",g.getId().toString());
                it.putExtra("idp",idp);
                startActivity(it);
                finish();
            }
        });

        ListView lista = (ListView) findViewById(R.id.lista_tarefas);
        final ArrayList<Tarefa> tarefas;
        tarefas = (ArrayList<Tarefa>) Tarefa.find(Tarefa.class, "grupo = ?", g.getId().toString());
        ArrayAdapter adapter = new TarefaAdapter(this, tarefas);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(ActivityGrupo.this, ActivityTarefa.class);
                intent.putExtra("idt",tarefas.get(i).getId().toString());
                intent.putExtra("idg",g.getId().toString());
                intent.putExtra("idp",idp);

                startActivity(intent);
                finish();
            }
        });

        lista.setAdapter(adapter);

        btnApagar = (ImageButton) findViewById(R.id.btnApagar);
        btnApagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Pessoa p = Pessoa.findById(Pessoa.class, Integer.parseInt(idp));
                if (g.getGerente().getId() == p.getId()){
                    AlertDialog.Builder builder = new AlertDialog.Builder(ActivityGrupo.this);
                    builder.setMessage("Deseja apagar este grupo?")
                            .setCancelable(false)
                            .setPositiveButton("Sim", new DialogInterface.OnClickListener(){
                                public void onClick(DialogInterface dialog, int id){
                                    for (int i=0; i<tarefas.size(); i++){
                                        tarefas.get(i).delete();
                                    }
                                    g.delete();
                                    Pessoa p = Pessoa.findById(Pessoa.class, Integer.parseInt(idp));
                                    Intent it = new Intent(ActivityGrupo.this, ActivityListaGrupos.class);
                                    it.putExtra("id", p.getId().toString());
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
                else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(ActivityGrupo.this);
                    builder.setMessage("Deseja sair deste grupo?")
                            .setCancelable(false)
                            .setPositiveButton("Sim", new DialogInterface.OnClickListener(){
                                public void onClick(DialogInterface dialog, int id){
                                    Pessoa p = Pessoa.findById(Pessoa.class, Integer.parseInt(idp));
                                    if (g.removeParticipante(p)){
                                        g.save();
                                        Intent it = new Intent(ActivityGrupo.this, ActivityListaGrupos.class);
                                        it.putExtra("id", p.getId().toString());
                                        startActivity(it);
                                        finish();

                                        Toast.makeText(getApplicationContext(), "Você saiu do grupo!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            })
                            .setNegativeButton("Não", new DialogInterface.OnClickListener(){
                                public void onClick(DialogInterface dialog, int id){}
                            });
                    AlertDialog alert = builder.create();
                    alert.show();
                }
            }
        });
    }

    public void onBackPressed() {
        Intent it = new Intent(ActivityGrupo.this, ActivityListaGrupos.class);
        it.putExtra("id", idp);
        startActivity(it);
        finish();
    }
}
