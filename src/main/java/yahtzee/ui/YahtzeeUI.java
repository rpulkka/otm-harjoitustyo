package yahtzee.ui;

import java.io.IOException;
import yahtzee.domain.*;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

//author rpulkka
public class YahtzeeUI extends Application {

    private Controller controller;
    private Stage primaryStage;
    private Label count;
    private ArrayList<DieImage> images;
    private ArrayList<ImageView> views;
    private ArrayList<Die> dice;
    private DiceThrower thrower;
    private TableView scoreboard;
    private CombinationManager combinationManager;

    public static void main(String[] args) {
        launch(args);
    }

    public YahtzeeUI() throws IOException, ClassNotFoundException {
        init();
    }

    @Override
    public void init() throws IOException, ClassNotFoundException {
        controller = new Controller(this);
        count = new Label();
        ArrayList<URL> urlList = urlList();
        images = images(urlList);
        thrower = new DiceThrower();
        views = imageViews(images);
        controller.initializeImages();
        dice = dice();
        thrower.setDice(dice);
        combinationManager = new CombinationManager(dice);
        scoreboard = scoreboard();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Yahtzee Game");
        this.primaryStage = primaryStage;
        Rectangle throwingArea = new Rectangle();
        Rectangle combinationArea = new Rectangle();
        Button throwTheDiceButton = throwTheDiceButton();
        initializeCountText();
        initializeThrowingArea(throwingArea);
        initializeCombinationArea(combinationArea);
        Pane layout = new Pane();
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
                try {
                    controller.handleCombinationScored();
                } catch (SQLException ex) {
                    Logger.getLogger(YahtzeeUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        throwTheDiceButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                controller.handleDiceThrow();
            }
        });
        initializeMainLayout(layout, throwingArea, combinationArea, throwTheDiceButton);
        Scene gameScene = new Scene(layout, 1200, 900);
        primaryStage.setScene(gameScene);
        primaryStage.show();
    }

    public void endGame(String scoreText) throws SQLException {
        Stage endGameScreen = new Stage();
        Pane layout = new Pane();
        Label endText = endText(scoreText);
        Label highscoreTitle = highscoreTitle();
        TableView highscores = highscoreTable();
        Button newGame = newGameButton();
        Button closeGame = closeGameButton();
        newGame.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                primaryStage.close();
                try {
                    endGameScreen.close();
                    init();
                    start(primaryStage);
                } catch (Exception ex) {
                    Logger.getLogger(YahtzeeUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        closeGame.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                endGameScreen.close();
                primaryStage.close();
            }
        });
        initializeEndGameLayout(layout, endText, highscoreTitle, highscores, newGame, closeGame);
        initializeEndGameStage(endGameScreen, layout);
    }

    public void identification() {
        Stage identificationScreen = new Stage();
        Pane layout = new Pane();
        Label instructions = instructions();
        TextField inputArea = inputArea();
        Button submit = submit();
        submit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                try {
                    if (controller.handleIdentification(inputArea.getText())) {
                        identificationScreen.close();
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(YahtzeeUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        initializeIdentificationLayout(layout, instructions, inputArea, submit);
        initializeIdentificationStage(identificationScreen, layout);
    }

    public ArrayList<URL> urlList() {
        ArrayList<URL> urlList = new ArrayList<URL>();
        urlList.add(this.getClass().getResource("/images/one.png"));
        urlList.add(this.getClass().getResource("/images/two.png"));
        urlList.add(this.getClass().getResource("/images/three.png"));
        urlList.add(this.getClass().getResource("/images/four.png"));
        urlList.add(this.getClass().getResource("/images/five.png"));
        urlList.add(this.getClass().getResource("/images/six.png"));
        return urlList;
    }

    public ArrayList<DieImage> images(ArrayList<URL> urlList) throws IOException {
        ArrayList<DieImage> images = new ArrayList<DieImage>();
        images.add(new DieImage(urlList.get(1)));
        images.add(new DieImage(urlList.get(2)));
        images.add(new DieImage(urlList.get(3)));
        images.add(new DieImage(urlList.get(4)));
        images.add(new DieImage(urlList.get(5)));
        images.add(new DieImage(urlList.get(6)));
        return images;
    }

    public ArrayList<ImageView> imageViews(ArrayList<DieImage> images) {
        ArrayList<ImageView> imageViews = new ArrayList<ImageView>();
        imageViews.add(new ImageView(images.get(0).getImage()));
        imageViews.add(new ImageView(images.get(0).getImage()));
        imageViews.add(new ImageView(images.get(0).getImage()));
        imageViews.add(new ImageView(images.get(0).getImage()));
        imageViews.add(new ImageView(images.get(0).getImage()));
        return imageViews;
    }

    public ArrayList<Die> dice() {
        ArrayList<Die> dice = new ArrayList<Die>();
        dice.add(new Die(650, 250, 380, 720));
        dice.add(new Die(750, 250, 480, 720));
        dice.add(new Die(850, 250, 580, 720));
        dice.add(new Die(700, 350, 680, 720));
        dice.add(new Die(800, 350, 780, 720));
        return dice;
    }

    public TableView scoreboard() {
        TableView scoreboard = new TableView();
        scoreboard.setEditable(true);
        scoreboard.setLayoutX(100);
        scoreboard.setLayoutY(150);
        scoreboard.setMinHeight(440);
        scoreboard.setItems(scoreboardRowData());
        scoreboard.getColumns().add(combinationColumn());
        scoreboard.getColumns().add(pointsColumn());
        return scoreboard;
    }

    public TableColumn<String, String> combinationColumn() {
        TableColumn<String, String> combinationColumn = new TableColumn<>("Combination");
        combinationColumn.setMinWidth(200);
        combinationColumn.setCellValueFactory(new PropertyValueFactory<String, String>("text"));
        combinationColumn.setSortable(false);
        return combinationColumn;
    }

    public TableColumn<String, String> pointsColumn() {
        TableColumn<String, String> pointsColumn = new TableColumn<>("Score");
        pointsColumn.setMinWidth(200);
        pointsColumn.setCellValueFactory(new PropertyValueFactory<String, String>("points"));
        pointsColumn.setSortable(false);
        return pointsColumn;
    }

    public ObservableList<Row> scoreboardRowData() {
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
        return data;
    }

    public TableView highscoreTable() throws SQLException {
        TableView highscores = new TableView();
        highscores.setLayoutX(10);
        highscores.setLayoutY(75);
        highscores.setMaxHeight(285);
        highscores.setMaxWidth(250);
        highscores.setEditable(false);
        ObservableList<Row> data = controller.highscoreTableConstructor();
        highscores.setItems(data);
        highscores.getColumns().add(nameColumn());
        highscores.getColumns().add(highscoreColumn());
        return highscores;
    }

    public TableColumn<String, String> nameColumn() {
        TableColumn<String, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setMinWidth(120);
        nameColumn.setMaxWidth(120);
        nameColumn.setCellValueFactory(new PropertyValueFactory<String, String>("text"));
        nameColumn.setSortable(false);
        return nameColumn;
    }

    public TableColumn<String, String> highscoreColumn() {
        TableColumn<String, String> highscoreColumn = new TableColumn<>("Score");
        highscoreColumn.setMinWidth(120);
        highscoreColumn.setMaxWidth(120);
        highscoreColumn.setCellValueFactory(new PropertyValueFactory<String, String>("points"));
        highscoreColumn.setSortable(false);
        return highscoreColumn;
    }

    public void initializeCountText() {
        count.setLayoutX(600);
        count.setLayoutY(510);
        count.setText("Times thrown: " + thrower.getTimesThrown() + "/3");
        count.setFont(Font.font("Cambria", 18));
    }

    private void initializeThrowingArea(Rectangle throwingArea) {
        throwingArea.setX(600);
        throwingArea.setY(200);
        throwingArea.setWidth(400);
        throwingArea.setHeight(300);
        throwingArea.setArcWidth(20);
        throwingArea.setArcHeight(20);
    }

    private void initializeCombinationArea(Rectangle combinationArea) {
        combinationArea.setX(350);
        combinationArea.setY(700);
        combinationArea.setWidth(520);
        combinationArea.setHeight(100);
        combinationArea.setArcWidth(20);
        combinationArea.setArcHeight(20);
    }

    public Button throwTheDiceButton() {
        Button button = new Button();
        button.setText("Throw the dice!");
        button.setLayoutX(800);
        button.setLayoutY(450);
        return button;
    }

    public void initializeMainLayout(Pane layout, Rectangle throwingArea, Rectangle combinationArea, Button throwTheDiceButton) {
        layout.getChildren().add(count);
        layout.getChildren().add(throwingArea);
        layout.getChildren().add(combinationArea);
        layout.getChildren().add(views.get(0));
        layout.getChildren().add(views.get(1));
        layout.getChildren().add(views.get(2));
        layout.getChildren().add(views.get(3));
        layout.getChildren().add(views.get(4));
        layout.getChildren().add(throwTheDiceButton);
        layout.getChildren().add(scoreboard);
    }

    public Label endText(String scoreText) {
        Label endText = new Label();
        endText.setText(scoreText);
        endText.setLayoutX(10);
        endText.setLayoutY(10);
        return endText;
    }

    public Label highscoreTitle() {
        Label highscoreTitle = new Label();
        highscoreTitle.setText("Top 10 List");
        highscoreTitle.setLayoutX(10);
        highscoreTitle.setLayoutY(50);
        return highscoreTitle;
    }

    public Button newGameButton() {
        Button newGame = new Button();
        newGame.setText("New game");
        newGame.setLayoutX(10);
        newGame.setLayoutY(400);
        return newGame;
    }

    public Button closeGameButton() {
        Button closeGame = new Button();
        closeGame.setText("Close the game");
        closeGame.setLayoutX(10);
        closeGame.setLayoutY(450);
        return closeGame;
    }

    public void initializeEndGameLayout(Pane layout, Label endText,
            Label highscoreTitle, TableView highscores,
            Button newGame, Button closeGame) {
        layout.getChildren().add(endText);
        layout.getChildren().add(highscoreTitle);
        layout.getChildren().add(highscores);
        layout.getChildren().add(newGame);
        layout.getChildren().add(closeGame);
    }

    public void initializeEndGameStage(Stage endGameScreen, Pane layout) {
        Scene scene = new Scene(layout, 300, 500);
        endGameScreen.setTitle("Game Over");
        endGameScreen.initModality(Modality.APPLICATION_MODAL);
        endGameScreen.setScene(scene);
        endGameScreen.show();
    }

    public Label instructions() {
        Label instructions = new Label();
        instructions.setText("Write your nickname (1-10 letters)");
        instructions.setLayoutX(10);
        instructions.setLayoutY(10);
        return instructions;
    }

    public TextField inputArea() {
        TextField inputArea = new TextField();
        inputArea.setLayoutX(40);
        inputArea.setLayoutY(50);
        inputArea.setMaxWidth(150);
        return inputArea;
    }

    public Button submit() {
        Button submit = new Button();
        submit.setText("Show Highscores");
        submit.setLayoutX(40);
        submit.setLayoutY(100);
        return submit;
    }

    public void initializeIdentificationLayout(Pane layout, Label instructions, TextField inputArea, Button submit) {
        layout.getChildren().add(instructions);
        layout.getChildren().add(inputArea);
        layout.getChildren().add(submit);
    }

    public void initializeIdentificationStage(Stage identificationScreen, Pane layout) {
        Scene scene = new Scene(layout, 300, 150);
        identificationScreen.setTitle("Nickname Selection");
        identificationScreen.initModality(Modality.APPLICATION_MODAL);
        identificationScreen.setScene(scene);
        identificationScreen.show();
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
