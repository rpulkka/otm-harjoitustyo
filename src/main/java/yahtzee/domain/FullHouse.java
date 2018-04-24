package yahtzee.domain;

import java.util.ArrayList;

// @author rpulkka
public class FullHouse implements Combination {

    private ArrayList<Die> dice;
    private boolean isAvailable;

    public FullHouse(ArrayList<Die> dice) {
        this.dice = dice;
        this.isAvailable = false;
    }

    public int score() {
        this.isAvailable = false;

        ChosenDiceList correctDice = new ChosenDiceList();
        dice = correctDice.chosenList(dice);

        DataList datalist1 = new DataList(dice);
        ArrayList<Integer> tripleValue = datalist1.list(3);
        DataList datalist2 = new DataList(dice);
        ArrayList<Integer> pairValue = datalist2.list(2);

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
}
