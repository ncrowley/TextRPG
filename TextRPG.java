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
        String userInput, userConfirmation;
        Scanner inputReader = new Scanner(System.in);
        humanPlayer mainCharacter = new humanPlayer();
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
                map.move(userInput.substring(3));
                map.printCurrRoom();
            } else if (userInput.equals("look")) {
                map.lookCurrRoom();
            } else if(userInput.startsWith("health")) {
                System.out.print("Your health is currently: ");
                System.out.print(mainCharacter.getHealth());
            }//If statement
        } //While loop
    } //Static void main
} //Class
