//Xandra Quevedo
//Started Nov 6 2025
//Player class for Dungeons of Java

public class Player {
	//Data variables - player info
	//Name from user input + base HP, power, and defense
	private String playerName;
	private int playerHP;
	private int playerPower;
	private int playerDefense;
	private int playerX;
	private int playerY;
	
	//Initial values for the player's info and location
	//The first position is (1, 0) due to the map's shape
	private final int BASE_HEALTH_POINTS = 10;
	private final int BASE_DAMAGE = 5;
	private final int BASE_DEFENSE = 5;
	private final int INITIAL_X = 1;
	private final int INITIAL_Y = 0;	

	
	//Partially parameterized constructor - the one that is used
	public Player (String playerName) {
		this.playerName = playerName;
		this.playerHP = BASE_HEALTH_POINTS;
		this.playerPower = BASE_DAMAGE;
		this.playerDefense = BASE_DEFENSE;
		this.playerX = INITIAL_X;
		this.playerY = INITIAL_Y;
	}
	
	//Fully parameterized constructor
	public Player (String playerName, int playerHP, int playerPower, 
			int playerDefense, int playerX, int playerY) {
		this.playerName = playerName;
		this.playerHP = playerHP;
		this.playerPower = playerPower;
		this.playerDefense = playerDefense;
		this.playerX = playerX;
		this.playerY = playerY;
	}
	
	//Non-parameterized constructor
	//Shouldn't ever use this but it's here for safety
	public Player() {
		this.playerName = "MISSING";
		this.playerHP = BASE_HEALTH_POINTS;
		this.playerPower = BASE_DAMAGE;
		this.playerDefense = BASE_DEFENSE;
		this.playerX = INITIAL_X;
		this.playerY = INITIAL_Y;
	}
	
	//Setters
	public void setPlayerName (String playerName) {
		this.playerName = playerName;
	}
	public void setPlayerHP (int playerHP) {
		this.playerHP = playerHP;
	}
	public void setPlayerPower (int playerPower) {
		this.playerPower = playerPower;
	}
	public void setPlayerDefense (int playerDefense) {
		this.playerDefense = playerDefense;
	}
	public void setPlayerX (int playerX) {
		this.playerX = playerX;
	}
	public void setPlayerY (int playerY) {
		this.playerY = playerY;
	}
	
	//Getters
	public String getPlayerName() {
		return playerName;
	}
	public int getPlayerHP() {
		return playerHP;
	}
	public int getPlayerPower() {
		return playerPower;
	}
	public int getPlayerDefense() {
		return playerDefense;
	}
	public int getPlayerX() {
		return playerX;
	}
	public int getPlayerY() {
		return playerY;
	}
	
	//		Algorithm: Player movement
	//		Changes player position based on input
	//Method to handle movement - this is used to update
	//the player's position if collision succeeds.
		//Note: A prior implementation had collision checked
		//within this class, but this was changed so that
		//everything is handled in DungeonsOfJava.
	public void move(String move) {
		//System.out.println("Called Player move");
		if (move.equals("W")) {
			this.playerY = playerY + 1;
		}//end W-move
		
		if (move.equals("A")) {
			this.playerX = playerX - 1;
		}//end A-move
		
		if (move.equals("S")) {
			this.playerY = playerY - 1;
		}//end S-move
		
		if (move.equals("D")) {
			this.playerX = playerX + 1;
		}//end D-move
	}//end move
	
	//toString - useful for checking if methods that affect the player
	//are actually working. Surrounded by newlines to be more easily found.
	@Override 
	public String toString() {
		return "\n" + playerName + ": HP = " + playerHP + " | POW = " + playerPower +
				" | DEF = " + playerDefense + " | X = " + playerX + " | Y = " 
				+ playerY + "\n";
	}
	
}//end Player
