package com.hci.solon.breakout;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

/**
 *  Written by Solon Pitts for CS6326.001, Final Project, starting Dec 1, 2017.
 *  NetID: sxp146230
 *
 *  GameView.java: This view contains the canvas which the game is played on, as well as the touch listener and sensor
 *  listener functionality
 * */
public class GameView extends View implements SensorEventListener{
    Paint pBlackFill,  pRedFill, pYellowFill, pLightOrangeFill, pDarkOrangeFill, pLightBlueFill;
    Paint pBlackStroke, pWhiteStroke;
    int width, height;
    int lives, score;
    Context context;
    Paddle paddle;
    Ball ball;
    ArrayList<Block> blocks;
    Sensor sensor;
    SensorManager sm;

    public GameView(Context con) {
        super(con);
        context = con;
        lives = 3;
        score = 0;
        setPaints();
        makeBlocks();
        sm=(SensorManager)context.getSystemService(Context.SENSOR_SERVICE);
        sensor=sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sm.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);

        //The listener shifts the paddles position based on where user touches
        setOnTouchListener(new OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int startPos = paddle.xpos;
                switch(motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN: //set the position of the touch to the start pos and say the paddle is stationary
                        startPos = (int)motionEvent.getX();
                        paddle.velocity = 0;
                        break;
                    case MotionEvent.ACTION_MOVE: //move the paddle
                        int shift = (int)motionEvent.getX() - startPos;
                        paddle.velocity = shift;
                        paddle.shift(shift);
                        startPos = (int)motionEvent.getX();
                        break;
                    case MotionEvent.ACTION_UP: //the paddle is stationary
                        paddle.velocity = 0;
                        break;
                }
                return true;
            }
        });
    }

    /**
    / Calls the objects draw methods and detects collisions
     */
    @Override
    public void onDraw(Canvas canvas) {
        width = this.getWidth();
        height = this.getHeight();

        //top blue bar
        canvas.drawRect(0,0,width,95,pLightBlueFill);
        canvas.drawText("Lives: " + lives,5,65,pWhiteStroke);
        canvas.drawText("Score: " + score,width-250,65,pWhiteStroke);
        //bottom blue bar
        canvas.drawRect(0,height-95,width,height,pLightBlueFill);

        if(paddle == null) {
            paddle = new Paddle(width, height);
            ball = new Ball(width, height);
        }
        paddle.drawPaddle(canvas);
        ball.drawBall(canvas);
        for(int index = 0; index < blocks.size(); index++) //detect if the ball hits any of the blocks
        {
            Block b = blocks.get(index);
            if(ball.ypos - ball.radius <= b.ypos && ((ball.xpos - ball.radius <= b.xpos+(b.width) && ball.xpos - ball.radius >= b.xpos) || (ball.xpos + ball.radius <= b.xpos+(b.width) && ball.xpos + ball.radius >= b.xpos))){
                blocks.remove(b);
                score+=b.score;
                ball.yspd = (-ball.yspd) + (score/5); //increase speed by 1/5rd of the score
            }
            else
                b.drawBlock(canvas);
        }
        //if no blocks left, you win
        if(blocks.size() == 0)
            gameOver(true);
        //ball collides with paddle
        if(ball.ypos + ball.radius >= paddle.ypos && ((ball.xpos - ball.radius <= paddle.xpos+(paddle.width/2) && ball.xpos - ball.radius >= paddle.xpos-(paddle.width/2)) || (ball.xpos + ball.radius <= paddle.xpos+(paddle.width/2) && ball.xpos + ball.radius >= paddle.xpos-(paddle.width/2)))){
            ball.yspd = - ball.yspd;
            ball.xspd = paddle.velocity;
        }
        else if(ball.ypos <= 95) //ball hits top bar
        {
            ball.yspd = - ball.yspd;
        }
        else if(ball.ypos >= height - 95){ //ball hits bottom, bar, lose a life and reset positions
            ball.xpos = width/2;
            ball.ypos = height - 600;
            ball.xspd = 0;
            lives--;
            if(lives==0)
                gameOver(false);
        }
        else if(ball.xpos <= 0 || ball.xpos >= width) //ball hits sides
        {
            ball.xspd = - ball.xspd;
        }
        invalidate();
    }

    /**
     * unregisters the listener and calls the Game Screen gameOver method
     * */
    private void gameOver(boolean win)
    {
        GameScreen game = (GameScreen)context;
        sm.unregisterListener(this);
        game.gameOver(score,win);
    }

    /**
     * If the sensor detects significant horizontal acceleration, shift the blocks
     * */
    public void onSensorChanged(SensorEvent e) {
        if (e.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            int xAccel = (int)e.values[0];
            //int yAccel = ()
            if(xAccel > 2 || xAccel < -2){
                tryShiftBlocks(-xAccel);
            }
        }
    }

    /**
     * Attempts to shift all the blocks in the direction of the acceleration
     * */
    private void tryShiftBlocks(int xAccel){
        if(xAccel>0)
            xAccel = 5;
        if(xAccel<0)
            xAccel = -5;
        for(int index = 0; index < blocks.size(); index++){
            Block cur = blocks.get(index);
            Block prev = cur;
            if(index!=0)
                prev = blocks.get(index-1);
            Block next = cur;
            if(index!=blocks.size()-1)
                next = blocks.get(index+1);
            if(xAccel < 0 && (index == 0 || cur.ypos != prev.ypos || (cur.xpos > prev.xpos + prev.width)))//to the right of the left one and on the same level, moving left
            {
                if(index == 0 || cur.ypos != prev.ypos)//far left
                    if(cur.xpos > 0-xAccel)
                        cur.xpos += xAccel; //accel is neg
                    else
                        cur.xpos = 0;
                else if(cur.xpos > prev.xpos + prev.width - xAccel)
                    cur.xpos += xAccel; //accel is neg
                else
                    cur.xpos = prev.xpos + prev.width;
            }
            else if(xAccel > 0 && (index == blocks.size()-1 || cur.ypos != next.ypos || (cur.xpos + cur.width < next.xpos)))//to the left of the right one and on the same level, moving right)
            {
                if(index == blocks.size()-1 || cur.ypos != next.ypos)
                    if(cur.xpos+cur.width < width-xAccel)
                        cur.xpos += xAccel; //accel is pos
                    else
                        cur.xpos = width - cur.width;
                else if(cur.xpos + cur.width < next.xpos - xAccel)
                    cur.xpos += xAccel; //accel is pos
                else
                    cur.xpos = next.xpos - cur.width;
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    /**
     * Creates the Block arraylist
     * */
    private void makeBlocks(){
        blocks = new ArrayList<Block>();
        Paint paint = pRedFill;

        for(int row = 0; row < 3; row++){
            if(row==1)
                paint = pYellowFill;
            else if(row==2)
                paint = pDarkOrangeFill;
            for(int column = 0; column < 13; column++) {
                blocks.add(new Block(column * 55 + 5, row * 30 + 100, paint, 3-row));
            }
        }
    }

    /**
     * Creates the various paints
     * */
    private void setPaints(){
        pBlackFill = new Paint();
        pBlackFill.setColor(Color.BLACK);
        pBlackFill.setStyle(Paint.Style.FILL);

        pRedFill = new Paint();
        pRedFill.setColor(Color.RED);
        pRedFill.setStyle(Paint.Style.FILL);

        pYellowFill = new Paint();
        pYellowFill.setColor(Color.YELLOW);
        pYellowFill.setStyle(Paint.Style.FILL);

        pLightOrangeFill = new Paint();
        pLightOrangeFill.setColor(Color.parseColor("#ffb74d"));
        pLightOrangeFill.setStyle(Paint.Style.FILL);

        pLightBlueFill = new Paint();
        pLightBlueFill.setColor(Color.parseColor("#4dd0e1"));
        pLightBlueFill.setStyle(Paint.Style.FILL);

        pDarkOrangeFill = new Paint();
        pDarkOrangeFill.setColor(Color.parseColor("#c88719"));
        pDarkOrangeFill.setStyle(Paint.Style.FILL);

        pBlackStroke = new Paint();
        pBlackStroke.setColor(Color.BLACK);
        pBlackStroke.setStyle(Paint.Style.STROKE);
        pBlackStroke.setStrokeWidth(5);
        pBlackStroke.setTextSize(60);

        pWhiteStroke = new Paint();
        pWhiteStroke.setColor(Color.WHITE);
        pWhiteStroke.setStyle(Paint.Style.STROKE);
        pWhiteStroke.setStrokeWidth(5);
        pWhiteStroke.setTextSize(60);
    }
}
