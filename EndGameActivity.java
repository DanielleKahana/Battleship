package com.example.ran.battleship;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.ran.battleship.Logic.Game;

public class EndGameActivity extends AppCompatActivity {

    private String winningPlayer;
    private int difficulty,score;

    private ImageView imageViewTop;
    private ImageView imageViewDown;
    MediaPlayer mPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game);

        imageViewTop =  (ImageView)findViewById(R.id.endGamePictureTop);
        imageViewDown = (ImageView)findViewById(R.id.endGamePictureDown);
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.endGame);

        Bundle extras = getIntent().getBundleExtra(GameActivity.THE_BUNDLE);

        if (extras!= null){
            difficulty = extras.getInt(GameActivity.THE_DIFFICULTY);
            winningPlayer = extras.getString(GameActivity.THE_WINNER);
            score = extras.getInt(GameActivity.THE_SCORE);
        }


        switch (winningPlayer){
            case "player":
                relativeLayout.setBackgroundResource(R.drawable.blue_stars);
                imageViewTop.setImageResource(R.drawable.win_text);
                imageViewDown.setImageResource(R.drawable.tressure);

                try {
                    if (mPlayer != null) {
                        mPlayer.release();
                        mPlayer = null;
                    }

                    mPlayer = MediaPlayer.create(EndGameActivity.this, R.raw.win);
                    mPlayer.start();

                } catch (Exception e){
                        e.printStackTrace();
                    }
                break;

            case "computer":
                relativeLayout.setBackgroundResource(R.drawable.loss);
                imageViewTop.setImageResource(R.drawable.failed);
                imageViewDown.setImageResource(R.drawable.lose);
                try {
                    if (mPlayer != null) {
                        mPlayer.release();
                        mPlayer = null;
                    }

                    mPlayer = MediaPlayer.create(EndGameActivity.this, R.raw.lost);
                    mPlayer.start();

                } catch (Exception e){
                    e.printStackTrace();
                }
                break;
        }
    }

    public void rematchGame(View view){
        Intent intent = new Intent(EndGameActivity.this, GameActivity.class);
        intent.putExtra(Intent.EXTRA_INDEX, difficulty);
        startActivity(intent);


    }

    public void goToMainMenu(View view){
        Intent intent = new Intent(EndGameActivity.this, MainActivity.class);
        startActivity(intent);
        finish();

    }
}
