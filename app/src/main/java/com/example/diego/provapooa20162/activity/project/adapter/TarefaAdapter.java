package com.example.diego.provapooa20162.activity.project.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.diego.provapooa20162.R;
import com.example.diego.provapooa20162.activity.project.model.Tarefa;

import java.util.ArrayList;

/**
 * Created by Diego on 23/03/2017.
 */

public class TarefaAdapter extends ArrayAdapter<Tarefa> {
    private final Context context;
    private final ArrayList<Tarefa> list;

    public TarefaAdapter(Context context, ArrayList<Tarefa> list){
        super(context, R.layout.linha_tarefa, list);
        this.context = context;
        this.list = list;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.linha_tarefa, parent, false);
        TextView txtNome = (TextView) rowView.findViewById(R.id.txtNome);
        TextView txtPrazo = (TextView) rowView.findViewById(R.id.txtPrazo);
        txtNome.setText(list.get(position).getTitulo());
        txtPrazo.setText("Prazo: "+list.get(position).getPrazo());
        return rowView;
    }
}
