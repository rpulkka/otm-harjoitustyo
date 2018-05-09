package yahtzee.domain;

import java.util.ArrayList;

/**
 * Combination handler for "sum combinations" meaning combinations that are
 * basically a sum of dice of the desired value.
 */
// @author rpulkka
public class SumCombination implements Combination {

    private CombinationType type;
    private boolean isAvailable;

    public SumCombination(CombinationType type) {
        this.type = type;
        this.isAvailable = true;
    }

    /**
     * Counts the points for the sum combinations by counting the sum of dice
     * which have the value of wantedValue().
     * 
     * @param dice The chosen dice that are being scored.
     * 
     * @see SumCombination#wantedValue() 
     * 
     * @return sum Sum of the appropriate dice values.
     */
    public int score(ArrayList<Die> dice) {
        this.isAvailable = false;
        int sum = 0;
        for (Die die : dice) {
            if (wantedValue() == 0 || die.getValue() == wantedValue()) {
                sum += die.getValue();
            }
        }
        return sum;
    }

    /**
     * Returns the value that is being looked for by checking which type of
     * combination is being scored.
     * 
     * @return Desired value.
     */
    public int wantedValue() {
        switch (type) {
            case ACES:
                return 1;
            case TWOS:
                return 2;
            case THREES:
                return 3;
            case FOURS:
                return 4;
            case FIVES:
                return 5;
            case SIXES:
                return 6;
            default:
                return 0;
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
