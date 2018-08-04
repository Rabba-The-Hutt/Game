package com.robert.robertlyon.guitarFretboardTrainer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class StartScreen extends AppCompatActivity {



    public void startGame(View v)
    {
        Intent anIntent = new Intent(this, MainActivity.class);
        startActivity(anIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);

    }
}
