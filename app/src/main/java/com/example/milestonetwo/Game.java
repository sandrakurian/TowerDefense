package com.example.milestonetwo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import android.animation.Animator;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Process;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ToggleButton;

import org.w3c.dom.Text;

public class Game extends AppCompatActivity {

    // VARIABLES ------------------------------------------------------------------------

    // game setup
    private static boolean wantToExitGame = false;
    private static TextView alertPopupGame;
    private static int difficulty = Config.difficulty();
    private static boolean firstGameScreenStartBool = Config.getFirstGameScreenStart();

    // money
    private static TextView money;
    private static int playerMoney = Config.getMoney() * 100;

    // monument
    private TextView monumentHealth;
    private static int health = Config.getHealth();

    // towers
    private static TowerList t1;
    int[] t1Image = new int[]{R.drawable.ship10, R.drawable.ship11, R.drawable.ship12};
    private static TowerList t2;
    int[] t2Image = new int[]{R.drawable.ship20, R.drawable.ship21, R.drawable.ship22};
    private static TowerList t3;
    int[] t3Image = new int[]{R.drawable.ship30, R.drawable.ship31, R.drawable.ship32};
    private static int[][] prices = new int[][]{
            {32, 52, 64},
            {40, 60, 72},
            {48, 68, 80}};
    private static int[][] range = new int[][]{
            {400, 300, 200},
            {500, 350, 250},
            {500, 400, 300}};
    private static int[][] attack = new int[][]{
            {30, 20, 10},
            {40, 30, 20},
            {50, 40, 30}};
    private GridView gridView;
    private static int[] images = new int[72];
    private int tower = 1;
    private boolean toPlace = true;
    private ToggleButton toggle;
    private Button tower1Button;
    private Button tower2Button;
    private Button tower3Button;
    private boolean place1 = false;
    private boolean place2 = false;
    private boolean place3 = false;

    // enemies
    private static int enemieskilledval = 0;
    private ImageView image1;
    private ImageView image2;
    private ImageView image3;
    private ImageView image4;
    private int x = 2;


    // extra
    private Spinner gameScreenButtonSpinner;
    private String[] buttonNames = {"", "Start Combat", "Trade Shop", "Abandon Castle"};
    private ArrayAdapter<String> adapter;


    // FUNCTIONS ------------------------------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        money = (TextView) findViewById(R.id.money);
        setImage();
        if (firstGameScreenStartBool) {
            playerMoney = Config.getMoney() * 100;
            difficulty = Config.difficulty();
            health = Config.getHealth();
            startGame();
            gridView = findViewById(R.id.gridView);
            CustomAdapter cAdapter = new CustomAdapter(images, this);
            gridView.setAdapter(cAdapter);
            setFirstGameScreenStartBool(false);
        } else {
            money.setText("Doubloons: " + playerMoney);
            updateMonument(health);
            gridView = findViewById(R.id.gridView);
            CustomAdapter cAdapter = new CustomAdapter(images, this);
            gridView.setAdapter(cAdapter);
        }
        alertPopupGame = (TextView) findViewById(R.id.alertPopupGame);
        gameScreenButtonSpinner = (Spinner) findViewById(R.id.gameScreenButtonsSpinner);
        adapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_list_item_1, buttonNames);
        gameScreenButtonSpinner.setAdapter(adapter);
        gameScreenButtonSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        break;
                    case 1:
                        moveAnimation1();
                        break;
                    case 2:
                        Intent j = new Intent(getApplicationContext(), Shop.class);
                        startActivity(j);
                        break;
                    case 3:
                        if (!wantToExitGame) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(Game.this);

                            builder.setCancelable(true);
                            builder.setTitle("Confirm Exit");
                            builder.setMessage("Are you sure you want to exit the game?");
                            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    alertPopupGame.setVisibility(View.VISIBLE);
                                }
                            });
                            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    wantToExitGame = true;
                                    moveTaskToBack(true);
                                    alertPopupGame.setVisibility(View.VISIBLE);
                                    Process.killProcess(Process.myPid());
                                    System.exit(1);
                                }
                            });
                            builder.show();
                        }
                        break;
                    default: break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        // place / upgrade tower
        toggle = (ToggleButton) findViewById(R.id.toggleButton);
        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (toggle.isChecked()) {
                    toPlace = false;
                } else {
                    toPlace = true;
                }
