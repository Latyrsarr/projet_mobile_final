package com.example.hello;


import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {
    private TextView textViewTitre;
    private TextView textViewDate;
    private TextView textViewContenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        textViewTitre = findViewById(R.id.textViewTitre);
        textViewDate = findViewById(R.id.textViewDate);
        textViewContenu = findViewById(R.id.textViewContenu);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String titre = extras.getString("titre");
            String date = extras.getString("date");
            String contenu = extras.getString("contenu");

            textViewTitre.setText(titre);
            textViewDate.setText(date);
            textViewContenu.setText(contenu);
        }

        ImageButton button=findViewById(R.id.retour);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DetailActivity.this, ListeArticle.class);
                startActivity(i);
            }
        });
    }
}
