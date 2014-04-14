/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package textrpg;

import java.util.Random;


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
    
    Player() {
        System.out.println("Player created, not initialized.");
    }
    
    protected void setName(String n) {
        name = n;
    }
    
    protected void attackCaculate() {
        attack = level * 5;
    }
    
    protected void defenseCalculate() {
        defense = level * 4;
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
    
    //Calculates damage taken by an attack and updates health
    public int defend(int incAttack) {
        int initHealth = health;
        if(defense <= incAttack) {
            health = health - (incAttack - defense);
        }
        
        return (initHealth - health);
    }
    
    //Calculates strength of an attack
    public int attack() {
        int MAX = attack;
        //Possibly add attack floor
        int MIN = 0;
        //Inverted boolean so on strike missed miss = 0
        int miss = 1;
        //This allows simple multiplication by the final result
        int random;
        int totalStrike;
        
        if((int)(Math.random()*100) > 95) {
            miss = 0;   //Strike is a miss
        }
        totalStrike = MIN + (int)(Math.random() * ((MAX - MIN) + 1));
        
        return totalStrike * miss;
    }
    
    // It is reocmmended that this method is overwritten.
    private void death() {
        System.out.println(name + " has died.");
    }
}
