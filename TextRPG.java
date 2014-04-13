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
        mapInit(map);
        
        //Testing
        //System.out.println("Enter the character name: ");
        //UserInput = inputReader.nextLine();
        userInput = "Nick";
        mainCharacter.setName(userInput);
        map.printCurrRoom();
        
        while(true) {
            System.out.println("What do you want to do?");
            userInput = inputReader.nextLine();
            
            if(userInput.equals("quit")) {
                System.out.println("Are you sure? (y/n):");
                userConfirmation = inputReader.nextLine();
                if(userConfirmation.toLowerCase().equals("y")) {
                    System.out.println("Goodbye!");
                    System.exit(0);
                }
            } else if (userInput.startsWith("go")) {
                map.move(userInput.substring(3));
                map.printCurrRoom();
            } else if (userInput.equals("look")) {
                map.lookCurrRoom();
            }//If statement
        } //While loop
    } //Static void main
    
    /*
    To do for this function:
    Add in the ability for comments in the map file apart from the header
    */
    public static void mapInit(Map gameMap) {
        BufferedReader mapFile;
        try{
            mapFile = new BufferedReader(new FileReader(new File(DATA_LOCATION + "map.txt")));
        } catch(FileNotFoundException e) {
            System.out.println(e.getMessage());
            return;
        } catch(IOException e) {
            System.out.println(e.getMessage());
            return;
        }
        String mapLine = new String();
        String description = new String();
        String exits = new String();
        String items = new String();
        String enemies = new String();
        
        int mapNum=0,x=0,y=0;
        int counter = 0;
        try{
        do{
            mapLine = mapFile.readLine();
        } while(mapLine.startsWith("#"));
        
        while(mapLine != null) {
            if(mapLine.equals(":")) {
                //Room number
                mapLine = mapFile.readLine();
                mapNum = Integer.parseInt(mapLine);
                System.out.println("Room number: " + mapNum);
                x = (mapNum - 1) % MAP_SIZE;
                y = (mapNum - 1) / MAP_SIZE;
                description = mapFile.readLine();
                exits = mapFile.readLine();
                items = mapFile.readLine();
                enemies = mapFile.readLine();
                counter = counter + 1;
                gameMap.setRoom(x, y, description, exits, items, enemies);
            }
            mapLine = mapFile.readLine();                    
        } //While
        
        mapFile.close();
        } catch(FileNotFoundException e) {
            System.out.println("File not found.");
            System.out.println(e.getMessage());
        } catch(IOException e) {
            System.out.println("File read error.");
            System.out.println(e.getMessage());            
        }
        if(counter == (MAP_SIZE * MAP_SIZE)) {
            System.out.println("File parsed successfully.\n\n");
        } else {
            System.out.println("Error reading map, not enough room entries found.");
            System.exit(1);
        }
    } //Function
    
} //Class
