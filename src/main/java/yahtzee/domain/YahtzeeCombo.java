
package yahtzee.domain;

import java.util.ArrayList;

// @author rpulkka

public class YahtzeeCombo implements Combination {

    private ArrayList<Die> dice;
    private boolean isAvailable;
    private boolean alreadyScored;

    public YahtzeeCombo(ArrayList<Die> dice) {
        this.dice = dice;
        this.isAvailable = false;
        this.alreadyScored = false;
    }

    public int score() {
        this.isAvailable = false;

        ChosenDiceList correctDice = new ChosenDiceList();
        dice = correctDice.chosenList(dice);
        if (dice.size() != 5) {
            return 0;
        }

        boolean validCombination = true;

        int number = dice.get(0).getValue();
        for (Die die : dice) {
            if (die.getValue() != number) {
                validCombination = false;
            }
        }
        if (validCombination == true) {
            return 50;
        }
        return 0;
    }

    public boolean getIsAvailable() {
        return this.isAvailable;
    }

    public void setIsAvailable(boolean b) {
        this.isAvailable = b;
    }

    public boolean getAlreadyScored() {
        return this.alreadyScored;
    }

    public void setAlreadyScored(boolean b) {
        this.alreadyScored = true;
    }
}
