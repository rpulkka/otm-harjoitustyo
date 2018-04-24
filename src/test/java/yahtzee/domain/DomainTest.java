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
    DieImage one;
    DieImage two;
    DieImage three;
    DieImage four;
    DieImage five;
    DieImage six;
    Die slot1;
    Die slot2;
    Die slot3;
    Die slot4;
    Die slot5;
    DiceThrower thrower;
    CombinationManager combinationManager;
    Reset reset;

    FirstRoundCombination first;
    Pair pair;

    DataList dataList;

    public DomainTest() throws Exception {
        URL urlOne = this.getClass().getResource("/images/one.png");
        URL urlTwo = this.getClass().getResource("/images/two.png");
        URL urlThree = this.getClass().getResource("/images/three.png");
        URL urlFour = this.getClass().getResource("/images/four.png");
        URL urlFive = this.getClass().getResource("/images/five.png");
        URL urlSix = this.getClass().getResource("/images/six.png");

        images = new ArrayList<DieImage>();

        one = new DieImage(urlOne);
        images.add(one);
        two = new DieImage(urlTwo);
        images.add(two);
        three = new DieImage(urlThree);
        images.add(three);
        four = new DieImage(urlFour);
        images.add(four);
        five = new DieImage(urlFive);
        images.add(five);
        six = new DieImage(urlSix);
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

        first = new FirstRoundCombination(dice, 1);

        pair = new Pair(dice, "one");

        dataList = new DataList(dice);

        //scoreboard = new TableView();
        //combinationManager = new CombinationManager(scoreboard, dice, images, thrower);
        //reset = new Reset(combinationManager, thrower, count, dice);
        //count = new Label();
    }

    @Before
    public void setUp() {
        die = new Die(images);
    }

    @Test
    public void value1Test() {
        die.setValue(1);
        assertEquals(die.getValue(), 1);
    }

    @Test
    public void wrongValueTest() {
        die.setValue(-2);
        assertEquals(die.getValue(), 1);
    }

    /*
    @Test
    public void throwCountTest(){
        thrower.setTimesThrown(0);
        thrower.throwDice(count);
        assertEquals(thrower.getTimesThrown(),1);
    }
    
    @Test
    public void throwLimitTest(){
        thrower.setTimesThrown(3);
        thrower.throwDice(count);
        assertEquals(thrower.getTimesThrown(),3);
        assertEquals(slot4.getChosen(),true);
    }
    
    @Test
    public void diceUnlockTest(){
        thrower.setTimesThrown(0);
        thrower.throwDice(count);
        assertEquals(slot4.getChosen(),false);    dice.get(0).setValue(1);
        dice.get(1).setValue(1);
        dice.get(2).setValue(1);
        dice.get(3).setValue(1);
        dice.get(4).setValue(1);

        int number = first.score();
        assertEquals(number, 0);
    }
    }
    /*
    @Test
    public void firstRoundCombinationScoredTest(){
        combinationManager.scoreCombination(reset);
        
    }

    @Test
    public void FirstRoundCombinationTest() {
        dice.get(0).setValue(1);
        dice.get(1).setValue(1);
        dice.get(2).setValue(1);
        dice.get(3).setValue(1);
        dice.get(4).setValue(1);

        int number = first.score();
        assertEquals(number, 0);
    }
    
    @Test
    public void PairTest() {
        dice.get(0).setValue(1);
        dice.get(1).setValue(2);
        dice.get(2).setValue(3);
        dice.get(3).setValue(4);
        dice.get(4).setValue(4);

        int number = first.score();
        assertEquals(number, 0);
    }*/
    @Test
    public void dataListTest() {
        int howMany = 5;
        ArrayList<Integer> list = dataList.list(howMany);
        int result = list.get(0);
        assertEquals(result, 1);
    }

    @Test
    public void image1Test() {
        slot1.changeImage(1);
        assertEquals(slot1.getImage(), one);
    }

    @Test
    public void image2Test() {
        slot1.changeImage(2);
        assertEquals(slot1.getImage(), two);
    }

    @Test
    public void image3Test() {
        slot1.changeImage(3);
        assertEquals(slot1.getImage(), three);
    }

    @Test
    public void image4Test() {
        slot1.changeImage(4);
        assertEquals(slot1.getImage(), four);
    }

    @Test
    public void image5Test() {
        slot1.changeImage(5);
        assertEquals(slot1.getImage(), five);
    }

    @Test
    public void image6Test() {
        slot1.changeImage(6);
        assertEquals(slot1.getImage(), six);
    }
}
