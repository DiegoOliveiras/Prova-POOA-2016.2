package com.example.diego.provapooa20162.activity.project.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.diego.provapooa20162.R;
import com.example.diego.provapooa20162.activity.project.model.Grupo;
import com.example.diego.provapooa20162.activity.project.model.Pessoa;

import java.util.List;

public class ActivityNovoGrupo extends AppCompatActivity {
    private EditText edtNomeGrupo, edtDescricao;
    private ImageButton btnOk;

    private int idp;
    private Pessoa p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_grupo);

        Intent intent = getIntent();

        idp = Integer.parseInt((String)intent.getSerializableExtra("id"));
        p = Pessoa.findById(Pessoa.class, idp);

        edtNomeGrupo = (EditText) findViewById(R.id.edtNomeGrupo);
        edtDescricao = (EditText) findViewById(R.id.edtDescricao);
        btnOk = (ImageButton) findViewById(R.id.btnOk);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ActivityNovoGrupo.this);
                builder.setMessage("Deseja Criar o grupo?")
                        .setCancelable(false)
                        .setPositiveButton("Sim", new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog, int id){
                                if (!edtNomeGrupo.getText().toString().isEmpty() && !edtDescricao.getText().toString().isEmpty()){
                                    Grupo g = new Grupo(edtNomeGrupo.getText().toString(), edtDescricao.getText().toString(), p);
                                    g.save();
                                    p.addGrupo(g);
                                    p.save();
                                    Toast.makeText(getApplicationContext(), "Grupo criado com sucesso!", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(ActivityNovoGrupo.this, ActivityListaGrupos.class);
                                    intent.putExtra("id", p.getId().toString());
                                    startActivity(intent);
                                    finish();
                                }
                                else{
                                    Toast.makeText(getApplicationContext(), "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
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
    }

    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(ActivityNovoGrupo.this);
        builder.setMessage("Cancelar a criação de novo grupo?")
                .setCancelable(false)
                .setPositiveButton("Sim", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id){
                        Intent it = new Intent(ActivityNovoGrupo.this, ActivityListaGrupos.class);
                        it.putExtra("id",p.getId().toString());
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
