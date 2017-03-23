package com.example.diego.provapooa20162.activity.project.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.diego.provapooa20162.R;

public class ActivityFlaticonCredits extends AppCompatActivity implements View.OnClickListener{

    private TextView txt1, txt2, txt3;
    private ImageView img1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flaticon_credits);

        txt1 = (TextView) findViewById(R.id.txt1);
        txt2 = (TextView) findViewById(R.id.txt2);
        txt3 = (TextView) findViewById(R.id.txt3);
        img1 = (ImageView) findViewById(R.id.img1);

        txt1.setOnClickListener(this);
        txt2.setOnClickListener(this);
        txt3.setOnClickListener(this);
        img1.setOnClickListener(this);
    }

    public void onBackPressed() {
        Intent it = new Intent(ActivityFlaticonCredits.this, ActivityLogin.class);
        startActivity(it);
    }

    @Override
    public void onClick(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(ActivityFlaticonCredits.this);
        builder.setMessage("Você será redirecionado para a página FlatIcon.com.")
                .setCancelable(false)
                .setPositiveButton("Confirmar", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id){
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.flaticon.com/authors/madebyoliver"));
                        startActivity(browserIntent);
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id){}
                });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
