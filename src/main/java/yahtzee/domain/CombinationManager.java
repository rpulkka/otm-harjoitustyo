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
import yahtzee.ui.YahtzeeUI;

// @author rpulkka
public class CombinationManager {

    private YahtzeeUI ui;

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

    public CombinationManager(YahtzeeUI ui) {
        this.ui = ui;

        this.dice = ui.getDice();

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
    }

    public void scoreCombination(String typeString) {
        if (typeString == null) {
            return;
        }
        CombinationType type = CombinationType.valueOf(typeString.toUpperCase().replaceAll("\\s+", ""));
        if (!chosenDiceExist() || isIllegalCombination(type)) {
            return;
        }
        Combination combination = getCombination(type);
        if (combination.getIsAvailable() == true) {
            countPoints(combination);
        }else{
            return;
        }
        checkRound();
        ui.refreshRound();
    }

    public Combination getCombination(CombinationType type) {
        Combination scoredCombination = null;
        for (Combination c : combinations) {
            if (c.getCombinationType().equals(type)) {
                scoredCombination = c;
            }
        }
        return scoredCombination;
    }
    
    public void countPoints(Combination combination) {
        if (combination.getIsAvailable() == true) {
        int score = combination.score();
            String points = "" + score;
            total += score;
            ui.refreshThisCell(points);
        }
    }

    public void checkRound() {
        if (this.isFirstRound == true) {
            if (firstRoundIsOver() == true) {
                beginSecondRound();
                scoreBonus();
                this.isFirstRound = false;
            }
        } else {
            if (gameIsOver() == true) {
                ui.refreshOtherCell("" + total, 16);
            }
        }
    }

    public boolean firstRoundIsOver() {
        boolean check = true;
        for (FirstRoundCombination combo : firstRound) {
            if (combo.getIsAvailable() == true) {
                check = false;
            }
        }
        return check;
    }

    public boolean gameIsOver() {
        boolean check = true;
        for (Combination combo : combinations) {
            if (combo.getIsAvailable() == true) {
                check = false;
            }
        }
        return check;
    }

    public void beginSecondRound() {
        for (Combination combo : combinations) {
            if (firstRound.contains(combo)) {
                continue;
            } else {
                combo.setIsAvailable(true);
            }
        }
    }

    public void scoreBonus() {
        if (this.total >= 63) {

            ui.refreshOtherCell("50", 6);
            this.total += 50;
        } else {

            ui.refreshOtherCell("0", 6);
        }
    }

    public boolean chosenDiceExist() {
        boolean check = false;
        for (Die die : dice) {
            if (die.getChosen() == true) {
                check = true;
            }
        }
        return check;
    }

    public boolean isIllegalCombination(CombinationType type) {
        if (type.equals(BONUS) || type.equals(TOTAL)) {
            return true;
        }
        return false;
    }
}
