package com.hci.solon.breakout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 *  Written by Solon Pitts for CS6326.001, Final Project, starting Dec 1, 2017.
 *  NetID: sxp146230
 *
 *  GameOver.java: This activity displays the final state and score of the game,
 *  and allows the user to enter a name for their high score
 * */
public class GameOver extends AppCompatActivity {
    int score;
    boolean win;
    TextView gameText;
    Button retry;
    Button highscore;
    EditText name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        if (intent != null)
        {
            score = intent.getIntExtra("score",0);
            win = intent.getBooleanExtra("win", false);
        }

        gameText = (TextView) findViewById(R.id.gameOverText);
        gameText.setTextSize(30);
        gameText.setTextColor(Color.BLACK);
        retry = (Button) findViewById(R.id.retryButton);
        final Intent resultData = new Intent();

        /**returns the appropriate information when the retry button is pressed
         * */
        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(2,resultData);
                finish();
            }
        });
        highscore = (Button) findViewById(R.id.highScoreButton);
        /**calls the highscore method when the highscore button is pressed
         * */
        highscore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewHighScores();
            }
        });
        name = (EditText) findViewById(R.id.nameText);

        if(win)
            gameText.setText("Congratulations, You Win!\nFinal Score: " + score);
        else
            gameText.setText("Sorry, You Lose!\nFinal Score: " + score);
    }

    /**
     * Creates the intent to return to the start screen and view the highscores
     * */
    private void viewHighScores()
    {
        final Intent intent = new Intent();

        intent.putExtra("score",score);
        String s = name.getText().toString();
        if(s.equals(""))
            s="Anonymous";
        intent.putExtra("name", s);
        setResult(3,intent);
        finish();
    }
}
