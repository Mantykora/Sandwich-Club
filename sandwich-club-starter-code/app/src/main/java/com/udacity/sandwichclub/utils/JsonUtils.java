package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) throws JSONException {
        final String NAME = "name";
        final String MAIN_NAME = "mainName";
        final String ALSO_KNOWN_AS = "alsoKnownAs";

        final String PLACE_OF_ORIGIN = "placeOfOrigin";
        final String DESCRIPTION = "description";
        final String IMAGE = "image";
        final String INGREDIENTS = "ingredients";

        JSONObject sandwichJson = new JSONObject(json);

        JSONObject name = sandwichJson.getJSONObject(NAME);
        String mainName = name.getString(MAIN_NAME);
        JSONArray alsoKnownAsName = name.getJSONArray(ALSO_KNOWN_AS);

        ArrayList<String> alsoList = new ArrayList<>();
        for (int i = 0; i < alsoKnownAsName.length(); i++) {
            alsoList.add(alsoKnownAsName.getString(i));
        }

        String origin = sandwichJson.getString(PLACE_OF_ORIGIN);
        String description = sandwichJson.getString(DESCRIPTION);
        String image = sandwichJson.getString(IMAGE);
        JSONArray ingredients = sandwichJson.getJSONArray(INGREDIENTS);

        ArrayList<String> ingredientsList = new ArrayList<>();
        for (int i = 0; i < ingredients.length(); i++) {
            ingredientsList.add(ingredients.getString(i));
        }


        return new Sandwich(mainName, alsoList, origin, description, image, ingredientsList);
    }
}
