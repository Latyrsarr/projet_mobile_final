package com.example.hello;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.icu.text.Transliterator;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import com.example.hello.NoteAdapter;
import java.util.ArrayList;



public class ListeArticle extends AppCompatActivity {

    private ListView listViewArticles;
    private DatabaseHelper databaseHelper;
    private ArrayList<Note> noteList;
    private NoteAdapter noteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_article);

        listViewArticles = findViewById(R.id.listViewArticles);
        databaseHelper = new DatabaseHelper(this);
        noteList = new ArrayList<>();
        noteAdapter = new NoteAdapter(this, noteList);
        listViewArticles.setAdapter(noteAdapter);

        listViewArticles.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {


                Note note = noteList.get(position);
                afficherDetail(note);
            }
        });

        afficherListeArticles();

        ImageButton button=findViewById(R.id.retour);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ListeArticle.this, MainActivity.class);
                startActivity(i);
            }
        });
    }

    private void afficherListeArticles() {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        String[] projection = {
                DatabaseHelper.COLUMN_ID,
                DatabaseHelper.COLUMN_TITLE,
                DatabaseHelper.COLUMN_CONTENT,
                DatabaseHelper.COLUMN_DATE
        };

        Cursor cursor = db.query(
                DatabaseHelper.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        noteList.clear();

        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_ID));
            String titre = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_TITLE));
            String contenu = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_CONTENT));
            String date = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_DATE));

            Note note = new Note(id, titre, contenu, date);
            noteList.add(note);
        }

        cursor.close();
        noteAdapter.notifyDataSetChanged();
    }

    private void afficherDetail(Note note) {
        Intent intent = new Intent(ListeArticle.this, DetailActivity.class);
        intent.putExtra("id", note.getId());
        intent.putExtra("titre", note.getTitre());
        intent.putExtra("contenu", note.getContenu());
        intent.putExtra("date", note.getDate());
        startActivity(intent);
    }

}