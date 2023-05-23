package com.example.hello;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private EditText editTextTitre;
    private EditText editTextContenu;
    private Button btnAjouter;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new DatabaseHelper(this);
        editTextTitre = findViewById(R.id.editTextTitre);
        editTextContenu = findViewById(R.id.editTextContenu);
        btnAjouter = findViewById(R.id.btnAjouter);

        btnAjouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ajouterNote();
            }
        });
    }
    private void ajouterNote() {
        String titre = editTextTitre.getText().toString();
        String contenu = editTextContenu.getText().toString();

        // Obtention de la date actuelle
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = dateFormat.format(calendar.getTime());

        // Insertion de la note dans la base de données
        insertNoteIntoDatabase(titre, contenu, currentDate);

        // affichage de la liste des article
        Button button=findViewById(R.id.retourner);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this,ListeArticle.class);
                startActivity(i);
            }
        });
    }

    private void insertNoteIntoDatabase(String titre, String contenu, String date) {
        databaseHelper.insertNote(titre, contenu, date);
    }
}
