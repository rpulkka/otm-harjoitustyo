package yahtzee.domain;

// @author rpulkka

/**
 * This class represents one die of the dice being thrown in the game.
 * It has functionality like moving and picking the die and it allows
 * other classes to manipulate the value of the die. 
 */
import yahtzee.ui.DieImage;
import java.util.ArrayList;
import yahtzee.ui.YahtzeeUI;

public class Die {

    private YahtzeeUI ui;
    private int value;
    private DieImage valueImage;
    private ArrayList<DieImage> imageOptions;
    private boolean chosen;
    private int x;
    private int y;
    private int x2;
    private int y2;
    private DiceThrower thr;
    private final int order;

    public Die(YahtzeeUI ui, int x, int y, int x2, int y2, int order) {
        this.ui = ui;
        this.value = 1;
        this.valueImage = ui.getImages().get(0);
        this.imageOptions = ui.getImages();
        this.chosen = false;
        this.x = x;
        this.y = y;
        this.x2 = x2;
        this.y2 = y2;
        this.thr = ui.getThrower();
        this.order = order;
    }

    public int getValue() {
        return value;
    }

    /**
     * Changes the value of the die and calls changeImage(int) so that
     * the right image of the die will be shown to the player.
     *
     * @param value The new value of the die.
     *
     * @see Die#changeImage(int) 
     */
    public void setValue(int value) {
        if (value >= 0 && value <= 6) {
            this.value = value;
            changeImage(value);
        }
    }
    
    /**
     * Calls viewImage(int, int) method to change the image to fit
     * the new value of the die.
     *
     * @param value The new value of the die.
     *
     * @see CombinationManager#countPoints(Combination)
     * @see CombinationManager#checkRound()
     * @see CombinationManager#isIllegalCombination(CombinationType)
     * @see CombinationManager#chosenDiceExist()
     */
    public void changeImage(int value) {
        valueImage = imageOptions.get(value-1);
        ui.viewImage(order, valueImage);
    }

    /**
     * Changes the x and y coordinates of the die and calls moveImage(int, int)
     * to change the image location on screen.
     *
     * @param x Coordinate X.
     * @param y Coordinate Y.
     */
    public void move(int x, int y) {
        this.x = x;
        this.y = y;
        ui.moveImage(this.x, this.y, order);
    }

    /**
     * Designed to be called when a die is being selected. Calls move(int, int)
     * function to move the die to the combination area and changes boolean chosen
     * to true, meaning that the die is selected now.
     */
    public void pick() {
        if (thr.getTimesThrown() != 0) {
            if (this.chosen == false) {
                this.x = x2;
                this.y = y2;
                this.move(x, y);
                this.chosen = true;
            }
        }
    }

    public DieImage getImage() {
        return this.valueImage;
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

    public void setThr(DiceThrower thr) {
        this.thr = thr;
    }
}
