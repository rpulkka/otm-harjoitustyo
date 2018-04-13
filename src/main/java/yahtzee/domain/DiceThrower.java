
package yahtzee.domain;

import java.util.ArrayList;
import java.util.Random;


// @author rpulkka

public class DiceThrower {
    
    private ArrayList<Die> dice;
    
    public DiceThrower(ArrayList<Die> dice){
        this.dice = dice;
    }
    
    public void throwDice() {
        for(Die die : dice){
            int random = (int)(Math.random()*6 + 1);
            die.setValue(random);
        }
    }
}
