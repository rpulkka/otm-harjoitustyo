package yahtzee.ui;

import yahtzee.domain.*;

import java.net.URL;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

//author rpulkka
public class YahtzeeUI extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Yahtzee Game");

        Label count = new Label();
        Label alert = new Label();
        Label instr = new Label();

        Rectangle throwingArea = new Rectangle();
        Rectangle combinationArea = new Rectangle();

        URL urlOne = this.getClass().getResource("/images/one.png");
        URL urlTwo = this.getClass().getResource("/images/two.png");
        URL urlThree = this.getClass().getResource("/images/three.png");
        URL urlFour = this.getClass().getResource("/images/four.png");
        URL urlFive = this.getClass().getResource("/images/five.png");
        URL urlSix = this.getClass().getResource("/images/six.png");

        ArrayList<DieImage> images = new ArrayList<DieImage>();

        DieImage one = new DieImage(urlOne);
        images.add(one);
        DieImage two = new DieImage(urlTwo);
        images.add(two);
        DieImage three = new DieImage(urlThree);
        images.add(three);
        DieImage four = new DieImage(urlFour);
        images.add(four);
        DieImage five = new DieImage(urlFive);
        images.add(five);
        DieImage six = new DieImage(urlSix);
        images.add(six);

        ArrayList<Die> dice = new ArrayList<Die>();

        Die slot1 = new Die(images);
        dice.add(slot1);
        Die slot2 = new Die(images);
        dice.add(slot2);
        Die slot3 = new Die(images);
        dice.add(slot3);
        Die slot4 = new Die(images);
        dice.add(slot4);
        Die slot5 = new Die(images);
        dice.add(slot5);

        DiceThrower thrower = new DiceThrower(dice);

        Button button = new Button();
        button.setText("Throw the dice!");

        Button reset = new Button();
        reset.setText("Reset (New Turn)");

        TableView scoreboard = new TableView();

        scoreboard.setEditable(true);

        TableColumn<String, String> combinationColumn = new TableColumn<>("Combination");
        combinationColumn.setMinWidth(200);
        combinationColumn.setCellValueFactory(new PropertyValueFactory<String, String>("combination"));
        TableColumn<String, String> playerColumn = new TableColumn<>("Player 1");
        playerColumn.setMinWidth(200);
        playerColumn.setCellValueFactory(new PropertyValueFactory<String, String>("points"));

        scoreboard.setItems(scoreboardData());

        scoreboard.getColumns().add(combinationColumn);
        scoreboard.getColumns().add(playerColumn);

        Pane layout = new Pane();

        count.setLayoutX(600);
        count.setLayoutY(510);
        count.setText("Times thrown: " + thrower.getTimesThrown() + "/3");
        count.setFont(Font.font("Cambria", 18));

        alert.setLayoutX(100);
        alert.setLayoutY(600);
        alert.setText("Notification: Scoreboard logic hasn't been added yet! Press the reset button to reset the dice:");
        alert.setFont(Font.font("Cambria", 18));
        
        instr.setLayoutX(400);
        instr.setLayoutY(120);
        instr.setText("Press the button 'Throw The Dice' to throw the dice. Select a die by clicking after a throw. After 3 times, reset.");
        instr.setFont(Font.font("Cambria", 14));

        throwingArea.setX(600);
        throwingArea.setY(200);
        throwingArea.setWidth(400);
        throwingArea.setHeight(300);
        throwingArea.setArcWidth(20);
        throwingArea.setArcHeight(20);

        combinationArea.setX(350);
        combinationArea.setY(700);
        combinationArea.setWidth(520);
        combinationArea.setHeight(100);
        combinationArea.setArcWidth(20);
        combinationArea.setArcHeight(20);

        slot1.getSlot().setLayoutX(650);
        slot1.getSlot().setLayoutY(250);
        slot2.getSlot().setLayoutX(750);
        slot2.getSlot().setLayoutY(250);
        slot3.getSlot().setLayoutX(850);
        slot3.getSlot().setLayoutY(250);
        slot4.getSlot().setLayoutX(700);
        slot4.getSlot().setLayoutY(350);
        slot5.getSlot().setLayoutX(800);
        slot5.getSlot().setLayoutY(350);

        scoreboard.setLayoutX(100);
        scoreboard.setLayoutY(150);

        slot1.getSlot().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (slot1.getChosen() == false) {
                    slot1.getSlot().setLayoutX(380);
                    slot1.getSlot().setLayoutY(720);
                    slot1.setChosen(true);
                }
            }
        });

        slot2.getSlot().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (slot2.getChosen() == false) {
                    slot2.getSlot().setLayoutX(480);
                    slot2.getSlot().setLayoutY(720);
                    slot2.setChosen(true);
                }
            }
        });

        slot3.getSlot().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (slot3.getChosen() == false) {
                    slot3.getSlot().setLayoutX(580);
                    slot3.getSlot().setLayoutY(720);
                    slot3.setChosen(true);
                }
            }
        });

        slot4.getSlot().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (slot4.getChosen() == false) {
                    slot4.getSlot().setLayoutX(680);
                    slot4.getSlot().setLayoutY(720);
                    slot4.setChosen(true);
                }
            }
        });

        slot5.getSlot().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (slot5.getChosen() == false) {
                    slot5.getSlot().setLayoutX(780);
                    slot5.getSlot().setLayoutY(720);
                    slot5.setChosen(true);
                }
            }
        });

        button.setLayoutX(800);
        button.setLayoutY(450);

        reset.setLayoutX(950);
        reset.setLayoutY(600);

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                thrower.throwDice();
                count.setText("Times thrown: " + thrower.getTimesThrown() + "/3");

            }
        });

        reset.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                for (Die die : dice) {
                    die.setChosen(true);
                    die.setValue(1);
                }

                thrower.setTimesThrown(0);

                slot1.getSlot().setLayoutX(650);
                slot1.getSlot().setLayoutY(250);
                slot2.getSlot().setLayoutX(750);
                slot2.getSlot().setLayoutY(250);
                slot3.getSlot().setLayoutX(850);
                slot3.getSlot().setLayoutY(250);
                slot4.getSlot().setLayoutX(700);
                slot4.getSlot().setLayoutY(350);
                slot5.getSlot().setLayoutX(800);
                slot5.getSlot().setLayoutY(350);
                
                count.setText("Times thrown: " + thrower.getTimesThrown() + "/3");
            }
        });

        layout.getChildren().add(count);
        layout.getChildren().add(alert);
        layout.getChildren().add(instr);

        layout.getChildren().add(throwingArea);
        layout.getChildren().add(combinationArea);

        layout.getChildren().add(slot1.getSlot());
        layout.getChildren().add(slot2.getSlot());
        layout.getChildren().add(slot3.getSlot());
        layout.getChildren().add(slot4.getSlot());
        layout.getChildren().add(slot5.getSlot());

        layout.getChildren().add(button);
        layout.getChildren().add(reset);

        layout.getChildren().add(scoreboard);

        Scene scene = new Scene(layout, 1200, 900);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public ObservableList<Score> scoreboardData() {
        ObservableList<Score> data = FXCollections.observableArrayList();
        data.add(new Score("Aces", "0"));
        data.add(new Score("Twos", "0"));
        data.add(new Score("Threes", "0"));
        data.add(new Score("Fours", "0"));
        data.add(new Score("Fives", "0"));
        data.add(new Score("Sixes", "0"));
        data.add(new Score("Bonus", "0"));
        data.add(new Score("3 of a kind", "0"));
        data.add(new Score("Full house", "0"));
        data.add(new Score("Small straight", "0"));
        data.add(new Score("Large straigth", "0"));
        data.add(new Score("Yahtzee", "0"));
        data.add(new Score("Chance", "0"));
        data.add(new Score("Total", "0"));
        return data;
    }
}
