package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import org.w3c.dom.Text;

import java.util.List;
import java.util.ListIterator;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;
    private static final String TAG = DetailActivity.class.getName();

    private ImageView ingredientsIv;
    private TextView mainNametv;
    private TextView alsoKnownAstv;
    private TextView placeOfOrigintv;
    private TextView descriptiontv;
    private TextView ingredientstv;


    private Sandwich sandwich;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ingredientsIv = findViewById(R.id.image_iv);

        placeOfOrigintv = (TextView) findViewById(R.id.origin_tv);
        descriptiontv = (TextView)findViewById(R.id.description_tv);
        ingredientstv = (TextView)findViewById(R.id.ingredients_tv);
        alsoKnownAstv = (TextView)findViewById(R.id.also_known_tv);

        Intent intent = getIntent();
        if (intent == null) {
            Log.d(TAG,"Intent is null");
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            Log.d(TAG,"Extra position not found in intent");
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            Log.d(TAG,"Sandwich is null");
            closeOnError();
            return;
        }

        populateUI();


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(sandwich.getMainName());

    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI() {

        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        placeOfOrigintv.setText(sandwich.getPlaceOfOrigin());
        descriptiontv.setText(sandwich.getDescription());
        ingredientstv.setText(TextUtils.join(", ", sandwich.getIngredients()));
        alsoKnownAstv.setText(TextUtils.join(", ", sandwich.getAlsoKnownAs()));



    }
}
