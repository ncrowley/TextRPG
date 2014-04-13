/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package textrpg;

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
}
