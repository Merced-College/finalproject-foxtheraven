/*
Xandra Quevedo
Started Nov 6 2025
Main game class for Dungeons of Java

Note: Some hard-coded values may seem a little unexplained –
this is generally because they're coming from my hand-drawn
map. While I assume a full scale, more in-depth program would actually
store this data somehow, I decided to just use the values outright
since the map is small and I don't want to get overwhelmed by
data structures.
*/

import java.util.Hashtable;
import java.util.Scanner;

public class DungeonsOfJava {

	public static void main(String[] args) {
;		//For player input
		Scanner scnr = new Scanner(System.in);
		//Keeps track of whether a battle is ongoing
		boolean fightMode = false;
	
		//This is not the actual map size, but a variable to use
		//for the hash table's size for potential scaling 
		int mapSize = 21;
		/*
		 * A set of GameMap objects, all with valid x and y
		 * coordinates from my drawn map
		 * Unfortunately, these are not consecutive values,
		 * so they cannot be straightforwardly initialized in a loop
		*/
		GameMap map1 = new GameMap(1, 0);
		GameMap map2 = new GameMap(1, 1);
		GameMap map3 = new GameMap(1, 2); //Contains rat
		GameMap map4 = new GameMap(0, 2);
		GameMap map5 = new GameMap(0, 3);
		GameMap map6 = new GameMap(0, 4); //Contains rat
		GameMap map7 = new GameMap(2, 2);
		GameMap map8 = new GameMap(2, 3);
		GameMap map9 = new GameMap(2, 4); //Contains skeleton
		GameMap map10 = new GameMap(1, 4);
		GameMap map11 = new GameMap(1, 5);
		GameMap map12 = new GameMap(1, 6); //Contains skeleton
		GameMap map13 = new GameMap(0, 6);
		GameMap map14 = new GameMap(1, 7);
		GameMap map15 = new GameMap(2, 7); //Contains big skeleton
		GameMap map16 = new GameMap(1, 8);
		GameMap map17 = new GameMap(1, 9); //Contains drake
		
		//Create an array of the valid map locations, for use in
		//the for loop to initialize the hash table
		GameMap[] maps = { 
				map1, map2, map3, map4, map5, map6, map7, map8, map9,
				map10, map11, map12, map13, map14, map15, map16, map17
		};
		
		//Create a hash table of Integer keys and GameMap objects 
		Hashtable<Integer, GameMap> validMap = new Hashtable<>(mapSize);
		//Initialize the hash table with a loop that cycles through the
		//GameMap array in order
		for (int i = 0; i < maps.length; i++) {
			validMap.put(i + 1, maps[i]);
			
		}
		
		//Create 2 new hash tables for areas that are normal hallways
		//and areas that are before an enemy ("scary")
		Hashtable<Integer, GameMap> normalHallways = new Hashtable<>(mapSize);
		Hashtable<Integer, GameMap> scaryHallways = new Hashtable<>(mapSize);
		
		//All the following values come from the drawn map
		normalHallways.put(1, map1);
		scaryHallways.put(2, map2);

		normalHallways.put(4, map4);
		scaryHallways.put(5, map5);

		normalHallways.put(7, map7);
		scaryHallways.put(8, map8);

		normalHallways.put(10, map10);
		scaryHallways.put(11, map11);

		normalHallways.put(13, map13);
		
		//Create 3 new hash tables for areas with enemies within them 
		Hashtable<Integer, GameMap> rats = new Hashtable<>(mapSize);
		Hashtable<Integer, GameMap> skellies = new Hashtable<>(mapSize);
		Hashtable<Integer, GameMap> bigSkellies = new Hashtable<>(mapSize);
		
		//Again, following values are from the drawn map
		rats.put(1, map3);
		rats.put(2, map6);
		
		skellies.put(1, map9);
		skellies.put(2, map12);
		
		bigSkellies.put(1, map15);
		
		//One more hash table, with String keys and Integer values
		//This stores enemy attributes by name for easy access and use
		Hashtable<String, Integer> enemyStats = new Hashtable<>();
		final String RAT_NAME = "Rat";
		final String SKELLY_NAME = "Skeleton";
		final String BIG_SKELLY_NAME = "Giant Skeleton";
		final String DRAKE_NAME = "Red Dragon";
		//The hard coded values here would be subject to change upon
		//test playing and difficulty scaling
		enemyStats.put("ratHP", 6);
		enemyStats.put("ratPOW", 2);
		enemyStats.put("skelHP", 12);
		enemyStats.put("skelPOW", 4);
		enemyStats.put("bigSkelHP", 16);
		enemyStats.put("bigSkelPOW", 6);
		enemyStats.put("drakeHP", 40);
		enemyStats.put("drakePOW", 11);
		
		//This null enemy is made for use in the actionCall method
		Enemy nameNone = new Enemy();
		//The game's 6 enemies are created with the appropriate values from the table
		//toString() statements can be used to check initialization is working properly
		Enemy rat1 = new Enemy(RAT_NAME, enemyStats.get("ratHP"), enemyStats.get("ratPOW"));
		//System.out.println(rat1.toString());
		Enemy rat2 = new Enemy(RAT_NAME, enemyStats.get("ratHP"), enemyStats.get("ratPOW"));
		//System.out.println(rat2.toString());
		Enemy skelly1 = new Enemy(SKELLY_NAME, enemyStats.get("skelHP"), enemyStats.get("skelPOW"));
		//System.out.println(skelly1.toString());
		Enemy skelly2 = new Enemy(SKELLY_NAME, enemyStats.get("skelHP"), enemyStats.get("skelPOW"));
		//System.out.println(skelly2.toString());
		Enemy bigSkelly = new Enemy(BIG_SKELLY_NAME, enemyStats.get("bigSkelHP"), enemyStats.get("bigSkelPOW"));
		//System.out.println(bigSkelly.toString());
		Enemy drake = new Enemy(DRAKE_NAME, enemyStats.get("drakeHP"), enemyStats.get("drakePOW"));
		//System.out.println(drake.toString());

		//"Opening screen" text sequence
		//Asks player for their name for use in the player object
		System.out.println("You have entered the Dungeons of Java...");
		//First instance of delay() - this will be called many times
		//for the sake of causing delayed text
		delay();
		System.out.println("What is your name?");
		
		//Name is taken from user input
		String userName = scnr.next();
		scnr.nextLine();
		System.out.println("Welcome, " + userName + "." );
		delay();
		
		//Create the player using their name and default values in the Player class
		//toString() can be used to check this is working
		Player player = new Player(userName);
		//System.out.println(player.toString());
		
		
		//TODO - Left as placeholder text for now
		//This would explain the player's move set (WASD, AA/AC/PY, O)
		//and the basic game mechanics
		System.out.println("EXPLANATION TEXT");
		
		//First use of a print method - there are many of these,
		//stored at the bottom of the code
		normalMessage();
		//optionExplainer();
		
		//Turn on the game and perform the first actionCall 
		//actionCall allows the player to move or start a fight when needed
		boolean gameplay = true;
		actionCall(scnr, player, fightMode, validMap, nameNone);
		
		//TODO - Implement some way of checking if a room has been 
		//accessed before, to avoid repeat text
		//This can be done by checking enemy HP and player values
		//or inventory, for item rooms
		
		//This while loop runs until the game is complete, which doesn't
		//happen naturally in this version, and instead triggers if the
		//last map square is accessed
		while(gameplay) {
			//Take the player's current locations, and store them in a new GameMap
			int x = player.getPlayerX();
			int y = player.getPlayerY();
			//System.out.println(x + ", " + y);
			GameMap playerLocale = new GameMap(x, y);
			
			//First, check if the player is dead, and end the game if so
			//This must be LESS THAN OR EQUAL 0 because HP can be negative
			if(player.getPlayerHP() <= 0) {
				youLose();
				gameplay = false;
				break;
			}
			
			//Otherwise, run through possible location if statements
			//These all operate with a message and an action call,
			//with additional code for fights or unique rooms
			
			//If the player is in a normal hallway, run normal hallway message
			if(normalHallways.contains(playerLocale)) {
				normalMessage();
				actionCall(scnr, player, fightMode, validMap, nameNone);
			}
			
			//If the player is in a scary hallway, run scary hallway message
			if(scaryHallways.contains(playerLocale)) {
				scaryMessage();
				actionCall(scnr, player, fightMode, validMap, nameNone);
			}
			
			//If the player's location is in the rat hash table...
			if(rats.contains(playerLocale)) {
				//Check if the rat is dead and the player is at 1,2
				//If so, turn fightMode to false, and print the dead rat message
				if ((rat1.getEnemyHP() <= 0 && x == 1 && y == 2)) {
					fightMode = false;
					System.out.println("There's nothing here but a rat's corpse.");
					delay();
					actionCall(scnr, player, fightMode, validMap, nameNone);
				}
				//Otherwise, fightMode is true, and actionCall is called with
				//rat1 - this would need to be updated to account for rat2
				else { 
					fightMode = true;
					ratFightMessage();
					actionCall(scnr, player, fightMode, validMap, rat1);
				}
			}
			
			//If the player's location is in the skeleton hash table...
			if(skellies.contains(playerLocale)) {
				//If the first skeleton is dead, run the shield room message
				//and strengthen the player
				if (skelly1.getEnemyHP() <= 0 && x == 2 && y == 4) {
					shieldRoom();
					player.setPlayerDefense(9);
					//FIXME - You can change this to be based on player's defense
					//AKA if their defense is 9 then they've been here before
					actionCall(scnr, player, fightMode, validMap, nameNone);
					break;
				}
				//If the second skeleton is dead, run the message for before
				//the left room 
				if (skelly2.getEnemyHP() <= 0 && x == 1 && y == 6) {
					beforeLeftRoom();
					actionCall(scnr, player, fightMode, validMap, nameNone);
					break;
				}
				//Otherwise, do an actionCall with the skeleton
				//Again, needs to be updated to account for skelly2
				else {
					fightMode = true;
					skellyFightMessage();
					actionCall(scnr, player, fightMode, validMap, skelly1);
				}
			}
			
			//Based on the map - this is before a room to the right
			//which needs a special message
			if (x == 1 && y == 7) {
				beforeRightRoom();
				actionCall(scnr, player, fightMode, validMap, nameNone);
			}
			
			//TODO - Unfinished - Should be combined with the below if 
			// and turned into one if, like the other enemy room if statements
			if(bigSkellies.contains(playerLocale)) {
				fightMode = true;
				bigSkellyFightMessage();
				//FIXME - This value may change, this is the better sword power
				//FIXME - Can be changed to be based on player's power
				//		  in terms of ensuring no repeat messages
				player.setPlayerPower(10);
				actionCall(scnr, player, fightMode, validMap, bigSkelly);
			}
			if (bigSkelly.getEnemyHP() <= 0 && x == 2 && y == 7) {
				beforeLeftRoom();
				actionCall(scnr, player, fightMode, validMap, nameNone);
			}
			
			//This is map square before the final boss room
			if(x == 1 && y == 8) {
				specialMessage();
				actionCall(scnr, player, fightMode, validMap, nameNone);
			}
			
			//This is the final boss room
			//For game-end testing purposes, this just ends the game
			//TODO - Add drake combat if statement
			if(x == 1 && y == 9) {
				break;
			}
		}
		
		scnr.close();
		
		//Some joke-y messages to display if you get to the final
		//map square. These would be changed to legitimate ending messages.
		//TODO - Add legitimate ending messages
		if (player.getPlayerHP() < 0) {
			System.out.println("YOU DIED");
		}
		else {
			System.out.println("yay you did it");
		}
	
		
	}//end main
	
//---------------------------------------------------------------
	
