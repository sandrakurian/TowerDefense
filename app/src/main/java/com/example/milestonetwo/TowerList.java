package com.example.milestonetwo;


import android.widget.ImageView;

import java.util.ArrayList;

public class TowerList {
    private int cost;
    private int range;
    private int attack;
    private double count;
    private ArrayList<Tower> inventory = new ArrayList();
    private ArrayList<Tower> onMap = new ArrayList();
    private static int towersboughtval = 0;

    public TowerList(int cost, int range, int attack) {
        this.cost = cost;
        this.range = range;
        this.attack = attack;
        count = 0;
    }

    public int addTower() {
        System.out.println("TowerList.addTower \t attempting to buy tower");
        if (Game.getPlayerMoney() - cost >= 0) {
            System.out.println("TowerList.addTower \t yes tower");
            count = count + .5;
            inventory.add(new Tower(cost, range, attack));
            towersboughtval++;
            return cost;
        } else {
            System.out.println("TowerList.addTower \t no tower");
            return 0;
        }
    }
    public int addMoneydecrement() {
        int cost = addTower();
        Game.setPlayerMoney(Game.getPlayerMoney() - cost);
        return cost;
    }
    public Boolean placeTower(int[] posXY) {
        if (inventory.size() != 0) {
            Tower t = (Tower) inventory.get(0);
            inventory.remove(0);
            onMap.add(t);
            t.setPos(posXY);
            count--;
            return true;
        } else {
            return false;
        }
    }

    // getters
    public int getCount() {

        return (int) count;
    }
    public int getCost() {

        return cost;
    }
    public Tower getTower(int[] posXY) {
        for (Tower t : onMap) {
            if (t.posXYMatch(posXY)){
                System.out.println("TowerList.getTower()\tfound tower at position");
                return t;
            }
        }
        System.out.println("TowerList.getTower()\tno tower at position");
        return null;
    }
    public static int getTowersBoughtVal() {
        return towersboughtval;
    }
    public ArrayList<Tower> getOnMap() {
        return onMap;
    }

}
