package yahtzee.domain;

import java.util.ArrayList;

// @author rpulkka
public class DiceThrower {

    private ArrayList<Die> dice;
    private int timesThrown;

    public DiceThrower(ArrayList<Die> dice) {
        this.dice = dice;
        this.timesThrown = 0;
    }

    public void throwDice() {
        if (timesThrown < 3) {
            for (Die die : dice) {
                if (die.getChosen() == false) {
                    int random = (int) (Math.random() * 6 + 1);
                    die.setValue(random);
                }
            }
            timesThrown++;
        }

    }

    public int getTimesThrown() {
        return timesThrown;
    }

    public void setTimesThrown(int a) {
        timesThrown = a;
    }
}
