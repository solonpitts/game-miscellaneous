package com.hci.solon.breakout;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 *  Written by Solon Pitts for CS6326.001, Final Project, starting Dec 1, 2017.
 *  NetID: sxp146230
 *
 *  Ball.java: This is the Ball object, which contains speed and position variables and draws itself
 * */
public class Ball {
    int radius, xpos, ypos, yspd, xspd;
    Paint paint;

    public Ball(int x, int y){
        paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);
        radius = 10;
        ypos = y-250;
        xpos = x/2;
        xspd = 0;
        yspd = 6;
    }

    //draw the ball
    public void drawBall(Canvas canvas) {
        xpos+=xspd;
        ypos+=yspd;
        canvas.drawCircle(xpos,ypos,radius,paint);
    }
}
