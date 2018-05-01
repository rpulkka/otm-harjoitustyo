package yahtzee.ui;

// @author rpulkka
public class Controller {

    private YahtzeeUI ui;

    public Controller(YahtzeeUI ui) {
        this.ui = ui;
    }

    public void handleDiceThrow() {
        String text = ui.getThrower().throwDice();
        if (text != null) {
            viewText(text);
        }
        viewAllImages();
    }

    public void handleDiePicked(int order) {
        if (ui.getThrower().getTimesThrown() != 0) {
            ui.getDice().get(order).pick();
            moveImage(ui.getDice().get(order).getX(), ui.getDice().get(order).getY(), order);
        }
    }

    public void handleCombinationScored() {
        Row row = (Row) ui.getScoreboard().getSelectionModel().getSelectedItem();
        if (ui.getCombinationManager().scoreCombination(row.getCombination()) == true) {
            refreshThisCell("" + ui.getCombinationManager().countPoints());
            if (ui.getCombinationManager().isIsFirstRound() == true) {
                if (ui.getCombinationManager().firstRoundIsOver() == true) {
                    ui.getCombinationManager().beginSecondRound();
                    if (ui.getCombinationManager().getTotal() >= 63) {

                        refreshOtherCell("50", 6);
                        ui.getCombinationManager().setTotal(ui.getCombinationManager().getTotal() + 50);
                    } else {

                        refreshOtherCell("0", 6);
                    }
                    ui.getCombinationManager().setIsFirstRound(false);
                }
            } else {
                if (ui.getCombinationManager().gameIsOver() == true) {
                    refreshOtherCell("" + ui.getCombinationManager().getTotal(), 16);
                }
            }
            refreshRound();
        }
    }

    public void resetNow() {
        ui.getReset().resetNow();
        viewAllImages();
        ui.getThrower().setTimesThrown(0);
        moveImage(650, 250, 0);
        moveImage(750, 250, 1);
        moveImage(850, 250, 2);
        moveImage(700, 350, 3);
        moveImage(800, 350, 4);
        viewText("Times thrown: " + ui.getThrower().getTimesThrown() + "/3");
    }

    public void viewText(String text) {
        ui.getCount().setText(text);
    }

    public void viewImage(int order, int value) {
        ui.getViews().get(order).setImage(ui.getImages().get(value - 1).getImage());
    }
    
    public void viewAllImages() {
        viewImage(0, ui.getDice().get(0).getValue());
        viewImage(1, ui.getDice().get(1).getValue());
        viewImage(2, ui.getDice().get(2).getValue());
        viewImage(3, ui.getDice().get(3).getValue());
        viewImage(4, ui.getDice().get(4).getValue());
    }

    public void moveImage(int x, int y, int order) {
        ui.getViews().get(order).setLayoutX(x);
        ui.getViews().get(order).setLayoutY(y);
    }

    public void refreshRound() {
        ui.getScoreboard().getSelectionModel().clearSelection();
        ui.getScoreboard().refresh();
        resetNow();
    }

    public void refreshThisCell(String points) {
        Row row = (Row) ui.getScoreboard().getSelectionModel().getSelectedItem();
        row.setPoints(points);
    }

    public void refreshOtherCell(String points, int whichRow) {
        Row row = (Row) ui.getScoreboard().getItems().get(whichRow);
        row.setPoints(points);
    }
}
