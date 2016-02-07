package com.example.nadeche.tamagotchi_oldschool_game;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    //Properties
    TextView First_number;
    TextView Second_number;
    TextView Win_or_not;
    Button Button_higher;
    Button Button_lower;
    Button Try_again;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Set properties to widgets
        First_number = (TextView) findViewById(R.id.first_number);
        Second_number = (TextView) findViewById(R.id.second_number);
        Win_or_not = (TextView) findViewById(R.id.win_or_not);
        Button_higher = (Button) findViewById(R.id.button_higher);
        Button_lower = (Button) findViewById(R.id.button_lower);
        Try_again = (Button) findViewById(R.id.try_again);

        //Initialize game
        initialize();

        //Listen to the user actions
        Button_higher.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                processPlayersChoice(true);

            }
        });
        Button_lower.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                processPlayersChoice(false);
            }
        });

        //If the user wants to play again, reinitialize game
        Try_again.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                initialize();
            }
        });
    }

    //Method to initialize game
    private void initialize(){

        //Hide some widgets
        Try_again.setVisibility(View.GONE);
        Second_number.setVisibility(View.GONE);
        Win_or_not.setVisibility(View.GONE);
        //Show some widgets
        Button_higher.setVisibility(View.VISIBLE);
        Button_lower.setVisibility(View.VISIBLE);

        //(Re)generate first number
        showRandomNumber(First_number);
    }

    //Method for generating rand.number (1 - 10) and show on screen
    private void showRandomNumber (TextView view){

        //Generate random number between 1 - 10
        Random rand = new Random();
        int rand_num = rand.nextInt(10) + 1;

        //Display generated number
        view.setText(Integer.toString(rand_num));

    }

    //Method to determine if the user has guessed correctly and display so on screen
    private void processPlayersChoice (boolean choice){

        //Display second number
        showRandomNumber(Second_number);

        //Set variables
        int firstNumber = Integer.parseInt(First_number.getText().toString());
        int secondNumber = Integer.parseInt(Second_number.getText().toString());
        String message;
        int color;

        //Check if the player has won or lost
        if ((firstNumber < secondNumber && choice) || (firstNumber > secondNumber && !choice)) {
            //Win
            message = "You won!!!";
            color = Color.GREEN;
        } else {
            //Lose
            message = "You lose!!!";
            color = Color.RED;
        }

        //Display win or lose message
        Win_or_not.setText(message);
        Win_or_not.setTextColor(color);
        Win_or_not.setVisibility(View.VISIBLE);
        //Display second number and let the user try again
        Second_number.setVisibility(View.VISIBLE);
        Try_again.setVisibility(View.VISIBLE);
        //Hide the play buttons
        Button_higher.setVisibility(View.GONE);
        Button_lower.setVisibility(View.GONE);
    }
}
