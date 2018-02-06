package com.hci.solon.breakout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

/**
*  Written by Solon Pitts for CS6326.001, Final Project, starting Dec 1, 2017.
*  NetID: sxp146230
*
*  StartScreen.java: This is the first activity of the program, from which the user can start a game, view the highscores, or view help
* */
public class StartScreen extends AppCompatActivity {
    Button startButton, helpButton, highScoreButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        TextView title = (TextView)findViewById(R.id.title);
        title.setTextSize(40);
        title.setTextColor(Color.BLACK);

        startButton = (Button) findViewById(R.id.startButton);
        helpButton = (Button) findViewById(R.id.helpButton);
        highScoreButton = (Button) findViewById(R.id.highScoreButton);

        /**
         * listeners for each button
         * */
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                startGame();
            }
        });
        helpButton.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                help();
            }
        });
        highScoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewHighScores("",0);
            }
        });
    }

    /**
     * Creates the intent to start a game
     * */
    @SuppressLint("RestrictedApi")
    private void startGame()
    {
        Intent intent = new Intent(this, GameScreen.class);
        startActivityForResult(intent, 1, null);
    }

    /**
     * Creates the intent to view the help
     * */
    @SuppressLint("RestrictedApi")
    private void help()
    {
        Intent intent = new Intent(this, HelpScreen.class);
        startActivityForResult(intent, 1, null);
    }

    /**
     * Creates the intent to view the highscores
     * */
    @SuppressLint("RestrictedApi")
    private void viewHighScores(String name, int score)
    {
        Intent intent = new Intent(this, HighScores.class);
        intent.putExtra("score",score);
        intent.putExtra("name", name);
        startActivityForResult(intent, 2, null);
    }

    /**
     * Creates the intent to view the Game Over screen
     * */
    @SuppressLint("RestrictedApi")
    private void gameOver(boolean win, int score)
    {
        Intent intent = new Intent(this, GameOver.class);
        intent.putExtra("score",score);
        intent.putExtra("win", win);
        startActivityForResult(intent, 3, null);
    }

    /**
     * Contains the logic to receive intent results
     * */
    @Override
    protected void onActivityResult(int reqCode, int resultCode, Intent data)
    {
        super.onActivityResult(reqCode, resultCode, data);
        if (reqCode == 1) //returning from gamescreen or help
        {
            if(resultCode == 2){
                gameOver(data.getBooleanExtra("win",false), data.getIntExtra("score",0));
            }
        }
        else if (reqCode == 2) //returning from high scores
        {
            if(resultCode == 2) //newgame button was pressed
            {
                startGame();
            }
        }
        else if(reqCode == 3)//returning from gameOver screen
        {
            if(resultCode == 2)
                startGame();
            else if(resultCode == 3){
                viewHighScores(data.getStringExtra("name"), data.getIntExtra("score",0));
            }
        }
    }
}