package yahtzee.domain;

import java.util.ArrayList;
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

        this.aces = new FirstRoundCombination(dice, 1);
        this.twos = new FirstRoundCombination(dice, 2);
        this.threes = new FirstRoundCombination(dice, 3);
        this.fours = new FirstRoundCombination(dice, 4);
        this.fives = new FirstRoundCombination(dice, 5);
        this.sixes = new FirstRoundCombination(dice, 6);

        this.pair = new Pair(dice, "one");
        this.twoPairs = new Pair(dice, "two");

        this.threeOfAKind = new XOfAKind(dice, "three");
        this.fourOfAKind = new XOfAKind(dice, "four");

        this.fullHouse = new FullHouse(dice);

        this.smallStraight = new Straight(dice, "small");
        this.largeStraight = new Straight(dice, "large");

        this.chance = new Chance(dice);

        this.yahtzee = new YahtzeeCombo(dice);

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

    public void scoreCombination(String score) {
        
        if (score == null) {
            return;
        }
        
        String combinationType = score;
        String points = "0";

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

        switch (combinationType) {
            case "Aces":
                if (aces.getIsAvailable() == true) {
                    int number = aces.score();
                    points = "" + number;
                    total += number;
                    ui.refreshThisCell(points);
                } else {
                    return;
                }
                break;
            case "Twos":
                if (twos.getIsAvailable() == true) {
                    int number = twos.score();
                    points = "" + number;
                    total += number;
                    ui.refreshThisCell(points);
                } else {
                    return;
                }
                break;
            case "Threes":
                if (threes.getIsAvailable() == true) {
                    int number = threes.score();
                    points = "" + number;
                    total += number;
                    ui.refreshThisCell(points);
                } else {
                    return;
                }
                break;
            case "Fours":
                if (fours.getIsAvailable() == true) {
                    int number = fours.score();
                    points = "" + number;
                    total += number;
                    ui.refreshThisCell(points);
                } else {
                    return;
                }
                break;
            case "Fives":
                if (fives.getIsAvailable() == true) {
                    int number = fives.score();
                    points = "" + number;
                    total += number;
                    ui.refreshThisCell(points);
                } else {
                    return;
                }
                break;
            case "Sixes":
                if (sixes.getIsAvailable() == true) {
                    int number = sixes.score();
                    points = "" + number;
                    total += number;
                    ui.refreshThisCell(points);
                } else {
                    return;
                }
                break;
            case "Bonus":
                return;
            case "Pair":
                if (pair.getIsAvailable() == true) {
                    int number = pair.score();
                    points = "" + number;
                    total += number;
                    ui.refreshThisCell(points);
                } else {
                    return;
                }
                break;
            case "Two pairs":
                if (twoPairs.getIsAvailable() == true) {
                    int number = twoPairs.score();
                    points = "" + number;
                    total += number;
                    ui.refreshThisCell(points);
                } else {
                    return;
                }
                break;
            case "3 of a kind":
                if (threeOfAKind.getIsAvailable() == true) {
                    int number = threeOfAKind.score();
                    points = "" + number;
                    total += number;
                    ui.refreshThisCell(points);
                } else {
                    return;
                }
                break;
            case "4 of a kind":
                if (fourOfAKind.getIsAvailable() == true) {
                    int number = fourOfAKind.score();
                    points = "" + number;
                    total += number;
                    ui.refreshThisCell(points);
                } else {
                    return;
                }
                break;
            case "Full house":
                if (fullHouse.getIsAvailable() == true) {
                    int number = fullHouse.score();
                    points = "" + number;
                    total += number;
                    ui.refreshThisCell(points);
                } else {
                    return;
                }
                break;
            case "Small straight":
                if (smallStraight.getIsAvailable() == true) {
                    int number = smallStraight.score();
                    points = "" + number;
                    total += number;
                    ui.refreshThisCell(points);
                } else {
                    return;
                }
                break;
            case "Large straight":
                if (largeStraight.getIsAvailable() == true) {
                    int number = largeStraight.score();
                    points = "" + number;
                    total += number;
                    ui.refreshThisCell(points);
                } else {
                    return;
                }
                break;
            case "Chance":
                if (chance.getIsAvailable() == true) {
                    int number = chance.score();
                    points = "" + number;
                    total += number;
                    ui.refreshThisCell(points);
                } else {
                    return;
                }
                break;
            case "Yahtzee":
                if (yahtzee.getIsAvailable() == true) {
                    int number = yahtzee.score();
                    points = "" + number;
                    total += number;
                    ui.refreshThisCell(points);
                } else {
                    return;
                }
                break;
            case "Total":
                return;
            default:
                return;
        }

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
                ui.refreshOtherCell(""+total, 16);
            }
        }
        ui.refreshRound();
    }

    public void setScoredYet(boolean b) {
        this.scoredYet = b;
    }
}
