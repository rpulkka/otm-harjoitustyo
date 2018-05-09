package yahtzee.domain;

import java.util.ArrayList;
import static yahtzee.domain.Combination.CombinationType.*;

// @author rpulkka
public class XOfAKind implements Combination {

    private ArrayList<Die> dice;
    private CombinationType type;
    private boolean isAvailable;

    public XOfAKind(ArrayList<Die> dice, CombinationType type) {
        this.dice = dice;
        this.type = type;
        this.isAvailable = false;
    }

    public int score() {
        this.isAvailable = false;
        ChosenDiceList correctDice = new ChosenDiceList();
        dice = correctDice.chosenList(dice);
        InstanceList instanceLister = new InstanceList(dice);
        if (type.equals(PAIR)) {
            return countPointsDefault(instanceLister, 2, 1);
        } else if (type.equals(TWOPAIRS)) {
            return countPointsDefault(instanceLister, 2, 2);
        } else if (type.equals(THREEOFAKIND)) {
            return countPointsDefault(instanceLister, 3, 1);
        } else if (type.equals(FOUROFAKIND)) {
            return countPointsDefault(instanceLister, 4, 1);
        } else if (type.equals(YAHTZEE)) {
            return countPointsDefault(instanceLister, 5, 1);
        }
        return 0;
    }

    public int countPointsDefault(InstanceList instanceLister, int lowerLimit, int appropriateValues) {
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
