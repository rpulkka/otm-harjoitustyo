package yahtzee.domain;

import java.util.ArrayList;

// @author rpulkka
public class FullHouse implements Combination {

    private ArrayList<Die> dice;
    private CombinationType type;
    private boolean isAvailable;

    public FullHouse(ArrayList<Die> dice, CombinationType type) {
        this.dice = dice;
        this.type = type;
        this.isAvailable = false;
    }

    public int score() {
        this.isAvailable = false;
        ChosenDiceList correctDice = new ChosenDiceList();
        dice = correctDice.chosenList(dice);
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
