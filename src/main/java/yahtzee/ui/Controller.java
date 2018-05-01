package yahtzee.ui;

// @author rpulkka

/**
 * Controller is the bridge between YahtzeeUI and domain classes. It is called
 * directing tasks to domain classes once an event is triggered in the UI/main
 * class.
 */
public class Controller {

    private YahtzeeUI ui;

    public Controller(YahtzeeUI ui) {
        this.ui = ui;
    }

    /**
     * This method handles the event triggered by the dice throwing button. It
     * calls DiceThrower's throwDice() method to throw the dice and then it
     * calls the controller's own method viewText(String) to pass forward the
     * data of how many throws has been used in this round.
     *
     * @see domain.DiceThrower#throwDice()
     * @see Controller#viewText(java.lang.String)
     */
    public void handleDiceThrow() {
        String text = ui.getThrower().throwDice();
        if (text != null) {
            viewText(text);
        }
        viewAllImages();
    }

    /**
     * This method handles the event triggered by a die being clicked. It calls
     * Die class' pick() method to inform that the die has been selected and
     * then it calls controller's moveImage(int, int, int) to move the die to
     * the combination area.
     *
     * @see domain.Die#pick()
     * @see Controller#moveImage(int, int, int)
     */
    public void handleDiePicked(int order) {
        if (ui.getThrower().getTimesThrown() != 0) {
            ui.getDice().get(order).pick();
            moveImage(ui.getDice().get(order).getX(), ui.getDice().get(order).getY(), order);
        }
    }

    /**
     * This method handles the event triggered by scoreboard being clicked. It
     * calls CombinationManager's combinationIsValid to check if combination can
     * be scored and then calls refreshThisCell() with parameter countPoints()
     * to update the received points to table's cell.
     *
     * @see domain.CombinationManager#combinationIsValid()
     * @see domain.CombinationManager#countPoints()
     * @see Controller#checkRound() 
     * @see Controller#refreshRound() 
     */
    public void handleCombinationScored() {
        Row row = (Row) ui.getScoreboard().getSelectionModel().getSelectedItem();
        if (ui.getCombinationManager().combinationIsValid(row.getCombination())) {
            refreshThisCell("" + ui.getCombinationManager().countPoints());
            checkRound();
            refreshRound();
        }
    }

    /**
     * Resets the dice back to their original places by calling moveImage(int, int, int)
     * for each die to get them back to throwing area and by calling CombinationManager's
     * resetDice() to reset dice values and by calling DiceThrower's setTimesThrown(int) to
     * reset throwing counter.
     *
     * @see domain.CombinationManager#resetDice()
     * @see domain.DiceThrower#getTimesThrown()
     * @see Controller#moveImage
     * @see Controller#viewText
     */
    public void resetNow() {
        ui.getCombinationManager().resetDice();
        viewAllImages();
        ui.getThrower().setTimesThrown(0);
        moveImage(650, 250, 0);
        moveImage(750, 250, 1);
        moveImage(850, 250, 2);
        moveImage(700, 350, 3);
        moveImage(800, 350, 4);
        viewText("Times thrown: " + ui.getThrower().getTimesThrown() + "/3");
    }

    /**
     * Checks if it's first round by calling isFirstRound() and
     * calls firstRoundIsOver and changeRound() if that's true.
     * Also checks if game's over by calling gameIsOver() and if true,
     * then refreshOtherCell(String, int) is called to view total score.
     *
     * @see domain.CombinationManager#isFirstRound()
     * @see domain.CombinationManager#firstRoundIsOver()
     * @see Controller#changeRound()
     * @see domain.CombinationManager#gameIsOver()
     * @see Controller#refreshOtherCell()
     */
    public void checkRound() {
        if (ui.getCombinationManager().isFirstRound()) {
            if (ui.getCombinationManager().firstRoundIsOver()) {
                changeRound();
            }
        } else {
            if (ui.getCombinationManager().gameIsOver()) {
                refreshOtherCell("" + ui.getCombinationManager().getTotal(), 16);
            }
        }
    }

    /**
     * Changes round by calling beginSecondRound() and then it calls
     * scoreBonus() to know which parameter to give to refreshOtherCell(String, int)
     * which updates the points in the bonus cell. 
     *
     * @see domain.CombinationManager#beginSecondRound()
     * @see domain.CombinationManager#scoreBonus()
     * @see Controller#refreshOtherCell()
     */
    public void changeRound() {
        ui.getCombinationManager().beginSecondRound();
        if (ui.getCombinationManager().scoreBonus()) {
            refreshOtherCell("50", 6);
        } else {
            refreshOtherCell("0", 6);
        }
    }

    /**
     * Changes the throwing counter text on screen.
     * 
     * @param text The text that will be shown on screen.
     */
    public void viewText(String text) {
        ui.getCount().setText(text);
    }

    /**
     * Changes the image of a die's value on screen.
     * 
     * @param order The dice that is referred.
     * @param value New value of the dice.
     */
    public void viewImage(int order, int value) {
        ui.getViews().get(order).setImage(ui.getImages().get(value - 1).getImage());
    }

    /**
     * Calls viewImage(int, int) for all of the dice.
     * 
     * @see Controller#viewImage(int, int) 
     */
    public void viewAllImages() {
        viewImage(0, ui.getDice().get(0).getValue());
        viewImage(1, ui.getDice().get(1).getValue());
        viewImage(2, ui.getDice().get(2).getValue());
        viewImage(3, ui.getDice().get(3).getValue());
        viewImage(4, ui.getDice().get(4).getValue());
    }

    /**
     * Moves the dice on the screen.
     * 
     * @param x The new x coordinate.
     * @param y The new y coordinate.
     * @param order The dice being referred.
     */
    public void moveImage(int x, int y, int order) {
        ui.getViews().get(order).setLayoutX(x);
        ui.getViews().get(order).setLayoutY(y);
    }

    /**
     * Refreshes the scoreboard and calls resetNow()
     * 
     * @see Controller#resetNow() 
     */
    public void refreshRound() {
        ui.getScoreboard().getSelectionModel().clearSelection();
        ui.getScoreboard().refresh();
        resetNow();
    }

    /**
     * Updates a new value to the table cell that's being selected
     * by calling Row's setPoints().
     * 
     * @see Row#setPoints(java.lang.String) 
     * 
     * @param points The string that will be shown on screen as points.
     */
    public void refreshThisCell(String points) {
        Row row = (Row) ui.getScoreboard().getSelectionModel().getSelectedItem();
        row.setPoints(points);
    }

     /**
     * Updates a new value to total or bonus cell
     * by calling Row's setPoints().
     * 
     * @see Row#setPoints(java.lang.String) 
     * 
     * @param points The string that will be shown on screen as points.
     */
    public void refreshOtherCell(String points, int whichRow) {
        Row row = (Row) ui.getScoreboard().getItems().get(whichRow);
        row.setPoints(points);
    }
}
