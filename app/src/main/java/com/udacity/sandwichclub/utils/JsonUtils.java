package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JsonUtils {

        private  static final String TAG = JsonUtils.class.getName();

        private static  final String NAME_CODE = "name";
        private static final String MAIN_NAME_CODE = "mainName";
        private static final String ALSO_KNOWN_AS_CODE = "alsoKnownAs";
        private static final String PLACE_OF_ORIGIN_CODE = "placeOfOrigin";
        private static final String DESCRIPTION_CODE = "description";
        private static final String IMAGE_CODE = "image";
        private static final String INGREDIENTS_CODE = "ingredients";

    public static Sandwich parseSandwichJson(String json) {

        Log.d(TAG,json);

        try {
            JSONObject obj = new JSONObject(json);

            JSONObject name = obj.getJSONObject(NAME_CODE);
            String mainName = name.getString(MAIN_NAME_CODE);

            JSONArray JSONArrayAlsoKnownAs = name.getJSONArray(ALSO_KNOWN_AS_CODE);
            List<String> alsoKnownAs = convertToListFromJSONArray(JSONArrayAlsoKnownAs);

            String placeOfOrigin = obj.getString(PLACE_OF_ORIGIN_CODE);
            String description = obj.getString(DESCRIPTION_CODE);
            String image = obj.getString(IMAGE_CODE);


            JSONArray JSONIngredients= obj.getJSONArray(INGREDIENTS_CODE);
            List<String> ingredients = convertToListFromJSONArray(JSONIngredients);

            return new Sandwich(mainName,alsoKnownAs,placeOfOrigin,description,image,ingredients);


        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }


    private static List<String> convertToListFromJSONArray(JSONArray jsonArray) throws JSONException{

            List<String> list = new ArrayList<String>();

            for(int i=0;i<jsonArray.length();i++){
                list.add(jsonArray.getString(i));
            }

            return list;

    }
}
