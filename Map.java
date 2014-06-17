/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


//package TextRPG;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Nick
 */
public class Map {
    private int size;
    private String rooms[][];
    private String exits[][];
    private String items[][];
    private String enemies[][];
    private int xx = 0, yy = 0;
    
    Map(int s) {
        size = s;
        rooms = new String[s][s];
        exits = new String[s][s];
        items = new String[s][s];
        enemies = new String[s][s];
        xx = 0;
        yy = 0;
    }
    
    public void setRoom(int x,int y,String desc, String ex, String it, String en) {
        rooms[x][y] = new String(desc);
        exits[x][y] = new String(ex);
        items[x][y] = new String(it);
        enemies[x][y] = new String(en);
    } 
    
    public void printRoom(int x,int y) {
        System.out.println(rooms[x][y]);
        System.out.println("Exits are: " + exits[x][y]);
        
    }
    
    public void printCurrRoom() {
        System.out.println(rooms[xx][yy]);
        System.out.println("Exits: " + exits[xx][yy]);
    }
    
    //Prints the look results: items in room, enemies in room, etc
    public void lookCurrRoom() {
        if(!items[xx][yy].equals("")) {
            System.out.println("Looking around you see on the ground " + items[xx][yy]);
        } else {
            System.out.println("There is nothing of value here.");
        }
        if(!enemies[xx][yy].equals("")) {
            System.out.println("Standing here you see " + enemies[xx][yy]);
        } else {
            System.out.println("There are no enemies here.");
        }
    }
    
    //Allows manually setting character location in map
    //Possibility of adding warping? Portals?
    public void setLocation(int x, int y) {
        xx = x;
        yy = y;
    }
    
    public boolean enemyCheck(String name) {
        int nameStart = 0, nameEnd = 0, count = 0;
        boolean checked = true;
        String currentEnemies = enemies[xx][yy].toLowerCase();
        
        if(!currentEnemies.equals("")) {
            for(int i = 0;i < currentEnemies.length();i+=1) {
                if(i == (currentEnemies.length() - 1) || (currentEnemies.substring(i).equals(",") && i > 0)) {
                    if(count > 1) {
                        nameStart = nameEnd + 3;
                        nameEnd = i - 1;
                    } else {
                        nameStart = nameEnd;
                        nameEnd = i - 1;
                    }
                    count += 1;
                    checked = false;
                }
                
                if(!checked) {
                    if(count > 0){
                        String nameTest = currentEnemies.substring(nameStart, nameEnd + 1);
                        if(name.equals(nameTest)) {
                            return true;
                        }
                        checked = true;
                    }
                }
            }
            
        }
        return false;
    }
    
    public String enemyInfo() {
        return enemies[xx][yy];
    }
    
    public boolean move(String d) {
        d = d.toLowerCase();
        if(d.equals("south") || d.equals("s")) {
            if(xx < (size - 1)) {
                if(exits[xx][yy].contains("s")) {
                    xx = xx + 1;
                    return true;
                } else {
                    System.out.println("There are no exits in that direction.");
                }
            } else {
                System.out.println("You have reached the edge of the map! Try another direction.");
            }
        } else if(d.equals("north") || d.equals("n")) {
            if(xx > 0) {
                if(exits[xx][yy].contains("n")) {
                    xx = xx - 1;
                    return true;
                } else {
                    System.out.println("There are no exits in that direction.");
                }
            } else {
                System.out.println("You have reached the edge of the map! Try another direction.");
            }
        } else if(d.equals("east") || d.equals("e")) {
            if(yy < (size - 1)) {
                if(exits[xx][yy].contains("e")) {
                    yy = yy + 1;
                    return true;
                } else {
                    System.out.println("There are no exits in that direction.");
                }
            } else {
                System.out.println("You have reached the edge of the map! Try another direction.");
            }
        } else if(d.equals("west") || d.equals("w")) {
            if(yy > 0) {
                if(exits[xx][yy].contains("w")) {
                    yy = yy - 1;
                    return true;
                } else {
                    System.out.println("There are no exits in that direction.");
                }
            } else {
                System.out.println("You have reached the edge of the map! Try another direction.");
            }
        }
        return false;
    }
    
    /*
    To do for this function:
    Add in the ability for comments in the map file apart from the header
    */
    public void mapInit(String dataLocation) {
        BufferedReader mapFile;
        try{
            mapFile = new BufferedReader(new FileReader(new File(dataLocation + "map.txt")));
        } catch(FileNotFoundException e) {
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
        //Use this giant try to catch any IOExceptions that might happen with the file
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
                    x = (mapNum - 1) % size;
                    y = (mapNum - 1) / size;
                    description = mapFile.readLine();
                    exits = mapFile.readLine();
                    items = mapFile.readLine();
                    enemies = mapFile.readLine();
                    counter = counter + 1;
                    setRoom(x, y, description, exits, items, enemies);
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
        if(counter == (size * size)) {
            System.out.println("File parsed successfully.\n\n");
        } else {
            System.out.println("Error reading map, not enough room entries found.");
            System.exit(1);
        }
    } //Function
}
