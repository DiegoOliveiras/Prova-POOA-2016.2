package com.example.diego.provapooa20162.activity.project.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.diego.provapooa20162.R;
import com.example.diego.provapooa20162.activity.project.model.Grupo;
import com.example.diego.provapooa20162.activity.project.model.Pessoa;
import com.example.diego.provapooa20162.activity.project.model.Tarefa;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ActivityTarefa extends AppCompatActivity {
    private int idg, idt, idp;

    private Tarefa t;
    private Grupo g;
    private Pessoa p;

    private ImageButton btnConcluir, btnApagar;
    private TextView txtTitulo, txtDesc, txtPrazo, txtDataInicio, txtDataTermino, txtUsuario, txtPontos;
    private EditText edtObservacoes;

    private Boolean concluir=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarefa);

        Intent intent = getIntent();

        idg = Integer.parseInt((String)intent.getSerializableExtra("idg"));
        idt = Integer.parseInt((String)intent.getSerializableExtra("idt"));
        idp = Integer.parseInt((String)intent.getSerializableExtra("idp"));

        t = Tarefa.findById(Tarefa.class, idt);
        g = Grupo.findById(Grupo.class, idg);
        p = Pessoa.findById(Pessoa.class, idp);

        txtTitulo = (TextView) findViewById(R.id.txtTitulo);
        txtTitulo.setText(t.getTitulo());
        txtDesc = (TextView) findViewById(R.id.txtDesc);
        txtDesc.setText(t.getDescricao());
        txtPrazo = (TextView) findViewById(R.id.txtPrazo);
        txtPrazo.setText("Prazo: "+t.getPrazo());
        txtDataInicio = (TextView) findViewById(R.id.txtDataInicio);
        txtDataInicio.setText("Data Início: "+t.getDataInicio());
        txtPontos = (TextView) findViewById(R.id.txtPontos);
        txtPontos.setText("Pontos: "+t.getPontos());

        txtDataInicio = (TextView) findViewById(R.id.txtDataInicio);
        txtDataTermino = (TextView) findViewById(R.id.txtDataTermino);
        txtUsuario = (TextView) findViewById(R.id.txtUsuario);

        edtObservacoes = (EditText) findViewById(R.id.edtObservacoes);

        btnApagar = (ImageButton) findViewById(R.id.btnApagar);
        btnApagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ActivityTarefa.this);
                builder.setMessage("Deseja apagar esta tarefa?")
                        .setCancelable(false)
                        .setPositiveButton("Sim", new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog, int id){
                                if (t.getGrupo().getGerente().getId() == p.getId()){
                                    t.delete();
                                    Intent it = new Intent(ActivityTarefa.this, ActivityGrupo.class);
                                    it.putExtra("idg", g.getId().toString());
                                    it.putExtra("idp", p.getId().toString());
                                    startActivity(it);
                                    finish();

                                    Toast.makeText(getApplicationContext(), "Tarefa apagada com sucesso!", Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    Toast.makeText(getApplicationContext(), "Autorização insuficiente!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .setNegativeButton("Não", new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog, int id){}
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });

        btnConcluir = (ImageButton) findViewById(R.id.btnConcluir);
        btnConcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!concluir && t.getStatus() != 1){
                    AlertDialog.Builder builder = new AlertDialog.Builder(ActivityTarefa.this);
                    builder.setMessage("Deseja concluir esta tarefa?")
                            .setCancelable(false)
                            .setPositiveButton("Sim", new DialogInterface.OnClickListener(){
                                public void onClick(DialogInterface dialog, int id){
                                    edtObservacoes.setVisibility(View.VISIBLE);
                                    concluir = true;
                                }
                            })
                            .setNegativeButton("Não", new DialogInterface.OnClickListener(){
                                public void onClick(DialogInterface dialog, int id){}
                            });
                    AlertDialog alert = builder.create();
                    alert.show();
                }
                else {
                    if (t.getStatus() != 1){
                        AlertDialog.Builder builder = new AlertDialog.Builder(ActivityTarefa.this);
                        builder.setMessage("Deseja salvar as alterações?")
                                .setCancelable(false)
                                .setPositiveButton("Sim", new DialogInterface.OnClickListener(){
                                    public void onClick(DialogInterface dialog, int id){
                                        t.concluirTarefa(p, edtObservacoes.getText().toString());
                                        t.save();

                                        p.addPontos(t.getPontos());
                                        p.save();
                                        concluir = false;

                                        Intent intent = new Intent(ActivityTarefa.this, ActivityTarefa.class);
                                        intent.putExtra("idt",t.getId().toString());
                                        intent.putExtra("idg",g.getId().toString());
                                        intent.putExtra("idp",p.getId().toString());

                                        startActivity(intent);
                                        finish();
                                        Toast.makeText(getApplicationContext(), "Tarefa conclúida com sucesso!", Toast.LENGTH_SHORT).show();
                                    }
                                })
                                .setNegativeButton("Não", new DialogInterface.OnClickListener(){
                                    public void onClick(DialogInterface dialog, int id){}
                                });
                        AlertDialog alert = builder.create();
                        alert.show();
                    }
                }
            }
        });

        if (t.getStatus() == 0){
            txtDataTermino.setVisibility(View.INVISIBLE);
            txtUsuario.setVisibility(View.INVISIBLE);
            edtObservacoes.setVisibility(View.INVISIBLE);
        }
        else {
            txtDataTermino.setText("Conluído em: "+t.getDataTermino());
            txtUsuario.setText("Concluído por: "+t.getPessoa().getNome());
            edtObservacoes.setText(t.getObservacao());
            edtObservacoes.setEnabled(false);
            btnConcluir.setVisibility(View.INVISIBLE);
        }
    }

    public void onBackPressed() {
        if (concluir){
            AlertDialog.Builder builder = new AlertDialog.Builder(ActivityTarefa.this);
            builder.setMessage("Deseja cancelar a alteração e voltar?")
                    .setCancelable(false)
                    .setPositiveButton("Sim", new DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface dialog, int id){
                            Intent it = new Intent(ActivityTarefa.this, ActivityGrupo.class);
                            it.putExtra("idg", g.getId().toString());
                            it.putExtra("idp", p.getId().toString());
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
        else {
            Intent it = new Intent(ActivityTarefa.this, ActivityGrupo.class);
            it.putExtra("idg", g.getId().toString());
            it.putExtra("idp", p.getId().toString());
            startActivity(it);
            finish();
        }
    }
}
