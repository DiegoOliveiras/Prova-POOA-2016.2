package com.example.diego.provapooa20162.activity.project.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.diego.provapooa20162.R;

public class ActivityNovoGrupo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_grupo);
    }

    public void onBackPressed() {
        Intent it = new Intent(ActivityNovoGrupo.this, ActivityListaGrupos.class);
        startActivity(it);
    }
}
