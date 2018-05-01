package yahtzee.ui;

import java.io.IOException;
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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

//author rpulkka
public class YahtzeeUI extends Application {

    private Controller controller;
    private Label count;
    private Label instr;
    private ArrayList<DieImage> images;
    private ArrayList<ImageView> views;
    private ArrayList<Die> dice;
    private DiceThrower thrower;
    private TableView scoreboard;
    private CombinationManager combinationManager;

    public static void main(String[] args) {
        launch(args);
    }

    public YahtzeeUI() throws IOException {
        controller = new Controller(this);
        
        count = new Label();
        instr = new Label();

        URL urlOne = this.getClass().getResource("/images/one.png");
        URL urlTwo = this.getClass().getResource("/images/two.png");
        URL urlThree = this.getClass().getResource("/images/three.png");
        URL urlFour = this.getClass().getResource("/images/four.png");
        URL urlFive = this.getClass().getResource("/images/five.png");
        URL urlSix = this.getClass().getResource("/images/six.png");

        images = new ArrayList<DieImage>();
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
        
        thrower = new DiceThrower();

        views = new ArrayList<ImageView>();
        
        ImageView view1 = new ImageView();
        view1.setImage(one.getImage());
        ImageView view2 = new ImageView();
        view2.setImage(one.getImage());
        ImageView view3 = new ImageView();
        view3.setImage(one.getImage());
        ImageView view4 = new ImageView();
        view4.setImage(one.getImage());
        ImageView view5 = new ImageView();
        view5.setImage(one.getImage());
        
        views.add(view1);
        views.add(view2);
        views.add(view3);
        views.add(view4);
        views.add(view5);
        
        controller.moveImage(650, 250, 0);
        controller.moveImage(750, 250, 1);
        controller.moveImage(850, 250, 2);
        controller.moveImage(700, 350, 3);
        controller.moveImage(800, 350, 4);
        
        dice = new ArrayList<Die>();

        Die die1 = new Die(650, 250, 380, 720);
        dice.add(die1);
        Die die2 = new Die(750, 250, 480, 720);
        dice.add(die2);
        Die die3 = new Die(850, 250, 580, 720);
        dice.add(die3);
        Die die4 = new Die(700, 350, 680, 720);
        dice.add(die4);
        Die die5 = new Die(800, 350, 780, 720);
        dice.add(die5);
        
        thrower.setDice(dice);

        combinationManager = new CombinationManager(dice);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Yahtzee Game");

        Rectangle throwingArea = new Rectangle();
        Rectangle combinationArea = new Rectangle();

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

        ObservableList<Row> data = FXCollections.observableArrayList();
        data.add(new Row("Aces", "-"));
        data.add(new Row("Twos", "-"));
        data.add(new Row("Threes", "-"));
        data.add(new Row("Fours", "-"));
        data.add(new Row("Fives", "-"));
        data.add(new Row("Sixes", "-"));
        data.add(new Row("Bonus", "-"));
        data.add(new Row("Pair", "-"));
        data.add(new Row("Two Pairs", "-"));
        data.add(new Row("Three Of A Kind", "-"));
        data.add(new Row("Four Of A Kind", "-"));
        data.add(new Row("Full House", "-"));
        data.add(new Row("Small Straight", "-"));
        data.add(new Row("Large Straight", "-"));
        data.add(new Row("Yahtzee", "-"));
        data.add(new Row("Chance", "-"));
        data.add(new Row("Total", "-"));

        scoreboard.setItems(data);

        scoreboard.getColumns().add(combinationColumn);
        scoreboard.getColumns().add(playerColumn);

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

        scoreboard.setLayoutX(100);
        scoreboard.setLayoutY(150);
        scoreboard.setMinHeight(440);

        button.setLayoutX(800);
        button.setLayoutY(450);

        views.get(0).setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                controller.handleDiePicked(0);
            }
        });

        views.get(1).setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                controller.handleDiePicked(1);
            }
        });

        views.get(2).setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                controller.handleDiePicked(2);
            }
        });

        views.get(3).setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                controller.handleDiePicked(3);
            }
        });

        views.get(4).setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                controller.handleDiePicked(4);
            }
        });

        scoreboard.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                controller.handleCombinationScored();
            }
        });

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                controller.handleDiceThrow();
            }
        });

        layout.getChildren().add(count);

        layout.getChildren().add(throwingArea);
        layout.getChildren().add(combinationArea);

        layout.getChildren().add(views.get(0));
        layout.getChildren().add(views.get(1));
        layout.getChildren().add(views.get(2));
        layout.getChildren().add(views.get(3));
        layout.getChildren().add(views.get(4));

        layout.getChildren().add(button);

        layout.getChildren().add(scoreboard);

        Scene scene = new Scene(layout, 1200, 900);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public ArrayList<DieImage> getImages() {
        return images;
    }

    public ArrayList<ImageView> getViews() {
        return views;
    }

    public void setViews(ArrayList<ImageView> views) {
        this.views = views;
    }

    public void setImages(ArrayList<DieImage> images) {
        this.images = images;
    }

    public ArrayList<Die> getDice() {
        return dice;
    }

    public void setDice(ArrayList<Die> dice) {
        this.dice = dice;
    }

    public DiceThrower getThrower() {
        return thrower;
    }

    public void setThrower(DiceThrower thrower) {
        this.thrower = thrower;
    }

    public TableView getScoreboard() {
        return scoreboard;
    }

    public void setScoreboard(TableView scoreboard) {
        this.scoreboard = scoreboard;
    }

    public CombinationManager getCombinationManager() {
        return combinationManager;
    }

    public void setCombinationManager(CombinationManager combinationManager) {
        this.combinationManager = combinationManager;
    }

    public Label getCount() {
        return count;
    }

    public void setCount(Label count) {
        this.count = count;
    }
}
