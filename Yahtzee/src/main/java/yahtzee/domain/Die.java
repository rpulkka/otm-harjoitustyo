
package yahtzee.domain;

// @author rpulkka
 
public class Die {
    private int value;
    
    public Die(){
        value = 1;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}