package com.robert.robertlyon.guitarFretboardTrainer;

import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView mCountdownTextView;
    private TextView mScoreTextView;
    private TextView mAnswerFeedbackTextView;
    private TextView mMainTimerTextView;

    private android.support.v7.widget.GridLayout noteGrid;
    Random random = new Random();
    private int currentRandom;
    private int numberOfQuestions = 0;
    private int correctAnswers = 0;

    Handler handler;
    Runnable countdownRunnable;

    Runnable mainTimerRunnable;

    /*
    When a note is selected the note is checked against the users answer using the
    check note method, then another random note is shown to the user.
     */
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

        /*
        Sets up admob advertising for the banner ad at the bottom of the main activity
         */
        MobileAds.initialize(this, "ca-app-pub-1187196233110059~6141688941");
        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        //Finds the views that are used in the activity
        mCountdownTextView = findViewById(R.id.countdownTextView);
        mMainTimerTextView = findViewById(R.id.timerTextView);
        mScoreTextView = findViewById(R.id.scoreView);
        mAnswerFeedbackTextView = findViewById(R.id.answerFeedback);
        noteGrid = findViewById(R.id.noteGrid);


        /*
        The images that represent the marker that denotes the note that has been
        selected are all loaded into the scene on create and then all made invisible
        **I have done this to make sure that all of the grid boxes in the grid view remain evenly
        spaced. There is surely a better way but this one does the job for now.
         */
        //TODO Revise a way to show the markers to the user without loading them all onto the screen at once.
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

        /*
        Shows the initial note marker when the activity is loaded.
         */
        Random random = new Random();
        int randomNoteIndex = random.nextInt(71);
        currentRandom = randomNoteIndex;
        noteGrid.getChildAt(randomNoteIndex).setVisibility(View.VISIBLE);

        /*
        Set a countdown timer that alerts the user that the game is about to begin.
         */
        handler = new Handler();

        countdownRunnable = new Runnable() {
            int countdownTime = 5;
            @Override
            public void run() {
                if(countdownTime <= -1)
                {
                    mCountdownTextView.setVisibility(View.INVISIBLE);
                    handler.removeCallbacks(countdownRunnable);
                }
                else if(countdownTime == 0)
                {
                    mCountdownTextView.setBackgroundColor(Color.parseColor("#3498db"));
                    mCountdownTextView.setText("GO");
                    countdownTime -= 1;
                    handler.postDelayed(this, 1000);

                }
                else if(countdownTime > 0)
                {
                    switch (countdownTime)
                    {
                        case 5:
                            mCountdownTextView.setBackgroundColor(Color.parseColor("#c0392b"));
                            break;
                        case 4:
                            mCountdownTextView.setBackgroundColor(Color.parseColor("#2980b9"));
                            break;
                        case 3:
                            mCountdownTextView.setBackgroundColor(Color.parseColor("#d35400"));
                            break;
                        case 2:
                            mCountdownTextView.setBackgroundColor(Color.parseColor("#27ae60"));
                            break;
                        case 1:
                            mCountdownTextView.setBackgroundColor(Color.parseColor("#f39c12"));
                            break;
                    }

                    mCountdownTextView.setText(String.valueOf(countdownTime));
                    countdownTime -= 1;
                    handler.postDelayed(this, 1000);
                }
            }
        };
        countdownRunnable.run();

        //Runs second timer which counts down dor 2 minutes
        mainTimerRunnable = new Runnable() {
            int time = 125;
            @Override
            public void run() {
                if(time == 0)
                {
                    mMainTimerTextView.setText("Time: " + "0");
                    handler.removeCallbacks(mainTimerRunnable);
                    Intent anIntent = new Intent(getApplicationContext(), GameOver.class);
                    anIntent.putExtra("correctAnswers", correctAnswers);
                    anIntent.putExtra("numberOfQuestions", numberOfQuestions);
                    startActivity(anIntent);
                }
                else
                {
                    if(time % 60 < 10 && time / 60 > 0) {
                        mMainTimerTextView.setText("Time: " + time / 60 + ":0" + time % 60);
                    }
                    else if(time / 60 == 0)
                    {
                        mMainTimerTextView.setText("Time: " + time % 60);
                    }
                    else
                    {
                        mMainTimerTextView.setText("Time: " + time / 60 + ":" + time % 60);
                    }

                    time -= 1;
                    handler.postDelayed(this, 1000);
                }
            }
        };

        AsyncTask.execute(mainTimerRunnable);
    }

    /*
    Helper method for the note selected method, this method checks which note has been pressed
    and updates the text views to display the appropriate feedback
     */
    //TODO find a better way to check which note has been selected rather than this horrible if spaghetti
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
                mAnswerFeedbackTextView.setText("Correct!");
                mAnswerFeedbackTextView.setTextColor(Color.GREEN);
            }
            else
            {
                //False
                mAnswerFeedbackTextView.setText("Wrong!");
                mAnswerFeedbackTextView.setTextColor(Color.RED);
            }
        }
        else if(i == 1 || i == 18 || i == 34 || i == 39 || i == 56 || i == 61)
        {
            //F#
            if(tag.equals("F1"))
            {
                //Correct
                correctAnswers++;
                mAnswerFeedbackTextView.setText("Correct!");
                mAnswerFeedbackTextView.setTextColor(Color.GREEN);
            }
            else
            {
                //False
                mAnswerFeedbackTextView.setText("Wrong!");
                mAnswerFeedbackTextView.setTextColor(Color.RED);
            }
        }
        else if(i == 2 || i == 19 || i == 35 || i == 40 || i == 57 || i == 62)
        {
            //G
            if(tag.equals("G"))
            {
                //Correct
                correctAnswers++;
                mAnswerFeedbackTextView.setText("Correct!");
                mAnswerFeedbackTextView.setTextColor(Color.GREEN);
            }
            else
            {
                //False
                mAnswerFeedbackTextView.setText("Wrong!");
                mAnswerFeedbackTextView.setTextColor(Color.RED);
            }
        }
        else if(i == 3 || i == 20 || i == 24 || i == 41 || i == 58 || i == 63)
        {
            //G#
            if(tag.equals("G1"))
            {
                //Correct
                correctAnswers++;
                mAnswerFeedbackTextView.setText("Correct!");
                mAnswerFeedbackTextView.setTextColor(Color.GREEN);
            }
            else
            {
                //False
                mAnswerFeedbackTextView.setText("Wrong!");
                mAnswerFeedbackTextView.setTextColor(Color.RED);
            }
        }
        else if(i == 4 || i == 21 || i == 25 || i == 42 || i == 59 || i == 64)
        {
            //A
            if(tag.equals("A"))
            {
                //Correct
                correctAnswers++;
                mAnswerFeedbackTextView.setText("Correct!");
                mAnswerFeedbackTextView.setTextColor(Color.GREEN);
            }
            else
            {
                //False
                mAnswerFeedbackTextView.setText("Wrong!");
                mAnswerFeedbackTextView.setTextColor(Color.RED);
            }
        }
        else if(i == 5 || i == 22 || i == 26 || i == 43 || i == 48 || i == 65)
        {
            //A#
            if(tag.equals("A1"))
            {
                //Correct
                correctAnswers++;
                mAnswerFeedbackTextView.setText("Correct!");
                mAnswerFeedbackTextView.setTextColor(Color.GREEN);
            }
            else
            {
                //False
                mAnswerFeedbackTextView.setText("Wrong!");
                mAnswerFeedbackTextView.setTextColor(Color.RED);
            }
        }
        else if(i == 6 || i == 23 || i == 27 || i == 44 || i == 49 || i == 66)
        {
            //B
            if(tag.equals("B"))
            {
                //Correct
                correctAnswers++;
                mAnswerFeedbackTextView.setText("Correct!");
                mAnswerFeedbackTextView.setTextColor(Color.GREEN);
            }
            else
            {
                //False
                mAnswerFeedbackTextView.setText("Wrong!");
                mAnswerFeedbackTextView.setTextColor(Color.RED);
            }
        }
        else if(i == 7 || i == 12 || i == 28 || i == 45 || i == 50 || i == 67)
        {
            //C
            if(tag.equals("C"))
            {
                //Correct
                correctAnswers++;
                mAnswerFeedbackTextView.setText("Correct!");
                mAnswerFeedbackTextView.setTextColor(Color.GREEN);
            }
            else
            {
                //False
                mAnswerFeedbackTextView.setText("Wrong!");
                mAnswerFeedbackTextView.setTextColor(Color.RED);
            }
        }
        else if(i == 8 || i == 13 || i == 29 || i == 46 || i == 51 || i == 68)
        {
            //C#
            if(tag.equals("C1"))
            {
                //Correct
                correctAnswers++;
                mAnswerFeedbackTextView.setText("Correct!");
                mAnswerFeedbackTextView.setTextColor(Color.GREEN);
            }
            else
            {
                //False
                mAnswerFeedbackTextView.setText("Wrong!");
                mAnswerFeedbackTextView.setTextColor(Color.RED);
            }
        }
        else  if(i == 9 || i == 14 || i == 30 || i == 47 || i == 52 || i == 69)
        {
            //D
            if(tag.equals("D"))
            {
                //Correct
                correctAnswers++;
                mAnswerFeedbackTextView.setText("Correct!");
                mAnswerFeedbackTextView.setTextColor(Color.GREEN);
            }
            else
            {
                //False
                mAnswerFeedbackTextView.setText("Wrong!");
                mAnswerFeedbackTextView.setTextColor(Color.RED);
            }
        }
        else if(i == 10 || i == 15 || i == 31 || i == 36 || i == 53 || i == 70)
        {
            //D#
            if(tag.equals("D1"))
            {
                //Correct
                correctAnswers++;
                mAnswerFeedbackTextView.setText("Correct!");
                mAnswerFeedbackTextView.setTextColor(Color.GREEN);
            }
            else
            {
                //False
                mAnswerFeedbackTextView.setText("Wrong!");
                mAnswerFeedbackTextView.setTextColor(Color.RED);
            }
        }
        else if(i == 11 || i == 16 || i == 32 || i == 37 || i == 54 || i == 71)
        {
            //E
            if(tag.equals("E"))
            {
                //Correct
                correctAnswers++;
                mAnswerFeedbackTextView.setText("Correct!");
                mAnswerFeedbackTextView.setTextColor(Color.GREEN);
            }
            else
            {
                //False
                mAnswerFeedbackTextView.setText("Wrong!");
                mAnswerFeedbackTextView.setTextColor(Color.RED);
            }
        }
        mScoreTextView.setText(correctAnswers + "/" + numberOfQuestions);


    }

    //Saving instance state of score because ads change the screen rotation
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {

        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString("scoreString", mScoreTextView.getText().toString());
        savedInstanceState.putInt("correctQuestions", correctAnswers);
        savedInstanceState.putInt("numOfQuestions", numberOfQuestions);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState)
    {
        super.onRestoreInstanceState(savedInstanceState);
        correctAnswers = savedInstanceState.getInt("correctQuestions");
        numberOfQuestions = savedInstanceState.getInt("numOfQuestions");
        mScoreTextView.setText(savedInstanceState.getString("scoreString"));

    }
}
