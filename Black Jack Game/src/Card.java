import com.sun.jdi.Value;

import java.awt.*;

/**
 * Created by chales on 11/6/2017.
 */
public class Card {

    //VARIABLE DECLARATION SECTION
    //Here's where you state which variables you are going to use.
    public int suitValue;                //holds the name of the hero
    //a boolean to denote if the hero is alive or dead.
    public int cardvalue;
    public int realcardvalue;
    int x1, y1, x2, y2;
    public int xpos;                //the x position
    public int ypos;                //the y position
    public int dx;                    //the speed of the hero in the x direction
    public int dy;                    //the speed of the hero in the y direction
    public int width;
    public int height;
    public boolean used;

    // METHOD DEFINITION SECTION

    // Constructor Definition
    // A constructor builds the object when called and sets variable values.


    //This is a SECOND constructor that takes 3 parameters.  This allows us to specify the hero's name and position when we build it.
    // if you put in a String, an int and an int the program will use this constructor instead of the one above.
    public Card(int suit, int value) {
        suitValue = suit;
        cardvalue = value;
        if (cardvalue >=10 ){
            realcardvalue = 10;
        }
        else{
            realcardvalue = cardvalue;
        }
        if (cardvalue == 1){
            realcardvalue = 11;
        }

//        xpos = pXpos;
//        ypos = pYpos;
        dx = 1;
        dy = 0;
        width = 60;
        height = 60;
        used = true;

    } // constructor

    //The move method.  Everytime this is run (or "called") the hero's x position and y position change by dx and dy
    public void move() {
        xpos = xpos + dx;
        ypos = ypos + dy;

    }
}






