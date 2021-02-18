package com.example.app4quizcidades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ActivityPontuacao extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pontuacao);

        Intent intent = getIntent();
        int score = intent.getIntExtra("score", -1);

        TextView textView = findViewById(R.id.textViewPontos);
        textView.setText(String.valueOf(score));
    }
}