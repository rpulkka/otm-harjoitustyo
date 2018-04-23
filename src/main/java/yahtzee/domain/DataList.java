
package yahtzee.domain;

// @author rpulkka

import java.util.ArrayList;
import java.util.HashMap;


public class DataList {
    private ArrayList<Die> dice;
    private HashMap<Integer, Integer> map;
    
    public DataList(ArrayList<Die> dice){
        this.dice = dice;
        this.map = new HashMap<Integer, Integer>();
        map.put(1, 0);
        map.put(2, 0);
        map.put(3, 0);
        map.put(4, 0);
        map.put(5, 0);
        map.put(6, 0);
    }
    
    public ArrayList<Integer> list(int howMany) {
        ArrayList<Integer> values = new ArrayList<Integer>();
        
        for (Die die : dice) {
            map.put(die.getValue(), map.get(die.getValue()) + 1);
        }
        
        for (Integer i : map.keySet()) {
            if (map.get(i) == howMany) {
                values.add(i);
            }
        }
        return values;
    }
}
