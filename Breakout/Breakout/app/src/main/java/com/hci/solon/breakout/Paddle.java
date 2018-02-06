package com.hci.solon.breakout;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 *  Written by Solon Pitts for CS6326.001, Final Project, starting Dec 1, 2017.
 *  NetID: sxp146230
 *
 *  Paddle.java: This is the Paddle object, which contains speed and position variables and draws itself
 * */
public class Paddle {
    int width, height, xpos, ypos, velocity;
    Paint pLightOrangeFill;

    public Paddle(int x, int y){
        pLightOrangeFill = new Paint();
        pLightOrangeFill.setColor(Color.parseColor("#ffb74d"));
        pLightOrangeFill.setStyle(Paint.Style.FILL);
        width = 150;
        height = 50;
        ypos = y-150;
        xpos = x/2;
        velocity = 0;
    }

    //draw the paddle
    public void drawPaddle(Canvas canvas) {
        canvas.drawRect(xpos-(width/2),ypos-(height/2),xpos+(width/2),ypos+(height/2),pLightOrangeFill);
    }

    //shift the x position
    public void shift(int x){
        xpos += x;
    }

}
