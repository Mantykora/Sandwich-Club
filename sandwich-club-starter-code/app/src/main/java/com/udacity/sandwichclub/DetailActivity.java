package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import org.json.JSONException;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;
    private TextView nameTextView;
    private TextView alsoTextView;
    private TextView originTextView;
    private TextView descriptionTextView;
    private TextView ingredientsTextView;
    Sandwich sandwich;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail2);

        alsoTextView = findViewById(R.id.also_known_tv);
        originTextView = findViewById(R.id.origin_tv);
        descriptionTextView = findViewById(R.id.description_tv);
        ingredientsTextView = findViewById(R.id.ingredients_tv);
        ImageView ingredientsIv = findViewById(R.id.image_iv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        try {
            sandwich = JsonUtils.parseSandwichJson(json);

            if (sandwich == null) {
                // Sandwich data unavailable
                closeOnError();
                return;
            }


            populateUI();
            Picasso.with(this)
                    .load(sandwich.getImage())
                    .into(ingredientsIv);

            setTitle(sandwich.getMainName());

        } catch (JSONException e) {
            Log.e("DetaiActivity.java", "Parsing JSON went wrong");
        }

    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI() {
        String getAlsoString = "";
        for (String san : sandwich.getAlsoKnownAs()) {
            getAlsoString += san + "\n";
        }

        sandwich.getAlsoKnownAs();
        alsoTextView.setText(getAlsoString);
        originTextView.setText(sandwich.getPlaceOfOrigin());
        descriptionTextView.setText(sandwich.getDescription());
        String getIngredientsString = "";
        for (String san : sandwich.getIngredients()) {
            getIngredientsString += san + "\n";
        }
        ingredientsTextView.setText(getIngredientsString);


    }

}
