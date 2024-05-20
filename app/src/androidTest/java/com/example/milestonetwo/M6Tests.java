package com.example.milestonetwo;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static java.util.Objects.isNull;

import org.junit.Test;

import java.util.ArrayList;


public class M6Tests{

    @Test
    public void gameOverTowersBought() {
        Game.setPlayerMoney(200);
        TowerList t = new TowerList(50, 10, 5);

        assertEquals(10, TowerList.getTowersBoughtVal());
    }

    @Test
    public void towerUpdateSuccessful() {
        Game.setPlayerMoney(100);

        int oldRange = 10;
        int oldAttack = 5;
        Tower t = new Tower(50, oldRange, oldAttack);

        // upgrades to level 1
        Game.upgradeTower(t);
        Game.upgradeTower(t);

        assertTrue(t.getAttack() > oldAttack
                && t.getRange() > oldRange );
    }

    @Test
    public void notEnoughMoneyToUpgrade(){
        int range = 10;
        int attack = 5;
        Tower t = new Tower(0, range, attack);

        assertEquals(-1, Game.upgradeTower(t));
    }

    @Test
    public void towerUpdateAtMaxLevel() {
        Game.setPlayerMoney(100);
        Tower t = new Tower(50, 10, 5);

        int flag = 100;
        for (int i = 0; i < 10; i++) {
            flag = Game.upgradeTower(t);
        }

        assertTrue(-1 == flag
                && t.getLevel() == 2);
    }

    @Test
    public void towerAtLocation() {
        Game.setPlayerMoney(100);
        TowerList tl = new TowerList(50, 10, 5);

        int[] p;
        for (int i = 0; i < 10; i++) {
            tl.addTower();
            p = new int[]{i, i};
            tl.placeTower(p);
        }
        boolean status = false;
        Tower t = tl.getTower(new int[]{4,4});

        assertTrue(t != null);
    }

    @Test
    public void noTowerAtLocation() {
        Game.setPlayerMoney(100);
        TowerList tl = new TowerList(50, 10, 5);

        int[] p;
        for (int i = 0; i < 10; i++) {
            tl.addTower();
            p = new int[]{i, i};
            tl.placeTower(p);
        }
        boolean status = false;
        Tower t = tl.getTower(new int[]{20,20});

        assertTrue(t == null);
    }

    @Test
    public void bossHealthDepleted() {
        int initialHealth = 100;
        int damage = 50;

        int firstHit = Game.bossHealth(initialHealth, damage);
        int secondHit = Game.bossHealth(firstHit, damage);

        assertTrue((initialHealth - damage) == firstHit
                    && (initialHealth - damage*2) == secondHit);
    }

    @Test
    public void monumentHealthDepleted() {
        int initialHealth = 100;
        int damage = 50;

        Game.setHealth(initialHealth);
        Game.setHealth(Game.getHealth() - damage);
        Game.setHealth(Game.getHealth() - damage);

        assertEquals(0, Game.getHealth());
    }

    @Test
    public void moneySpent() {

        int money = 200;
        Game.setPlayerMoney(money);
        TowerList towerList = new TowerList(40, 5, 5);

        for (int i = 0; i < Math.random()*20; i++) {
            money = money - towerList.addMoneydecrement();
            towerList.placeTower(new int[]{i, i});
            Tower tower = towerList.getTower(new int[]{i, i});
            for (int j = 0; tower != null && j < Math.random()*5; j++) {
                int cost = tower.upgradeMoneydecrement();
                if (cost != -1) {
                    money = money - cost;
                }
            }
        }

        assertEquals(money, Game.getPlayerMoney());
    }

    @Test
    public void enemiesKilledGameOver() {

        int enemiesInitial = 6;
        int difficultyLevel = 1;
        int towersBought = 3;

        assertEquals(3, Game.enemiesKilled(difficultyLevel, enemiesInitial, towersBought));
    }


}


