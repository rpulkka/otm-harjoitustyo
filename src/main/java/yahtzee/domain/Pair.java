
package yahtzee.domain;

import java.util.ArrayList;

// @author rpulkka

public class Pair implements Combination {

    private ArrayList<Die> dice;
    private String type;
    private boolean isAvailable;
    private boolean alreadyScored;

    public Pair(ArrayList<Die> dice, String type) {
        this.dice = dice;
        this.type = type;
        this.isAvailable = false;
        this.alreadyScored = false;
    }

    public int score() {
        this.isAvailable = false;
        
        ChosenDiceList correctDice = new ChosenDiceList();
        dice = correctDice.chosenList(dice);
        
        DataList datalist = new DataList(dice);
        ArrayList<Integer> pairValue = datalist.list(2);

        if (pairValue.size() == 1 && type.equals("one")) {
            return pairValue.get(0) * 2;
        } else if (pairValue.size() == 2) {
            Integer highest = 0;
            Integer lowest = 10;

            for (Integer i : pairValue) {
                if (i > highest) {
                    highest = i;
                }
                if (i < lowest) {
                    lowest = i;
                }
            }
            
            if(type.equals("one")){
                Integer sum = highest*2;
                System.out.println(sum);
                return sum;
            }else if(type.equals("two")){
                Integer sum = highest*2 + lowest*2;
                return sum;
            }
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
