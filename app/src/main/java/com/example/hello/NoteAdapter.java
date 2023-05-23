package com.example.hello;

import android.widget.ArrayAdapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import java.util.ArrayList;

public class NoteAdapter extends ArrayAdapter {

    private Context context;
    private ArrayList<Note> noteList;

    public NoteAdapter(Context context, ArrayList<Note> noteList) {
        super(context, 0, noteList);
        this.context = context;
        this.noteList = noteList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(context).inflate(R.layout.list_item_note, parent, false);
        }

        Note currentNote = noteList.get(position);

        TextView textViewTitre = listItemView.findViewById(R.id.textViewTitre);
        TextView textViewContenu = listItemView.findViewById(R.id.textViewContenu);

        textViewTitre.setText(currentNote.getTitre());
        textViewContenu.setText(currentNote.getContenu());

        return listItemView;
    }

}
