package yahtzee.domain;

// @author rpulkka
/**
 * An interface for all combination handlers.
 */
import java.util.ArrayList;

public interface Combination {

    /**
     * The combination type as enum.
     */
    enum CombinationType {
        ACES, TWOS, THREES, FOURS, FIVES, SIXES,
        PAIR, TWOPAIRS, THREEOFAKIND, FOUROFAKIND,
        FULLHOUSE, SMALLSTRAIGHT, LARGESTRAIGHT,
        YAHTZEE, CHANCE,
        BONUS, TOTAL
    }

    /**
     * Method in the combination handler which counts points for the combination.
     */
    public int score(ArrayList<Die> dice);
    
    public boolean getIsAvailable();

    public void setIsAvailable(boolean b);

    public CombinationType getCombinationType();
}
