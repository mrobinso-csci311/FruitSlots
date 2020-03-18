package com.example.fruitslots;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView pointNum;
    private GridLayout grid;
    private SeekBar speedBar;
    private Button stopButton;
    private Button rulesButton;
    private Drawable cherry;
    private Drawable grape;
    private Drawable pear;
    private Drawable strawberry;
    private Boolean on;
    private ImageView[] imageViews;
    private Handler gameHandler;
    public updateSlot1 update1;
    public updateSlot2 update2;
    public updateSlot3 update3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pointNum = findViewById(R.id.pointNum);
        grid = findViewById(R.id.grid);
        speedBar = findViewById(R.id.speedBar);
        stopButton = findViewById(R.id.stopButton);
        rulesButton = findViewById(R.id.rulesButton);
        cherry = getDrawable(R.drawable.cherry);
        grape = getDrawable(R.drawable.grape);
        pear = getDrawable(R.drawable.pear);
        strawberry = getDrawable(R.drawable.strawberry);
        imageViews = new ImageView[3];
        gameHandler = new Handler();
        imageViews[0] = findViewById(R.id.slot1);
        imageViews[1] = findViewById(R.id.slot2);
        imageViews[2] = findViewById(R.id.slot3);
    }

    public void rulesPressed(View v){
        Intent i = new Intent(this, FruitSlotsHelp.class);
        startActivity(i);
    }

    public void startPressed(View v) {
        if (on) {
            pointNum.setText("STOP");
            on = false;
            gameHandler.removeCallbacks(update1);
            gameHandler.removeCallbacks(update2);
            gameHandler.removeCallbacks(update3);
        } else {
            pointNum.setText("START");
            on = true;
            gameHandler.postDelayed(update1, 1000);
            gameHandler.postDelayed(update2, 1000);
            gameHandler.postDelayed(update3, 1000);
        }
    }

    private class updateSlot1 implements Runnable {
        public void run() {
            if (imageViews[0].getDrawable() == cherry){
                imageViews[0].setImageDrawable(grape);
            }else if (imageViews[0].getDrawable() == grape){
                imageViews[0].setImageDrawable(pear);
            }else if (imageViews[0].getDrawable() == pear){
                imageViews[0].setImageDrawable(strawberry);
            }else{
                imageViews[0].setImageDrawable(cherry);
            }
        }
    }

    private class updateSlot2 implements Runnable {
        public void run() {
            if (imageViews[1].getDrawable() == cherry){
                imageViews[1].setImageDrawable(grape);
            }else if (imageViews[1].getDrawable() == grape){
                imageViews[1].setImageDrawable(pear);
            }else if (imageViews[1].getDrawable() == pear){
                imageViews[1].setImageDrawable(strawberry);
            }else{
                imageViews[1].setImageDrawable(cherry);
            }
        }
    }

    private class updateSlot3 implements Runnable {
        public void run() {
            if (imageViews[2].getDrawable() == cherry){
                imageViews[2].setImageDrawable(grape);
            }else if (imageViews[2].getDrawable() == grape){
                imageViews[2].setImageDrawable(pear);
            }else if (imageViews[2].getDrawable() == pear){
                imageViews[2].setImageDrawable(strawberry);
            }else{
                imageViews[2].setImageDrawable(cherry);
            }
        }
    }
}
