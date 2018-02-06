package com.hci.solon.breakout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

/**
 *  Written by Solon Pitts for CS6326.001, Final Project, starting Dec 1, 2017.
 *  NetID: sxp146230
 *
 *  GameScreen.java: This is the activity which creates the GameView
 * */
public class GameScreen extends AppCompatActivity {
    GameView gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        gameView = new GameView(this);

        setContentView(gameView);
    }

    /**
     * Creates the intent to return to the start screen
     * */
    @SuppressLint("RestrictedApi")
    public void gameOver(int score, boolean win)
    {
        final Intent intent = new Intent();
        intent.putExtra("score",score);
        intent.putExtra("win", win);
        setResult(2,intent);
        finish();
    }

    @Override
    protected void onDestroy(){
        gameView.sm.unregisterListener(gameView);
        super.onDestroy();
    }

}

