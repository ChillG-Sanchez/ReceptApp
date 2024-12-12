package com.example.receptapp;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class RecipeAdapter extends ArrayAdapter<Recipe> {
    private final Context context;
    private final List<Recipe> recipes;

    public RecipeAdapter(Context context, List<Recipe> recipes) {
        super(context, 0, recipes);
        this.context = context;
        this.recipes = recipes;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LinearLayout layout = new LinearLayout(context);
            layout.setOrientation(LinearLayout.VERTICAL);
            layout.setPadding(16, 16, 16, 16);

            TextView tvTitle = new TextView(context);
            tvTitle.setId(View.generateViewId());
            tvTitle.setTextSize(18);
            layout.addView(tvTitle);

            TextView tvDetails = new TextView(context);
            tvDetails.setId(View.generateViewId());
            tvDetails.setTextSize(14);
            layout.addView(tvDetails);

            Button btnDelete = new Button(context);
            btnDelete.setId(View.generateViewId());
            btnDelete.setText("Törlés");
            layout.addView(btnDelete);

            convertView = layout;
        }

        Recipe recipe = recipes.get(position);

        TextView tvTitle = (TextView) ((LinearLayout) convertView).getChildAt(0);
        TextView tvDetails = (TextView) ((LinearLayout) convertView).getChildAt(1);
        Button btnDelete = (Button) ((LinearLayout) convertView).getChildAt(2);

        tvTitle.setText(recipe.getName());
        tvDetails.setText("Minőség: " + recipe.getQuality() + " | Nehézség: " + recipe.getDifficulty());

        btnDelete.setOnClickListener(v -> {
            new AlertDialog.Builder(context)
                    .setTitle("Törlés")
                    .setMessage("Biztosan törölni szeretnéd ezt a receptet?")
                    .setPositiveButton("Igen", (dialog, which) -> {
                        recipes.remove(position);
                        notifyDataSetChanged();
                    })
                    .setNegativeButton("Nem", null)
                    .show();
        });

        return convertView;
    }
}
