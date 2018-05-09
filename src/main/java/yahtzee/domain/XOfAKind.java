package yahtzee.domain;

import java.util.ArrayList;
import static yahtzee.domain.Combination.CombinationType.*;

// @author rpulkka
/**
 * A class meant to handle "XOfAKind" combinations which are combinations that
 * require some number of instances of dice values to receive points. The
 * approach is different from "sum combinations" because sum combinations
 * already know which value is being looked for, when in XOfAKind, only the
 * number of instances is known and values can vary.
 */
public class XOfAKind implements Combination {

    private CombinationType type;
    private boolean isAvailable;

    public XOfAKind(CombinationType type) {
        this.type = type;
        this.isAvailable = false;
    }

    /**
     * This method returns the score given for a XOfAKind combination. It calls
     * countPoints with different parameters depending on what type of
     * combination is being scored.
     *
     * @param dice The chosen dice that are being scored.
     *
     * @see XOfAKind#countPoints(yahtzee.domain.InstanceList, int, int)
     *
     * @return The points given for the combination.
     */
    public int score(ArrayList<Die> dice) {
        this.isAvailable = false;
        InstanceList instanceLister = new InstanceList(dice);
        if (type.equals(PAIR)) {
            return countPoints(instanceLister, 2, 1);
        } else if (type.equals(TWOPAIRS)) {
            return countPoints(instanceLister, 2, 2);
        } else if (type.equals(THREEOFAKIND)) {
            return countPoints(instanceLister, 3, 1);
        } else if (type.equals(FOUROFAKIND)) {
            return countPoints(instanceLister, 4, 1);
        } else if (type.equals(YAHTZEE)) {
            return countPoints(instanceLister, 5, 1);
        }
        return 0;
    }

    /**
     * This method calls limitedList() using the parameters given to it. It also
     * checks if the combination being scored is Yahtzee or Two Pairs as those
     * need a little extra functionality.
     *
     * @param instanceLister An object that creates instance lists.
     * @param lowerLimit Lowest legitimate number of instances.
     * @param appropriateValues Desired number of values that are legitimate.
     *
     * @return The points given for the combination.
     */
    public int countPoints(InstanceList instanceLister, int lowerLimit, int appropriateValues) {
        ArrayList<Integer> instanceList = instanceLister.limitedList(lowerLimit);
        if (instanceList.size() >= appropriateValues) {
            if (type.equals(YAHTZEE)) {
                return 50;
            } else if (type.equals(TWOPAIRS)) {
                return instanceList.get(0) * lowerLimit + instanceList.get(1) * lowerLimit;
            } else {
                return instanceList.get(0) * lowerLimit;
            }
        }
        return 0;
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
