/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package textrpg;

import java.util.HashSet;
import java.util.Scanner;
import java.io.FileReader;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author Nick
 */
public class TextRPG {
    public static String DATA_LOCATION = new String("C:\\textRPG\\");
    public static int MAP_SIZE = 2;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String userInput;
        Scanner inputReader = new Scanner(System.in);
        humanPlayer mainCharacter = new humanPlayer();
        computerPlayer enemies[] = new computerPlayer[5];
        int enemyCount = 0;
        Map map = new Map(MAP_SIZE);
        map.mapInit(DATA_LOCATION);
        
        //Testing
        //System.out.println("Enter the character name: ");
        //UserInput = inputReader.nextLine();
        userInput = "Nick";
        mainCharacter.setName(userInput);
        map.printCurrRoom();
        
        while(true) {
            System.out.println("What do you want to do?");
            userInput = inputReader.nextLine().toLowerCase();
            
            if(userInput.equals("quit")) {
                //Testing
                //System.out.println("Are you sure? (y/n):");
                //userConfirmation = inputReader.nextLine();
                //if(userConfirmation.toLowerCase().equals("y")) {
                    System.out.println("Goodbye!");
                    System.exit(0);
                //}
            } else if (userInput.startsWith("go")) {
                if(map.move(userInput.substring(3))) {
                    //Clear enemies each time you enter a room
                    enemies = new computerPlayer[5];
                    enemyCount = setEnemies(map, enemies);
                }
                map.printCurrRoom();
            } else if (userInput.equals("look")) {
                map.lookCurrRoom();
            } else if(userInput.startsWith("health")) {
                System.out.print("Your health is currently: ");
                System.out.print(mainCharacter.getHealth() + "\n");
            } else if(userInput.startsWith("attack")) {
                //Check if the enemy has been declared
                //If not then check if it exists in the room
                System.out.println("Attempting to initiate fight.");
                System.out.println("Enemy count: " + enemyCount);
                if(enemyCount > 0) {
                    for(int i  = 0;i < enemyCount;i += 1) {
                        System.out.println("Enemy: " + (i + 1));
                        System.out.println(userInput.substring(7));
                        System.out.println(enemies[i].getName().toLowerCase());
                        if(userInput.substring(7).equals(enemies[i].getName().toLowerCase())) {
                            System.out.println("That enemy was found here.");
                        }
                    }
                } else {
                    System.out.println("You don't see anybody by that name here.");
                }
            }//If statement
        } //While loop
    } //Static void main
    
    /**
     *
     * @param enemies
     */
    public static int setEnemies(Map gameMap, computerPlayer enemies[]) {
        String enemyInfo = gameMap.enemyInfo();
        int enemyCount = 0;
        
        for(String info : enemyInfo.split(",")) {
            enemies[enemyCount] = new computerPlayer(info);
            enemyCount += 1;
            System.out.println("New enemy declared: " + info);
        }
        
        return enemyCount;
    }
} //Class
