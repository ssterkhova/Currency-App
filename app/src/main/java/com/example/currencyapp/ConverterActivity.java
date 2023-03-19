package com.example.currencyapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;

public class ConverterActivity extends AppCompatActivity {

    //Declaration of Variables
    private RequestQueue queue1;
    double eurUsdRate;
    double eurGbpRate;
    double eurNokRate;
    double eurSekRate;
    double eurDkkRate;
    double eurJpyRate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_converter);

        //Instantiate the request queue
        queue1 = Volley.newRequestQueue(this);

        //Declaration of areas for entering the amount of euros
        TextView resultTextView = findViewById(R.id.resultTextView);
        //and for displaying the result
        EditText ed1 = findViewById(R.id.AmmountEditText);

        //Spinner that allows you to select the desired currency for conversion from the drop-down list
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        String[] to = {"USD","GBP","NOK","SEK","DKK","JPY"}; //drop-down list array
        ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, to);
        spinner.setAdapter(adapter);

        //Declaration of a button that makes the conversion of the required amount of euros
        Button b1 = findViewById(R.id.calculateButton);

        //to set a listener for a button
        b1.setOnClickListener(new View.OnClickListener() {

            //method to update rates from REST API
            public void onClick(View v) {

                //link for current exchange rate information
                String url1 = "https://cdn.jsdelivr.net/gh/fawazahmed0/currency-api@1/latest/currencies/eur.json";

                StringRequest stringRequest = new StringRequest(Request.Method.GET, url1,
                        response -> {
                            Log.d("CURRENCY_APP", response);
                            parseJsonAndUpdateUI1(response);
                        }, error -> {
                    Log.d("CURRENCY_APP", error.toString());
                });
                queue1.add(stringRequest);
            }

            //method to parse data from JSON object and calculate result
            private void parseJsonAndUpdateUI1(String response) {
                try {
                    JSONObject currencyResponse = new JSONObject(response);

                    eurUsdRate = currencyResponse.getJSONObject("eur").getDouble("usd");
                    eurGbpRate = currencyResponse.getJSONObject("eur").getDouble("gbp");
                    eurNokRate = currencyResponse.getJSONObject("eur").getDouble("nok");
                    eurSekRate = currencyResponse.getJSONObject("eur").getDouble("sek");
                    eurDkkRate = currencyResponse.getJSONObject("eur").getDouble("dkk");
                    eurJpyRate = currencyResponse.getJSONObject("eur").getDouble("jpy");

                    Double total;

                    //The number that the user entered in the "amount in euros" field
                    Double amt = Double.parseDouble(ed1.getText().toString());

                    //Calculation and display of the result depending on which option is selected on the spinner
                    if(spinner.getSelectedItem().toString() == "USD")
                    {
                        total = amt * eurUsdRate;
                        String formattedTot = String.format("%.2f", total);
                        resultTextView.setText(formattedTot + " USD");

                    }
                    else if (spinner.getSelectedItem().toString() == "GBP")
                    {
                        total = amt * eurGbpRate;
                        String formattedTot = String.format("%.2f", total);
                        resultTextView.setText(formattedTot + " GBP");
                    }
                    else if (spinner.getSelectedItem().toString() == "NOK")
                    {
                        total = amt * eurNokRate;
                        String formattedTot = String.format("%.2f", total);
                        resultTextView.setText(formattedTot + " NOK");
                    }
                    else if (spinner.getSelectedItem().toString() == "SEK")
                    {
                        total = amt * eurSekRate;
                        String formattedTot = String.format("%.2f", total);
                        resultTextView.setText(formattedTot + " SEK");
                    }
                    else if (spinner.getSelectedItem().toString() == "DKK")
                    {
                        total = amt * eurDkkRate;
                        String formattedTot = String.format("%.2f", total);
                        resultTextView.setText(formattedTot + " DKK");
                    }
                    else if (spinner.getSelectedItem().toString() == "JPY")
                    {
                        total = amt * eurJpyRate;
                        String formattedTot = String.format("%.2f", total);
                        resultTextView.setText(formattedTot + " JPY");
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    //Method to return to Main activity
    public void openMainActivity(View view) {
        finish();
    }

}