	//Methods used by main
	
	//actionCall is how the player's actions are executed
	//It also manages the game state to an extent
	public static void actionCall(Scanner scnr, Player player, boolean fightMode, 
			Hashtable<Integer, GameMap> validMap, Enemy enemy) {
		System.out.println("What will you do?");
		
		//System.out.println(enemy.getEnemyName());
		
		//If the enemy at the current square is dead, exit fight mode
		//(This is a fail-safe)
		if (enemy.getEnemyHP() <= 0) {
			fightMode = false;
		}
		
		//If fightMode is active, check the enemt's name and jump
		//to the appropriate combat method
		if (fightMode == true) {
			if (enemy.getEnemyName().equals("Rat")) {
				combatRat(scnr, player, enemy, fightMode);
			}
			else if (enemy.getEnemyName().equals("Skeleton")) {
				combatSkel(scnr, player, enemy, fightMode);
			}
			else if (enemy.getEnemyName().equals("Giant Skeleton")) {
				combatBigSkel(scnr, player, enemy, fightMode);
			}
			else if (enemy.getEnemyName().equals("Red Dragon")) {
				combatDrake(scnr, player, enemy, fightMode);
			}
		}
		
		//So long as the player is alive...
		if (player.getPlayerHP() > 0) {
			//Get the player's action
			String act = scnr.next();
			scnr.nextLine();
		
			//If a movement command is entered...
			if (act.equals("W") || act.equals("A") || 
					act.equals("S") || act.equals("D")) {
				//Check collision using this variable
				boolean noCollide = collision(validMap, player, act);
				//If there was no collision with the wall, move the player
				//based on their command
				if (noCollide) {
					player.move(act);
				}
				//If there was collision, print the out of bounds message,
				//and repeat the actionCall
				else {
					outOfBounds();
					actionCall(scnr, player, fightMode, validMap, enemy);
				}
			}
			//If a battle command is entered...
			else if (act.equals("AC") || act.equals("AA") || 
					act.equals("PY")) {
				//If fightMode is off, print error message and repeat 
				//the actionCall
				if (!fightMode) {
					System.out.println("What are you doing? There are no enemies here. Try again.");
					actionCall(scnr, player, fightMode, validMap, enemy);
				}
			}
			//If O is entered...
			else if (act.equals("O")) {
				//Call the option explainer message and repeat the
				//actionCall
				optionExplainer();
				actionCall(scnr, player, fightMode, validMap, enemy);
			}
		}
		
	}//end actionCall
	
//---------------------------------------------------------------
	
