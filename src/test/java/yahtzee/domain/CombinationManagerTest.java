package yahtzee.domain;

import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static yahtzee.domain.Combination.CombinationType.*;

// @author rpulkka
public class CombinationManagerTest {

    private CombinationManager combinationManager;
    private ArrayList<Die> dice;

    public CombinationManagerTest() {
    }

    @Before
    public void setUp() {
        dice = new ArrayList<Die>();
        Die die1 = new Die(650, 250, 380, 720);
        dice.add(die1);
        Die die2 = new Die(750, 250, 480, 720);
        dice.add(die2);
        Die die3 = new Die(850, 250, 580, 720);
        dice.add(die3);
        Die die4 = new Die(700, 350, 680, 720);
        dice.add(die4);
        Die die5 = new Die(800, 350, 780, 720);
        dice.add(die5);
        die1.setChosen(true);
        die2.setChosen(true);
        die3.setChosen(true);
        die4.setChosen(true);
        die5.setChosen(true);
        this.combinationManager = new CombinationManager(dice);
    }

    @Test
    public void rejectsNull() {
        boolean nullNotRejected = combinationManager.combinationIsValid(null);
        assertEquals(nullNotRejected, false);
    }

    @Test
    public void identifiesRightCombination() {
        Combination combination = combinationManager.findCombination(ACES);
        assertEquals(combination.getCombinationType(), ACES);
    }

    @Test
    public void scoresAces() {
        dice.get(0).setValue(2);
        dice.get(1).setValue(2);
        dice.get(2).setValue(1);
        dice.get(3).setValue(1);
        dice.get(4).setValue(1);
        combinationManager.setCurrentCombination(combinationManager.findCombination(ACES));
        int points = combinationManager.countPoints();
        assertEquals(points, 3);
    }

    @Test
    public void scoresTwos() {
        dice.get(0).setValue(2);
        dice.get(1).setValue(2);
        dice.get(2).setValue(1);
        dice.get(3).setValue(1);
        dice.get(4).setValue(1);
        combinationManager.setCurrentCombination(combinationManager.findCombination(TWOS));
        int points = combinationManager.countPoints();
        assertEquals(points, 4);
    }

    @Test
    public void scoresThrees() {
        dice.get(0).setValue(3);
        dice.get(1).setValue(3);
        dice.get(2).setValue(3);
        dice.get(3).setValue(4);
        dice.get(4).setValue(4);
        combinationManager.setCurrentCombination(combinationManager.findCombination(THREES));
        int points = combinationManager.countPoints();
        assertEquals(points, 9);
    }

    @Test
    public void scoresFours() {
        dice.get(0).setValue(3);
        dice.get(1).setValue(3);
        dice.get(2).setValue(3);
        dice.get(3).setValue(4);
        dice.get(4).setValue(4);
        combinationManager.setCurrentCombination(combinationManager.findCombination(FOURS));
        int points = combinationManager.countPoints();
        assertEquals(points, 8);
    }

    @Test
    public void scoresFives() {
        dice.get(0).setValue(5);
        dice.get(1).setValue(5);
        dice.get(2).setValue(6);
        dice.get(3).setValue(6);
        dice.get(4).setValue(6);
        combinationManager.setCurrentCombination(combinationManager.findCombination(FIVES));
        int points = combinationManager.countPoints();
        assertEquals(points, 10);
    }

    @Test
    public void scoresSixes() {
        dice.get(0).setValue(6);
        dice.get(1).setValue(6);
        dice.get(2).setValue(6);
        dice.get(3).setValue(6);
        dice.get(4).setValue(1);
        combinationManager.setCurrentCombination(combinationManager.findCombination(SIXES));
        int points = combinationManager.countPoints();
        assertEquals(points, 24);
    }

    @Test
    public void scoresPair() {
        dice.get(0).setValue(6);
        dice.get(1).setValue(6);
        dice.get(2).setValue(1);
        dice.get(3).setValue(2);
        dice.get(4).setValue(3);
        combinationManager.setCurrentCombination(combinationManager.findCombination(PAIR));
        int points = combinationManager.countPoints();
        assertEquals(points, 12);
    }

    @Test
    public void scoresTwoPairs() {
        dice.get(0).setValue(6);
        dice.get(1).setValue(6);
        dice.get(2).setValue(1);
        dice.get(3).setValue(2);
        dice.get(4).setValue(2);
        combinationManager.setCurrentCombination(combinationManager.findCombination(TWOPAIRS));
        int points = combinationManager.countPoints();
        assertEquals(points, 16);
    }

    @Test
    public void scoresThreeOfAKind() {
        dice.get(0).setValue(6);
        dice.get(1).setValue(6);
        dice.get(2).setValue(6);
        dice.get(3).setValue(2);
        dice.get(4).setValue(3);
        combinationManager.setCurrentCombination(combinationManager.findCombination(THREEOFAKIND));
        int points = combinationManager.countPoints();
        assertEquals(points, 18);
    }

    @Test
    public void scoresFourOfAKind() {
        dice.get(0).setValue(6);
        dice.get(1).setValue(6);
        dice.get(2).setValue(6);
        dice.get(3).setValue(6);
        dice.get(4).setValue(3);
        combinationManager.setCurrentCombination(combinationManager.findCombination(FOUROFAKIND));
        int points = combinationManager.countPoints();
        assertEquals(points, 24);
    }

    @Test
    public void scoresFullHouse() {
        dice.get(0).setValue(6);
        dice.get(1).setValue(6);
        dice.get(2).setValue(6);
        dice.get(3).setValue(3);
        dice.get(4).setValue(3);
        combinationManager.setCurrentCombination(combinationManager.findCombination(FULLHOUSE));
        int points = combinationManager.countPoints();
        assertEquals(points, 24);
    }

