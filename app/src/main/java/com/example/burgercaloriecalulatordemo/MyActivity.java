package com.example.burgercaloriecalulatordemo;

import android.app.Activity;


import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class MyActivity extends Activity {

    //1. Declare UI objects to be referenced
    private RadioGroup pattyRG;
    private CheckBox prosciuttoCBX;
    private RadioGroup cheeseRG;
    private SeekBar sauceSBR;
    private TextView caloriesTV;

    //2. Declare variables for computing calories
    private Burger burger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);


        //3. Initialize UI Objects and variables
        burger = new Burger();
        initialize();

        //4. Register Change listeners
        registerChangeListener();
    }

    private void initialize() {
        //5. Get reference to each of the UI components
        pattyRG = (RadioGroup) findViewById(R.id.radioGroup1);
        prosciuttoCBX = (CheckBox) findViewById(R.id.checkBox1);
        cheeseRG = (RadioGroup) findViewById(R.id.radioGroup2);
        sauceSBR = (SeekBar) findViewById(R.id.seekBar1);
        caloriesTV = (TextView) findViewById(R.id.textView2);

        displayCalories();
    }

    private void registerChangeListener() {

        pattyRG.setOnCheckedChangeListener(foodListener);
        prosciuttoCBX.setOnClickListener(baconListener);
        cheeseRG.setOnCheckedChangeListener(foodListener);
        sauceSBR.setOnSeekBarChangeListener(sauceListener);
    }

    private OnCheckedChangeListener foodListener = new OnCheckedChangeListener() {
        public void onCheckedChanged(RadioGroup rbGroup, int radioId) {
            switch (radioId) {
                case 0x7f080002: //Beef Patty
                    burger.setPattyCalories(Burger.BEEF);
                    break;
                case 0x7f080003: //Lamb Patty
                    burger.setPattyCalories(Burger.LAMB);
                    break;
                case 0x7f080004: //Ostrich Patty
                    burger.setPattyCalories(Burger.OSTRICH);
                    break;
                case 0x7f080005: //Asiago cheese
                    burger.setPattyCalories(Burger.ASIAGO);
                    break;
                case 0x7f080006: //Creme Fraiche
                    burger.setPattyCalories(Burger.CREME_PRAICHE);
            }
            displayCalories();
        }
    };

    private OnClickListener baconListener = new OnClickListener() {
        public void onClick(View v) {
            if (((CheckBox) v).isChecked())
                burger.setProsciuttoCalories(Burger.PROSCIUTTO);
            else
                burger.clearProsciuttoCalories();

            displayCalories();
        }
    };

    private OnSeekBarChangeListener sauceListener = new
            OnSeekBarChangeListener() {
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            burger.setSauceCalories(seekBar.getProgress());
            displayCalories();

        }

        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        public void onStopTrackingTouSeekBar(SeekBar seekBar) {

        }
            };

    private void displayCalories() {

        // Construct an output string and display in the textview
        String calorieText = "Calories: " + burger.getTotalCalories();
        caloriesTV.setText(calorieText);
    }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            //Inflate the menu;
            getMenuInflater().inflate(R.menu.my, menu);
            return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here.
        int id = item.getItemId();
        if (id ==R.id.action_settings) {

            return true;
        }
        return super.onOptionsItemSelected(item);



    }

}