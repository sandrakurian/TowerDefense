package com.example.milestonetwo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Welcome extends AppCompatActivity {
    private Button startButton;
    private Button exitButton;

    private TextView alertPopupWelcome;

    private boolean wantToExit = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        //getSupportActionBar().hide();

        startButton = (Button) findViewById(R.id.startButton);
        exitButton = (Button) findViewById(R.id.exitButton);
        alertPopupWelcome = (TextView) findViewById(R.id.alertPopupWelcome);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openConfig();
            }
        });


        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!wantToExit) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Welcome.this);

                    builder.setCancelable(true);
                    builder.setTitle("Confirm Exit");
                    builder.setMessage("Are you sure you want to exit the game?");
                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            alertPopupWelcome.setVisibility(View.VISIBLE);
                        }
                    });
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            wantToExit = true;
                            moveTaskToBack(true);
                            alertPopupWelcome.setVisibility(View.VISIBLE);
                            android.os.Process.killProcess(android.os.Process.myPid());
                            System.exit(1);
                        }
                    });
                    builder.show();
                }
            }
        });
    }

    public void openConfig() {
        Intent intent = new Intent(this, Config.class);
        startActivity(intent);
    }


}