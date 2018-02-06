package com.hci.solon.breakout;

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
 *  HelpScreen.java: This activity contains the help info
 * */
public class HelpScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Help");
        setSupportActionBar(toolbar);
    }

}
