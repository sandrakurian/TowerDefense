package com.example.milestonetwo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Config extends AppCompatActivity {

    //private static Config c1;
    private String name;
    private String difficultyLevel = "";
    private static int money;
    private static int mHealth;
    private static boolean difficultyChosen;
    private static boolean confirmConfigs;

    private static boolean firstGameScreenStart;

    private EditText nameInput;

    private Button easyButton;
    private Button mediumButton;
    private Button hardButton;

    private static int difficulty;

    private Button playButton;

    private TextView alertPopup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);
        //getSupportActionBar().hide();

        nameInput = (EditText) findViewById(R.id.name_input);

        easyButton = (Button) findViewById(R.id.easy_button);
        mediumButton = (Button) findViewById(R.id.medium_button);
        hardButton = (Button) findViewById(R.id.hard_button);


        playButton = (Button) findViewById(R.id.play_button);
        alertPopup = (TextView) findViewById(R.id.alertPopup);

        //easy button
        easyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                easyButton.setBackgroundColor(Color.DKGRAY);
                mediumButton.setBackgroundColor(Color.parseColor("#808080"));
                hardButton.setBackgroundColor(Color.parseColor("#808080"));

                setConfigButtons(6, 100);
                difficultyLevel = "EASY";
                difficulty = 0;
            }
        });

        //medium button
        mediumButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                easyButton.setBackgroundColor(Color.parseColor("#808080"));
                mediumButton.setBackgroundColor(Color.DKGRAY);
                hardButton.setBackgroundColor(Color.parseColor("#808080"));

                setConfigButtons(4, 75);
                difficultyLevel = "MEDIUM";
                difficulty = 1;
            }
        });

        //hard button
        hardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                easyButton.setBackgroundColor(Color.parseColor("#808080"));
                mediumButton.setBackgroundColor(Color.parseColor("#808080"));
                hardButton.setBackgroundColor(Color.DKGRAY);

                setConfigButtons(2, 50);
                difficultyLevel = "HARD";
                difficulty = 2;

            }
        });

        //play button
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = nameInput.getText().toString();

                if (!difficultyChosen || name.trim().isEmpty()) {
                    if (!difficultyChosen) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(Config.this);

                        builder.setCancelable(true);
                        builder.setTitle("You haven't picked a difficulty level");
                        builder.setMessage("Please pick a difficulty level");
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                alertPopup.setVisibility(View.VISIBLE);
                            }
                        });
                        builder.show();
                    }
                    if (name.trim().isEmpty()) {

                        AlertDialog.Builder builder = new AlertDialog.Builder(Config.this);

                        builder.setCancelable(true);
                        builder.setTitle("You entered an invalid name");
                        builder.setMessage("Please enter another name");
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                alertPopup.setVisibility(View.VISIBLE);
                            }
                        });
                        builder.show();
                    }
                } else {

                    if (!confirmConfigs) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(Config.this);

                        builder.setCancelable(true);
                        builder.setTitle("Please confirm yer name and difficulty level");
                        builder.setMessage("Pirate name: " + name + "\nDifficulty level: "
                                + difficultyLevel);
                        builder.setNegativeButton("Make changes",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        alertPopup.setVisibility(View.VISIBLE);
                                    }
                                });
                        builder.setPositiveButton("Continue to game",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        alertPopup.setVisibility(View.VISIBLE);
                                        firstGameScreenStart = true;
                                        Game.setFirstGameScreenStartBool(true);
                                        Intent j = new Intent(getApplicationContext(), Game.class);
                                        startActivity(j);
                                    }
                                });
                        builder.show();
                    }

                }

            }
        });
    }

    public static void setConfigButtons(int moneyConfig, int healthConfig) {
        money = moneyConfig;
        mHealth = healthConfig;
        difficultyChosen = true;
    }
    public static String configError(String name, Boolean difficultyChosen) {
        if (!difficultyChosen || name.trim().isEmpty()) {
            if (!difficultyChosen) {
                return "noDifficulty";
            }
            if (name.trim().isEmpty()) {
                return null;
            }
        } else {
            if (!confirmConfigs) {
                return name;
            }
        }
        return "Error";
    }


    // getters
    protected static int getMoney() {
        return money;
    }

    protected static int getHealth() {
        return mHealth;
    }

    public static int difficulty() {
        return difficulty;
    }

    //setters
    public static void setDifficulty(int i) {
        difficulty = i;
    }



    protected static boolean getFirstGameScreenStart() {
        return firstGameScreenStart;
    }
    protected static void setFirstGameScreenStart() {
        firstGameScreenStart = true;
    }

}



















