package yahtzee.domain;

// @author rpulkka

/**
 * This class represents one die of the dice being thrown in the game. 
 * It allows other classes to manipulate the value and coordinates of the die.
 */

public class Die {

    private int value;
    private boolean chosen;
    private int x;
    private int y;
    private int x2;
    private int y2;

    public Die(int x, int y, int x2, int y2) {
        this.value = 1;
        this.chosen = false;
        this.x = x;
        this.y = y;
        this.x2 = x2;
        this.y2 = y2;
    }

    public int getValue() {
        return value;
    }

    /**
     * Changes the value of the die.
     *
     * @param value The new value of the die.
     */
    public void setValue(int value) {
        if (value >= 0 && value <= 6) {
            this.value = value;
        }
    }
    
    /**
     * Changes the x and y coordinates of the die.
     *
     * @param x Coordinate X.
     * @param y Coordinate Y.
     */
    public void move(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Designed to be called when a die is being selected. 
     * Changes coordinates to combination area and changes boolean
     * chosen to true, meaning that the die is selected now.
     */
    public boolean pick() {

        if (this.chosen == false) {
            this.x = x2;
            this.y = y2;
            this.chosen = true;
            return true;
        }

        return false;
    }

    public boolean getChosen() {
        return chosen;
    }

    public void setChosen(boolean boo) {
        chosen = boo;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
