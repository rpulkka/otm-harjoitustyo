package yahtzee.domain;

import java.util.ArrayList;

// @author rpulkka
/**
 * A class that manages all the actions that will take place when the dice are
 * being thrown.
 */
public class DiceThrower {

    private ArrayList<Die> dice;
    private int timesThrown;

    public DiceThrower() {
        this.timesThrown = 0;
    }

    /**
     * Designed to be called once the dice are thrown. It checks if the
     * conditions to throw the dice are met and and it checks which dice are
     * already selected. It changes the values of the thrown dice by passing a
     * suitable random number to Die class' setValue(int) method.
     *
     * @see Die#setValue(int)
     * @see Die#setChosen(boolean)
     * @see DiceThrower#writeTimesThrown()
     */
    public String throwDice() {
        if (timesThrown < 3) {
            for (Die die : dice) {
                if (timesThrown == 0) {
                    die.setChosen(false);
                }
                if (die.getChosen() == false) {
                    int random = (int) (Math.random() * 6 + 1);
                    die.setValue(random);
                }
            }
            return writeTimesThrown();
        }
        return null;
    }

    /**
     * Adds the value of timesThrown counter and creates the text from it, which
     * can be viewed on screen.
     */
    public String writeTimesThrown() {
        timesThrown++;
        String text = ("Times thrown: " + timesThrown + "/3");
        return text;
    }

    public int getTimesThrown() {
        return timesThrown;
    }

    public void setTimesThrown(int a) {
        timesThrown = a;
    }

    public void setDice(ArrayList<Die> dice) {
        this.dice = dice;
    }
}