    @Test
    public void scoresSmallStraight() {
        dice.get(0).setValue(5);
        dice.get(1).setValue(3);
        dice.get(2).setValue(2);
        dice.get(3).setValue(4);
        dice.get(4).setValue(1);
        combinationManager.setCurrentCombination(combinationManager.findCombination(SMALLSTRAIGHT));
        int points = combinationManager.countPoints();
        assertEquals(points, 15);
    }

    @Test
    public void scoresLargeStraight() {
        dice.get(0).setValue(5);
        dice.get(1).setValue(3);
        dice.get(2).setValue(2);
        dice.get(3).setValue(4);
        dice.get(4).setValue(6);
        combinationManager.setCurrentCombination(combinationManager.findCombination(LARGESTRAIGHT));
        int points = combinationManager.countPoints();
        assertEquals(points, 20);
    }

    @Test
    public void scoresChance() {
        dice.get(0).setValue(5);
        dice.get(1).setValue(1);
        dice.get(2).setValue(2);
        dice.get(3).setValue(4);
        dice.get(4).setValue(6);
        combinationManager.setCurrentCombination(combinationManager.findCombination(CHANCE));
        int points = combinationManager.countPoints();
        assertEquals(points, 18);
    }

    @Test
    public void scoresYahtzee() {
        dice.get(0).setValue(1);
        dice.get(1).setValue(1);
        dice.get(2).setValue(1);
        dice.get(3).setValue(1);
        dice.get(4).setValue(1);
        combinationManager.setCurrentCombination(combinationManager.findCombination(YAHTZEE));
        int points = combinationManager.countPoints();
        assertEquals(points, 50);
    }

    @Test
    public void firstRoundContinues() {
        assertEquals(combinationManager.firstRoundIsOver(), false);
    }

    @Test
    public void firstRoundEnds() {
        combinationManager.getAces().setIsAvailable(false);
        combinationManager.getTwos().setIsAvailable(false);
        combinationManager.getThrees().setIsAvailable(false);
        combinationManager.getFours().setIsAvailable(false);
        combinationManager.getFives().setIsAvailable(false);
        combinationManager.getSixes().setIsAvailable(false);
        assertEquals(combinationManager.firstRoundIsOver(), true);
    }

    @Test
    public void gameContinues() {
        assertEquals(combinationManager.gameIsOver(), false);
    }

    @Test
    public void gameEnds() {
        for (Combination combination : combinationManager.getCombinations()) {
            combination.setIsAvailable(false);
        }
        assertEquals(combinationManager.gameIsOver(), true);
    }

    @Test
    public void roundChanges() {
        combinationManager.beginSecondRound();
        assertEquals(combinationManager.getPair().getIsAvailable(), true);
        assertEquals(combinationManager.getTwoPairs().getIsAvailable(), true);
        assertEquals(combinationManager.getThreeOfAKind().getIsAvailable(), true);
        assertEquals(combinationManager.getFourOfAKind().getIsAvailable(), true);
        assertEquals(combinationManager.getFullHouse().getIsAvailable(), true);
        assertEquals(combinationManager.getSmallStraight().getIsAvailable(), true);
        assertEquals(combinationManager.getLargeStraight().getIsAvailable(), true);
        assertEquals(combinationManager.getChance().getIsAvailable(), true);
        assertEquals(combinationManager.getYahtzee().getIsAvailable(), true);
    }

    @Test
    public void bonusIsDenied() {
        combinationManager.setTotal(50);
        assertEquals(combinationManager.scoreBonus(), false);
    }

    @Test
    public void bonusIsGiven() {
        combinationManager.setTotal(70);
        assertEquals(combinationManager.scoreBonus(), true);
    }

    @Test
    public void noChosenSpotted() {
        combinationManager.getDice().get(0).setChosen(false);
        combinationManager.getDice().get(1).setChosen(false);
        combinationManager.getDice().get(2).setChosen(false);
        combinationManager.getDice().get(3).setChosen(false);
        combinationManager.getDice().get(4).setChosen(false);
        assertEquals(combinationManager.chosenDiceExist(), false);
    }

    @Test
    public void chosenSpotted() {
        combinationManager.getDice().get(0).setChosen(true);
        assertEquals(combinationManager.chosenDiceExist(), true);
    }

    @Test
    public void clickOnBonusIgnored() {
        assertEquals(combinationManager.isIllegalCombination(BONUS), true);
    }

    @Test
    public void clickOnTotalIgnored() {
        assertEquals(combinationManager.isIllegalCombination(TOTAL), true);
    }

    @Test
    public void diceAreReset() {
        combinationManager.resetDice();
        assertEquals(combinationManager.getDice().get(0).getChosen(), false);
        assertEquals(combinationManager.getDice().get(1).getChosen(), false);
        assertEquals(combinationManager.getDice().get(2).getChosen(), false);
        assertEquals(combinationManager.getDice().get(3).getChosen(), false);
        assertEquals(combinationManager.getDice().get(4).getChosen(), false);
        assertEquals(combinationManager.getDice().get(0).getValue(), 1);
        assertEquals(combinationManager.getDice().get(1).getValue(), 1);
        assertEquals(combinationManager.getDice().get(2).getValue(), 1);
        assertEquals(combinationManager.getDice().get(3).getValue(), 1);
        assertEquals(combinationManager.getDice().get(4).getValue(), 1);
    }
}
