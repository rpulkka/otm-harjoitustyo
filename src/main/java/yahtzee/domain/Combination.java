package yahtzee.domain;

// @author rpulkka
public interface Combination {

    enum CombinationType {
        ACES, TWOS, THREES, FOURS, FIVES, SIXES,
        PAIR, TWOPAIRS, THREEOFAKIND, FOUROFAKIND,
        FULLHOUSE, SMALLSTRAIGHT, LARGESTRAIGHT,
        YAHTZEE, CHANCE,
        BONUS, TOTAL
    }

    public int score();

    public boolean getIsAvailable();

    public void setIsAvailable(boolean b);
    
    public CombinationType getCombinationType();
}
