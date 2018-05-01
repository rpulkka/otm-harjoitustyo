package yahtzee.domain;

import java.util.ArrayList;
import yahtzee.domain.Combination.CombinationType;
import static yahtzee.domain.Combination.CombinationType.ACES;
import static yahtzee.domain.Combination.CombinationType.BONUS;
import static yahtzee.domain.Combination.CombinationType.CHANCE;
import static yahtzee.domain.Combination.CombinationType.FIVES;
import static yahtzee.domain.Combination.CombinationType.FOUROFAKIND;
import static yahtzee.domain.Combination.CombinationType.FOURS;
import static yahtzee.domain.Combination.CombinationType.FULLHOUSE;
import static yahtzee.domain.Combination.CombinationType.LARGESTRAIGHT;
import static yahtzee.domain.Combination.CombinationType.PAIR;
import static yahtzee.domain.Combination.CombinationType.SIXES;
import static yahtzee.domain.Combination.CombinationType.SMALLSTRAIGHT;
import static yahtzee.domain.Combination.CombinationType.THREEOFAKIND;
import static yahtzee.domain.Combination.CombinationType.THREES;
import static yahtzee.domain.Combination.CombinationType.TOTAL;
import static yahtzee.domain.Combination.CombinationType.TWOPAIRS;
import static yahtzee.domain.Combination.CombinationType.TWOS;
import static yahtzee.domain.Combination.CombinationType.YAHTZEE;

// @author rpulkka

/**
 * This class is used to count points for combinations that the player forms in
 * the combination area. It finds out which combination is being scored and then
 * directs the dice to the right combination handler.
 */
public class CombinationManager {

    private final ArrayList<Die> dice;

    private int total;

    private final FirstRoundCombination aces;
    private final FirstRoundCombination twos;
    private final FirstRoundCombination threes;
    private final FirstRoundCombination fours;
    private final FirstRoundCombination fives;
    private final FirstRoundCombination sixes;

    private final Pair pair;
    private final Pair twoPairs;

    private final XOfAKind threeOfAKind;
    private final XOfAKind fourOfAKind;

    private final FullHouse fullHouse;

    private final Straight smallStraight;
    private final Straight largeStraight;

    private final Chance chance;

    private final YahtzeeCombo yahtzee;

    private final ArrayList<FirstRoundCombination> firstRound;
    private final ArrayList<Combination> combinations;

    private boolean isFirstRound;
    
    private Combination currentCombination;

    public CombinationManager(ArrayList<Die> dice) {
        this.dice = dice;

        this.total = 0;

        this.aces = new FirstRoundCombination(dice, ACES);
        this.twos = new FirstRoundCombination(dice, TWOS);
        this.threes = new FirstRoundCombination(dice, THREES);
        this.fours = new FirstRoundCombination(dice, FOURS);
        this.fives = new FirstRoundCombination(dice, FIVES);
        this.sixes = new FirstRoundCombination(dice, SIXES);

        this.pair = new Pair(dice, PAIR);
        this.twoPairs = new Pair(dice, TWOPAIRS);

        this.threeOfAKind = new XOfAKind(dice, THREEOFAKIND);
        this.fourOfAKind = new XOfAKind(dice, FOUROFAKIND);

        this.fullHouse = new FullHouse(dice, FULLHOUSE);

        this.smallStraight = new Straight(dice, SMALLSTRAIGHT);
        this.largeStraight = new Straight(dice, LARGESTRAIGHT);

        this.chance = new Chance(dice, CHANCE);

        this.yahtzee = new YahtzeeCombo(dice, YAHTZEE);

        this.firstRound = new ArrayList<FirstRoundCombination>();
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
            public int score() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

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
        };
    }

    /**
     * This method is designed to be called when player wishes to score a
     * combination. It checks the input to ignore invalid input and if input is
     * valid, it returns true.
     *
     * @param typeString String that contains data of combination type.
     * 
     * @see CombinationManager#findCombination(yahtzee.domain.Combination.CombinationType) 
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
        int score = currentCombination.score();
        total += score;
        return score;
    }
    
    /**
     * Checks if the first 6 combination have been scored and 
     * second round, meaning the unlocking of the combinations 
     * below "Bonus", has begun. 
     */
    public boolean firstRoundIsOver() {
        boolean check = true;
        for (FirstRoundCombination combo : firstRound) {
            if (combo.getIsAvailable() == true) {
                check = false;
            }
        }
        return check;
    }

    /**
     * Checks if the all combinations have been scored, meaning that the 
     * game is over.
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
     * Starts the "second round" by unlocking all of the second round combinations
     * and setting isFirstRound to false.
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
     * Checks if the player is privileged to receive bonus after the first round.
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
     * Designed to be called after scoring the combination to return the dice
     * to their original state by setting setChosen(boolean) to false and
     * setting setValue(int) to 1 for each die.
     * 
     * @see die#setChosen(boolean)
     * @see die#setValue(int)
     */
    public void resetDice() {
        for (Die die : dice) {
            die.setChosen(false);
            die.setValue(1);
        }
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

    public FirstRoundCombination getAces() {
        return aces;
    }

    public FirstRoundCombination getTwos() {
        return twos;
    }

    public FirstRoundCombination getThrees() {
        return threes;
    }

    public FirstRoundCombination getFours() {
        return fours;
    }

    public FirstRoundCombination getFives() {
        return fives;
    }

    public FirstRoundCombination getSixes() {
        return sixes;
    }

    public Pair getPair() {
        return pair;
    }

    public Pair getTwoPairs() {
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

    public Chance getChance() {
        return chance;
    }

    public YahtzeeCombo getYahtzee() {
        return yahtzee;
    }

    public ArrayList<FirstRoundCombination> getFirstRound() {
        return firstRound;
    }

    public ArrayList<Combination> getCombinations() {
        return combinations;
    }

    public boolean isIsFirstRound() {
        return isFirstRound;
    }
    
    
}
