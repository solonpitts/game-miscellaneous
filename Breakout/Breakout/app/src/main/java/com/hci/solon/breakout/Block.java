package com.hci.solon.breakout;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 *  Written by Solon Pitts for CS6326.001, Final Project, starting Dec 1, 2017.
 *  NetID: sxp146230
 *
 *  Block.java: This is the Block object, which contains score and position variables and draws itself
 * */
public class Block {
    int width, height, xpos, ypos, score;
    Paint paint;

    public Block(int x, int y, Paint p, int scr){
        paint = p;
        width = 50;
        height = 25;
        ypos = y;
        xpos = x;
        score = scr;
    }

    public void drawBlock(Canvas canvas) {
        canvas.drawRect(xpos,ypos,xpos+(width),ypos+(height),paint);
    }

    public void shift(int x){
        xpos += x;
    }

    public int getScore(){
        return score;
    }
}
