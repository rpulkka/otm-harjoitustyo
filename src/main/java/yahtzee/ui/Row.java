package yahtzee.ui;

import javafx.beans.property.SimpleStringProperty;

// @author rpulkka
public class Row {

    private SimpleStringProperty text;
    private SimpleStringProperty points;

    public Row(String combo, String number) {
        super();
        this.text = new SimpleStringProperty(combo);
        this.points = new SimpleStringProperty(number);
    }

    public String getText() {
        return text.get();
    }

    public void setText(String newValue) {
        text.set(newValue);
    }

    public String getPoints() {
        return points.get();
    }

    public void setPoints(String newValue) {
        points.set(newValue);
    }
}
