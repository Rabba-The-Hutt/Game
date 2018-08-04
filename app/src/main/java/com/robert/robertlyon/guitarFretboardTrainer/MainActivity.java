package com.robert.robertlyon.guitarFretboardTrainer;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView scoreView;
    TextView answerFeedback;

    android.support.v7.widget.GridLayout noteGrid;
    Random random = new Random();
    int currentRandom;
    int numberOfQuestions = 0;
    int correctAnswers = 0;



    public void noteSelected(View v)
    {
        Button button = (Button) v;
        checkNote(currentRandom, button.getTag().toString());
        noteGrid.getChildAt(currentRandom).setVisibility(View.INVISIBLE);
        int randomNoteIndex = random.nextInt(71);
        currentRandom = randomNoteIndex;
        noteGrid.getChildAt(randomNoteIndex).setVisibility(View.VISIBLE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scoreView = findViewById(R.id.scoreView);
        answerFeedback = findViewById(R.id.answerFeedback);

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
        currentRandom = randomNoteIndex;
        noteGrid.getChildAt(randomNoteIndex).setVisibility(View.VISIBLE);
    }

    //Needs to be refactored
    private void checkNote(int i, String tag)
    {
        numberOfQuestions++;
        if(i == 0 || i == 17 || i == 33 || i == 38 || i == 55 || i ==60)
        {
            //F
            if(tag.equals("F"))
            {
                //Correct
                correctAnswers++;
                answerFeedback.setText("Correct!");
                answerFeedback.setTextColor(Color.GREEN);
            }
            else
            {
                //False
                answerFeedback.setText("Wrong!");
                answerFeedback.setTextColor(Color.RED);
            }
        }
        else if(i == 1 || i == 18 || i == 34 || i == 39 || i == 56 || i == 61)
        {
            //F#
            if(tag.equals("F1"))
            {
                //Correct
                correctAnswers++;
                answerFeedback.setText("Correct!");
                answerFeedback.setTextColor(Color.GREEN);
            }
            else
            {
                //False
                answerFeedback.setText("Wrong!");
                answerFeedback.setTextColor(Color.RED);
            }
        }
        else if(i == 2 || i == 19 || i == 35 || i == 40 || i == 57 || i == 62)
        {
            //G
            if(tag.equals("G"))
            {
                //Correct
                correctAnswers++;
                answerFeedback.setText("Correct!");
                answerFeedback.setTextColor(Color.GREEN);
            }
            else
            {
                //False
                answerFeedback.setText("Wrong!");
                answerFeedback.setTextColor(Color.RED);
            }
        }
        else if(i == 3 || i == 20 || i == 24 || i == 41 || i == 58 || i == 63)
        {
            //G#
            if(tag.equals("G1"))
            {
                //Correct
                correctAnswers++;
                answerFeedback.setText("Correct!");
                answerFeedback.setTextColor(Color.GREEN);
            }
            else
            {
                //False
                answerFeedback.setText("Wrong!");
                answerFeedback.setTextColor(Color.RED);
            }
        }
        else if(i == 4 || i == 21 || i == 25 || i == 42 || i == 59 || i == 64)
        {
            //A
            if(tag.equals("A"))
            {
                //Correct
                correctAnswers++;
                answerFeedback.setText("Correct!");
                answerFeedback.setTextColor(Color.GREEN);
            }
            else
            {
                //False
                answerFeedback.setText("Wrong!");
                answerFeedback.setTextColor(Color.RED);
            }
        }
        else if(i == 5 || i == 22 || i == 26 || i == 43 || i == 48 || i == 65)
        {
            //A#
            if(tag.equals("A1"))
            {
                //Correct
                correctAnswers++;
                answerFeedback.setText("Correct!");
                answerFeedback.setTextColor(Color.GREEN);
            }
            else
            {
                //False
                answerFeedback.setText("Wrong!");
                answerFeedback.setTextColor(Color.RED);
            }
        }
        else if(i == 6 || i == 23 || i == 27 || i == 44 || i == 49 || i == 66)
        {
            //B
            if(tag.equals("B"))
            {
                //Correct
                correctAnswers++;
                answerFeedback.setText("Correct!");
                answerFeedback.setTextColor(Color.GREEN);
            }
            else
            {
                //False
                answerFeedback.setText("Wrong!");
                answerFeedback.setTextColor(Color.RED);
            }
        }
        else if(i == 7 || i == 12 || i == 28 || i == 45 || i == 50 || i == 67)
        {
            //C
            if(tag.equals("C"))
            {
                //Correct
                correctAnswers++;
                answerFeedback.setText("Correct!");
                answerFeedback.setTextColor(Color.GREEN);
            }
            else
            {
                //False
                answerFeedback.setText("Wrong!");
                answerFeedback.setTextColor(Color.RED);
            }
        }
        else if(i == 8 || i == 13 || i == 29 || i == 46 || i == 51 || i == 68)
        {
            //C#
            if(tag.equals("C1"))
            {
                //Correct
                correctAnswers++;
                answerFeedback.setText("Correct!");
                answerFeedback.setTextColor(Color.GREEN);
            }
            else
            {
                //False
                answerFeedback.setText("Wrong!");
                answerFeedback.setTextColor(Color.RED);
            }
        }
        else  if(i == 9 || i == 14 || i == 30 || i == 47 || i == 52 || i == 69)
        {
            //D
            if(tag.equals("D"))
            {
                //Correct
                correctAnswers++;
                answerFeedback.setText("Correct!");
                answerFeedback.setTextColor(Color.GREEN);
            }
            else
            {
                //False
                answerFeedback.setText("Wrong!");
                answerFeedback.setTextColor(Color.RED);
            }
        }
        else if(i == 10 || i == 15 || i == 31 || i == 36 || i == 53 || i == 70)
        {
            //D#
            if(tag.equals("D1"))
            {
                //Correct
                correctAnswers++;
                answerFeedback.setText("Correct!");
                answerFeedback.setTextColor(Color.GREEN);
            }
            else
            {
                //False
                answerFeedback.setText("Wrong!");
                answerFeedback.setTextColor(Color.RED);
            }
        }
        else if(i == 11 || i == 16 || i == 32 || i == 37 || i == 54 || i == 71)
        {
            //E
            if(tag.equals("E"))
            {
                //Correct
                correctAnswers++;
                answerFeedback.setText("Correct!");
                answerFeedback.setTextColor(Color.GREEN);
            }
            else
            {
                //False
                answerFeedback.setText("Wrong!");
                answerFeedback.setTextColor(Color.RED);
            }
        }
        scoreView.setText(correctAnswers + "/" + numberOfQuestions);


    }

}
