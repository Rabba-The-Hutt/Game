package com.example.robertlyon.game;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    android.support.v7.widget.GridLayout noteGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        noteGrid = findViewById(R.id.noteGrid);

        ImageView anImage;

        for(int i = 0; i < noteGrid.getRowCount() * noteGrid.getColumnCount(); i++)
        {
            anImage = new ImageView(this);
            anImage.setImageResource(R.drawable.note_marker);
            if(i % 12 == 0 || i == 0)
            {
                anImage.setPadding(20, 0, 0, 0);
            }
            anImage.setVisibility(View.INVISIBLE);
            noteGrid.addView(anImage, i);
        }

        Random random = new Random();
        int randomNoteIndex = random.nextInt(71);
        Log.i("rand", randomNoteIndex + "");
        ImageView workImage = (ImageView)noteGrid.getChildAt(randomNoteIndex);
        workImage.setVisibility(View.VISIBLE);
    }
}
