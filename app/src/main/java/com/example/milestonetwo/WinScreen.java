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

public class WinScreen extends AppCompatActivity {

    private Button yesbuttonwin;
    private Button nobuttonwin;
    private TextView moneyspenttextwin;
    private TextView towersboughttextwin;
    private TextView enemieskilledtextwin;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winscreen);


        yesbuttonwin = (Button) findViewById(R.id.yesbuttonwin);
        nobuttonwin = (Button) findViewById(R.id.nobuttonwin);
        moneyspenttextwin = (TextView) findViewById(R.id.moneyspenttextwin);
        towersboughttextwin = (TextView) findViewById(R.id.towersboughttextwin);
        enemieskilledtextwin = (TextView) findViewById(R.id.enemieskilledtextwin);

        setStats();

        yesbuttonwin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Config.setFirstGameScreenStart();
                Intent j = new Intent(getApplicationContext(), Welcome.class);
                startActivity(j);
            }
        });

        nobuttonwin.setOnClickListener(new View.OnClickListener() {
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
        moneyspenttextwin.setText("" + Shop.getMoneySpentVal());
        towersboughttextwin.setText("" + TowerList.getTowersBoughtVal()/2);
        enemieskilledtextwin.setText("" + Game.getEnemiesKilledVal());
    }

}