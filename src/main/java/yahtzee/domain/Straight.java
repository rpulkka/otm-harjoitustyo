package yahtzee.domain;

import java.util.ArrayList;

//@author rpulkka
public class Straight implements Combination {

    ArrayList<Die> requirements;
    private ArrayList<DieImage> images;
    private ArrayList<Die> dice;
    private Die slot1;
    private Die slot2;
    private Die slot3;
    private Die slot4;
    private Die slot5;
    private String smallOrLarge;
    private boolean isAvailable;
    private boolean alreadyScored;

    public Straight(ArrayList<DieImage> images, ArrayList<Die> dice, String type) {
        requirements = new ArrayList<Die>();
        this.dice = dice;
        this.images = images;
        slot1 = new Die(images);
        slot2 = new Die(images);
        slot3 = new Die(images);
        slot4 = new Die(images);
        slot5 = new Die(images);
        smallOrLarge = type;
        this.isAvailable = false;
        this.alreadyScored = false;
    }

    public int score() {
        this.isAvailable = false;
        
        ChosenDiceList correctDice = new ChosenDiceList();
        dice = correctDice.chosenList(dice);

        if (smallOrLarge.equals("large")) {
            slot1.setValue(2);
            slot2.setValue(3);
            slot3.setValue(4);
            slot4.setValue(5);
            slot5.setValue(6);
        } else if (smallOrLarge.equals("small")) {
            slot1.setValue(1);
            slot2.setValue(2);
            slot3.setValue(3);
            slot4.setValue(4);
            slot5.setValue(5);
        }

        requirements.add(slot1);
        requirements.add(slot2);
        requirements.add(slot3);
        requirements.add(slot4);
        requirements.add(slot5);
        
        if (dice.containsAll(requirements)) {
            if (smallOrLarge.equals("large")) {
                return 20;
            } else if (smallOrLarge.equals("small")) {
                return 15;
            }
        }else{
            return 0;
        }
        return 0;
    }
    
    public boolean getIsAvailable(){
        return this.isAvailable;
    }
    
    public void setIsAvailable(boolean b){
        this.isAvailable = b;
    }
    
    public boolean getAlreadyScored() {
        return this.alreadyScored;
    }
    
    public void setAlreadyScored(boolean b) {
        this.alreadyScored = true;
    }
}
