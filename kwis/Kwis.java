package com.hfad.kwis;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static android.graphics.Color.GREEN;
import static android.graphics.Color.YELLOW;


public class Kwis extends Activity implements AsyncTaskCompleteListener{

    int number = 0;
    int Resultaat = 0;
    int aantal;
    String nummer;
    String antwoord = "";
    String goed = "";
    String blok = "";
    String tekst = "";

    public static final String LOG_TAG = Kwis.class.getSimpleName();

    private ArrayList<String> vragen;
    private ArrayList<String> antwoordA;
    private ArrayList<String> antwoordB;
    private ArrayList<String> antwoordC;
    private ArrayList<String> antwoordD;
    private ArrayList<String> Goed;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kwis);

        Intent intent = getIntent();
        aantal = intent.getExtras().getInt("Aantal");

        vragen = new ArrayList<>();
        antwoordA = new ArrayList<>();
        antwoordB = new ArrayList<>();
        antwoordC = new ArrayList<>();
        antwoordD = new ArrayList<>();
        Goed = new ArrayList<>();
        updateUI();
    }

    private void updateUI() {

        AsyncTaskUrlToJson asyncTaskUrlToJson = new AsyncTaskUrlToJson(this, this);
        asyncTaskUrlToJson.execute("http://rudy.mediade.eu/gettekstkwis.php?name=");
    }
    public void next(View view) {
        antwoord="";
        tekst="";

        number++;
        blok="false";
        if (number == aantal) {
            Intent Intent = new Intent(Kwis.this, Result.class);
            Intent.putExtra("Resultaat", Resultaat);
            Intent.putExtra("Aantal", aantal);
            Kwis.this.startActivity(Intent);

        }else {

        reloadUI();
    }}
    private void reloadUI(){
        goed=Goed.get(number);
        nummer=""+(number+1);

        TextView textView_nummer = (TextView) findViewById(R.id.textView_nummer);
        textView_nummer.setText("");
        textView_nummer.setText(nummer);

        TextView textView_vragen = (TextView) findViewById(R.id.textView_vragen);
        textView_vragen.setText("");
        textView_vragen.setText(vragen.get(number));

        TextView textView_antwoordA = (TextView) findViewById(R.id.textView_antwoordA);
        textView_antwoordA.setText("");
        textView_antwoordA.setText(antwoordA.get(number));

        TextView textView_antwoordB = (TextView) findViewById(R.id.textView_antwoordB);
        textView_antwoordB.setText("");
        textView_antwoordB.setText(antwoordB.get(number));

        TextView textView_antwoordC = (TextView) findViewById(R.id.textView_antwoordC);
        textView_antwoordC.setText("");
        textView_antwoordC.setText(antwoordC.get(number));

        TextView textView_antwoordD = (TextView) findViewById(R.id.textView_antwoordD);
        textView_antwoordD.setText("");
        textView_antwoordD.setText(antwoordD.get(number));

        TextView textView_Goed = (TextView) findViewById(R.id.textView_Goed);
        textView_Goed.setText("");
        textView_Goed.setText(antwoord);

        TextView textView_next = (TextView) findViewById(R.id.textView_next);
        textView_next.setText("");
        textView_next.setText(tekst);


    }

    public void antwoordA(View view) {
        if (blok == "true") {
            pauze();
        } else {
            if (goed.equals("A") || goed.equals("a")) {
                antwoord = "GOED!";
                Resultaat++;
                TextView textView_Goed = (TextView) findViewById(R.id.textView_Goed);
                textView_Goed.setText("");
                textView_Goed.setText(antwoord);
            } else {
                antwoord = "Helaas, dat is niet goed. Het juiste antwoord is: " + goed;
                TextView textView_Goed = (TextView) findViewById(R.id.textView_Goed);
                textView_Goed.setText("");
                textView_Goed.setText(antwoord);
            }
            blok = "true";
            pauze();
        }
    }
    public void antwoordB(View view) {
        if (blok=="true"){
            pauze();
        }
        else{
        if (goed.equals("B") || goed.equals("b")){
            antwoord="GOED!";
            Resultaat++;
            TextView textView_Goed = (TextView) findViewById(R.id.textView_Goed);
            textView_Goed.setText("");
            textView_Goed.setText(antwoord);
        }
        else {
            antwoord="Helaas, dat is niet goed. Het juiste antwoord is: " + goed;
            TextView textView_Goed = (TextView) findViewById(R.id.textView_Goed);
            textView_Goed.setText("");
            textView_Goed.setText(antwoord);
        }
        blok = "true";
            pauze();
        }
    }
    public void antwoordC(View view) {
        if (blok == "true") {
            pauze();
        } else {
            if (goed.equals("C") || goed.equals("c")) {
                antwoord = "GOED!";
                Resultaat++;
                TextView textView_Goed = (TextView) findViewById(R.id.textView_Goed);
                textView_Goed.setText("");
                textView_Goed.setText(antwoord);
            } else {
                antwoord = "Helaas, dat is niet goed. Het juiste antwoord is: " + goed;
                TextView textView_Goed = (TextView) findViewById(R.id.textView_Goed);
                textView_Goed.setText("");
                textView_Goed.setText(antwoord);
            }
            blok = "true";
            pauze();
        }
    }
    public void antwoordD(View view) {
        if (blok == "true") {
            pauze();
        } else {
            if (goed.equals("D") || goed.equals("d")) {
                antwoord = "GOED!";
                Resultaat++;
                TextView textView_Goed = (TextView) findViewById(R.id.textView_Goed);
                textView_Goed.setText("");
                textView_Goed.setText(antwoord);
            } else {
                antwoord = "Helaas, dat is niet goed. Het juiste antwoord is: " + goed;
                TextView textView_Goed = (TextView) findViewById(R.id.textView_Goed);
                textView_Goed.setText("");
                textView_Goed.setText(antwoord);
            }
            blok = "true";
            pauze();
        }
    }
    public void pauze() {
        if (number == aantal-1){
            tekst="Bekijk het resultaat";
        }
        else {
            tekst = "Ga naar de volgende vraag";
        }


        TextView textView_next = (TextView) findViewById(R.id.textView_next);
        textView_next.setText("");
        textView_next.setText(tekst);

        }


    private void jsonToArray(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);

            vragen.clear();
            antwoordA.clear();
            antwoordB.clear();
            antwoordC.clear();
            antwoordD.clear();
            Goed.clear();


            JSONArray jsonArrayVragen = jsonObject.getJSONArray("Vraag");
            for (int i = 0; i < aantal; i++) {
                JSONObject jsonObj = jsonArrayVragen.getJSONObject(i);
                vragen.add(new String(jsonObj.getString("text")));
            }
            JSONArray jsonArrayAntwoordA = jsonObject.getJSONArray("A");
            for (int i = 0; i < aantal; i++) {
                JSONObject jsonObj = jsonArrayAntwoordA.getJSONObject(i);
                antwoordA.add(new String(jsonObj.getString("text")));
            }
            JSONArray jsonArrayAntwoordB = jsonObject.getJSONArray("B");
            for (int i = 0; i < aantal; i++) {
                JSONObject jsonObj = jsonArrayAntwoordB.getJSONObject(i);
                antwoordB.add(new String(jsonObj.getString("text")));
            }
            JSONArray jsonArrayAntwoordC = jsonObject.getJSONArray("C");
            for (int i = 0; i < aantal; i++) {
                JSONObject jsonObj = jsonArrayAntwoordC.getJSONObject(i);
                antwoordC.add(new String(jsonObj.getString("text")));
            }
            JSONArray jsonArrayAntwoordD = jsonObject.getJSONArray("D");
            for (int i = 0; i < aantal; i++) {
                JSONObject jsonObj = jsonArrayAntwoordD.getJSONObject(i);
                antwoordD.add(new String(jsonObj.getString("text")));
            }
            JSONArray jsonArrayGoed = jsonObject.getJSONArray("Juist");
            for (int i = 0; i < aantal; i++) {
                JSONObject jsonObj = jsonArrayGoed.getJSONObject(i);
                Goed.add(new String(jsonObj.getString("text")));
            }
        } catch (JSONException ex) {
            Log.e(LOG_TAG, "jsonToArray: ", ex);

        }
    }





        @Override
        public void onTaskComplete(Object result) {
        String json=(String)result;
        jsonToArray(json);
        reloadUI();
    }
}