package com.example.ran.battleship;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.NumberPicker;

public class MainActivity extends AppCompatActivity {
    public static final String[] DIFFICULTY= {"Easy","Medium", "Hard"};
    public final static String  THE_BUNDLE= "bundle";
    public final static String  THE_DIFFICULTY= "difficulty";

    private NumberPicker picker = null;
    private Button startButton;
    private ImageButton instructionButton;
    private int theDifficulty;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        picker = (NumberPicker) findViewById(R.id.difficultyPickerPicker);
        picker.setMinValue(0);
        picker.setMaxValue(DIFFICULTY.length - 1);
        picker.setDisplayedValues(DIFFICULTY);

        startButton = (Button) findViewById(R.id.startButton);
        instructionButton = (ImageButton)findViewById(R.id.instructions_button);


    }



    public void startGame(View view) {
        theDifficulty = picker.getValue();
        Intent intent = new Intent(MainActivity.this, GameActivity.class);
        Bundle mBundle = new Bundle();
        mBundle.putInt(THE_DIFFICULTY, theDifficulty);
        intent.putExtra(THE_BUNDLE , mBundle);
        startActivity(intent);
    }

    public void goToInstructions(View view){
        Intent intent = new Intent(MainActivity.this, InstructionsActivity.class);
        startActivity(intent);
    }

}




