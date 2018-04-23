package yahtzee.domain;

import java.util.ArrayList;
import javafx.scene.control.TableView;

// @author rpulkka
public class CombinationManager {

    private TableView scoreboard;
    
    private ArrayList<Die> dice;

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

    public CombinationManager(TableView scoreboard, ArrayList<Die> dice, ArrayList<DieImage> images) {
        this.scoreboard = scoreboard;
        
        this.dice = dice;

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

        this.smallStraight = new Straight(images, dice, "small");
        this.largeStraight = new Straight(images, dice, "large");

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

    public void scoreCombination(Score score, Reset reset) {
        String combinationType = score.getCombination();
        System.out.println(combinationType);
        String points = "0";
        
        boolean test = false;
        for(Die die:dice){
            if(die.getChosen() == true){
                test = true;
            }
        }
        
        if(test == false){
            return;
        }
        
        if(this.scoredYet == true){
            return;
        }

        this.scoredYet = true;
        
        switch (combinationType) {
            case "Aces":
                if(aces.getAlreadyScored()==true){
                    System.out.println("true");
                    return;
                }
                if (aces.getIsAvailable() == true) {
                    int number = aces.score();
                    points = "" + number;
                    total += number;
                    score.setPoints(points);
                }
                break;
            case "Twos":
                if(twos.getAlreadyScored()==true){
                    return;
                }
                if (twos.getIsAvailable() == true) {
                    int number = twos.score();
                    points = "" + number;
                    total += number;
                    score.setPoints(points);
                }
                break;
            case "Threes":
                if(threes.getAlreadyScored()==true){
                    return;
                }
                if (threes.getIsAvailable() == true) {
                    int number = threes.score();
                    points = "" + number;
                    total += number;
                    score.setPoints(points);
                }
                break;
            case "Fours":
                if(fours.getAlreadyScored()==true){
                    return;
                }
                if (fours.getIsAvailable() == true) {
                    int number = fours.score();
                    points = "" + number;
                    total += number;
                    score.setPoints(points);
                }
                break;
            case "Fives":
                if(fives.getAlreadyScored()==true){
                    return;
                }
                if (fives.getIsAvailable() == true) {
                    int number = fives.score();
                    points = "" + number;
                    total += number;
                    score.setPoints(points);
                }
                break;
            case "Sixes":
                if(sixes.getAlreadyScored()==true){
                    return;
                }
                if (sixes.getIsAvailable() == true) {
                    int number = sixes.score();
                    points = "" + number;
                    total += number;
                    score.setPoints(points);
                }
                break;
            case "Bonus":
                break;
            case "Pair":
                if(pair.getAlreadyScored()==true){
                    return;
                }
                if (pair.getIsAvailable() == true) {
                    int number = pair.score();
                    points = "" + number;
                    total += number;
                    score.setPoints(points);
                }
                break;
            case "Two pairs":
                if(twoPairs.getAlreadyScored()==true){
                    return;
                }
                if (twoPairs.getIsAvailable() == true) {
                    int number = twoPairs.score();
                    points = "" + number;
                    total += number;
                    score.setPoints(points);
                }
                break;
            case "3 of a kind":
                if(threeOfAKind.getAlreadyScored()==true){
                    return;
                }
                if (threeOfAKind.getIsAvailable() == true) {
                    int number = threeOfAKind.score();
                    points = "" + number;
                    total += number;
                    score.setPoints(points);
                }
                break;
            case "4 of a kind":
                if(fourOfAKind.getAlreadyScored()==true){
                    return;
                }
                if (fourOfAKind.getIsAvailable() == true) {
                    int number = fourOfAKind.score();
                    points = "" + number;
                    total += number;
                    score.setPoints(points);
                }
                break;
            case "Full house":
                if(fullHouse.getAlreadyScored()==true){
                    return;
                }
                if (fullHouse.getIsAvailable() == true) {
                    int number = fullHouse.score();
                    points = "" + number;
                    total += number;
                    score.setPoints(points);
                }
                break;
            case "Small straight":
                if(smallStraight.getAlreadyScored()==true){
                    return;
                }
                if (smallStraight.getIsAvailable() == true) {
                    int number = smallStraight.score();
                    points = "" + number;
                    total += number;
                    score.setPoints(points);
                }
                break;
            case "Large straight":
                if(largeStraight.getAlreadyScored()==true){
                    return;
                }
                if (largeStraight.getIsAvailable() == true) {
                    int number = largeStraight.score();
                    System.out.println(number);
                    points = "" + number;
                    total += number;
                    score.setPoints(points);
                }
                break;
            case "Chance":
                if(chance.getAlreadyScored()==true){
                    return;
                }
                if (chance.getIsAvailable() == true) {
                    int number = chance.score();
                    points = "" + number;
                    total += number;
                    score.setPoints(points);
                }
                break;
            case "Yahtzee":
                if(yahtzee.getAlreadyScored()==true){
                    return;
                }
                if (yahtzee.getIsAvailable() == true) {
                    int number = yahtzee.score();
                    points = "" + number;
                    total += number;
                    score.setPoints(points);
                }
                break;
            case "Total":
                break;
            default:
                break;
        }

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
                item.setPoints("50");
                this.total += 50;
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
                System.out.println(item.getCombination());
            }
        }
        System.out.println(points);
        scoreboard.getSelectionModel().clearSelection();
        scoreboard.refresh();
        reset.resetNow();
    }
    
    public void setScoredYet(boolean b){
        this.scoredYet = b;
    }
}
