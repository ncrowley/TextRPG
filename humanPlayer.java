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
public class humanPlayer extends Player {
    humanPlayer() {
        this.health = 20;
        this.level = 2;
        this.attackCaculate();
        this.defenseCalculate();
    }
    
}
