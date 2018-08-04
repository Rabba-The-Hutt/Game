package com.example.robertlyon.game;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;

public class StartScreen extends AppCompatActivity {


    Switch aSwitch;
    EditText editText;
    TextView timeTextDesc;
    RadioGroup sharpsOrFlats;

    public void startGame(View v)
    {
        Intent anIntent = new Intent(this, MainActivity.class);
        startActivity(anIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);

        editText = findViewById(R.id.editText);
        timeTextDesc = findViewById(R.id.timeTextDesc);

        sharpsOrFlats = findViewById(R.id.sharpsOrFlats);

        aSwitch = findViewById(R.id.switch2);

        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                {
                    editText.setVisibility(View.VISIBLE);
                    timeTextDesc.setVisibility(View.VISIBLE);
                }
                else
                {
                    editText.setVisibility(View.INVISIBLE);
                    timeTextDesc.setVisibility(View.INVISIBLE);
                    editText.setText("60");
                }
            }
        });
    }
}
