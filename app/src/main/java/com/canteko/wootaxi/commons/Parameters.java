package com.canteko.wootaxi.commons;

import android.content.Context;
import android.content.SharedPreferences;

import com.canteko.wootaxi.R;

import java.util.Map;

public abstract class Parameters {
    private static final String api_base_url = "https://test.wootaxi.com/WooApi/v2/";

    public static String getApiBaseUrl(Context context) {
        // Buscamos si en configuracion hemos guardado el endpoint
        SharedPreferences sharedPref = context.getSharedPreferences(context.getString(R.string.parameters_file_name), Context.MODE_PRIVATE);
        String apiBaseUrl = sharedPref.getString(context.getString(R.string.parameters_api_base_url), api_base_url);

        // Si el endpoint guardado no termina en '/', se lo añadimos
        if(!apiBaseUrl.endsWith("/")) {
            apiBaseUrl += "/";
        }

        return apiBaseUrl;
    }

    public static String getApiKey(Context context) {
        return getStringParameter(context, context.getString(R.string.parameters_api_key));
    }

    public static String getEmail(Context context) {
        return getStringParameter(context, context.getString(R.string.parameters_email));
    }

    public static void setApiKey(Context context, String apiKey) {
        setStringParameter(context, context.getString(R.string.parameters_api_key), apiKey);
    }

    public static void setEmail(Context context, String email) {
        setStringParameter(context, context.getString(R.string.parameters_email), email);
    }

    // Private methods

    private static SharedPreferences getApiSharedPreferences(Context context) {
        return context.getSharedPreferences(context.getString(R.string.parameters_file_name), Context.MODE_PRIVATE);
    }

    private static String getStringParameter(Context context, String parameter) {
        SharedPreferences sharedPref = getApiSharedPreferences(context);
        return sharedPref.getString(parameter, "");
    }

    private static void setStringParameter(Context context, String parameter, String value) {
        SharedPreferences sharedPref = getApiSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(parameter, value);
        editor.apply();
    }

    private static void setStringParameters(Context context, Map<String, String> parametersValues) {
        SharedPreferences sharedPref = getApiSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPref.edit();
        for(String key : parametersValues.keySet()) {
            editor.putString(key, parametersValues.get(key));
        }
        editor.apply();
    }
}
