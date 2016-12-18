package com.example.diego.provapooa20162;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ActivityNovaConta extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova_conta);
    }

    public void onBackPressed() {
        Intent it = new Intent(ActivityNovaConta.this, ActivityLogin.class);
        startActivity(it);
    }
}
