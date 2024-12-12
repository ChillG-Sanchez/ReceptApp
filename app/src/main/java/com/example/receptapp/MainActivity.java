package com.example.receptapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Recipe> recipeList;
    private RecipeAdapter recipeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText etRecipeName = findViewById(R.id.etRecipeName);
        EditText etQuality = findViewById(R.id.etQuality);
        EditText etDifficulty = findViewById(R.id.etDifficulty);
        Button btnAdd = findViewById(R.id.btnAdd);
        ListView recipeListView = findViewById(R.id.recipeListView);

        recipeList = new ArrayList<>();
        recipeAdapter = new RecipeAdapter(this, recipeList);
        recipeListView.setAdapter(recipeAdapter);

        btnAdd.setOnClickListener(v -> {
            String name = etRecipeName.getText().toString();
            String qualityStr = etQuality.getText().toString();
            String difficultyStr = etDifficulty.getText().toString();

            if (name.isEmpty() || qualityStr.isEmpty() || difficultyStr.isEmpty()) {
                Toast.makeText(this, "Minden mezőt ki kell tölteni!", Toast.LENGTH_SHORT).show();
                return;
            }

            int quality, difficulty;
            try {
                quality = Integer.parseInt(qualityStr);
                difficulty = Integer.parseInt(difficultyStr);

                if (quality < 1 || quality > 100 || difficulty < 0 || difficulty > 3) {
                    Toast.makeText(this, "Érvényes minőséget (1-100) és nehézséget (0-3) adj meg!", Toast.LENGTH_SHORT).show();
                    return;
                }
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Helyes számokat adj meg!", Toast.LENGTH_SHORT).show();
                return;
            }

            Recipe newRecipe = new Recipe(name, quality, difficulty);
            recipeList.add(newRecipe);
            recipeAdapter.notifyDataSetChanged();

            etRecipeName.setText("");
            etQuality.setText("");
            etDifficulty.setText("");
        });

        recipeListView.setOnItemClickListener((parent, view, position, id) -> {
            Recipe selectedRecipe = recipeList.get(position);
            Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
            intent.putExtra("recipe", selectedRecipe);
            startActivity(intent);
        });
    }
}