	//Combat methods
	
	//		Algorithm: Combat sequence
	//		Changes player and enemy HP
	//First (and only completed) combat method, for rats
	public static void combatRat(Scanner scnr, Player player, Enemy rat, boolean fightMode) {
		//Round count determines enemy behavior, as seen below
		int round = 1;
		//To more obviously show damage amounts
		int damage = 0;
		
		//While fightMode is active...
		while (fightMode) {
			//Get player action
			String act = scnr.next();
			//While player's action is not a valid combat action...
			while (!act.equals("AA") && !act.equals("AC") && !act.equals("PY")) {
				//Print an error message, and get a new player action
				System.out.println("What are you doing? There's no time for that!");
				System.out.println("You have to fight!");
				delay();
				System.out.println("What will you do?");
				act = scnr.next();
			}
			
			//On odd-numbered rounds:
			if (round % 2 != 0) {
				/* An earlier test method to check the rat death
				 * was working correctly:
				 * if (act.equals("K")) {
				 * 	rat.setEnemyHP(0);
				 * }
				 */
				//If the player protects themselves:
				if (act.equals("PY")) {
					//Correct action for this round. No damage taken.
					System.out.println("The rat surges forward with snapping jaws!");
					delay();
					System.out.println("It slams against your raised shield!");
					delay();
				}
				//If the player attacks:
				else if (act.equals("AA") || act.equals("AC")) {
					//Incorrect action. Both player and rat are wounded.
					System.out.println("You try to strike the rat, but it was already about to hit you!");
					delay();
					//damage = rat.getEnemyPower();
					//Below damage statement is to ensure player death works,
					//as it is impossible to lose to the rat currently.
					damage = 20;
					
					//PlayerHP is decremented by the rat's power
					player.setPlayerHP(player.getPlayerHP() - damage);
					System.out.println("You take " + damage + " points of damage. The rat is only partially wounded.");
					delay();
					//RatHP is decremented by half the player's power
					rat.setEnemyHP(rat.getEnemyHP() - (player.getPlayerPower() / 2 ));
				}
				
				//Only print the next round messages if rat and player are both alive
				if (rat.getEnemyHP() > 0 && player.getPlayerHP() > 0) {
					System.out.println("The rat stands back, caution in its wild eyes.");
					delay();
					System.out.println("What will you do?");
					round++;
				}
			}//end odd round if
			
			//On even-numbered rounds:
			else {
				//If the player protects themselves:
				if (act.equals("PY")) {
					//Incorrect action. Nothing happens.
					System.out.println("The rat skitters idly backwards.");
					delay();
					System.out.println("You hold your shield up against nothing.");
					delay();
				}
				//If the player attacks carefully:
				else if (act.equals("AC")) {
					//Incorrect action. The rat dodges, and nothing happens.
					System.out.println("You approach with caution, but the rat expected your attack!");
					delay();
					System.out.println("It narrowly dodges your blade!");
					delay();
				}
				//If the player attacks aggressively:
				else if (act.equals("AA")) {
					//Correct action. The rat takes damage.
					System.out.println("You leap suddenly toward the rat and strike it!");
					delay();
					System.out.println("The rat screeches in pain!");
					delay();
					//Rat takes playerPower amount of damage
					rat.setEnemyHP(rat.getEnemyHP() - player.getPlayerPower());
				}
				
				//Only print the next round messages if rat and player are both alive
				if (rat.getEnemyHP() > 0 && player.getPlayerHP() > 0){
					System.out.println("The rat bears its sharp teeth.");
					delay();
					System.out.println("What will you do?");
					round++;
				}
			}//end even round if
			
			//Check if the player is dead - if so, break from the loop
			if (player.getPlayerHP() <= 0) {
				fightMode = false;
				break;
			}
			
			//Check if the rat is dead - if so, break from the loop
			if (rat.getEnemyHP() <= 0) {
				System.out.println("The rat cries out, and keels over in a dramatic display.");
				delay();
				System.out.println("You're free to wander once more.");
				delay();
				fightMode = false;
				break;
			}
			
		}//end while(fightMode)
			
	}//end combatRat
	
