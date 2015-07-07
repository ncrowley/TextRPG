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
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Nick
 */
public class Map2 {
    private List<Room> rooms = new ArrayList<Room>();
    private int currRoom;
    
    Map2(String mapLocation) {
    	mapInit(mapLocation);
    	currRoom = 1; 			//Starting room
    }
    
    
    public void printRoom(String roomName) {
        System.out.println(rooms.get(currRoom).getName());
        for(String s : rooms.get(currRoom).getExits()) {
        	System.out.print(s + " ");
        }
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
    public void mapInit(String mapLocation) {
    	String roomName;
    	String description;
    	List<computerPlayer> enemies;
    	List<computerPlayer> npcs;
    	List<item> items;
    	List<String> exits;
    	
	    try {
			File fXmlFile = new File(mapLocation);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
		 
			doc.getDocumentElement().normalize();
		 
			NodeList nList = doc.getElementsByTagName("room");
		 
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				System.out.println("\nCurrent Element :" + nNode.getNodeName());
		 
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
		 
					Element eElement = (Element) nNode;
					
					System.out.println("Room ID: " + eElement.getAttribute("id"));
					System.out.println("Room Name: " + eElement.getElementsByTagName("name").item(0).getTextContent());
					for(int i = 0;i < eElement.getElementsByTagName("exit").getLength();i++) {
						System.out.println("Exit " + (i + 1) + ": " + eElement.getElementsByTagName("exit").item(i).getTextContent());
					}
					System.out.println("Enemy: " + eElement.getElementsByTagName("enemies").item(0).getTextContent());
					System.out.println("Items: " + eElement.getElementsByTagName("items").item(0).getTextContent());
					//rooms.add(new Room(mapLocation, mapLocation, null, temp, null, temp, null, temp, null, temp));
		 
				}
			}
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
    } //Function
    
    public List<computerPlayer> getCurrEnemies() {
    	return rooms.get(currRoom).getEnemies();
    }
}
