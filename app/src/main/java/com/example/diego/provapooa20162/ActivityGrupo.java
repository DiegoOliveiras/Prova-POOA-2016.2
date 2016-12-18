package com.example.diego.provapooa20162;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class ActivityGrupo extends AppCompatActivity {

    private ImageButton btnNovoGrupo, btnParticipantes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grupo);

        btnNovoGrupo = (ImageButton) findViewById(R.id.btnNovoGrupo);
        btnNovoGrupo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(ActivityGrupo.this, ActivityNovoGrupo.class);
                startActivity(it);
            }
        });

        btnParticipantes = (ImageButton) findViewById(R.id.btnParticipantes);
        btnParticipantes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(ActivityGrupo.this, ActivityParticipantes.class);
                startActivity(it);
            }
        });
    }

    public void onBackPressed() {
        Intent it = new Intent(ActivityGrupo.this, ActivityListaGrupos.class);
        startActivity(it);
    }
}
