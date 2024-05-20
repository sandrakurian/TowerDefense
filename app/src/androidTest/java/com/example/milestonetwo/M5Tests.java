package com.example.milestonetwo;


import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class M5Tests {
    @Test
    public void sufficientTowerCombat() {
        String towersPlaced = "sufficient towers";
        assertEquals(towersPlaced, Shop.shopTowerCombat(10));
    }

    @Test
    public void enemyHealthDecreasesAfterAttack() {
        int enemyHealth = 50;
        assertEquals(20, Enemy.healthAttack(enemyHealth));
    }

    @Test
    public void purchaseTowersAfterCombat() {
        boolean startedCombatOnce = true;

        assertEquals(true, Shop.purchaseTower(startedCombatOnce));
    }

    @Test
    public void enemiesNotCreated() {
        String enemiesCreation = "enemies not created";
        assertEquals(enemiesCreation, Enemy.enemyCombat(0));
    }

    @Test
    public void startingEnemyCombatMediumPlayerSettings() {
        int playerMoney = 400;
        int health = 75;
        int difficulty = 1;
        int enemiesCombat = 5;
        int towersCombat = 5;

        assertEquals(true, Game.startPlayerConfigurations(playerMoney, health, difficulty,
                enemiesCombat, towersCombat));
    }

    @Test
    public void towersDifferInEnemyDamage() {
        int enemyInitialHealth = 100;
        assertEquals(true, Tower.enemyDamage(enemyInitialHealth));
    }

    @Test
    public void towerCombatNeedsMoreTowers() {
        String towersDetermined = "more towers needed";
        assertEquals(towersDetermined, Shop.shopTowerCombat(0));
    }

    @Test
    public void towersShootEnemiesInRange() {
        int towerLocationXVal = 5;
        int towerLocationYVal = 7;
        int enemyLocationXVal = 6;
        int enemyLocationYVal = 3;

        assertEquals(true, Game.attackEnemies(towerLocationXVal, towerLocationYVal,
                enemyLocationXVal, enemyLocationYVal));
    }


    @Test
    public void enemiesConfiguredForCombat() {
        String enemiesConfigured = "enemy configured";
        assertEquals(enemiesConfigured, Enemy.enemyCombat(8));
    }


    @Test
    public void enemyHealthDepletedDisappear() {
        int enemyHealth = 0;
        assertEquals(true, Enemy.depleteHealth(enemyHealth));
    }


}


