package yahtzee.domain;

// @author rpulkka
public class DiceMover {

    private DiceThrower thr;
    private Die die;
    private int x;
    private int y;

    public DiceMover(DiceThrower thr, Die die, int x, int y) {
        this.thr = thr;
        this.die = die;
        this.x = x;
        this.y = y;
    }

    public void move() {
        if (thr.getTimesThrown() != 0) {
            if (die.getChosen() == false) {
                die.getSlot().setLayoutX(x);
                die.getSlot().setLayoutY(y);
                die.setChosen(true);
            }
        }
    }
}
