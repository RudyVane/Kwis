package com.hfad.kwis;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Statistieken extends AppCompatActivity implements AsyncTaskCompleteListener {

    public static final String LOG_TAG = Statistieken.class.getSimpleName();

    int Gem10;
    int Gem25;
    int Gem50;
    int Gem100;
    int GemTotaal;
    private ArrayList<String> Gemiddelde;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistieken);
        Gemiddelde = new ArrayList<>();

        updateUI();
    }

    private void updateUI() {

        AsyncTaskUrlToJson asyncTaskUrlToJson = new AsyncTaskUrlToJson(this, this);
        asyncTaskUrlToJson.execute("http://rudy.mediade.eu/gemiddelden.php?name=");
    }

    private void reloadUI() {

        TextView textView_nummer = (TextView) findViewById(R.id.textView_g10);
        textView_nummer.setText("");
        textView_nummer.setText(Gemiddelde.get(0));

        TextView textView_vragen = (TextView) findViewById(R.id.textView_g25);
        textView_vragen.setText("");
        textView_vragen.setText(Gemiddelde.get(1));

        TextView textView_antwoordA = (TextView) findViewById(R.id.textView_g50);
        textView_antwoordA.setText("");
        textView_antwoordA.setText(Gemiddelde.get(2));

        TextView textView_antwoordB = (TextView) findViewById(R.id.textView_g100);
        textView_antwoordB.setText("");
        textView_antwoordB.setText(Gemiddelde.get(3));

        TextView textView_antwoordC = (TextView) findViewById(R.id.textView_gtotaal);
        textView_antwoordC.setText("");
        textView_antwoordC.setText(Gemiddelde.get(4));

    }

    private void jsonToArray(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            Gemiddelde.clear();

            JSONArray jsonArrayGemiddelden = jsonObject.getJSONArray("Gemiddelde");
            for (int i = 0; i < 5; i++) {
                JSONObject jsonObj = jsonArrayGemiddelden.getJSONObject(i);
                Gemiddelde.add(new String(jsonObj.getString("text")));

            }
        } catch (JSONException ex) {
            Log.e(LOG_TAG, "jsonToArray: ", ex);

        }


    }

    @Override
    public void onTaskComplete(Object result) {
        String json = (String) result;
        jsonToArray(json);
        reloadUI();
    }
}