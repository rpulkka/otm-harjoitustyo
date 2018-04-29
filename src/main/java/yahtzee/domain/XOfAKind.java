package yahtzee.domain;

import java.util.ArrayList;
import static yahtzee.domain.Combination.CombinationType.FOUROFAKIND;
import static yahtzee.domain.Combination.CombinationType.THREEOFAKIND;

// @author rpulkka
public class XOfAKind implements Combination {

    private ArrayList<Die> dice;
    private CombinationType type;
    private boolean isAvailable;

    public XOfAKind(ArrayList<Die> dice, CombinationType type) {
        this.dice = dice;
        this.isAvailable = false;
        this.type = type;
    }

    public int score() {
        this.isAvailable = false;

        ChosenDiceList correctDice = new ChosenDiceList();
        dice = correctDice.chosenList(dice);

        DataList datalist = new DataList(dice);
        ArrayList<Integer> value;
        if (type.equals(THREEOFAKIND)) {
            value = datalist.list(3);
            if (value.isEmpty() == false) {
                return value.get(0) * 3;
            } else {
                return 0;
            }
        } else if (type.equals(FOUROFAKIND)) {
            value = datalist.list(4);
            if (value.isEmpty() == false) {
                return value.get(0) * 4;
            } else {
                return 0;
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
