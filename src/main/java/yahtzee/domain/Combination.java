package yahtzee.domain;

// @author rpulkka
public interface Combination {

    public int score();

    public boolean getIsAvailable();

    public void setIsAvailable(boolean b);
}
