/**
 * Name: STUDENT #2
 * Course: CSCI 241 - Computer Science I
 * Section: 001
 * Assignment Number: 8
 * 
 * Project/Class Descritpion: This class is pretty much getters and to get the players name.
 * Known Bugs:none i think
 */
import java.text.*;

public class Player 
{
private String name; // String for players name.
private int element;// what the player has rock-paper- or scissor
private int wins; // holds number of wins

    public Player (String name)
    {
        this.name  = name;//using this to get player name
        element = 0;// set the element to 0
        wins    = 0;// starts the wins off at 0
    }
    // a setter for the element
    public void setElement (int elementArg)
    {
        element = elementArg;
    }
   
    // a getter for the name
    public String getName()
    {
        
        return name;
    }
    // a getter for rock-paper- or scissor
    public  int getElement()
    {
        return element;
    }
    // a getter for amount of wins
    public  int getWins()
    {
        return wins;
    }
    // adds a win to the total
    public void addWin()
    {
        wins++;
    }
} 