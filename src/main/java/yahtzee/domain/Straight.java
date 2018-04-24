package yahtzee.domain;

import java.util.ArrayList;
import java.util.Collections;

//@author rpulkka
public class Straight implements Combination {

    ArrayList<Die> requirements;
    private ArrayList<Die> dice;
    private String smallOrLarge;
    private boolean isAvailable;

    public Straight(ArrayList<Die> dice, String type) {
        requirements = new ArrayList<Die>();
        this.dice = dice;
        smallOrLarge = type;
        this.isAvailable = false;
    }

    public int score() {
        this.isAvailable = false;

        ChosenDiceList correctDice = new ChosenDiceList();

        if (correctDice.chosenList(dice).size() != 5) {
            return 0;
        }

        dice = correctDice.chosenList(dice);

        ArrayList<Integer> org = new ArrayList<Integer>();
        ArrayList<Integer> req = new ArrayList<Integer>();

        if (smallOrLarge.equals("large")) {
            req.add(2);
            req.add(3);
            req.add(4);
            req.add(5);
            req.add(6);
        } else if (smallOrLarge.equals("small")) {
            req.add(1);
            req.add(2);
            req.add(3);
            req.add(4);
            req.add(5);
        }

        org.add(dice.get(0).getValue());
        org.add(dice.get(1).getValue());
        org.add(dice.get(2).getValue());
        org.add(dice.get(3).getValue());
        org.add(dice.get(4).getValue());

        Collections.sort(org);

        if (org.equals(req)) {
            if (smallOrLarge.equals("large")) {
                return 20;
            } else if (smallOrLarge.equals("small")) {
                return 15;
            }
        } else {
            return 0;
        }
        return 0;
    }

    public boolean getIsAvailable() {
        return this.isAvailable;
    }

    public void setIsAvailable(boolean b) {
        this.isAvailable = b;
    }
}
