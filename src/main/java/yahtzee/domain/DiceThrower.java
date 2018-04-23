package yahtzee.domain;

import java.util.ArrayList;
import javafx.scene.control.Label;

// @author rpulkka
public class DiceThrower {

    private ArrayList<Die> dice;
    private int timesThrown;

    public DiceThrower(ArrayList<Die> dice) {
        this.dice = dice;
        this.timesThrown = 0;
    }

    public void throwDice(Label label) {
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
            timesThrown++;
            label.setText("Times thrown: "+timesThrown+"/3");
        }

    }

    public int getTimesThrown() {
        return timesThrown;
    }

    public void setTimesThrown(int a) {
        timesThrown = a;
    }
}
