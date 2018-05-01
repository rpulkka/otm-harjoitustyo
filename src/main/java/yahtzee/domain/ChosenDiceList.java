package yahtzee.domain;

import java.util.ArrayList;

// @author rpulkka

/**
 * This class is used to count points for combinations that the player forms in
 * the combination area. It finds out which combination is being scored and then
 * directs the dice to the right combination handler.
 */
public class ChosenDiceList {

    public ChosenDiceList() {
    }

    public ArrayList<Die> chosenList(ArrayList<Die> dice) {
        ArrayList<Die> chosenOnes = new ArrayList<Die>();
        for (Die die : dice) {
            if (die.getChosen() == true) {
                chosenOnes.add(die);
            }
        }
        return chosenOnes;
    }
}
