package yahtzee.domain;

import java.util.ArrayList;

// @author rpulkka
public class FirstRoundCombination implements Combination {

    private CombinationType type;
    private ArrayList<Die> dice;
    private boolean isAvailable;

    public FirstRoundCombination(ArrayList<Die> dice, CombinationType type) {
        this.type = type;
        this.dice = dice;
        this.isAvailable = true;
    }

    public int score() {
        this.isAvailable = false;
        ChosenDiceList correctDice = new ChosenDiceList();
        dice = correctDice.chosenList(dice);
        int sum = 0;
        for (Die die : dice) {
            if (die.getValue() == wantedValue()) {
                sum += die.getValue();
            }
        }
        return sum;
    }
    
    public int wantedValue() {
        switch(type) {
            case ACES: return 1;
            case TWOS: return 2;
            case THREES: return 3;
            case FOURS: return 4;
            case FIVES: return 5;
            case SIXES: return 6;
            default: return 0;
        }
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
