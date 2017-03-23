package com.example.diego.provapooa20162.activity.project.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.diego.provapooa20162.R;
import com.example.diego.provapooa20162.activity.project.model.Grupo;

import org.w3c.dom.Text;

public class ActivityGrupo extends AppCompatActivity {

    private ImageButton btnNovaTarefa, btnParticipantes;
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
                Intent it = new Intent(ActivityGrupo.this, ActivityTarefa.class);
                startActivity(it);
                finish();
            }
        });

        btnParticipantes = (ImageButton) findViewById(R.id.btnParticipantes);
        btnParticipantes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(ActivityGrupo.this, ActivityParticipantes.class);
                startActivity(it);
                finish();
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
