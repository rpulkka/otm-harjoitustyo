
package yahtzee.domain;

import java.util.ArrayList;


// @author rpulkka
 
public class FirstRoundCombination implements Combination {
    private int type;
    private ArrayList<Die> dice;
    private boolean isAvailable;
    
    public FirstRoundCombination(ArrayList<Die> dice, int type){
        this.type = type;
        this.dice = dice;
        this.isAvailable = true;
    }
    
    public int score(){
        this.isAvailable = false;
        
        ChosenDiceList correctDice = new ChosenDiceList();
        dice = correctDice.chosenList(dice);
        
        int sum=0;
        for(Die die:dice){
            if(die.getValue() == type){
                sum+=die.getValue();
            }
        }
        return sum;
    }
    
    public boolean getIsAvailable(){
        return this.isAvailable;
    }
    
    public void setIsAvailable(boolean b){
        this.isAvailable = b;
    }
}
