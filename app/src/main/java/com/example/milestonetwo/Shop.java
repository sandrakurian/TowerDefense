package com.example.milestonetwo;
import android.content.Intent;
import android.os.Bundle;
import android.content.DialogInterface;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class Shop extends AppCompatActivity {

    private Button tower1Button;
    private Button tower2Button;
    private Button tower3Button;

    private TextView tower1price;
    private TextView tower2price;
    private TextView tower3price;


    private Button exitShop;

    private TextView tower1Count;
    private TextView tower2Count;
    private TextView tower3Count;

    private static TextView moneyShop;

    private static TextView alertPopupShop;

    private static int moneyspentval = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        alertPopupShop = (TextView) findViewById(R.id.alertPopupShop);

        tower1Button = (Button) findViewById(R.id.tower1_add);
        tower1Button.setText("Tower 1");
        tower1price = (TextView) findViewById(R.id.tower1_price);
        tower1price.setText(String.valueOf(Game.getT1().getCost()));

        tower2Button = (Button) findViewById(R.id.tower2_add);
        tower2Button.setText("Tower 2");
        tower2price = (TextView) findViewById(R.id.tower2_price);
        tower2price.setText(String.valueOf(Game.getT2().getCost()));

        tower3Button = (Button) findViewById(R.id.tower3_add);
        tower3Button.setText("Tower 3");
        tower3price = (TextView) findViewById(R.id.tower3_price);
        tower3price.setText(String.valueOf(Game.getT3().getCost()));


        tower1Count = (TextView) findViewById(R.id.tower1_count);
        tower2Count = (TextView) findViewById(R.id.tower2_count);
        tower3Count = (TextView) findViewById(R.id.tower3_count);

        tower1Count.setText(String.valueOf(Game.getT1().getCount()));
        tower2Count.setText(String.valueOf(Game.getT2().getCount()));
        tower3Count.setText(String.valueOf(Game.getT3().getCount()));


        moneyShop = (TextView) findViewById(R.id.moneyShop);

        exitShop = (Button) findViewById(R.id.exitShop);


        moneyShop.setText("Doubloons: " + Game.getPlayerMoney());


        //tower1Button = findViewById(R.id.tower1_add);
        tower1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //tower1Count = findViewById(R.id.tower1_count);
                tower1Count.setText(String.valueOf(towerCountIncrement(Game.getT1())));
            }
        });

        //tower2Button = findViewById(R.id.tower2_add);
        tower2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //tower2Count = findViewById(R.id.tower2_count);
                tower2Count.setText(String.valueOf(towerCountIncrement(Game.getT2())));
            }
        });

        //tower3Button = findViewById(R.id.tower3_add);
        tower3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //tower3Count = findViewById(R.id.tower3_count);
                tower3Count.setText(String.valueOf(towerCountIncrement(Game.getT3())));
            }
        });

        exitShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), Game.class);
                startActivity(intent);

            }
        });
    }



    private void towerCountRestart(TextView tower) {
        tower.setText("0");
    }


    public int towerCountIncrement(TowerList tower) {
        System.out.println("Shop.towerCountIncrement \t attempting to buy tower");
        int cost = tower.addTower();
        if (tower.addTower() != 0) {
            System.out.println("Shop.towerCountIncrement \t yes tower \t count: "
                    + tower.getCount());
            //            count.setText(String.valueOf(tower.getCount()));
            Game.setPlayerMoney(Game.getPlayerMoney() - cost);
            moneyspentval+=cost;
            moneyShop.setText("Doubloons: " + Game.getPlayerMoney());
        } else {
            System.out.println("Shop.towerCountIncrement \t no tower");
            AlertDialog.Builder builder = new AlertDialog.Builder(Shop.this);

            builder.setCancelable(true);
            builder.setTitle("Insufficient funds");
            builder.setMessage("You do not have enough money to purchase this tower\n\n"
                    + "Current doubloons: " + Game.getPlayerMoney());
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    alertPopupShop.setVisibility(View.VISIBLE);
                    //TEST
                    moneyShop.setText("Doubloons: " + Game.getPlayerMoney());
                }
            });
            builder.show();
        }
        return tower.getCount();
    }

    public static String shopTowerCombat (int towerCountIncremented) {
        if (towerCountIncremented > 0) {
            return "sufficient towers";
        } else {
            return "more towers needed";
        }
        //return "Error";
    }

    public static boolean purchaseTower(boolean startedCombat) {
        return startedCombat;
    }

    public static int getMoneySpentVal() {
        return moneyspentval;
    }

//    public int getPlayerMoney() {
//        return Game.getPlayerMoney();
//    }
}


