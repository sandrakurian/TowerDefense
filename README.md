# M6 Junits


### 1. gameOverTowersBought():
This function lists the number of towers the plate has bought throughout the game. `getTowersBoughtVal()` is called when showing the stats at the end of the game.


### 2. towerUpdateSuccessful():
If the player has sufficient funds and the tower has not maxed out on its upgrades, then the tower should upgrade. This ensures that the tower is upgraded with a better range and attack power.


### 3. notEnoughMoneyToUpgrade():
There might be cases in which the player tries to upgrade a tower even though they do not have enough money to do so. This test checks that the towers cannot be upgraded when there is not sufficient money.


### 4. towerUpdateAtMaxLevel():
n this game, the towers have 3 levels (levels 0 to 2) and, therefore, can only be upgraded 2 times. This test checks that the tower stops upgrading after it has reached its limit.


### 5. towerAtLocation():
During the game, the map will hold multiple towers. When the player wants to upgrade a tower, we have to make sure that the correct tower is being upgraded. This test case creates 10 towers and places them on a map. Then we call `towerList.getTower()` with a specific (x,y) coordinate. The function will iterate through all the towers until it finds the tower at [4,4] the player wants to upgrade.


### 6. noTowerAtLocation():
During the game, the user might accidently press a position on the map where there is no tower while upgrading. To ensure that there are no breaks in the middle of the game, we have considered this edge case. This test case creates 10 towers and places them on a map. Then we call `towerList.getTower()` with a specific (x,y) coordinate. The function will iterate through all the towers. Since there are no towers at [20,20], the function will return null.


### 7. bossHealthDepleted(): 
This test case checks that the boss’s health decreases when it is attacked multiple times. The player wins when the health is depleted and `Game.bossHealth()` will give us the boss health.


### 8. monumentHealthDepleted(): 
This test case checks that the monument’s health decreases when it is attacked by the enemies and boss multiple times. The player loses when the monument health is depleted and `Game.getHealth()` will give us the monument oss health.


### 9. moneySpent(): 
This test case checks the amount of money spent and the money the player corresponds accordingly. This is important throughout the game to ensure that the player’s purchases properly decrement their money and is correctly displayed to the player. This test case randomly purchases up to 20 towers. It can also attempt to upgrade the tower up to 5 times (it won’t upgrade more than 2 times).
The randomness accounts for instances in which there is not enough money for the player to purchase or upgrade a tower.


### 10. enemiesKilledGameOver():
enemiesKilledGameOver(): This test case checks if the right amount of enemies killed in the game is displayed to the player once the game ends (either by winning or losing). The test case simulates a situation where the player plays on medium level and buys three towers. It is important to track the number of enemies killed throughout the game because this value is an important statistic in the game over screen.



# M5 Junits


### sufficientTowerCombat(): 
This test checks if there are sufficient towers that were purchased for combat to take place. This test is relevant to the implementation because towers need to be on the map in order to attack enemies.


### enemyHealthDecreasesAfterAttack(): 
This test checks if enemy health decreases after each tower attack. This test is relevant to the implementation because combat between towers and enemies need to occur in order for the enemies to get killed so that the player can win the game.


### purchaseTowersAfterCombat(): 
This test checks if towers are able to be purchased after combat. The test asserts true if the program allows the player to be able to purchase towers. This test is relevant to the implementation because the player must be able to purchase towers in later rounds.


### enemiesNotCreated(): 
This test checks for failure in generating enemies. If the right amount of enemies is not created when the user clicks start combat, the test will indicate that enemies were not created properly. This test is relevant to the implementation because the enemies must spawn correctly in order for game play to work.


### startingEnemyCombatMediumPlayerSettings(): 
This test checks to see if a specific setting of the game is functional. At medium setting, the player money, health, difficulty, enemies in combat, and towers in combat must all have certain values for game play to work. The test checks if the values are right and asserts true when they are.


### towersDifferInEnemyDamage(): 
This test checks to see if the different towers cause varying damage amounts to the enemy. The enemy health decreases by a different amount from each tower attack and the test asserts true when this is the case.


### towerCombatNeedsMoreTowers(): 
This test checks to see if more towers are needed in order to play the game. When towers purchased is 0, the player will not be able to see any defense from their side. This test is necessary to check if optimal game conditions are set with regards to towers.


### towersShootEnemiesInRange(): 
The test checks the x and y coordinates of the enemies and towers to see if the enemies will be able to be shot by the towers when they are in range. The test will assert true when the enemies shoot within range. This test is relevant for implementation because the player must be able to defend their monument using towers that reliably shoot in range.


### enemiesConfiguredForCombat(): 
This test checks for optimal enemy configuration conditions. When enemy is configured at the right levels, the game functions properly as the right number of enemies spawn to attack the tower.


### enemyHealthDepletedDisappear(): 
This test checks to see if the enemies will disappear when their health becomes depleted. This test is crucial for the implementation of the game because the towers must be able to inflict damage that will hinder the enemies from reaching the monument, allowing the player a chance for victory.



# M4 JUnits


