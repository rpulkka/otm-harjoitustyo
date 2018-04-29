package yahtzee.domain;

// @author rpulkka
import java.util.ArrayList;
import javafx.scene.control.Label;
import yahtzee.ui.YahtzeeUI;

public class Reset {

    CombinationManager com;
    DiceThrower thr;
    Label lbl;
    ArrayList<Die> dice;

    public Reset(YahtzeeUI ui) {
        this.com = ui.getCombinationManager();
        this.thr = ui.getThrower();
        this.lbl = ui.getCount();
        this.dice = ui.getDice();
    }

    public void resetNow() {
        for (Die die : dice) {
            die.setChosen(false);
            die.setValue(1);
        }

        thr.setTimesThrown(0);
        
        dice.get(0).move(650, 250);
        dice.get(1).move(750, 250);
        dice.get(2).move(850, 250);
        dice.get(3).move(700, 350);
        dice.get(4).move(800, 350);

        lbl.setText("Times thrown: " + thr.getTimesThrown() + "/3");
        //com.setScoredYet(false);
    }
}
