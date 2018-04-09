package yahtzee.ui;

import yahtzee.domain.*;

import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

//author rpulkka
public class YahtzeeUI extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Yahtzee Game");

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

        Pane layout = new Pane();

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

        button.setLayoutX(800);
        button.setLayoutY(450);

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                thrower.throwDice();
                
            }
        });

        layout.getChildren().add(slot1.getSlot());
        layout.getChildren().add(slot2.getSlot());
        layout.getChildren().add(slot3.getSlot());
        layout.getChildren().add(slot4.getSlot());
        layout.getChildren().add(slot5.getSlot());

        layout.getChildren().add(button);

        Scene scene = new Scene(layout, 1200, 900);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
