package yahtzee.domain;

import java.util.ArrayList;

// @author rpulkka

/**
 * This class manages the actions that take place once the turn is over and the
 * game must be reset for the new turn to be played.
 *
 * @param typeString String that contains data of combination type.
 *
 * @see CombinationManager#countPoints(Combination)
 * @see CombinationManager#checkRound()
 * @see CombinationManager#isIllegalCombination(CombinationType)
 * @see CombinationManager#chosenDiceExist()
 */
public class Reset {

    private ArrayList<Die> dice;

    public Reset(ArrayList<Die> dice) {
        this.dice = dice;
    }

    /**
     * Resets the dice to their original locations by calling move(int, int) function
     * of the Die class for each die and sets their values to zero by calling setValue(int)
     * and sets setChosen() to false for each die so that it is known that they can't be
     * picked before throwing. Also sets DiceThrower's setTimesThrown(int) to zero to
     * reset the game and method viewText is then assigned to show getTimesThrown() on screen.
     * 
     * @see Die#move(int, int) 
     * @see Die#setValue(int) 
     * @see Die#setChosen(boolean) 
     * @see DiceThrower#setTimesThrown(int) 
     * @see DiceThrower#getTimesThrown()
     */
    public void resetNow() {
        for (Die die : dice) {
            die.setChosen(false);
            die.setValue(1);
        }
    }
}
