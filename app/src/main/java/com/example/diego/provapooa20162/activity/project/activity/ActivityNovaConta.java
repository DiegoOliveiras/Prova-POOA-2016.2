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
import com.example.diego.provapooa20162.activity.project.model.Pessoa;

public class ActivityNovaConta extends AppCompatActivity {
    private EditText edtNome, edtSenha, edtSenha2, edtEmail;
    private ImageButton btnOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova_conta);

        edtNome = (EditText) findViewById(R.id.edtNome);
        edtSenha = (EditText) findViewById(R.id.edtSenha);
        edtSenha2 = (EditText) findViewById(R.id.edtSenha2);
        edtEmail = (EditText) findViewById(R.id.edtEmail);

        btnOk = (ImageButton) findViewById(R.id.btnOk);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ActivityNovaConta.this);
                builder.setMessage("Deseja Cadastrar sua Conta?")
                        .setCancelable(false)
                        .setPositiveButton("Sim", new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog, int id){
                                if (!edtNome.getText().toString().isEmpty() && !edtEmail.getText().toString().isEmpty() && !edtSenha.getText().toString().isEmpty()){
                                    if (edtSenha.getText().toString().equals(edtSenha2.getText().toString())){
                                        Pessoa p = new Pessoa(edtNome.getText().toString(),
                                                edtEmail.getText().toString(),
                                                edtSenha.getText().toString()
                                        );
                                        p.save();
                                        Toast.makeText(getApplicationContext(), "Conta criada com sucesso!", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(ActivityNovaConta.this, ActivityLogin.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                    else {
                                        Toast.makeText(getApplicationContext(), "As senhas digitadas não conferem!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                                else {
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
        Intent it = new Intent(ActivityNovaConta.this, ActivityLogin.class);
        startActivity(it);
        finish();
    }
}
