package yahtzee.domain;

import java.util.ArrayList;
import javafx.scene.control.TableView;

// @author rpulkka
public class CombinationManager {

    private TableView scoreboard;

    private ArrayList<Die> dice;
    
    private DiceThrower thrower;

    private int total;

    private boolean scoredYet = false;

    private FirstRoundCombination aces;
    private FirstRoundCombination twos;
    private FirstRoundCombination threes;
    private FirstRoundCombination fours;
    private FirstRoundCombination fives;
    private FirstRoundCombination sixes;

    private Pair pair;
    private Pair twoPairs;

    private XOfAKind threeOfAKind;
    private XOfAKind fourOfAKind;

    private FullHouse fullHouse;

    private Straight smallStraight;
    private Straight largeStraight;

    private Chance chance;

    private YahtzeeCombo yahtzee;

    private ArrayList<FirstRoundCombination> firstRound;
    private ArrayList<Combination> combinations;

    private boolean isFirstRound;

    public CombinationManager(TableView scoreboard, ArrayList<Die> dice, ArrayList<DieImage> images, DiceThrower thrower) {
        this.scoreboard = scoreboard;

        this.dice = dice;
        
        this.thrower = thrower;

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

    public void scoreCombination(Reset reset) {
        Score score = (Score) scoreboard.getSelectionModel().getSelectedItem();
        
        if (score == null) {
            return;
        }
        
        String combinationType = score.getCombination();
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
                    score.setPoints(points);
                } else {
                    return;
                }
                break;
            case "Twos":
                if (twos.getIsAvailable() == true) {
                    int number = twos.score();
                    points = "" + number;
                    total += number;
                    score.setPoints(points);
                } else {
                    return;
                }
                break;
            case "Threes":
                if (threes.getIsAvailable() == true) {
                    int number = threes.score();
                    points = "" + number;
                    total += number;
                    score.setPoints(points);
                } else {
                    return;
                }
                break;
            case "Fours":
                if (fours.getIsAvailable() == true) {
                    int number = fours.score();
                    points = "" + number;
                    total += number;
                    score.setPoints(points);
                } else {
                    return;
                }
                break;
            case "Fives":
                if (fives.getIsAvailable() == true) {
                    int number = fives.score();
                    points = "" + number;
                    total += number;
                    score.setPoints(points);
                } else {
                    return;
                }
                break;
            case "Sixes":
                if (sixes.getIsAvailable() == true) {
                    int number = sixes.score();
                    points = "" + number;
                    total += number;
                    score.setPoints(points);
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
                    score.setPoints(points);
                } else {
                    return;
                }
                break;
            case "Two pairs":
                if (twoPairs.getIsAvailable() == true) {
                    int number = twoPairs.score();
                    points = "" + number;
                    total += number;
                    score.setPoints(points);
                } else {
                    return;
                }
                break;
            case "3 of a kind":
                if (threeOfAKind.getIsAvailable() == true) {
                    int number = threeOfAKind.score();
                    points = "" + number;
                    total += number;
                    score.setPoints(points);
                } else {
                    return;
                }
                break;
            case "4 of a kind":
                if (fourOfAKind.getIsAvailable() == true) {
                    int number = fourOfAKind.score();
                    points = "" + number;
                    total += number;
                    score.setPoints(points);
                } else {
                    return;
                }
                break;
            case "Full house":
                if (fullHouse.getIsAvailable() == true) {
                    int number = fullHouse.score();
                    points = "" + number;
                    total += number;
                    score.setPoints(points);
                } else {
                    return;
                }
                break;
            case "Small straight":
                if (smallStraight.getIsAvailable() == true) {
                    int number = smallStraight.score();
                    points = "" + number;
                    total += number;
                    score.setPoints(points);
                } else {
                    return;
                }
                break;
            case "Large straight":
                if (largeStraight.getIsAvailable() == true) {
                    int number = largeStraight.score();
                    points = "" + number;
                    total += number;
                    score.setPoints(points);
                } else {
                    return;
                }
                break;
            case "Chance":
                if (chance.getIsAvailable() == true) {
                    int number = chance.score();
                    points = "" + number;
                    total += number;
                    score.setPoints(points);
                } else {
                    return;
                }
                break;
            case "Yahtzee":
                if (yahtzee.getIsAvailable() == true) {
                    int number = yahtzee.score();
                    points = "" + number;
                    total += number;
                    score.setPoints(points);
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
                Score item = (Score) scoreboard.getItems().get(6);
                if (this.total >= 63) {
                    item.setPoints("50");
                    this.total += 50;
                }else{
                    item.setPoints("0");
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
                Score item = (Score) scoreboard.getItems().get(16);
                item.setPoints("" + total);
                thrower.setTimesThrown(99);
            }
        }
        scoreboard.getSelectionModel().clearSelection();
        scoreboard.refresh();
        reset.resetNow();
    }

    public void setScoredYet(boolean b) {
        this.scoredYet = b;
    }
}
