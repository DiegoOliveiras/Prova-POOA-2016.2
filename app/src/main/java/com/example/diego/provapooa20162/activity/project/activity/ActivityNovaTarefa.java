package com.example.diego.provapooa20162.activity.project.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.diego.provapooa20162.R;
import com.example.diego.provapooa20162.activity.project.model.Grupo;
import com.example.diego.provapooa20162.activity.project.model.Tarefa;

public class ActivityNovaTarefa extends AppCompatActivity {

    private EditText edtNome, edtDesc, edtPrazo;
    private ImageButton btnOk;

    private int idg;
    private String idp;
    private Grupo g;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova_tarefa);

        Intent intent = getIntent();

        idg = Integer.parseInt((String)intent.getSerializableExtra("idg"));
        idp = (String)intent.getSerializableExtra("idp");
        g = Grupo.findById(Grupo.class, idg);

        edtNome = (EditText) findViewById(R.id.edtNome);
        edtDesc = (EditText) findViewById(R.id.edtDesc);
        edtPrazo = (EditText) findViewById(R.id.edtPrazo);

        btnOk = (ImageButton) findViewById(R.id.btnOk);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ActivityNovaTarefa.this);
                builder.setMessage("Deseja criar nova tarefa?")
                        .setCancelable(false)
                        .setPositiveButton("Sim", new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog, int id){
                                if (!edtNome.getText().toString().isEmpty() && !edtDesc.getText().toString().isEmpty() && !edtPrazo.getText().toString().isEmpty()){
                                    Tarefa t = new Tarefa(edtNome.getText().toString(), edtDesc.getText().toString(), edtPrazo.getText().toString(), g);
                                    t.save();
                                    Toast.makeText(getApplicationContext(), "Tarefa criada com sucesso!", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(ActivityNovaTarefa.this, ActivityGrupo.class);
                                    intent.putExtra("idg", g.getId().toString());
                                    intent.putExtra("idp", idp);
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
        AlertDialog.Builder builder = new AlertDialog.Builder(ActivityNovaTarefa.this);
        builder.setMessage("Cancelar a criação de nova tarefa?")
                .setCancelable(false)
                .setPositiveButton("Sim", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id){
                        Intent it = new Intent(ActivityNovaTarefa.this, ActivityGrupo.class);
                        it.putExtra("idg", g.getId().toString());
                        it.putExtra("idp", idp);
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
