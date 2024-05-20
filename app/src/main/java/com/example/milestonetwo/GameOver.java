package com.example.milestonetwo;
import android.content.Intent;
import android.os.Bundle;
import android.content.DialogInterface;
import android.os.Process;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class GameOver extends AppCompatActivity {

    private Button yesbutton;
    private Button nobutton;
    private TextView moneyspenttext;
    private TextView towersboughttext;
    private TextView enemieskilledtext;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameover);


        yesbutton = (Button) findViewById(R.id.yesbutton);
        nobutton = (Button) findViewById(R.id.nobutton);
        moneyspenttext = (TextView) findViewById(R.id.moneyspenttext);
        towersboughttext = (TextView) findViewById(R.id.towersboughttext);
        enemieskilledtext = (TextView) findViewById(R.id.enemieskilledtext);

        setStats();


        yesbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Config.setFirstGameScreenStart();
                Intent j = new Intent(getApplicationContext(), Welcome.class);
                startActivity(j);
            }
        });

        nobutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Game.setWantToExitGame(true);
                Game.setFirstGameScreenStartBool(true);
                moveTaskToBack(true);
                Process.killProcess(Process.myPid());
                System.exit(1);

            }
        });

    }

    public void setStats() {
        moneyspenttext.setText("" + Shop.getMoneySpentVal());
        towersboughttext.setText("" + TowerList.getTowersBoughtVal()/2);
        enemieskilledtext.setText("" + Game.getEnemiesKilledVal());
    }

}