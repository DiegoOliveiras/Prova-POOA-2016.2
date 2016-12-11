package com.example.diego.provapooa20162;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class ListaGrupos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_grupos);

    }

    public void onBackPressed() {
        Intent it = new Intent(ListaGrupos.this, MainActivity.class);
        startActivity(it);
    }
}
