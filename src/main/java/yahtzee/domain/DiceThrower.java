package yahtzee.domain;

import java.util.ArrayList;
import yahtzee.ui.YahtzeeUI;

// @author rpulkka
public class DiceThrower {

    private YahtzeeUI ui;
    private ArrayList<Die> dice;
    private int timesThrown;

    public DiceThrower(YahtzeeUI ui) {
        this.ui = ui;
        this.timesThrown = 0;
    }

    public void throwDice() {
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
            writeTimesThrown();
        }
    }
    
    public void writeTimesThrown() {
        timesThrown++;
        String text = ("Times thrown: " + timesThrown + "/3");
        ui.viewText(text);
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
