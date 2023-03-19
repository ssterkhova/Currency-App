package com.example.currencyapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    //Declaration of Variables
    double eurUsdRate;
    double eurGbpRate;
    double eurNokRate;
    double eurSekRate;
    double eurDkkRate;
    double eurJpyRate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    //method to update rates from REST API
    public void fetchCurrencyData(View view) {

        //link for current exchange rate information
        String url = "https://cdn.jsdelivr.net/gh/fawazahmed0/currency-api@1/latest/currencies/eur.json";

        //Instantiate the request queue
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                response -> {
                    Log.d("CURRENCY_APP", response);
                    parseJsonAndUpdateUI(response);
                }, error -> {
                Log.d("CURRENCY_APP", error.toString());
        });

        queue.add(stringRequest);
    }

    //method to parse data from JSON object and set it to the textViews
    private void parseJsonAndUpdateUI(String response) {
        try {
            JSONObject currencyResponse = new JSONObject(response);

            eurUsdRate = currencyResponse.getJSONObject("eur").getDouble("usd");
            eurGbpRate = currencyResponse.getJSONObject("eur").getDouble("gbp");
            eurNokRate = currencyResponse.getJSONObject("eur").getDouble("nok");
            eurSekRate = currencyResponse.getJSONObject("eur").getDouble("sek");
            eurDkkRate = currencyResponse.getJSONObject("eur").getDouble("dkk");
            eurJpyRate = currencyResponse.getJSONObject("eur").getDouble("jpy");

            TextView eurUsdRateTextView = findViewById(R.id.eurUsdRateTextView);
            eurUsdRateTextView.setText("" + eurUsdRate);
            TextView eurGbpRateTextView = findViewById(R.id.eurGbpRateTextView);
            eurGbpRateTextView.setText("" + eurGbpRate);
            TextView eurNokRateTextView = findViewById(R.id.eurNokRateTextView);
            eurNokRateTextView.setText("" + eurNokRate);
            TextView eurSekRateTextView = findViewById(R.id.eurSekRateTextView);
            eurSekRateTextView.setText("" + eurSekRate);
            TextView eurDkkRateTextView = findViewById(R.id.eurDkkRateTextView);
            eurDkkRateTextView.setText("" + eurDkkRate);
            TextView eurJpyRateTextView = findViewById(R.id.eurJpyRateTextView);
            eurJpyRateTextView.setText("" + eurJpyRate);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //Method to start another activity to show Currency converter
    public void openConverterActivity(View view) {

        Intent openConverter = new Intent(this, ConverterActivity.class);
        startActivity(openConverter);
    }

    //method to save data - needed in case of re-creating the activity, e.g. in case of changing orientation
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putDouble("USD", eurUsdRate);
        outState.putDouble("GBP", eurGbpRate);
        outState.putDouble("NOK", eurNokRate);
        outState.putDouble("SEK", eurSekRate);
        outState.putDouble("DKK", eurDkkRate);
        outState.putDouble("JPY", eurJpyRate);
    }
    //method to restore data - needed in case of re-creating the activity, e.g. in case of changing orientation
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        eurUsdRate = savedInstanceState.getDouble("USD", eurUsdRate);
        eurGbpRate = savedInstanceState.getDouble("GBP", eurGbpRate);
        eurNokRate = savedInstanceState.getDouble("NOK", eurNokRate);
        eurSekRate = savedInstanceState.getDouble("SEK", eurSekRate);
        eurDkkRate = savedInstanceState.getDouble("DKK", eurDkkRate);
        eurJpyRate = savedInstanceState.getDouble("JPY", eurJpyRate);

        TextView eurUsdRateTextView = findViewById(R.id.eurUsdRateTextView);
        eurUsdRateTextView.setText("" + eurUsdRate);
        TextView eurGbpRateTextView = findViewById(R.id.eurGbpRateTextView);
        eurGbpRateTextView.setText("" + eurGbpRate);
        TextView eurNokRateTextView = findViewById(R.id.eurNokRateTextView);
        eurNokRateTextView.setText("" + eurNokRate);
        TextView eurSekRateTextView = findViewById(R.id.eurSekRateTextView);
        eurSekRateTextView.setText("" + eurSekRate);
        TextView eurDkkRateTextView = findViewById(R.id.eurDkkRateTextView);
        eurDkkRateTextView.setText("" + eurDkkRate);
        TextView eurJpyRateTextView = findViewById(R.id.eurJpyRateTextView);
        eurJpyRateTextView.setText("" + eurJpyRate);
    }

}
