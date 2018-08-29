package com.robert.robertlyon.guitarFretboardTrainer;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import org.w3c.dom.Text;

public class GameOver extends AppCompatActivity {

    private InterstitialAd mInterstitialAd;

    public void restartGame(View v)
    {
        Intent anIntent = new Intent(this, MainActivity.class);
        startActivity(anIntent);
    }

    public void returnToTitle(View v)
    {
        Intent anIntent = new Intent(this, StartScreen.class);
        startActivity(anIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);


        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-1187196233110059/2893937403");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        if(mInterstitialAd.isLoaded())
        {
            mInterstitialAd.show();
        }

        mInterstitialAd.setAdListener(new AdListener()
        {
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                mInterstitialAd.show();
            }
        });

        int correctAnswers = getIntent().getIntExtra("correctAnswers", 0);
        int numberOfQuestions = getIntent().getIntExtra("numberOfQuestions", 0);

        TextView resultMessageTextView = findViewById(R.id.resultMessageTextView);
        resultMessageTextView.setText("Well done you manged to get " + correctAnswers +
                " correct answers out of a possible " + numberOfQuestions + "!!");
    }


}
