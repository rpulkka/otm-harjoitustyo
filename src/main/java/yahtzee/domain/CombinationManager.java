package yahtzee.domain;

import java.util.ArrayList;
import yahtzee.domain.Combination.CombinationType;
import static yahtzee.domain.Combination.CombinationType.ACES;
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
import static yahtzee.domain.Combination.CombinationType.TWOPAIRS;
import static yahtzee.domain.Combination.CombinationType.TWOS;
import static yahtzee.domain.Combination.CombinationType.YAHTZEE;
import yahtzee.ui.YahtzeeUI;

// @author rpulkka
public class CombinationManager {

    private YahtzeeUI ui;

    private final ArrayList<Die> dice;

    private int total;

    private boolean scoredYet = false;

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
        
        CombinationType type = CombinationType.valueOf(typeString.toUpperCase().replaceAll("\\s+",""));

        if (type == null) {
            return;
        }

        boolean test = false;
        for (Die die : dice) {
            if (die.getChosen() == true) {
                test = true;
            }
        }

        if (test == false) {
            return;
        }

        if (this.scoredYet == true) {
            return;
        }
        
        String points = pointsScored(type);

        this.scoredYet = true;

        if (this.isFirstRound == true) {
            boolean check = true;
            for (FirstRoundCombination combo : firstRound) {
                if (combo.getIsAvailable() == true) {
                    check = false;
                }
            }
            if (check == true) {
                for (Combination combo : combinations) {
                    if (firstRound.contains(combo)) {
                        continue;
                    } else {
                        combo.setIsAvailable(true);
                    }
                }

                if (this.total >= 63) {

                    ui.refreshOtherCell("50", 6);
                    this.total += 50;
                } else {

                    ui.refreshOtherCell("0", 6);
                }
                this.isFirstRound = false;
            }
        } else {
            boolean check = true;
            for (Combination combo : combinations) {
                if (combo.getIsAvailable() == true) {
                    check = false;
                }
            }
            if (check == true) {
                ui.refreshOtherCell("" + total, 16);
            }
        }
        ui.refreshRound();
    }

    public String pointsScored(CombinationType type) {
        Combination scoredCombination = null;
        for (Combination c : combinations) {
            if (c.getCombinationType().equals(type)) {
                scoredCombination = c;
            }
        }
        if (scoredCombination.getIsAvailable() == true) {
            int score = scoredCombination.score();
            String points = "" + score;
            total += score;
            ui.refreshThisCell(points);
            return points;
        } else {
            return "0";
        }
    }

    public void setScoredYet(boolean b) {
        this.scoredYet = b;
    }
}
