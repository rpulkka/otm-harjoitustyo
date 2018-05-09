package yahtzee.domain;

import java.util.ArrayList;

// @author rpulkka
/**
 * A class for handling the Full House combination scoring. It is
 * separated from XOfAKind because Full House functions a little bit
 * differently as it needs two different sizes of instance lists. In detail,
 * another difference is that the number of instances needed for a Full House is
 * exact, and the others can use a function that counts instances higher than x.
 */
public class FullHouse implements Combination {

    private CombinationType type;
    private boolean isAvailable;

    public FullHouse(CombinationType type) {
        this.type = type;
        this.isAvailable = false;
    }

    /**
     * This method counts the score for the Full House combination. It first
     * gets the chosen dice with help of ChosenDiceList and then gets 2 instance
     * lists, one with the value of 2 instances and other with the value of 3 
     * instances. If the lists (combinations) exist, it counts the sum of the 
     * dice and returns the sum.
     * 
     * @param dice The chosen dice that are being scored.
     *
     * @see ChosenDiceList#chosenList(java.util.ArrayList) 
     * @see InstanceList#getList(int) 
     *
     * @return sum Sum of the dice, if full house exists, else 0.
     */
    public int score(ArrayList<Die> dice) {
        this.isAvailable = false;
        InstanceList instanceList1 = new InstanceList(dice);
        ArrayList<Integer> tripleValue = instanceList1.getList(3);
        InstanceList instanceList2 = new InstanceList(dice);
        ArrayList<Integer> pairValue = instanceList2.getList(2);
        if (tripleValue.isEmpty() || pairValue.isEmpty()) {
            return 0;
        } else {
            int sum = 0;
            for (Die die : dice) {
                sum += die.getValue();
            }
            return sum;
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
