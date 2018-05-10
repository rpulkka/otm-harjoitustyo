
package yahtzee.domain;

import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

//@author rpulkka
public class DiceThrowerTest {
    private DiceThrower thrower;
    
    public DiceThrowerTest() {
    }
    
    @Before
    public void setUp() {
        thrower = new DiceThrower();
        ArrayList<Die> dice = new ArrayList<Die>();
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
        thrower.setDice(dice);
    }
    
    @Test
    public void counterChanges() {
        String timesThrown = thrower.throwDice();
        assertEquals(timesThrown, "Times thrown: " + 1 + "/3");
    }
    
    @Test
    public void wontThrowTooManyTimes() {
        thrower.setTimesThrown(3);
        String timesThrown = thrower.throwDice();
        assertEquals(timesThrown, null);
    }
    
    @Test 
    public void timesThrownIsCorrect() {
        thrower.throwDice();
        thrower.throwDice();
        assertEquals(thrower.getTimesThrown(), 2);
    }
}
