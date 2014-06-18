/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//import java.util.Random;


/**
 *
 * @author Nick
 */
public class Player {
	//Add strength, intelligence, speed
    protected int health;		//Current health
    protected int healthMax;	//Maximum health
    protected int level;
    protected int attack;
    protected int defense;
    
    protected double experience;
    protected int expToLevel;
    protected String[] inventory = new String [20];
    protected String[] equipment = new String [5];
    protected String name = new String();
    
    protected boolean dead = false;
    
    Player() {
    	level = 1;
    	attackCalculate();
    	defenseCalculate();
    }
    
    Player(String n) {
    	name = n;
    	healthCalculate();
    	attackCalculate();
    	defenseCalculate();
    	expToLevelCalculate();
    }    
    
    protected void setName(String n) {
        name = n;
    }
    
    protected void attackCalculate() {
    	//Eventually add in items / stats
    	attack = 20 + level * 5;
    }
    
    protected void defenseCalculate() {
    	//Eventually add in items / stats
        defense = 10 + level * 4;
    }
    
    protected void healthCalculate() {
    	health = 90 + (level * 10);
    	healthMax = health;
    }
    
    protected void expToLevelCalculate() {
    	expToLevel = 100 * level;
    }
    
    public int getCurrHealth() {
        return health;
    }
    
    public int getHealthMax() {
    	return healthMax;
    }
    
    public int getAttack() {
        return attack;
    }
    
    public int getDefense() {
        return defense;
    }
    
    public int getLevel() {
    	return level;
    }
    
    public double getExp() {
    	return experience;
    }
    
    //Calculate experience gained by defeating an enemy
    public void defeated(computerPlayer p) {
    	
    }
    
    private void checkLevelUp() {
    	
    }
    
    //Calculates damage taken by an attack and updates health
    public int defend(int incAttack) {
        int initHealth = health;
        double defenseRand = 0.5 + Math.random() * 1.5;

        if((defense * defenseRand) < incAttack) {
            health = health - (incAttack - (int)(defense * defenseRand));
        }
        return (initHealth - health);
    }
    
    //Calculates strength of an attack
    public int attack() {
        int MAX = attack;
        //Possibly add attack floor
        int MIN = level / 4 + 1;
        //Inverted boolean so on strike missed miss = 0
        //This allows simple multiplication by the final result
        int miss = 1;
        int randMiss = (int)(Math.random() * 100);
        int	randCrit = (int)(Math.random() * 100);
        int totalStrike;
        
        totalStrike = MIN + (int)(Math.random() * ((MAX - MIN) + 1));
        
        if(randMiss > 97) {
            miss = 0;   //Strike is a miss
        } else if(randCrit > 97) {
        	totalStrike = totalStrike * 2;
        }
        
        return totalStrike * miss;
    }
    
    public boolean isDead() {
        if(!dead) {
            if(health <= 0) {
                System.out.println(name + " has died.");
                dead = true;
            }
        }
        return dead;
    }
    
    public String getName() {
        return name;
    }
}