### configSpaceName(): 
This test checks if the program correctly identifies a valid player name that includes characters besides only whitespace. If a player name is an empty string, the configError() method returns null. In the test case, name is initialized to one whitespace character which configError() will trim and evaluate as a null String. If Config.configError(name, true)’s value is null, it matches up with the expected value. The test passes only when the program correctly identifies the test case of a whitespace character as an invalid player name. This test is relevant to the implementation requirements because a player must have a valid name in order to continue to the game.


### configNoName(): 
This test checks to see if the program detects a player with no name. An empty String passed into the test is an invalid player name, and the program will accordingly output a null in this case. A null is to be expected so the test case passes on the condition that the program correctly checks that there is not a player name. This test is relevant to the implementation requirements because players must have a name with characters other than only whitespace in order to be able to play the game.


### configNoDifficulty(): 
This test checks if the program does not detect a difficulty level. When there is no difficulty level, false is passed into Config.configError(name, false) which accordingly returns a “noDifficulty” from the lack of difficulty level chosen. This matches the expected value of “noDifficulty” resulting in a passing test which confirms that the program correctly identifies when a difficulty level has not been determined. This test is relevant to the implementation requirements because the money, monument health, and purchasing costs of towers all depend on the type of difficulty chosen. Without a chosen difficulty, the program will not be able to set up the game.


### configWorks(): 
This test represents the scenario where the configuration screen has a valid name and chosen difficulty level. In this case, the configuration screen should accept these values and set up the game with the proper money value, monument health level, and purchasing cost values. When Config.configError(name, true) outputs the name, this confirms that the test passed with an actual player name with characters other than only whitespace. This test is relevant to the implementation requirements because a functional configuration screen should allow the player to continue to the game only when the player name is valid and a difficulty level has been determined. The game can adequately set up the money, monument health, and purchasing costs of towers after knowing the difficulty level.


### testIsSufficientFundForEasyTower1(): 
This test checks if the program correctly allows the purchase of a tower when player money is greater than the tower cost. The test specifically determines the cost for tower 1 on the easy level as $32 and with playerMoney set to $63, the program should increment tower count to indicate a purchase. In this case, the program returns the tower cost ($32) and the test expects $32. The test fails when the program does not correctly determine that playerMoney is not greater than the cost of the tower. This test is relevant to the implementation requirements because towers must only be purchased with sufficient funds. Successful purchase of a tower (represented by an increment of tower count) is crucial to a player’s defense in the game.


### testIsSufficientFundForEasyTower2(): 
This test checks if the program bars a player from purchasing towers due to insufficient funds. In this scenario, the test case simulates a player with $30 attempting to purchase tower 1 on the easy level ($32). Since the playerMoney is less than the cost of the tower, the program should return 0 and not increment tower count. This test is relevant to the implementation requirements because tower count must not increment when insufficient funds are used in an attempt to purchase a tower.


### notEnoughTowersToPlace(): 
This test simulates a scenario where the program detects that there are not enough towers in inventory when a place tower is attempted. By instantiating a TowerList variable with the cost of 32, we are stating that there exists a tower type (in this case, it is an easy tower1). Then we are attempting to place the tower on the map. Since there were no towers purchased, there are no towers available in inventory. placeTower() will check inventory and will return false since there are no towers in inventory. This test is relevant to the implementation requirements because when there aren’t enough towers in inventory, towers that a player has not purchased should not be able to be placed.


### enoughTowersToPlace(): 
This test simulates a scenario where there are enough towers to place a tower. By instantiating a TowerList variable with the cost of 32, we are stating that there exists a tower type (in this case, it is an easy tower1). The user is able to purchase 1 tower since they have enough money and the inventory size will increase by 1. When placeTower() is called, it returns true as there is at least 1 tower in inventory. The assertTrue statement will also check if the inventory is 0 as placeTower() removes the tower from inventory. This test is relevant to the implementation requirements because the player should be able to place towers if there are enough towers in inventory. Proper functionality of this feature is important for the player’s defense in the game.


### towerPricePerDifficulty(): 
This test checks if the program correctly differentiates in the tower prices depending on the difficulty level. Tower 1 on easy level should be the lowest cost followed by tower 1 on the medium level. Tower 3 on the difficult level should have the highest cost. When this test passes, this indicates that the program successfully identified a different cost depending on the level for each tower. This test is relevant to the implementation of the program because tower costs must vary accordingly with difficulty level determined and the type of tower selected as well. Varying tower costs are crucial to the game’s complexity.


### correctInventory(): 
This test checks if the program correctly represents the right inventory amount after the purchase of a tower. Tower count should increment by one after each purchase with sufficient funds, and the test case checks this by purchasing two towers with $300 in playerMoney. The tower cost that the program is checking for purchase is a $32 tower and by purchasing two towers, inventory must show a tower count of 2. The test passes when tower count is 2 and fails when inventory shows a tower count of a number other than 2. This test is relevant to the implementation of the program because placing towers relies on the tower count in the inventory to function correctly so the player can place only the exact number of towers they have purchased from the shop.



# M3 JUnits


