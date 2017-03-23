package com.example.diego.provapooa20162.activity.project.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.diego.provapooa20162.R;
import com.example.diego.provapooa20162.activity.project.model.Pessoa;

import java.util.ArrayList;

/**
 * Created by Diego on 23/03/2017.
 */

public class PessoaAdapter extends ArrayAdapter<Pessoa>{
    private final Context context;
    private final ArrayList<Pessoa> list;

    public PessoaAdapter(Context context, ArrayList<Pessoa> list){
        super(context, R.layout.linha_pessoa, list);
        this.context = context;
        this.list = list;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.linha_pessoa, parent, false);
        TextView txtNome = (TextView) rowView.findViewById(R.id.txtNome);
        TextView txtEmail = (TextView) rowView.findViewById(R.id.txtEmail);
        txtNome.setText(list.get(position).getNome());
        txtEmail.setText(list.get(position).getEmail());
        return rowView;
    }
}
