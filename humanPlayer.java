/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Nick
 */
public class humanPlayer extends Player {
    humanPlayer() {
        this.level = 2;
        this.healthCalculate();
        this.attackCalculate();
        this.defenseCalculate();
    }
}
