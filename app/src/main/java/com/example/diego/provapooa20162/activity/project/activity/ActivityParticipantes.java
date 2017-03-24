package com.example.diego.provapooa20162.activity.project.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.diego.provapooa20162.R;
import com.example.diego.provapooa20162.activity.project.adapter.PessoaAdapter;
import com.example.diego.provapooa20162.activity.project.adapter.TarefaAdapter;
import com.example.diego.provapooa20162.activity.project.model.Grupo;
import com.example.diego.provapooa20162.activity.project.model.Pessoa;
import com.example.diego.provapooa20162.activity.project.model.Tarefa;

import java.util.ArrayList;

public class ActivityParticipantes extends AppCompatActivity {
    private TextView txtNomeGrupo;
    private ImageButton btnAddPessoa;

    private Grupo g;
    private ArrayList<Pessoa> p;

    private int idg;
    private String idp, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participantes);

        Intent intent = getIntent();

        idp = (String)intent.getSerializableExtra("idp");
        idg = Integer.parseInt((String)intent.getSerializableExtra("idg"));
        g = Grupo.findById(Grupo.class, idg);

        txtNomeGrupo = (TextView) findViewById(R.id.txtNomeGrupo);
        txtNomeGrupo.setText(g.getNome()+": Participantes");

        ListView lista = (ListView) findViewById(R.id.lista_participantes);
        final ArrayList<Pessoa> pessoas;
        pessoas = (ArrayList<Pessoa>) Pessoa.find(Pessoa.class, "id = ?", g.getGerente().getId().toString());
        try {
            pessoas.addAll(Pessoa.find(Pessoa.class, "id = ?", g.getP1().getId().toString()));
        } catch (Exception e){}
        try {
            pessoas.addAll(Pessoa.find(Pessoa.class, "id = ?", g.getP2().getId().toString()));
        } catch (Exception e){}
        try {
            pessoas.addAll(Pessoa.find(Pessoa.class, "id = ?", g.getP3().getId().toString()));
        } catch (Exception e){}

        ArrayAdapter adapter = new PessoaAdapter(this, pessoas);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });

        lista.setAdapter(adapter);

        btnAddPessoa = (ImageButton) findViewById(R.id.btnAddPessoa);
        btnAddPessoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ActivityParticipantes.this);
                builder.setTitle("Informe o e-mail do usuário a ser convidado:");

                // Set up the input
                final EditText input = new EditText(ActivityParticipantes.this);
                // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
                input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
                builder.setView(input);

                // Set up the buttons
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        email = input.getText().toString();
                        p = (ArrayList<Pessoa>) Pessoa.find(Pessoa.class, "email = ?", email.toString());
                        if (!p.isEmpty()){
                            if (g.addParticipante(p.get(0))){
                                g.save();
                                Toast.makeText(getApplicationContext(), p.get(0).getNome()+" convidado com sucesso!", Toast.LENGTH_SHORT).show();
                                Intent it = new Intent(ActivityParticipantes.this, ActivityParticipantes.class);
                                it.putExtra("idg",g.getId().toString());
                                it.putExtra("idp",idp);
                                startActivity(it);
                                finish();
                            }
                            else{
                                Toast.makeText(getApplicationContext(), "Grupo cheio ou o Usuário existente no grupo!", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "E-mail inválido!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();
            }
        });
    }

    public void onBackPressed() {
        Intent it = new Intent(ActivityParticipantes.this, ActivityGrupo.class);
        it.putExtra("idg",g.getId().toString());
        it.putExtra("idp",idp);
        startActivity(it);
        finish();
    }
}