	//TODO - Unfinished combat methods for other enemies.
	public static void combatSkel(Scanner scnr, Player player, Enemy skelly, boolean fightMode) {
		
	}
	
	public static void combatBigSkel(Scanner scnr, Player player, Enemy bigSkelly, boolean fightMode) {
		
	}
	
	/*
	 * The dragon would have special behavior that limits the battle to
	 * a certain number of rounds. On the final round, it would immediately
	 * KO the player no matter what. This would mean the player needs the
	 * upgraded sword to take out the dragon fast enough.
	 * (In game terms, it's a DPS check.)
	*/
	public static void combatDrake(Scanner scnr, Player player, Enemy drake, boolean fightMode) {
		
	}
	
//---------------------------------------------------------------
	
	//Movement methods
	
	//		Algorithm: Collision
	//		Checks if a player is allowed to move
	//Run collision whenever the player tries to move
	//(Initially tried to put this in the player class,
	//but hash table bugs were afoot)
	public static boolean collision(Hashtable<Integer, GameMap> validMap, Player player, String move) {
		//Create test positions using player's move, 
		//their current coordinate, and the move methods
		int testX = moveX(move, player.getPlayerX());
		int testY = moveY(move, player.getPlayerY());
		
		//Make a GameMap with the test positions
		GameMap position = new GameMap(testX, testY);
		
		//If the valid game map contains the position, 
		//return true - if not, return false;
		if (validMap.contains(position)) {
			return true;
		}
		else {
			return false;
		}
		
	}//end collision
	
