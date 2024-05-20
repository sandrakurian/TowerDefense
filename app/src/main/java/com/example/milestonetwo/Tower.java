package com.example.milestonetwo;

import android.widget.ImageView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class Tower {
    private int cost;
    private int range;
    private int attack;
    private double level;
    private int health;

    private int posX = -1;
    private int posY = -1;

    private static ArrayList<Integer> t1Pos = new ArrayList<Integer>();
    private static ArrayList<Integer> t2Pos = new ArrayList<Integer>();
    private static ArrayList<Integer> t3Pos = new ArrayList<Integer>();

    private int image;


    public Tower(int cost, int range, int attack) {
        level = 0;
        health = 100;
        this.cost = cost;
        this.range = range;
        this.attack = attack;
    }

    public int upgradeTower() {
        double cost = getUpgradeCost();
        if (cost == 0 && level < 3) {        // no more upgrades
            System.out.println("Tower.upgradeTower()\tno upgrade");
            return -1;
        }
        level += .5;
        range = (int) (range * 1.25);
        attack += 3;
//        Game.setPlayerMoney((int) (Game.getPlayerMoney() - cost));
        System.out.println("Tower.upgradeTower()\tupgraded to level " + level);
        return (int)cost;
    }
    public int upgradeMoneydecrement() {
        int cost = upgradeTower();
        if (cost != -1) {
            Game.setPlayerMoney(Game.getPlayerMoney() - cost);
        }
        return cost;
    }

    // position stuff

    public static void clearT123() {
        ArrayList<Integer> t1Pos = new ArrayList<Integer>();
        ArrayList<Integer> t2Pos = new ArrayList<Integer>();
        ArrayList<Integer> t3Pos = new ArrayList<Integer>();
    }
    public static void addT1(int i) {
        t1Pos.add(i);
    }
    public static void addT2(int i) {
        t2Pos.add(i);
    }
    public static void addT3(int i) {
        t3Pos.add(i);
    }
    public static ArrayList<Integer> getT1() {
        return t1Pos;
    }
    public static ArrayList<Integer> getT2() {
        return t2Pos;
    }
    public static ArrayList<Integer> getT3() {
        return t3Pos;
    }

    public boolean posXYMatch(int[] posXY) {
        return ( (posX == posXY[0]) && (posY == posXY[1]));
    }

    public static boolean enemyDamage(int enemyInitialHealth) {
        if (enemyInitialHealth - 10 - 20 - 30 > 0) {
            return true;
        }

        return false;
    }

    // getters
    public double getUpgradeCost() {
        if (level + 1 < 3) {
            return cost * .25;
        } else {
            return 0;
        }
    }
    public int getHealth() {
        return health;
    }
    public int getLevel() { return (int)level; }
    public int getImage() {
        return image;
    }
    public int getRange() {return range;}
    public int getAttack() {return attack;}
    public int getPosX() {return posX;}

    // setters
    public void setPos (int[] posXY) {
        posX = posXY[0];
        posY = posXY[1];
        System.out.println("Tower.setPos() \t Tower at " + posX + "," + posY);
    }
    public void setImage(int i) {
        image = i;
    }
}
