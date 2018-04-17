
package yahtzee.domain;

import java.net.URL;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

// @author rpulkka
public class DomainTest {
    ArrayList<DieImage> images;
    ArrayList<Die> dice;
    Die die;
    Die slot1;
    Die slot2;
    Die slot3;
    Die slot4;
    Die slot5;
    DiceThrower thrower;

    public DomainTest() throws Exception{
        URL urlOne = this.getClass().getResource("/images/one.png");
        URL urlTwo = this.getClass().getResource("/images/two.png");
        URL urlThree = this.getClass().getResource("/images/three.png");
        URL urlFour = this.getClass().getResource("/images/four.png");
        URL urlFive = this.getClass().getResource("/images/five.png");
        URL urlSix = this.getClass().getResource("/images/six.png");

        images = new ArrayList<DieImage>();

        DieImage one = new DieImage(urlOne);
        images.add(one);
        DieImage two = new DieImage(urlTwo);
        images.add(two);
        DieImage three = new DieImage(urlThree);
        images.add(three);
        DieImage four = new DieImage(urlFour);
        images.add(four);
        DieImage five = new DieImage(urlFive);
        images.add(five);
        DieImage six = new DieImage(urlSix);
        images.add(six);
        
        dice = new ArrayList<Die>();
        
        slot1 = new Die(images);
        dice.add(slot1);
        slot2 = new Die(images);
        dice.add(slot2);
        slot3 = new Die(images);
        dice.add(slot3);
        slot4 = new Die(images);
        dice.add(slot4);
        slot5 = new Die(images);
        dice.add(slot5);
        
        thrower = new DiceThrower(dice);
    }

    @Before
    public void setUp() {
        die = new Die(images);
    }
    
    @Test
    public void value1Test(){
        die.setValue(1);
        assertEquals(die.getValue(),1);
    }
    
    @Test
    public void wrongValueTest(){
        die.setValue(-2);
        assertEquals(die.getValue(),1);
    }
    
    @Test
    public void throwCountTest(){
        thrower.setTimesThrown(0);
        thrower.throwDice();
        assertEquals(thrower.getTimesThrown(),1);
    }
    
    @Test
    public void throwLimitTest(){
        thrower.setTimesThrown(3);
        thrower.throwDice();
        assertEquals(thrower.getTimesThrown(),3);
        assertEquals(slot4.getChosen(),true);
    }
    
    @Test
    public void diceUnlockTest(){
        thrower.setTimesThrown(0);
        thrower.throwDice();
        assertEquals(slot4.getChosen(),false);
    }
}
