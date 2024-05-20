package com.example.milestonetwo;



import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class M3Tests {

//    @Test
//    public void configSpaceName() {
//        String name = " ";
//        assertEquals(null, Config.configError(name, true));
//    }
//
//    @Test
//    public void configNoName() {
//        assertEquals(null, Config.configError("", true));
//    }
//
//    @Test
//    public void configNoDifficulty() {
//        String name = "Sandra";
//        assertEquals("noDifficulty", Config.configError(name, false));
//    }
//
//    @Test
//    public void configWorks() {
//        String name = "Sandra";
//        assertEquals(name, Config.configError(name, true));
//    }
//
//    @Test
//    public void testIsSufficientFundForEasyTower1()  {
//        int[] costOfTower = {32, 40, 48};
//        TowerList test = new TowerList(32);
//        Game.setPlayerMoney(63);
//        // verify by sufficient fund 25 is the sufficient fund............
//        assertEquals(32, test.addTower());
//    }
//
//    @Test
//    public void testIsSufficientFundForEasyTower2()  {
//        int[] costOfTower = {32, 40, 48};
//        TowerList test = new TowerList(32);
//        Game.setPlayerMoney(30);
//        // verify by sufficient fund 25 is the sufficient fund............
//        assertEquals(0, test.addTower());
//    }
//
//    //Sandra
//    @Test
//    public void notEnoughTowersToPlace() {
//        TowerList towerList = new TowerList(0);
//        // there are no towers in inventory
//        assertFalse(towerList.placeTower());
//    }
//
//    @Test
//    public void notEnoughTowersToPlace2() {
//        Game.setPlayerMoney(100);
//        TowerList towerList = new TowerList(32);
//        // there are no towers in inventory
//        towerList.addTower();
//        assertTrue(towerList.placeTower());
//    }
//
//    @Test
//    // tower prices is easy < medium < hard
//    public void towerPricePerDifficulty() {
//        int[][] prices = Game.towerPrices();
//        assertTrue(prices[0][0] < prices[0][1] && prices[0][1] < prices[0][2]
//                && prices[1][0] < prices[1][1] && prices[1][1] < prices[1][2]
//                && prices[2][0] < prices[2][1] && prices[2][1] < prices[2][2]);
//    }
//
//
//    @Test
//    public void correctInventory() {
//        Game.setPlayerMoney(300);
//        TowerList towerList = new TowerList(32);
//        towerList.addTower();
//        towerList.addTower();
//        assertEquals(2, towerList.getInvCount());
//    }
}
