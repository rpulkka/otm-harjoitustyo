
package yahtzee.domain;

import java.util.ArrayList;


// @author rpulkka
 
public class ChosenDiceList {
    
    public ChosenDiceList (){
    }
    
    public ArrayList<Die> chosenList(ArrayList<Die> dice){
        ArrayList<Die> chosenOnes = new ArrayList<Die>();
        for(Die die:dice){
            if(die.getChosen()==true){
                chosenOnes.add(die);
            }
        }
        return chosenOnes;
    }
}