### 1) configSpaceName(): 
This test checks if the program correctly identifies a valid player name
that includes characters besides only whitespace. If a player name is an empty string, the
configError() method returns null. In the test case, name is initialized to one whitespace
character which configError() will trim and evaluate as a null String. If Config.configError(name,
true)’s value is null, it matches up with the expected value. The test passes only when the
program correctly identifies the test case of a whitespace character as an invalid player name.
This test is relevant to the implementation requirements because a player must have a valid
name in order to continue to the game.


### 2) configNoName(): 
This test checks to see if the program detects a player with no name. An
empty String passed into the test is an invalid player name, and the program will accordingly
output a null in this case. A null is to be expected so the test case passes on the condition that
the program correctly checks that there is not a player name. This test is relevant to the
implementation requirements because players must have a name with characters other than
only whitespace in order to be able to play the game.


### 3) configNoDifficulty(): 
This test checks if the program does not detect a difficulty level. When
there is no difficulty level, false is passed into Config.configError(name, false) which accordingly
returns a “noDifficulty” from the lack of difficulty level chosen. This matches the expected value
of “noDifficulty” resulting in a passing test which confirms that the program correctly identifies
when a difficulty level has not been determined. This test is relevant to the implementation
requirements because the money, monument health, and purchasing costs of towers all depend
on the type of difficulty chosen. Without a chosen difficulty, the program will not be able to set up
the game.


### 4) configWorks(): 
This test represents the scenario where the configuration screen has a valid
name and chosen difficulty level. In this case, the configuration screen should accept these
values and set up the game with the proper money value, monument health level, and
purchasing cost values. When Config.configError(name, true) outputs the name, this confirms
that the test passed with an actual player name with characters other than only whitespace. This
test is relevant to the implementation requirements because a functional configuration screen
should allow the player to continue to the game only when the player name is valid and a
difficulty level has been determined. The game can adequately set up the money, monument
health, and purchasing costs of towers after knowing the difficulty level.


### 5) testIsSufficientFundForEasyTower1(): 
This test checks if the program correctly allows the
purchase of a tower when player money is greater than the tower cost. The test specifically
determines the cost for tower 1 on the easy level as $32 and with playerMoney set to $63, the
program should increment tower count to indicate a purchase. In this case, the program returns
the tower cost ($32) and the test expects $32. The test fails when the program does not
correctly determine that playerMoney is not greater than the cost of the tower. This test is
relevant to the implementation requirements because towers must only be purchased with
sufficient funds. Successful purchase of a tower (represented by an increment of tower count) is
crucial to a player’s defense in the game.


### 6) testIsSufficientFundForEasyTower2(): 
This test checks if the program bars a player from
purchasing towers due to insufficient funds. In this scenario, the test case simulates a player
with $30 attempting to purchase tower 1 on the easy level ($32). Since the playerMoney is less
than the cost of the tower, the program should return 0 and not increment tower count. This test
is relevant to the implementation requirements because tower count must not increment when
insufficient funds are used in an attempt to purchase a tower.


### 7) notEnoughTowersToPlace(): 
This test simulates a scenario where the program detects that
there are not enough towers in inventory when a place tower is attempted. By instantiating a
TowerList variable with the cost of 32, we are stating that there exists a tower type (in this case,
it is an easy tower1). Then we are attempting to place the tower on the map. Since there were
no towers purchased, there are no towers available in inventory. placeTower() will check
inventory and will return false since there are no towers in inventory. This test is relevant to the
implementation requirements because when there aren’t enough towers in inventory, towers that
a player has not purchased should not be able to be placed.


### 8) enoughTowersToPlace(): 
This test simulates a scenario where there are enough towers to
place a tower. By instantiating a TowerList variable with the cost of 32, we are stating that there
exists a tower type (in this case, it is an easy tower1). The user is able to purchase 1 tower
since they have enough money and the inventory size will increase by 1. When placeTower() is
called, it returns true as there is at least 1 tower in inventory. The assertTrue statement will also
check if the inventory is 0 as placeTower() removes the tower from inventory. This test is
relevant to the implementation requirements because the player should be able to place towers
if there are enough towers in inventory. Proper functionality of this feature is important for the
player’s defense in the game.


### 9) towerPricePerDifficulty(): 
This test checks if the program correctly differentiates in the tower
prices depending on the difficulty level. Tower 1 on easy level should be the lowest cost followed
by tower 1 on the medium level. Tower 3 on the difficult level should have the highest cost.
When this test passes, this indicates that the program successfully identified a different cost
depending on the level for each tower. This test is relevant to the implementation of the program
because tower costs must vary accordingly with difficulty level determined and the type of tower
selected as well. Varying tower costs are crucial to the game’s complexity.


### 10) correctInventory(): 
This test checks if the program correctly represents the right inventory
amount after the purchase of a tower. Tower count should increment by one after each purchase
with sufficient funds, and the test case checks this by purchasing two towers with $300 in
playerMoney. The tower cost that the program is checking for purchase is a $32 tower and by
purchasing two towers, inventory must show a tower count of 2. The test passes when tower
count is 2 and fails when inventory shows a tower count of a number other than 2. This test is
relevant to the implementation of the program because placing towers relies on the tower count
in the inventory to function correctly so the player can place only the exact number of towers
they have purchased from the shop.

