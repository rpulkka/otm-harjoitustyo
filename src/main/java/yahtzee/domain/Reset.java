package yahtzee.domain;

// @author rpulkka
import java.util.ArrayList;
import javafx.scene.control.Label;

public class Reset {

    CombinationManager com;
    DiceThrower thr;
    Label lbl;
    ArrayList<Die> dice;

    public Reset(CombinationManager com, DiceThrower thr, Label count, ArrayList<Die> dice) {
        this.com = com;
        this.thr = thr;
        this.lbl = count;
        this.dice = dice;
    }

    public void resetNow() {
        for (Die die : dice) {
            die.setChosen(false);
            die.setValue(1);
        }

        thr.setTimesThrown(0);

        dice.get(0).getSlot().setLayoutX(650);
        dice.get(0).getSlot().setLayoutY(250);
        dice.get(1).getSlot().setLayoutX(750);
        dice.get(1).getSlot().setLayoutY(250);
        dice.get(2).getSlot().setLayoutX(850);
        dice.get(2).getSlot().setLayoutY(250);
        dice.get(3).getSlot().setLayoutX(700);
        dice.get(3).getSlot().setLayoutY(350);
        dice.get(4).getSlot().setLayoutX(800);
        dice.get(4).getSlot().setLayoutY(350);

        lbl.setText("Times thrown: " + thr.getTimesThrown() + "/3");
        com.setScoredYet(false);
    }
}
