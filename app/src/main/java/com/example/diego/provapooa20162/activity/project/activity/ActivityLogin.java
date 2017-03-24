package com.example.diego.provapooa20162.activity.project.activity;

        import android.content.DialogInterface;
        import android.content.Intent;
        import android.support.v7.app.AlertDialog;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.ImageView;
        import android.widget.Toast;

        import com.example.diego.provapooa20162.R;
        import com.example.diego.provapooa20162.activity.project.model.Pessoa;

        import java.util.List;

public class ActivityLogin extends AppCompatActivity {

    private EditText edtEmail, edtSenha;
    private Button btnConectar, btnNovaConta;
    private ImageView flaticonLogo;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtSenha = (EditText) findViewById(R.id.edtPassword);

        btnConectar = (Button) findViewById(R.id.btnConectar);
        btnConectar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edtEmail.getText().toString();
                String senha = edtSenha.getText().toString();
                if (email.isEmpty() || senha.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Informe o email e a senha!", Toast.LENGTH_SHORT).show();
                }
                else {
                    List<Pessoa> querry = Pessoa.find(Pessoa.class, "email = ?", email);
                    Boolean login=false;
                    for (int i=0; i<querry.size(); i++){
                        if (querry.get(i).getSenha().equals(senha)){
                            Intent it = new Intent(ActivityLogin.this, ActivityListaGrupos.class);
                            it.putExtra("id", querry.get(i).getId().toString());
                            login=true;
                            startActivity(it);
                            finish();
                        }
                    }
                    if (!login){
                        Toast.makeText(getApplicationContext(), "Dados inválidos!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnNovaConta = (Button) findViewById(R.id.btnNovaConta);
        btnNovaConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(ActivityLogin.this, ActivityNovaConta.class);
                startActivity(it);
                finish();
            }
        });

        flaticonLogo = (ImageView) findViewById(R.id.flaticonLogo);
        flaticonLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(ActivityLogin.this, ActivityFlaticonCredits.class);
                startActivity(it);
                finish();
            }
        });

    }

    public void onBackPressed(){
        AlertDialog.Builder builder = new AlertDialog.Builder(ActivityLogin.this);
        builder.setMessage("Deseja sair?")
                .setCancelable(false)
                .setPositiveButton("Sim", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id){
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
