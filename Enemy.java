//Xandra Quevedo
//Started Nov 6 2025
//Finished Dec 4 2025
//Enemy class for Dungeons of Java

public class Enemy {
	
	//Data variables - enemy info
	//Enemy name, HP, and power
	private String enemyName;
	private Integer enemyHP;
	private Integer enemyPower;
		
	//Parameterized constructor
	public Enemy (String enemyName, int enemyHP, int enemyPower) {
		this.enemyName = enemyName;
		this.enemyHP = enemyHP;
		this.enemyPower = enemyPower;
	}
	
	//Non-parameterized constructor
	public Enemy () {
		this.enemyName = null;
		this.enemyHP = 0;
		this.enemyPower = 0;
	}
		
	//Setters
	public void setEnemyName (String enemyName) {
		this.enemyName = enemyName;
	}
	public void setEnemyHP (int enemyHP) {
		this.enemyHP = enemyHP;
	}
	public void setEnemyPower (int enemyPower) {
		this.enemyPower = enemyPower;
	}
	
	//Getters
	public String getEnemyName() {
		return enemyName;
	}
	public int getEnemyHP() {
		return enemyHP;
	}
	public int getEnemyPower() {
		return enemyPower;
	}
	
	//For testing purposes 
	@Override
	public String toString() {
		System.out.println();
		return this.enemyName + ": " + this.enemyHP + " HP, " + this.enemyPower + " POW";
	}
	
}
