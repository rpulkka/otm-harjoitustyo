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

    TableView scoreboard;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Yahtzee Game");

        Label count = new Label();
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

        DiceMover mover1 = new DiceMover(thrower, slot1, 380, 720);
        DiceMover mover2 = new DiceMover(thrower, slot2, 480, 720);
        DiceMover mover3 = new DiceMover(thrower, slot3, 580, 720);
        DiceMover mover4 = new DiceMover(thrower, slot4, 680, 720);
        DiceMover mover5 = new DiceMover(thrower, slot5, 780, 720);

        Button button = new Button();
        button.setText("Throw the dice!");

        scoreboard = new TableView();

        scoreboard.setEditable(true);

        TableColumn<String, String> combinationColumn = new TableColumn<>("Combination");
        combinationColumn.setMinWidth(200);
        combinationColumn.setCellValueFactory(new PropertyValueFactory<String, String>("combination"));
        combinationColumn.setSortable(false);
        TableColumn<String, String> playerColumn = new TableColumn<>("Player 1");
        playerColumn.setMinWidth(200);
        playerColumn.setCellValueFactory(new PropertyValueFactory<String, String>("points"));
        playerColumn.setSortable(false);

        ObservableList<Score> data = FXCollections.observableArrayList();
        data.add(new Score("Aces", "-"));
        data.add(new Score("Twos", "-"));
        data.add(new Score("Threes", "-"));
        data.add(new Score("Fours", "-"));
        data.add(new Score("Fives", "-"));
        data.add(new Score("Sixes", "-"));
        data.add(new Score("Bonus", "-"));
        data.add(new Score("Pair", "-"));
        data.add(new Score("Two pairs", "-"));
        data.add(new Score("3 of a kind", "-"));
        data.add(new Score("4 of a kind", "-"));
        data.add(new Score("Full house", "-"));
        data.add(new Score("Small straight", "-"));
        data.add(new Score("Large straight", "-"));
        data.add(new Score("Yahtzee", "-"));
        data.add(new Score("Chance", "-"));
        data.add(new Score("Total", "-"));

        scoreboard.setItems(data);

        scoreboard.getColumns().add(combinationColumn);
        scoreboard.getColumns().add(playerColumn);

        CombinationManager combinationManager = new CombinationManager(scoreboard, dice, images, thrower);

        Reset reset = new Reset(combinationManager, thrower, count, dice);

        Pane layout = new Pane();

        count.setLayoutX(600);
        count.setLayoutY(510);
        count.setText("Times thrown: " + thrower.getTimesThrown() + "/3");
        count.setFont(Font.font("Cambria", 18));

        instr.setLayoutX(100);
        instr.setLayoutY(600);
        instr.setText("INSTRUCTIONS: Press the button 'Throw The Dice' to throw the dice. Move a die into combination area by clicking after a throw. "
                + "Click a combination name\nfrom the scoreboard to score that combination. The combination of dice that the player wishes to score"
                + " must be found from the combination area.\n"
                + "Points will be added to scoreboard, if the dice of the combination area meet the requirements of combination that is selected from "
                + "the scoreboard. "
                + "After\nscoring, dice will be reset, and a new turn starts. Check the combination requirements from Yahtzee wikipedia page until"
                + " in-game instructions are finished.\nNotification: you must fill the combinations above 'Bonus' before the other combinations"
                + " will be unlocked, like in the real game. Close the window to quit,\nbetter solution is under work.");
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
        scoreboard.setMinHeight(440);
        
        button.setLayoutX(800);
        button.setLayoutY(450);

        slot1.getSlot().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                mover1.move();
            }
        });

        slot2.getSlot().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                mover2.move();
            }
        });

        slot3.getSlot().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                mover3.move();
            }
        });

        slot4.getSlot().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                mover4.move();
            }
        });

        slot5.getSlot().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                mover5.move();
            }
        });

        scoreboard.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                combinationManager.scoreCombination(reset);
            }
        });

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                thrower.throwDice(count);
            }
        });

        layout.getChildren().add(count);
        layout.getChildren().add(instr);

        layout.getChildren().add(throwingArea);
        layout.getChildren().add(combinationArea);

        layout.getChildren().add(slot1.getSlot());
        layout.getChildren().add(slot2.getSlot());
        layout.getChildren().add(slot3.getSlot());
        layout.getChildren().add(slot4.getSlot());
        layout.getChildren().add(slot5.getSlot());

        layout.getChildren().add(button);

        layout.getChildren().add(scoreboard);

        Scene scene = new Scene(layout, 1200, 900);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
