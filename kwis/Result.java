package com.hfad.kwis;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Result extends AppCompatActivity {
    public static final String LOG_TAG = Result.class.getSimpleName();
    int result;
    double score;
    String percent;
    String uitslag;
    String oordeel;
    int aantal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);


        kwisuitslag();
    }

    private void kwisuitslag() {

        Intent intent = getIntent();
        result = intent.getExtras().getInt("Resultaat");
        aantal = intent.getExtras().getInt("Aantal");
        uitslag = "" + result;
        TextView textView_resultaat = (TextView) findViewById(R.id.textView_resultaat);
        textView_resultaat.setText("");
        textView_resultaat.setText(uitslag);
        score = ((double) result / (double) aantal) * 100;
        percent = "Dat is " + (int) score + "%";
        TextView textView_proc = (TextView) findViewById(R.id.textView_percentage);
        textView_proc.setText("");
        textView_proc.setText(percent);

        if ((int) score >= 95) {
            oordeel = "Uitstekend!";
        } else if ((int) score >= 80) {
            oordeel = "Heel goed!";
        } else if ((int) score >= 70) {
            oordeel = "Mooi gedaan";
        } else if ((int) score >= 60) {
            oordeel = "Oké, maar dat kan beter!";
        } else if ((int) score >= 50) {
            oordeel = "Hm, magertjes";
        } else if ((int) score < 50) {
            oordeel = "Weet je zeker dat je een bolleboos bent?";
        }


        TextView textView_weging = (TextView) findViewById(R.id.textView_weging);
        textView_weging.setText("");
        textView_weging.setText(oordeel);

        AsyncTaskUrlToJson asyncTaskUrlToJson = new AsyncTaskUrlToJson(this);

        asyncTaskUrlToJson.execute("http://rudy.mediade.eu/statistieken.php/?aantal="+ aantal + "&result=" +result + "&score=" +score);

}



        }