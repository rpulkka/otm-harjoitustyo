package yahtzee.domain;

import java.util.ArrayList;
import java.util.Collections;
import static yahtzee.domain.Combination.CombinationType.LARGESTRAIGHT;
import static yahtzee.domain.Combination.CombinationType.SMALLSTRAIGHT;

/**
 * Combination handler class for combinations that are formed of straights of
 * dice values.
 */
//@author rpulkka
public class Straight implements Combination {

    ArrayList<Die> requirements;
    private CombinationType type;
    private boolean isAvailable;

    public Straight(CombinationType type) {
        requirements = new ArrayList<Die>();
        this.type = type;
        this.isAvailable = false;
    }

    /**
     * A method for counting the points for the straight combinations by calling
     * getRequirements() and comparing it to the chosen dice and by giving
     * points according to what type of straight the combination is.
     *
     * @param dice The chosen dice that are being scored.
     *
     * @see Straight#getRequirements()
     *
     * @return The points given for the combination
     */
    public int score(ArrayList<Die> dice) {
        this.isAvailable = false;
        if (dice.size() != 5) {
            return 0;
        }
        ArrayList<Integer> org = originalList(dice);
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

    /**
     * Returns the requirements for the combination that is being scored by
     * calling the right requirement constructor.
     *
     * @see Straight#largeStraightRequirements()
     * @see Straight#smallStraightRequirements()
     *
     * @return A list of required values.
     */
    public ArrayList<Integer> getRequirements() {
        ArrayList<Integer> requirements = new ArrayList<Integer>();
        if (type.equals(LARGESTRAIGHT)) {
            requirements = largeStraightRequirements();
        } else if (type.equals(SMALLSTRAIGHT)) {
            requirements = smallStraightRequirements();
        }
        return requirements;
    }

    /**
     * Returns the requirements for the large straight.
     *
     * @return List of required values for large straight.
     */
    public ArrayList<Integer> largeStraightRequirements() {
        ArrayList<Integer> requirements = new ArrayList<Integer>();
        requirements.add(2);
        requirements.add(3);
        requirements.add(4);
        requirements.add(5);
        requirements.add(6);
        return requirements;
    }

    /**
     * Returns the requirements for the small straight.
     *
     * @return List of required values for small straight.
     */
    public ArrayList<Integer> smallStraightRequirements() {
        ArrayList<Integer> requirements = new ArrayList<Integer>();
        requirements.add(1);
        requirements.add(2);
        requirements.add(3);
        requirements.add(4);
        requirements.add(5);
        return requirements;
    }

    /**
     * Returns the values of the chosen dice.
     *
     * @return List of chosen dice values.
     */
    public ArrayList<Integer> originalList(ArrayList<Die> dice) {
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