//                System.out.println((toPlace ? "place" : "upgrade"));
            }
        });


        tower1Button = (Button) findViewById(R.id.tower1_add);
        tower2Button = (Button) findViewById(R.id.tower2_add);
        tower3Button = (Button) findViewById(R.id.tower3_add);
        tower1Button.setBackgroundColor(Color.parseColor("#006600"));
        tower2Button.setBackgroundColor(Color.parseColor("#800000"));
        tower3Button.setBackgroundColor(Color.parseColor("#996600"));
        tower1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                place1 = !place1;
                place2 = false;
                place3 = false;
                if (place1 && t1.getCount() >= 1) {
                    tower = 1;
                    tower1Button.setBackgroundColor(Color.parseColor("#009933"));
                } else if (!place1) {
                    tower1Button.setBackgroundColor(Color.parseColor("#006600"));

                }
            }
        });
        tower2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                place1 = false;
                place2 = !place2;
                place3 = false;
                if (place2 && t2.getCount() >= 1) {
                    tower = 2;
                    tower2Button.setBackgroundColor(Color.parseColor("#cc0000"));
                } else if (!place2) {
                    tower2Button.setBackgroundColor(Color.parseColor("#800000"));
                }
            }
        });
        tower3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                place1 = false;
                place2 = false;
                place3 = !place3;
                if (place3 && t3.getCount() >= 1) {
                    tower = 3;
                    tower3Button.setBackgroundColor(Color.parseColor("#cc9900"));
                } else if (!place3) {
                    tower3Button.setBackgroundColor(Color.parseColor("#996600"));
                }
            }
        });
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                clicked(adapterView, view, i, l);
                clicked(adapterView, view, i, l);
            }
        });
    }

    // game setup
    private void startGame() {

        money.setText("Doubloons: " + playerMoney);
        restartTowerList();
        playerMoney = Config.getMoney() * 100;
        health = Config.getHealth();
        difficulty = Config.difficulty();
        //Tower.clearT123();
        System.out.println("Game.startGame() \t tower lists created");
        updateMonument(Config.getHealth());
        updateEnemyHealth(100, (ProgressBar) findViewById(R.id.enemyBar));
    }
    public static boolean gameError() {
        return health == 0;
    }
    public static void restartTowerList() {
        t1 = new TowerList(prices[0][difficulty], range[0][difficulty], attack[0][difficulty]);
        t2 = new TowerList(prices[1][difficulty], range[1][difficulty], attack[1][difficulty]);
        t3 = new TowerList(prices[2][difficulty], range[2][difficulty], attack[2][difficulty]);
    }
    public static boolean startPlayerConfigurations(int money, int health, int difficulty, int enemiesCombat, int towersCombat) {
        if (money > 300 && money < 500) {
            if (health > 70 && health < 80) {
                if (difficulty == 1) {
                    if (enemiesCombat > 0 && towersCombat > 0) {
                        return true;
                    }
                }
            }
        }

        return false;
    }
    public static void setFirstGameScreenStartBool(boolean firstGameScreenStartBool1) {
        firstGameScreenStartBool = firstGameScreenStartBool1;
    }
    public static void setWantToExitGame (boolean wantToExitGame1) {
        wantToExitGame = wantToExitGame1;
    }

    // enemies
    public void setImage() {
        image1 = (ImageView) findViewById(R.id.enemy1);
        image2 = (ImageView) findViewById(R.id.enemy2);
        image3 = (ImageView) findViewById(R.id.enemy3);
        image4 = (ImageView) findViewById(R.id.boss);


    }
    public void moveAnimation1() {
        image1.setVisibility(View.VISIBLE);
        //move down

        final Animation img = new TranslateAnimation(Animation.ABSOLUTE,
                Animation.ABSOLUTE + 170, Animation.ABSOLUTE, Animation.ABSOLUTE);
        img.setDuration(1000);
        img.setFillAfter(true);
        image1.startAnimation(img);

        final Animation img2 = new TranslateAnimation(Animation.ABSOLUTE + 170,
                Animation.ABSOLUTE + 170, Animation.ABSOLUTE, Animation.ABSOLUTE + 510);
        img2.setDuration(3000);
        img2.setFillAfter(true);

        final Animation img3 = new TranslateAnimation(Animation.ABSOLUTE + 170,
                Animation.ABSOLUTE + 670, Animation.ABSOLUTE + 510, Animation.ABSOLUTE + 510);
        img3.setDuration(3000);
        img3.setFillAfter(true);

        final Animation img4 = new TranslateAnimation(Animation.ABSOLUTE + 670,
                Animation.ABSOLUTE + 670, Animation.ABSOLUTE + 510, Animation.ABSOLUTE);
        img4.setDuration(3000);
        img4.setFillAfter(true);

        final Animation img5 = new TranslateAnimation(Animation.ABSOLUTE + 670, Animation.ABSOLUTE + 1180,
                Animation.ABSOLUTE, Animation.ABSOLUTE);
        img5.setDuration(3000);
        img5.setFillAfter(true);

        final Animation img6 = new TranslateAnimation(Animation.ABSOLUTE + 1180, Animation.ABSOLUTE + 1180,
                Animation.ABSOLUTE, Animation.ABSOLUTE + 510);
        img6.setDuration(3000);
        img6.setFillAfter(true);

        final Animation img7 = new TranslateAnimation(Animation.ABSOLUTE + 1180,
                Animation.ABSOLUTE + 1690, Animation.ABSOLUTE + 510, Animation.ABSOLUTE + 510);
        img7.setDuration(3000);
        img7.setFillAfter(true);

        final Animation img8 = new TranslateAnimation(Animation.ABSOLUTE + 1690,
                Animation.ABSOLUTE + 1690, Animation.ABSOLUTE + 510, Animation.ABSOLUTE + 340);
        img8.setDuration(1000);
        img8.setFillAfter(true);


        img.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                image1.startAnimation(img2);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

        });

        img2.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                image1.startAnimation(img3);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

        });

        img3.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                image1.startAnimation(img4);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

        });

        img4.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                image1.startAnimation(img5);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

        });

        img5.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                image1.startAnimation(img6);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

        });

        img6.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                moveAnimation2();


            }

            @Override
            public void onAnimationEnd(Animation animation) {

                image1.startAnimation(img7);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

        });

        img7.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {

                image1.startAnimation(img8);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

        });

        img8.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                image1 = (ImageView) findViewById(R.id.enemy1);

                health -= 20;

                if (health <= 0) {
                    System.out.println("here!!");
                    health = 0;
                    updateMonument(0);
                    //healthBar.setProgress(0);
                    //image1.setVisibility(View.INVISIBLE);
                    Intent j = new Intent(getApplicationContext(), GameOver.class);
                    startActivity(j);
                }
                enemieskilledval++;
                updateMonument(health);
                //healthBar.setProgress(health);
                image1 = (ImageView) findViewById(R.id.enemy1);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

        });

    }
    public void moveAnimation2() {
        image2.setVisibility(View.VISIBLE);
        //move down



        final Animation img = new TranslateAnimation(Animation.ABSOLUTE,
                Animation.ABSOLUTE + 170, Animation.ABSOLUTE, Animation.ABSOLUTE);
        img.setDuration(1000);
        img.setFillAfter(true);
        image2.startAnimation(img);

        final Animation img2 = new TranslateAnimation(Animation.ABSOLUTE + 170,
                Animation.ABSOLUTE + 170, Animation.ABSOLUTE, Animation.ABSOLUTE + 510);
        img2.setDuration(3000);
        img2.setFillAfter(true);

        final Animation img3 = new TranslateAnimation(Animation.ABSOLUTE + 170,
                Animation.ABSOLUTE + 670, Animation.ABSOLUTE + 510,
                Animation.ABSOLUTE + 510);
        img3.setDuration(3000);
        img3.setFillAfter(true);

        final Animation img4 = new TranslateAnimation(Animation.ABSOLUTE + 670,
                Animation.ABSOLUTE + 670, Animation.ABSOLUTE + 510,
                Animation.ABSOLUTE);
        img4.setDuration(3000);
        img4.setFillAfter(true);

        final Animation img5 = new TranslateAnimation(Animation.ABSOLUTE + 670,
                Animation.ABSOLUTE + 1180,
                Animation.ABSOLUTE, Animation.ABSOLUTE);
        img5.setDuration(3000);
        img5.setFillAfter(true);

        final Animation img6 = new TranslateAnimation(Animation.ABSOLUTE + 1180,
                Animation.ABSOLUTE + 1180,
                Animation.ABSOLUTE, Animation.ABSOLUTE + 510);
        img6.setDuration(3000);
        img6.setFillAfter(true);

        final Animation img7 = new TranslateAnimation(Animation.ABSOLUTE + 1180,
                Animation.ABSOLUTE + 1690, Animation.ABSOLUTE + 510,
                Animation.ABSOLUTE + 510);
        img7.setDuration(3000);
        img7.setFillAfter(true);

        final Animation img8 = new TranslateAnimation(Animation.ABSOLUTE + 1690,
                Animation.ABSOLUTE + 1690, Animation.ABSOLUTE + 510,
                Animation.ABSOLUTE + 340);
        img8.setDuration(1000);
        img8.setFillAfter(true);

        img.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                image2.startAnimation(img2);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

        });

        img2.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                image2.startAnimation(img3);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

        });

        img3.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                image2.startAnimation(img4);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

        });

        img4.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                image2.startAnimation(img5);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

        });

        img5.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                image2.startAnimation(img6);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

        });

        img6.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                moveAnimation3();
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                image2.startAnimation(img7);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

        });

        img7.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {

                image2.startAnimation(img8);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

        });

        img8.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                image2 = (ImageView) findViewById(R.id.enemy2);
                health -= 30;

                if (health <= 0) {
                    System.out.println("here!!");
                    health = 0;
                    updateMonument(0);
                    //healthBar.setProgress(0);
                    //image1.setVisibility(View.INVISIBLE);
                    Intent j = new Intent(getApplicationContext(), GameOver.class);
                    startActivity(j);
                }
                enemieskilledval++;
                updateMonument(health);
                //healthBar.setProgress(health);
                image2 = (ImageView) findViewById(R.id.enemy2);            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

        });


    }
    public void moveAnimation3() {
        x--;
        image3.setVisibility(View.VISIBLE);
        //move down
        final Animation img = new TranslateAnimation(Animation.ABSOLUTE,
                Animation.ABSOLUTE + 170, Animation.ABSOLUTE, Animation.ABSOLUTE);
        img.setDuration(1000);
        img.setFillAfter(true);
        image3.startAnimation(img);

        final Animation img2 = new TranslateAnimation(Animation.ABSOLUTE + 170,
                Animation.ABSOLUTE + 170,
                Animation.ABSOLUTE, Animation.ABSOLUTE + 510);
        img2.setDuration(3000);
        img2.setFillAfter(true);

        final Animation img3 = new TranslateAnimation(Animation.ABSOLUTE + 170,
                Animation.ABSOLUTE + 670,
                Animation.ABSOLUTE + 510, Animation.ABSOLUTE + 510);
        img3.setDuration(3000);
        img3.setFillAfter(true);

        final Animation img4 = new TranslateAnimation(Animation.ABSOLUTE + 670,
                Animation.ABSOLUTE + 670,
                Animation.ABSOLUTE + 510, Animation.ABSOLUTE);
        img4.setDuration(3000);
        img4.setFillAfter(true);

        final Animation img5 = new TranslateAnimation(Animation.ABSOLUTE + 670,
                Animation.ABSOLUTE + 1180,
                Animation.ABSOLUTE, Animation.ABSOLUTE);
        img5.setDuration(3000);
        img5.setFillAfter(true);

        final Animation img6 = new TranslateAnimation(Animation.ABSOLUTE + 1180,
                Animation.ABSOLUTE + 1180,
                Animation.ABSOLUTE, Animation.ABSOLUTE + 510);
        img6.setDuration(3000);
        img6.setFillAfter(true);

        final Animation img7 = new TranslateAnimation(Animation.ABSOLUTE + 1180,
                Animation.ABSOLUTE + 1690, Animation.ABSOLUTE + 510,
                Animation.ABSOLUTE + 510);
        img7.setDuration(3000);
        img7.setFillAfter(true);

        final Animation img8 = new TranslateAnimation(Animation.ABSOLUTE + 1690,
                Animation.ABSOLUTE + 1690, Animation.ABSOLUTE + 510,
                Animation.ABSOLUTE + 340);
        img8.setDuration(1000);
        img8.setFillAfter(true);
        img.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                image3.startAnimation(img2);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

        });

        img2.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                image3.startAnimation(img3);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

        });

        img3.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {

                image3.startAnimation(img4);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

        });

        img4.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                image3.startAnimation(img5);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

        });

        img5.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                image3.startAnimation(img6);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

        });

        img6.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                if (x == 0) {
                    moveAnimationBoss();
                } else {
                    moveAnimation1();
                }


            }

            @Override
            public void onAnimationEnd(Animation animation) {

                image3.startAnimation(img7);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

        });

        img7.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {

                image3.startAnimation(img8);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

        });

        img8.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                image3 = (ImageView) findViewById(R.id.enemy3);
                health -= 40;

                if (health <= 0) {
                    System.out.println("here!!");
                    health = 0;
                    updateMonument(0);
                    //healthBar.setProgress(0);
                    //image1.setVisibility(View.INVISIBLE);
                    Intent j = new Intent(getApplicationContext(), GameOver.class);
                    startActivity(j);
                }
                enemieskilledval++;
                updateMonument(health);
                //healthBar.setProgress(health);
                image3 = (ImageView) findViewById(R.id.enemy3);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

        });
    }
    private void updateEnemyHealth(int health, ProgressBar bar) {
        healthBar(health,bar);
    }

    // boss
    public void moveAnimationBoss() {
        image4.setVisibility(View.VISIBLE);
        //move down
        final Animation img = new TranslateAnimation(Animation.ABSOLUTE,
                Animation.ABSOLUTE + 170, Animation.ABSOLUTE, Animation.ABSOLUTE);
        img.setDuration(1000);
        img.setFillAfter(true);
        image4.startAnimation(img);

        final Animation img2 = new TranslateAnimation(Animation.ABSOLUTE + 170,
                Animation.ABSOLUTE + 170,
                Animation.ABSOLUTE, Animation.ABSOLUTE + 510);
        img2.setDuration(3000);
        img2.setFillAfter(true);

        final Animation img3 = new TranslateAnimation(Animation.ABSOLUTE + 170,
                Animation.ABSOLUTE + 670,
                Animation.ABSOLUTE + 510, Animation.ABSOLUTE + 510);
        img3.setDuration(3000);
        img3.setFillAfter(true);

        final Animation img4 = new TranslateAnimation(Animation.ABSOLUTE + 670,
                Animation.ABSOLUTE + 670,
                Animation.ABSOLUTE + 510, Animation.ABSOLUTE);
        img4.setDuration(3000);
        img4.setFillAfter(true);

        final Animation img5 = new TranslateAnimation(Animation.ABSOLUTE + 670,
                Animation.ABSOLUTE + 1180,
                Animation.ABSOLUTE, Animation.ABSOLUTE);
        img5.setDuration(3000);
        img5.setFillAfter(true);

        final Animation img6 = new TranslateAnimation(Animation.ABSOLUTE + 1180,
                Animation.ABSOLUTE + 1180,
                Animation.ABSOLUTE, Animation.ABSOLUTE + 510);
        img6.setDuration(3000);
        img6.setFillAfter(true);

        final Animation img7 = new TranslateAnimation(Animation.ABSOLUTE + 1180,
                Animation.ABSOLUTE + 1690, Animation.ABSOLUTE + 510,
                Animation.ABSOLUTE + 510);
        img7.setDuration(3000);
        img7.setFillAfter(true);

        final Animation img8 = new TranslateAnimation(Animation.ABSOLUTE + 1690,
                Animation.ABSOLUTE + 1690, Animation.ABSOLUTE + 510,
                Animation.ABSOLUTE + 340);
        img8.setDuration(1000);
        img8.setFillAfter(true);
        img.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                image4.startAnimation(img2);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

        });

        img2.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                image4.startAnimation(img3);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

        });

        img3.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {

                image4.startAnimation(img4);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

        });

        img4.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                image4.startAnimation(img5);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

        });

        img5.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                image4.startAnimation(img6);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

        });

        img6.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                //moveAnimation1();


            }

            @Override
            public void onAnimationEnd(Animation animation) {

                image4.startAnimation(img7);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

        });

        img7.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {

                image4.startAnimation(img8);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

        });

        img8.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                image4 = (ImageView) findViewById(R.id.boss);
                health -= 50;

                if (health <= 0) {
                    System.out.println("here!!");
                    health = 0;
                    updateMonument(0);
                    //healthBar.setProgress(0);
                    //image1.setVisibility(View.INVISIBLE);
                    Intent j = new Intent(getApplicationContext(), GameOver.class);
                    startActivity(j);
                }
                enemieskilledval++;
                updateMonument(health);
                //healthBar.setProgress(health);
                image4 = (ImageView) findViewById(R.id.boss);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

        });
    }
    public static int bossHealth(int initialHealth, int damage) {
        return initialHealth - damage;
    }

    public static int enemiesKilled(int difficultyLevel, int enemiesInitial, int towersBought) {


        int enemiesKill = enemiesInitial*difficultyLevel - towersBought;

        return enemiesKill;
    }

    // tower
    private void clicked(AdapterView<?> adapterView, View view, int i, long l) {    // placement
        ImageView iv = view.findViewById(R.id.imageView);
        int[] posXY = new int[2];
        iv.getLocationOnScreen(posXY);
        System.out.println("Game.clicked()\t"+posXY[0]  + " " + posXY[1]);
        if (((Integer) iv.getTag() == R.drawable.oceantile)
                && !(i >= 74 && i <= 77) && !(i >= 56 && i <= 59)
                && !(i >= 63 && i <= 65) && !(i >= 66 && i <= 71)) {

            if (toPlace){ // place towers on map
                System.out.println("Game.clicked()\tplacing towers");
                if (tower == 1) {
                    if (place1 && t1.getCount() >= 1) {
                        images[i] = R.drawable.ship10;
                        iv.setImageResource(R.drawable.ship10);
                        iv.setTag(R.drawable.ship10);
                        Tower.addT1(i);
                        t1.placeTower(posXY);
                    } else {
                        tower1Button.setBackgroundColor(Color.parseColor("#006600"));
                    }
                } else if (place2 && tower == 2) {
                    if (t2.getCount() >= 1) {
                        images[i] = R.drawable.ship20;
                        iv.setImageResource(R.drawable.ship20);
                        iv.setTag(R.drawable.ship20);
                        Tower.addT2(i);
                        t2.placeTower(posXY);
                    } else {
                        tower2Button.setBackgroundColor(Color.parseColor("#800000"));
                    }
                } else {
                    if (place3 && t3.getCount() >= 1) {
                        images[i] = R.drawable.ship30;
                        iv.setImageResource(R.drawable.ship30);
                        iv.setTag(R.drawable.ship30);
                        Tower.addT3(i);
                        t3.placeTower(posXY);
                    } else {
                        tower3Button.setBackgroundColor(Color.parseColor("#996600"));
                    }
                }

            }
        }
        if (!toPlace) { // upgrade towers
            // which tower should we update
            System.out.println("Game.clicked()\tupgrading towers");
            Tower t = t1.getTower(posXY);
            if (t != null) {
                upgradeTower(t, iv, i, t1Image);
                System.out.println("Game.clicked()\tship 1 image");
            } else {
                t = t2.getTower(posXY);
                if (t != null) {
                    upgradeTower(t, iv, i, t2Image);
                    System.out.println("Game.clicked()\tship 2 image");
                } else {
                    t = t3.getTower(posXY);
                    if (t != null) {
                        upgradeTower(t, iv, i, t3Image);
                        System.out.println("Game.clicked()\tship 3 image");
                    }
                }
            }
        }
    }
    public void upgradeTower(Tower t, ImageView iv, int i, int[] imageList) {
        int cost = upgradeTower(t);
        if (cost < 0) {

            AlertDialog.Builder builder = new AlertDialog.Builder(Game.this);

            builder.setCancelable(true);
            builder.setTitle("Insufficient funds");
            builder.setMessage("You do not have enough money to upgrade this tower\n\n"
                    + "Current doubloons: " + Game.getPlayerMoney());
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    alertPopupGame.setVisibility(View.VISIBLE);
                }
            });
            builder.show();
        } else {
            setPlayerMoney(getPlayerMoney() - cost);
            money.setText("Doubloons: " + playerMoney);
            images[i] = imageList[t.getLevel()];
            iv.setImageResource(imageList[t.getLevel()]);
            iv.setTag(imageList[t.getLevel()]);
            t.setImage(imageList[t.getLevel()]);
        }

    }
    public static int upgradeTower(Tower t) {
        return t.upgradeTower();
    }
    public static int[][] towerPrices() {
        return prices;
    }
    public static boolean attackEnemies(int towerLocationXVal, int towerLocationYVal, int enemyLocationXVal, int enemyLocationYVal) {
        if (towerLocationXVal + towerLocationYVal > 0) {
            if (enemyLocationXVal + enemyLocationYVal > 0) {
                return true;
            }
        }
        return false;
    }

    // update
    private void updateMonument(int health) {
        monumentHealth = findViewById(R.id.monumentHealth);
        monumentHealth.setText("Monument Heath: " + health);
        healthBar(health, (ProgressBar) findViewById(R.id.HealthBar));
    }
    private void healthBar(int health, ProgressBar bar) {
        Drawable progressDrawable = bar.getProgressDrawable();

        if (health > 75) {
            progressDrawable.setColorFilter(Color.rgb(0,100,0),
                    android.graphics.PorterDuff.Mode.SRC_IN);
        } else if (health > 50) {
            progressDrawable.setColorFilter(Color.YELLOW,
                    android.graphics.PorterDuff.Mode.SRC_IN);
        } else if (health > 25) {
            progressDrawable.setColorFilter(Color.rgb(0,100,0),
                    android.graphics.PorterDuff.Mode.SRC_IN);
        } else {
            progressDrawable.setColorFilter(Color.RED,
                    android.graphics.PorterDuff.Mode.SRC_IN);
        }

        bar.setProgressDrawable(progressDrawable);
        bar.setProgress(health);
    }
