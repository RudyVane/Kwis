package com.hfad.kwis;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int aantal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Kwis() {

        Intent Intent = new Intent(MainActivity.this, Kwis.class);
        Intent.putExtra("Aantal", aantal);
        startActivity(Intent);
    }

    public void Statistieken(View view) {

        Intent Intent = new Intent(MainActivity.this, Statistieken.class);

        startActivity(Intent);
    }


    public void Vraag10(View view) {
        aantal=10;
        Kwis();
    }

    public void Vraag25(View view) {
        aantal=25;
        Kwis();
    }

    public void Vraag50(View view) {
        aantal=50;
        Kwis();
    }

    public void Vraag100(View view) {
        aantal=100;
        Kwis();
    }


}
