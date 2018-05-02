package yahtzee.domain;

import java.util.ArrayList;
import java.util.Collections;
import static yahtzee.domain.Combination.CombinationType.LARGESTRAIGHT;
import static yahtzee.domain.Combination.CombinationType.SMALLSTRAIGHT;

//@author rpulkka
public class Straight implements Combination {

    ArrayList<Die> requirements;
    private ArrayList<Die> dice;
    private CombinationType type;
    private boolean isAvailable;

    public Straight(ArrayList<Die> dice, CombinationType type) {
        requirements = new ArrayList<Die>();
        this.dice = dice;
        this.type = type;
        this.isAvailable = false;
    }

    public int score() {
        this.isAvailable = false;
        ChosenDiceList correctDice = new ChosenDiceList();
        if (correctDice.chosenList(dice).size() != 5) {
            return 0;
        }
        dice = correctDice.chosenList(dice);
        ArrayList<Integer> org = originalList();
        ArrayList<Integer> req = getRequirements();
        if (org.equals(req)) {
            if (type.equals(LARGESTRAIGHT)) {
                return 20;
            } else if (type.equals(SMALLSTRAIGHT)) {
                return 15;
            }
        }
        return 0;
    }

    public ArrayList<Integer> getRequirements() {
        ArrayList<Integer> requirements = new ArrayList<Integer>();
        if (type.equals(LARGESTRAIGHT)) {
            requirements = largeStraightRequirements();
        } else if (type.equals(SMALLSTRAIGHT)) {
            requirements = smallStraightRequirements();
        }
        return requirements;
    }

    public ArrayList<Integer> largeStraightRequirements() {
        ArrayList<Integer> requirements = new ArrayList<Integer>();
        requirements.add(2);
        requirements.add(3);
        requirements.add(4);
        requirements.add(5);
        requirements.add(6);
        return requirements;
    }

    public ArrayList<Integer> smallStraightRequirements() {
        ArrayList<Integer> requirements = new ArrayList<Integer>();
        requirements.add(1);
        requirements.add(2);
        requirements.add(3);
        requirements.add(4);
        requirements.add(5);
        return requirements;
    }

    public ArrayList<Integer> originalList() {
        ArrayList<Integer> original = new ArrayList<Integer>();
        original.add(dice.get(0).getValue());
        original.add(dice.get(1).getValue());
        original.add(dice.get(2).getValue());
        original.add(dice.get(3).getValue());
        original.add(dice.get(4).getValue());
        Collections.sort(original);
        return original;
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