	//Method to create an X test position
	//In WASD controls, A is left, D is right
	public static int moveX(String move, int playerX) {
		if (move.equals("A")) {
			playerX = playerX - 1;
		}//end A-move
		
		if (move.equals("D")) {
			playerX = playerX + 1;
		}//end D-move
		
		return playerX;
		
	}//end moveX
	
	//Method to create a Y test position
	//In WASD controls, W is up, S is down
	public static int moveY(String move, int playerY) {
		//System.out.println("Called GameMap moveY");
		if (move.equals("W")) {
			playerY = playerY + 1;
		}//end W-move
		
		if (move.equals("S")) {
			playerY = playerY - 1;
		}//end S-move
		
		return playerY;
		
	}//end moveY
	
//---------------------------------------------------------------
	
	//My one million print methods, 
	//so the main code isn't cluttered with them
	
	//Lists the player's options in case they need a refresher
	public static void optionExplainer() {
		System.out.println("--------------------------");
		
		System.out.println("Player's abilities:");
		System.out.println("AC : Attack carefully");
		System.out.println("AA : Attack aggressively");
		System.out.println("PY : Protect yourself");
		System.out.println("W : Move forward");
		System.out.println("A : Move left");
		System.out.println("S : Move backward");
		System.out.println("D : Move right");
		System.out.println("O : Explain abilities");
		
		System.out.println("--------------------------");
	}
	
