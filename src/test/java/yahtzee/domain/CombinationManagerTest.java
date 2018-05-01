package yahtzee.domain;

import java.io.IOException;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static yahtzee.domain.Combination.CombinationType.ACES;
import yahtzee.ui.YahtzeeUI;

// @author rpulkka
public class CombinationManagerTest {

    private CombinationManager combinationManager;

    public CombinationManagerTest() {
    }

    @Before
    public void setUp() {
        this.combinationManager = new CombinationManager();
    }

    @Test
    public void identifiesRightCombination() {
        Combination combination = combinationManager.getCombination(ACES);
        assertEquals(combination.getCombinationType(), ACES);
    }

    @Test
    public void scoresAcesRight() {
        ArrayList<Die> dice = ui.getDice();
        ui.getDice().get(0).setValue(2);
        ui.getDice().get(0).setValue(2);
        Combination combination = new FirstRoundCombination(dice, ACES);
        assertEquals(combinationManager.countPoints(combination), 3);
    }
}
