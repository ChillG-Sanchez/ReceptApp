package com.example.receptapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        TextView tvRecipeName = findViewById(R.id.tvRecipeName);
        TextView tvQuality = findViewById(R.id.tvQuality);
        TextView tvDifficulty = findViewById(R.id.tvDifficulty);
        TextView tvYear = findViewById(R.id.tvYear);
        Button btnBack = findViewById(R.id.btnBack);

        Intent intent = getIntent();
        Recipe recipe = (Recipe) intent.getSerializableExtra("recipe");

        if (recipe != null) {
            tvRecipeName.setText("Recept neve: " + recipe.getName());
            tvQuality.setText("Minőség: " + recipe.getQuality());
            tvDifficulty.setText("Nehézség: " + recipe.getDifficulty());

            Random random = new Random();
            int randomYear = random.nextInt(2024 - 1500) + 1500;
            tvYear.setText("Random évszám: " + randomYear);
        }

        btnBack.setOnClickListener(v -> finish());
    }
}
