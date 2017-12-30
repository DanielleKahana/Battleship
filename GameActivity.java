package com.example.ran.battleship;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.example.ran.battleship.Logic.Game;
import com.example.ran.battleship.Logic.Point;
import com.example.ran.battleship.Logic.Tile;


public class GameActivity extends AppCompatActivity {

    public final static String  THE_WINNER= "winner";
    public final static String  THE_DIFFICULTY= "difficulty";
    public final static String  THE_SCORE= "score";
    public final static String  THE_BUNDLE= "bundle";

    private final int COMPUTER_GRID_TILE_SIZE = 90;
    private final int PLAYER_GRID_TILE_SIZE = 60;

    private Game mGame;
    private GridView playerGrid,computerGrid;
    private int difficulty,state,score;
    private boolean doOnce=true, gameIsOver = false;
    private String the_winner;

    MediaPlayer mPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        ((ProgressBar)findViewById(R.id.progresss_bar)).setVisibility(View.INVISIBLE);

        Intent intent = getIntent();
        Bundle extras = intent.getBundleExtra(MainActivity.THE_BUNDLE);

        if (extras != null) {
            difficulty = extras.getInt(MainActivity.THE_DIFFICULTY);
        }

        mGame = new Game(difficulty);

        computerGrid = (GridView)findViewById(R.id.enemy_grid);
        playerGrid = (GridView)findViewById(R.id.player_grid);

        computerGrid.setAdapter(new TileAdapter(getApplicationContext(), mGame.getComputerBoard() , COMPUTER_GRID_TILE_SIZE));
        playerGrid.setAdapter(new TileAdapter(getApplicationContext(), mGame.getPlayerBoard(), PLAYER_GRID_TILE_SIZE));


        computerGrid.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (mGame.isPlayerTurn() && gameIsOver == false) {
                    int size = mGame.getPlayerBoard().getBoardSize();
                    int row = position / size;
                    int col = position % size;
                    Point point = new Point(row, col);
                    state = mGame.playTile(point, mGame.getComputerBoard());
                    playSound(state);
                    if (mGame.isPlayerTurn())
                        return;
                }

                    ((TileAdapter) computerGrid.getAdapter()).notifyDataSetChanged();

                    if (mGame.checkIfWin(mGame.getComputerBoard())){
                        the_winner = "player";
                        endTheGame(the_winner);
                    }

                if(!mGame.isPlayerTurn() && gameIsOver == false) {

                    if (doOnce) {
                        doOnce = false;

                        ((TextView)findViewById(R.id.player_turn)).setText(R.string.computer_turn);
                        ((ProgressBar)findViewById(R.id.progresss_bar)).setVisibility(View.VISIBLE);

                       // GridView cpuGridView = (GridView)findViewById(R.id.CPUBoardGridView);
                        //cpuGridView.setClickable(false);

                        Thread t = new Thread(new Runnable() {

                            @Override
                            public void run() {
                                try {
                                    Thread.sleep(1500);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        mGame.computerPlay();
                                        ((TileAdapter) playerGrid.getAdapter()).notifyDataSetChanged();
                                        ((ProgressBar) findViewById(R.id.progresss_bar)).setVisibility(View.INVISIBLE);

//                                        GridView cpuGridView = (GridView)findViewById(R.id.CPUBoardGridView);
//                                        cpuGridView.setClickable(true);


                                        if (mGame.checkIfWin(mGame.getPlayerBoard())) {
                                            the_winner = "computer";
                                            endTheGame(the_winner);
                                        }

                                        ((TextView) findViewById(R.id.player_turn)).setText(R.string.player_turn);
                                        doOnce=true;
                                    }
                                });
                            }
                        });
                        t.start();
                    }
                }

            }
        });

    }

    private void playSound(int state) {
        try{
            if (state == 1){
                if (mPlayer!= null){
                    mPlayer.release();
                    mPlayer = null;
                }
                mPlayer = MediaPlayer.create(GameActivity.this, R.raw.hit);
                mPlayer.start();
            }

            if (state == 0){
                if (mPlayer!= null){
                    mPlayer.release();
                    mPlayer = null;
                }
                mPlayer = MediaPlayer.create(GameActivity.this, R.raw.water);
                mPlayer.start();
            }

            if (state == 2){
                if (mPlayer!= null){
                    mPlayer.release();
                    mPlayer = null;
                }
                mPlayer = MediaPlayer.create(GameActivity.this, R.raw.sunk);
                mPlayer.start();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private void endTheGame(String the_winner){
        gameIsOver = true;
        Intent intent = new Intent(GameActivity.this, EndGameActivity.class);
        Bundle mBundle = new Bundle();

        mBundle.putInt(THE_SCORE,score);
        mBundle.putString(THE_WINNER, the_winner);
        mBundle.putInt(THE_DIFFICULTY, difficulty);

        intent.putExtra(THE_BUNDLE , mBundle);
        startActivity(intent);

        finish();

    }

}