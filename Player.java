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
    protected int health;
    protected int level;
    protected int attack;
    protected int defense;
    
    protected double experience;
    
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
    }    
    
    Player(Player p) {
    	name = p.getName();
    	level = p.getLevel();
    	experience = p.getExp();
    	health = p.getHealth();
    	attack = p.getAttack();
    	defense = p.getDefense();
    }
    
    protected void setName(String n) {
        name = n;
    }
    
    protected void attackCalculate() {
        attack = level * 5;
    }
    
    protected void defenseCalculate() {
        defense = level * 4;
    }
    
    protected void healthCalculate() {
    	health = 90 + (level * 10);
    }
    
    public int getHealth() {
        return health;
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
    
    //Calculates damage taken by an attack and updates health
    public int defend(int incAttack) {
        int initHealth = health;
        if(defense < incAttack) {
            health = health - (incAttack - defense);
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
        int randMiss = (int)(Math.random()*100);
        int	randCrit = (int)(Math.random()*100);
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
