package yahtzee.domain;

import java.util.ArrayList;

// @author rpulkka
public class YahtzeeCombo implements Combination {

    private ArrayList<Die> dice;
    private CombinationType type;
    private boolean isAvailable;

    public YahtzeeCombo(ArrayList<Die> dice, CombinationType type) {
        this.dice = dice;
        this.type = type;
        this.isAvailable = false;
    }

    public int score() {
        this.isAvailable = false;
        ChosenDiceList correctDice = new ChosenDiceList();
        dice = correctDice.chosenList(dice);
        if (dice.size() != 5) {
            return 0;
        }
        if (validCombination() == true) {
            return 50;
        }
        return 0;
    }

    public boolean validCombination() {
        boolean validCombination = true;
        int number = dice.get(0).getValue();
        for (Die die : dice) {
            if (die.getValue() != number) {
                validCombination = false;
            }
        }
        return validCombination;
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
