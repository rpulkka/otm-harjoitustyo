package yahtzee.domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

// @author rpulkka
public class DieTest {

    private Die die;

    public DieTest() {
    }

    @Before
    public void setUp() {
        die = new Die(650, 250, 380, 720);
    }

    @Test
    public void valueChangesCorrectly() {
        die.setValue(6);
        assertEquals(die.getValue(), 6);
    }

    @Test
    public void canBeChosen() {
        die.setChosen(true);
        assertEquals(die.getChosen(), true);
    }

    @Test
    public void pickingWorks() {
        die.pick();
        assertEquals(die.getX(), 380);
        assertEquals(die.getY(), 720);
    }

    @Test
    public void wontPickChosenDice() {
        die.setChosen(true);
        die.pick();
        assertEquals(die.getX(), 650);
        assertEquals(die.getY(), 250);
    }

    @Test
    public void dieMoves() {
        die.move(10, 10);
        assertEquals(die.getX(), 10);
        assertEquals(die.getY(), 10);
    }

    @Test
    public void parametersCanBeChanged() {
        die.setX(10);
        die.setY(10);
        assertEquals(die.getX(), 10);
        assertEquals(die.getY(), 10);
    }
}
