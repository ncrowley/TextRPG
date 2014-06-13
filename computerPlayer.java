/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


//package textrpg;

/**
 *
 * @author Nick
 */
public class computerPlayer extends Player {
    computerPlayer(String n) {
        this.name = n;
        this.level = 1;
        this.health = 20;
        this.attackCaculate();
        this.defenseCalculate();
    }
    
}
