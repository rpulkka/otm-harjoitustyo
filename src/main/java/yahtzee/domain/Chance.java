package yahtzee.domain;

import java.util.ArrayList;

// @author rpulkka
public class Chance implements Combination {

    private ArrayList<Die> dice;
    private CombinationType type;
    private boolean isAvailable;

    public Chance(ArrayList<Die> dice, CombinationType type) {
        this.dice = dice;
        this.type = type;
        this.isAvailable = false;
    }

    public int score() {
        this.isAvailable = false;

        ChosenDiceList correctDice = new ChosenDiceList();
        dice = correctDice.chosenList(dice);

        int sum = 0;

        for (Die die : dice) {
            sum += die.getValue();
        }

        return sum;
    }

    public boolean getIsAvailable() {
        return this.isAvailable;
    }

    public void setIsAvailable(boolean b) {
        this.isAvailable = b;
    }

    @Override
    public CombinationType getCombinationType() {
        return this.type;
    }

}
