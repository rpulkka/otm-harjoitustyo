package yahtzee.ui;

import javafx.beans.property.SimpleStringProperty;

// @author rpulkka
public class Row {

    private SimpleStringProperty combination;
    private SimpleStringProperty points;

    public Row(String combo, String number) {
        super();
        this.combination = new SimpleStringProperty(combo);
        this.points = new SimpleStringProperty(number);
    }

    public String getCombination() {
        return combination.get();
    }

    public void setCombination(String newValue) {
        combination.set(newValue);
    }

    public String getPoints() {
        return points.get();
    }

    public void setPoints(String newValue) {
        points.set(newValue);
    }
}
