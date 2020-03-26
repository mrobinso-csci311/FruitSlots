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
    private Button rollButton;
    private Button rulesButton;
    private Drawable cherry;
    private Drawable grape;
    private Drawable pear;
    private Drawable strawberry;
    public int point;
    private boolean on;
    private ImageView[] imageViews;
    private Handler timeHandler;
    public updateSlot1 update1;
    public updateSlot2 update2;
    public updateSlot3 update3;
    public updatePoint update4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pointNum = findViewById(R.id.pointNum);
        grid = findViewById(R.id.grid);
        speedBar = findViewById(R.id.speedBar);
        rollButton = findViewById(R.id.rollButton);
        rulesButton = findViewById(R.id.rulesButton);
        cherry = getDrawable(R.drawable.cherry);
        grape = getDrawable(R.drawable.grape);
        pear = getDrawable(R.drawable.pear);
        strawberry = getDrawable(R.drawable.strawberry);
        imageViews = new ImageView[3];
        timeHandler = new Handler();
        imageViews[0] = findViewById(R.id.slot1);
        imageViews[1] = findViewById(R.id.slot2);
        imageViews[2] = findViewById(R.id.slot3);
        update1 = new updateSlot1();
        update2 = new updateSlot2();
        update3 = new updateSlot3();
        update4 = new updatePoint();
        if (savedInstanceState == null){
            point = 0;
            on = false;
        }else{
            point = savedInstanceState.getInt("POINT");
            pointNum.setText(""+point);
            on = savedInstanceState.getBoolean("ON");
            if (on){
                rollButton.setText("STOP");
            }
            imageViews[0].setImageDrawable(intToDrawable(savedInstanceState.getInt("FRUIT1")));
            imageViews[1].setImageDrawable(intToDrawable(savedInstanceState.getInt("FRUIT2")));
            imageViews[2].setImageDrawable(intToDrawable(savedInstanceState.getInt("FRUIT3")));
        }
    }

    public void onPause(){
        super.onPause();
        timeHandler.removeCallbacks(update1);
        timeHandler.removeCallbacks(update2);
        timeHandler.removeCallbacks(update3);
    }

    public void onResume(){
        super.onResume();
        if (on){
            timeHandler.postDelayed(update1,1000);
            timeHandler.postDelayed(update2,1000);
            timeHandler.postDelayed(update3,1000);
        }
    }

    public void onSaveInstanceState(Bundle bundle){
        super.onSaveInstanceState(bundle);
        bundle.putInt("POINT",point);
        bundle.putBoolean("ON",on);
        bundle.putInt("FRUIT1",drawableToInt(imageViews[0].getDrawable()));
        bundle.putInt("FRUIT2",drawableToInt(imageViews[1].getDrawable()));
        bundle.putInt("FRUIT3",drawableToInt(imageViews[2].getDrawable()));
    }

    public int drawableToInt(Drawable d){
        if (d == cherry){
            return R.drawable.cherry;
        }else if (d == grape){
            return R.drawable.grape;
        }else if (d == pear){
            return R.drawable.pear;
        }else{
            return R.drawable.strawberry;
        }
    }

    public Drawable intToDrawable(int i){
        switch(i){
            case R.drawable.strawberry: return strawberry;
            case R.drawable.pear: return pear;
            case R.drawable.cherry: return cherry;
            default: return grape;
        }
    }

    public void rulesPressed(View v){
        Intent i = new Intent(this, FruitSlotsHelp.class);
        startActivity(i);
    }

    public void rollPressed(View v) {
        if (on) {
            on = false;
            rollButton.setText("START");
            timeHandler.removeCallbacks(update1);
            timeHandler.removeCallbacks(update2);
            timeHandler.removeCallbacks(update3);
            timeHandler.postDelayed(update4,1000);

        } else {
            on = true;
            rollButton.setText("STOP");
            timeHandler.postDelayed(update1, 1000);
            timeHandler.postDelayed(update2, 1000);
            timeHandler.postDelayed(update3, 1000);
            timeHandler.removeCallbacks(update4);
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
            timeHandler.postDelayed(update1,1500-speedBar.getProgress());
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
            timeHandler.postDelayed(update2, 1000-speedBar.getProgress());
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
            timeHandler.postDelayed(update3, 750-speedBar.getProgress());
        }
    }

    private class updatePoint implements Runnable {
        public void run() {
            if (imageViews[0].getDrawable() == imageViews[1].getDrawable() && imageViews[0].getDrawable() == imageViews[2].getDrawable()){
                point = point + 2;
                pointNum.setText(point+"");
            }else if (imageViews[0].getDrawable() == imageViews[1].getDrawable() || imageViews[0].getDrawable() == imageViews[2].getDrawable() || imageViews[1].getDrawable() == imageViews[2].getDrawable()){
                point++;
                pointNum.setText(point+"");
            }
        }
    }
}