//    private static void updateMoney() {
//        TextView money = (TextView)
//        money.setText("Doubloons: " + playerMoney);
//    }

    // getters
    public static int getPlayerMoney() {
        return playerMoney;
    }
    public static int getEnemiesKilledVal() {
        return enemieskilledval;
    }
    public static TowerList getT1() {
        return t1;
    }
    public static TowerList getT2() {
        return t2;
    }
    public static TowerList getT3() {
        return t3;
    }
    public static int getHealth(){
        return health;
    }


    // setters
    public static void setPlayerMoney(int playerMoney1) {
        playerMoney = playerMoney1;
    }
    public static void setHealth(int h){
        health = h;
    }


    public class CustomAdapter extends BaseAdapter {
        private int[] imagesPhoto;
        private Context context;
        private LayoutInflater layoutInflater;

        public CustomAdapter(int[] imagesPhoto, Context context) {
            this.imagesPhoto = imagesPhoto;
            this.context = context;
            this.layoutInflater =
                    (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return imagesPhoto.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            if (view == null) {
                view = layoutInflater.inflate(R.layout.row_items, viewGroup, false);
            }
            ImageView imageView = view.findViewById(R.id.imageView);
            if ((i >= 12 && i <= 13) || (i >= 16 && i <= 19)
                    || i == 25 || i == 28 || i == 31 || i == 37 || i == 40 || i == 43
                    || i == 46 || (i >= 49 && i <= 52) || (i >= 55 && i <= 58) || i == 46) {
                imageView.setImageResource(R.drawable.sandtile);
                imageView.setTag(R.drawable.sandtile);
            } else if (Tower.getT1().contains(i))  {
                imageView.setImageResource(R.drawable.tower1);
                imageView.setTag(R.drawable.tower1);
            } else if (Tower.getT2().contains(i)) {
                imageView.setImageResource(R.drawable.tower2);
                imageView.setTag(R.drawable.tower2);
            } else if (Tower.getT3().contains(i)) {
                imageView.setImageResource(R.drawable.tower3);
                imageView.setTag(R.drawable.tower3);
            } else {
                imageView.setImageResource(R.drawable.oceantile);
                imageView.setTag(R.drawable.oceantile);
                //imageView.setVisibility(View.INVISIBLE);
            }
            return view;
        }
    }



}

