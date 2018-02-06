package com.hci.solon.breakout;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *  Written by Solon Pitts for CS6326.001, Final Project, starting Dec 1, 2017.
 *  NetID: sxp146230
 *
 *  HighScores.java: This activity reads the high scores from "scores.txt" and adds a new one if needed
 * */
public class HighScores extends AppCompatActivity {
    private int newScore;
    private String name;
    //used in the file reader method
    private String line;
    //list of contacts in single string form to be used in listview
    private ArrayList<String> items = new ArrayList<String>();
    //adapter for controlling the listview
    private ArrayAdapter adapter;
    private Button newGameButton;
    private Button mainMenuButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        if (intent != null)
        {
            newScore = intent.getIntExtra("score",0);
            name = intent.getStringExtra("name");
        }

        setContentView(R.layout.activity_high_scores);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("High Scores");
        newGameButton = (Button) findViewById(R.id.newGameButton);
        mainMenuButton = (Button) findViewById(R.id.mainMenuButton);

        final ListView listView = (ListView) findViewById(R.id.listView);
        items = new ArrayList<String>();


        readFromFile();
        if(newScore!=0)
        {
            items.add(Integer.toString(newScore)+" - "+name);
        }

        adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, items);
        listView.setAdapter(adapter);
        sortList();

        final Intent resultData = new Intent();

        /**returns the appropriate information when the newGame button is pressed
         * */
        newGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(2,resultData);
                finish();
            }
        });

        /**Returns to the main menu when the button is pressed
         * */
        mainMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(1,resultData);
                finish();
            }
        });

    }

    /**Sorts the listview by score
     * */
    private void sortList() {
        //Collections.sort(items);
        adapter.sort(new Comparator<String>() {
            @Override
            public int compare(String first, String second) {
                String[] strs = first.split(" ");
                int one = Integer.parseInt(strs[0]);
                strs = second.split(" ");
                int two = Integer.parseInt(strs[0]);
                return two-one;
            }
        });
    }

    /**Reads all the contacts in from the file, "scores.txt"
     * */
    public void readFromFile()
    {
        try {
            InputStream inputStream = this.openFileInput("scores.txt");

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                line = bufferedReader.readLine();
                while ( line != null ) {
                    items.add(line);
                    line = bufferedReader.readLine();
                }

                inputStream.close();
            }
        }catch (FileNotFoundException e) {
            Log.e("Exception", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("Exception", "Can not read file: " + e.toString());
        }
    }

    /**Saves all the contacts to the file "scores.txt"
     * */
    public void writeToFile()
    {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(this.openFileOutput("scores.txt", Context.MODE_PRIVATE));
            for(String s: items)
            {
                outputStreamWriter.write(s + "\n");
                //if(items.indexOf(s)<items.size()-1)
                //outputStreamWriter.write("\n");
            }
            outputStreamWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**Calls writeToFile() when the activity pauses in order to save
     * */
    @Override
    protected void onPause() {
        writeToFile();
        super.onPause();
    }


}