	public static void outOfBounds() {
		System.out.println("A wall blocks your way.");
	}
	
	//The normal hallway message. This appears repeatedly,
	//so it's been made a method
	public static void normalMessage() {
		System.out.println("Before you lays a torchlit hallway.");
		delay();
	}//end normalMessage
	
	//Pre-enemy message. Also appears repeatedly
	public static void scaryMessage() {
		System.out.println("There is something in the dark in front of you...");
		delay();
	}
	
	public static void shieldRoom() {
		System.out.println("The skeleton held an iron shield, engraved with a hawk.");
		delay();
		System.out.println("It seems a great deal stronger than the wooden one on your back.");
		delay();
		System.out.println("The exit to the hallway is to your left.");
		delay();
	}
	
	public static void beforeLeftRoom() {
		System.out.println("There's an entrance to your left.");
		delay();
		System.out.println("Before you lays a torchlit hallway.");
		delay();
	}
	
	public static void leftRoom() {
		System.out.println("The walls here are made of a darker stone.");
		delay();
		System.out.println("...But there's nothing here.");
		delay();
		System.out.println("The exit to the hallway is to your right.");
		delay();
	}
	
	public static void beforeRightRoom() {
		System.out.println("There's an entrance to your right...");
		delay();
		System.out.println("Before you lays a torchlit hallway.");
		delay();
	}
	
	public static void rightRoom() {
		System.out.println("The giant skeleton was guarding a gleaming sword.");
		delay();
		System.out.println("In your hands, it emanates divine power.");
		delay();
		System.out.println("The exit to the hallway is to your left.");
		delay();
	}
	
	public static void specialMessage() {
		System.out.println("You feel intense dread.");
		delay();
		System.out.println("Something tells you this is the end.");
		delay();
		System.out.println("Steel yourself, adventurer...");
		delay();
	}
	
	//Rat fight message - rats are always aggressive
	//The correct strategy is to defend then attack
	public static void ratFightMessage() {
		System.out.println("A huge rat screeches at your approach!");
		delay();
		System.out.println("It seems ready to lunge!");
	}
	
	//Skeleton fight message - skeletons can be cautious
	//The correct strategy for this one is to attack aggressively
	public static void skellyFightMessage() {
		System.out.println("A skeleton stands before you!");
		delay();
		System.out.println("It holds up an old iron shield!");
	}
	
	//Big skeleton fight message - this is a special buff skeleton
	//The correct strategy is to attack carefully
	public static void bigSkellyFightMessage() {
		System.out.println("A huge skeleton looms over you!");
		delay();
		System.out.println("It just barely seems to notice you...");
	}
	
	//Drake fight message - the final boss of the first level
	//The drake will fake certain behavior and act erratic.
	public static void drakeFightMessage() {
		System.out.println("A massive red dragon stares you down!");
		delay();
		System.out.println("It seems disinterested in your very existence...");
	}
	
	//Displayed when HP drops to or below 0
	public static void youLose() {
		System.out.print("Despite your best efforts, you have succumbed ");
		System.out.println("to the dungeon. You must try again.");
	}
	
	//Used to make the print statements a little more dramatic
	public static void delay() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}//end delay

}//end class
