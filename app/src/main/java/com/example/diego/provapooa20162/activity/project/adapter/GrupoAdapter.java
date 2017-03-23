package com.example.diego.provapooa20162.activity.project.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.diego.provapooa20162.R;
import com.example.diego.provapooa20162.activity.project.model.Grupo;

import java.util.ArrayList;

/**
 * Created by Diego on 23/03/2017.
 */

public class GrupoAdapter extends ArrayAdapter<Grupo> {
    private final Context context;
    private final ArrayList<Grupo> list;

    public GrupoAdapter(Context context, ArrayList<Grupo> list){
        super(context, R.layout.linha_grupo, list);
        this.context = context;
        this.list = list;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.linha_grupo, parent, false);
        TextView nomegrupo = (TextView) rowView.findViewById(R.id.nomegrupo);
        TextView tarefas = (TextView) rowView.findViewById(R.id.tarefas);
        nomegrupo.setText(list.get(position).getNome());
        tarefas.setText("Tarefas em Aberto: "+list.get(position).getTarefas().size());
        return rowView;
    }
}
