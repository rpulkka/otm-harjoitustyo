package yahtzee.domain;

import java.util.ArrayList;
import yahtzee.domain.Combination.CombinationType;
import static yahtzee.domain.Combination.CombinationType.*;

// @author rpulkka
/**
 * This class is used to count points for combinations that the player forms in
 * the combination area. It finds out which combination is being scored and then
 * directs the dice to the right combination handler.
 */
public class CombinationManager {

    private final ArrayList<Die> dice;

    private int total;

    private SumCombination aces;
    private SumCombination twos;
    private SumCombination threes;
    private SumCombination fours;
    private SumCombination fives;
    private SumCombination sixes;

    private XOfAKind pair;
    private XOfAKind twoPairs;

    private XOfAKind threeOfAKind;
    private XOfAKind fourOfAKind;

    private FullHouse fullHouse;

    private Straight smallStraight;
    private Straight largeStraight;

    private SumCombination chance;

    private XOfAKind yahtzee;

    private ArrayList<SumCombination> firstRound;
    private ArrayList<Combination> combinations;

    private boolean isFirstRound;

    private Combination currentCombination;

    public CombinationManager(ArrayList<Die> dice) {
        this.dice = dice;
        reset();
    }

    /**
     * This method is designed to be called when the player wishes to score a
     * combination. It checks the input to ignore invalid input and if input is
     * valid, it returns true.
     *
     * @param typeString String that contains data of combination type.
     *
     * @see
     * CombinationManager#isIllegalCombination(yahtzee.domain.Combination.CombinationType)
     * @see CombinationManager#chosenDiceExist()
     * @see
     * CombinationManager#findCombination(yahtzee.domain.Combination.CombinationType)
     *
     * @return Returns boolean true, if the combination can be scored.
     */
    public boolean combinationIsValid(String typeString) {
        if (typeString == null) {
            return false;
        }
        CombinationType type = CombinationType.valueOf(typeString.toUpperCase().replaceAll("\\s+", ""));
        if (!chosenDiceExist() || isIllegalCombination(type)) {
            return false;
        }
        currentCombination = findCombination(type);
        if (currentCombination.getIsAvailable() == true) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * This method finds the right Combination by CombinationType.
     *
     * @param type CombinationType format.
     *
     * @return scoredCombination The corresponding combination.
     */
    public Combination findCombination(CombinationType type) {
        Combination scoredCombination = null;
        for (Combination c : combinations) {
            if (c.getCombinationType().equals(type)) {
                scoredCombination = c;
            }
        }
        return scoredCombination;
    }

    /**
     * A method that gives control to the right combination handler. It receives
     * the score from the combination handler, then adds the score to total
     * score and returns the score.
     *
     * @return score The points from the combination.
     */
    public int countPoints() {
        int score = currentCombination.score(chosenDiceList());
        total += score;
        return score;
    }

    /**
     * Checks if the first 6 combinations have been scored and second round,
     * meaning the unlocking of the combinations below "Bonus", has begun.
     *
     * @return Returns true if first round combinations have been scored
     * already.
     */
    public boolean firstRoundIsOver() {
        boolean check = true;
        for (SumCombination combo : firstRound) {
            if (combo.getIsAvailable() == true) {
                check = false;
            }
        }
        return check;
    }

    /**
     * Checks if the all combinations have been scored, meaning that the game is
     * over.
     *
     * @return Returns true if all combinations have been scored.
     */
    public boolean gameIsOver() {
        boolean check = true;
        for (Combination combo : combinations) {
            if (combo.getIsAvailable() == true) {
                check = false;
            }
        }
        return check;
    }

    /**
     * Starts the "second round" by unlocking all of the second round
     * combinations and setting variable boolean isFirstRound to false.
     */
    public void beginSecondRound() {
        for (Combination combo : combinations) {
            if (firstRound.contains(combo)) {
                continue;
            } else {
                combo.setIsAvailable(true);
            }
        }
        isFirstRound = false;
    }

    /**
     * Checks if the player is privileged to receive bonus after the first round
     * and sums bonus to total if true.
     *
     * @return Returns true if player receives bonus.
     */
    public boolean scoreBonus() {
        if (this.total >= 63) {
            this.total += 50;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks if there is at least one die in the combination area to be scored.
     *
     * @return check The check result as boolean.
     */
    public boolean chosenDiceExist() {
        boolean check = false;
        for (Die die : dice) {
            if (die.getChosen() == true) {
                check = true;
            }
        }
        return check;
    }

    /**
     * Checks that bonus or total are not being scored as combinations.
     *
     * @param type The CombinationType of the combination that's being scored.
     *
     * @return The check result as boolean.
     */
    public boolean isIllegalCombination(CombinationType type) {
        if (type.equals(BONUS) || type.equals(TOTAL)) {
            return true;
        }
        return false;
    }
    
    /**
     * Checks which dice are chosen by the player, in other words, which dice are 
     * taken into account when scoring the combination.
     *
     * @return chosenDice List of dice that are chosen.
     */
    public ArrayList<Die> chosenDiceList() {
        ArrayList<Die> chosenDice = new ArrayList<Die>();
        for (Die die : dice) {
            if (die.getChosen() == true) {
                chosenDice.add(die);
            }
        }
        return chosenDice;
    }

    /**
     * Designed to be called after scoring the combination to return the dice to
     * their original state and setting every die's value to one.
     *
     * @see Die#setChosen(boolean)
     * @see Die#setValue(int)
     */
    public void resetDice() {
        for (Die die : dice) {
            die.setChosen(false);
            die.setValue(1);
        }
    }
    
    /**
     * Basically a constructor which works for playing a new game without
     * quitting the game.
     */
    public void reset() {
        this.total = 0;

        this.aces = new SumCombination(ACES);
        this.twos = new SumCombination(TWOS);
        this.threes = new SumCombination(THREES);
        this.fours = new SumCombination(FOURS);
        this.fives = new SumCombination(FIVES);
        this.sixes = new SumCombination(SIXES);

        this.pair = new XOfAKind(PAIR);
        this.twoPairs = new XOfAKind(TWOPAIRS);

        this.threeOfAKind = new XOfAKind(THREEOFAKIND);
        this.fourOfAKind = new XOfAKind(FOUROFAKIND);

        this.fullHouse = new FullHouse(FULLHOUSE);

        this.smallStraight = new Straight(SMALLSTRAIGHT);
        this.largeStraight = new Straight(LARGESTRAIGHT);

        this.chance = new SumCombination(CHANCE);

        this.yahtzee = new XOfAKind(YAHTZEE);

        this.firstRound = new ArrayList<SumCombination>();
        this.combinations = new ArrayList<Combination>();

        this.firstRound.add(aces);
        this.firstRound.add(twos);
        this.firstRound.add(threes);
        this.firstRound.add(fours);
        this.firstRound.add(fives);
        this.firstRound.add(sixes);

        this.combinations.add(aces);
        this.combinations.add(twos);
        this.combinations.add(threes);
        this.combinations.add(fours);
        this.combinations.add(fives);
        this.combinations.add(sixes);
        this.combinations.add(pair);
        this.combinations.add(twoPairs);
        this.combinations.add(threeOfAKind);
        this.combinations.add(fourOfAKind);
        this.combinations.add(fullHouse);
        this.combinations.add(smallStraight);
        this.combinations.add(largeStraight);
        this.combinations.add(chance);
        this.combinations.add(yahtzee);

        this.isFirstRound = true;

        this.currentCombination = new Combination() {
            @Override
            public boolean getIsAvailable() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void setIsAvailable(boolean b) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public CombinationType getCombinationType() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public int score(ArrayList<Die> dice) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public boolean isFirstRound() {
        return isFirstRound;
    }

    public void setIsFirstRound(boolean isFirstRound) {
        this.isFirstRound = isFirstRound;
    }

    public Combination getCurrentCombination() {
        return currentCombination;
    }

    public void setCurrentCombination(Combination currentCombination) {
        this.currentCombination = currentCombination;
    }

    public ArrayList<Die> getDice() {
        return dice;
    }

    public SumCombination getAces() {
        return aces;
    }

    public SumCombination getTwos() {
        return twos;
    }

    public SumCombination getThrees() {
        return threes;
    }

    public SumCombination getFours() {
        return fours;
    }

    public SumCombination getFives() {
        return fives;
    }

    public SumCombination getSixes() {
        return sixes;
    }

    public XOfAKind getPair() {
        return pair;
    }

    public XOfAKind getTwoPairs() {
        return twoPairs;
    }

    public XOfAKind getThreeOfAKind() {
        return threeOfAKind;
    }

    public XOfAKind getFourOfAKind() {
        return fourOfAKind;
    }

    public FullHouse getFullHouse() {
        return fullHouse;
    }

    public Straight getSmallStraight() {
        return smallStraight;
    }

    public Straight getLargeStraight() {
        return largeStraight;
    }

    public SumCombination getChance() {
        return chance;
    }

    public XOfAKind getYahtzee() {
        return yahtzee;
    }

    public ArrayList<SumCombination> getFirstRound() {
        return firstRound;
    }

    public ArrayList<Combination> getCombinations() {
        return combinations;
    }

    public boolean isIsFirstRound() {
        return isFirstRound;
    }

}
