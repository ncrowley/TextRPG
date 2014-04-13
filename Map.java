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
    
    public void lookCurrRoom() {
        if(items[xx][yy] != "") {
            System.out.println("Looking around you see on the ground " + items[xx][yy]);
        } else {
            System.out.println("You see nothing of value here.");
        }
        if(enemies[xx][yy] != "") {
            System.out.println("Standing here you see " + enemies[xx][yy]);
        } else {
            System.out.println("There are no enemies here.");
        }
    }
    
    public void setRoom(int x, int y) {
        xx = x;
        yy = y;
    }
    
    public boolean move(String d) {
        if(d.equals("south")) {
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
        } else if(d.equals("north")) {
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
        } else if(d.equals("east")) {
            if(yy > 0) {
                if(exits[xx][yy].contains("e")) {
                    yy = yy - 1;
                    return true;
                } else {
                    System.out.println("There are no exits in that direction.");
                }
            } else {
                System.out.println("You have reached the edge of the map! Try another direction.");
            }
        } else if(d.equals("west")) {
            if(xx < (size - 1)) {
                if(exits[xx][yy].contains("w")) {
                    yy = yy + 1;
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
}
