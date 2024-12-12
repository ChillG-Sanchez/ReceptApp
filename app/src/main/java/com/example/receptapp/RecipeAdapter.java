package com.example.receptapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class RecipeAdapter extends ArrayAdapter<Recipe> {
    private final Context context;
    private final List<Recipe> recipes;

    public RecipeAdapter(Context context, List<Recipe> recipes) {
        super(context, 0, recipes);
        this.context = context;
        this.recipes = recipes;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = new LinearLayout(context);
            ((LinearLayout) convertView).setOrientation(LinearLayout.VERTICAL);
            convertView.setPadding(16, 16, 16, 16);

            TextView tvTitle = new TextView(context);
            tvTitle.setId(View.generateViewId());
            tvTitle.setTextSize(18);
            ((LinearLayout) convertView).addView(tvTitle);

            TextView tvDetails = new TextView(context);
            tvDetails.setId(View.generateViewId());
            tvDetails.setTextSize(14);
            ((LinearLayout) convertView).addView(tvDetails);

            Button btnDelete = new Button(context);
            btnDelete.setId(View.generateViewId());
            btnDelete.setText("Törlés");
            btnDelete.setFocusable(false);
            btnDelete.setFocusableInTouchMode(false);
            ((LinearLayout) convertView).addView(btnDelete);
        }

        Recipe recipe = recipes.get(position);

        TextView tvTitle = (TextView) ((LinearLayout) convertView).getChildAt(0);
        TextView tvDetails = (TextView) ((LinearLayout) convertView).getChildAt(1);
        Button btnDelete = (Button) ((LinearLayout) convertView).getChildAt(2);

        tvTitle.setText(recipe.getName());
        tvDetails.setText("Minőség: " + recipe.getQuality() + " | Nehézség: " + recipe.getDifficulty());

        convertView.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetailsActivity.class);
            intent.putExtra("recipe", recipe);
            context.startActivity(intent);
        });

        btnDelete.setOnClickListener(v -> new AlertDialog.Builder(context)
                .setTitle("Törlés")
                .setMessage("Biztosan törölni szeretnéd ezt a receptet?")
                .setPositiveButton("Igen", (dialog, which) -> {
                    recipes.remove(position);
                    notifyDataSetChanged();
                })
                .setNegativeButton("Nem", null)
                .show());

        return convertView;
    }
}
