
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

    
    public static void main(String[] args){
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
        
        InputStream isOne = urlOne.openStream();
        InputStream isTwo = urlTwo.openStream();
        InputStream isThree = urlThree.openStream();
        InputStream isFour = urlFour.openStream();
        InputStream isFive = urlFive.openStream();
        
        Image one = new Image(isOne);
        Image two = new Image(isTwo);
        Image three = new Image(isThree);
        Image four = new Image(isFour);
        Image five = new Image(isFive);
        
        ImageView oneView = new ImageView(one);
        ImageView twoView = new ImageView(two);
        ImageView threeView = new ImageView(three);
        ImageView fourView = new ImageView(four);
        ImageView fiveView = new ImageView(five);
        
        Button button = new Button();
        button.setText("Throw the dice!");
        
        Pane layout = new Pane();
        
        oneView.setLayoutX(650);
        oneView.setLayoutY(250);
        twoView.setLayoutX(750);
        twoView.setLayoutY(250);
        threeView.setLayoutX(850);
        threeView.setLayoutY(250);
        fourView.setLayoutX(700);
        fourView.setLayoutY(350);
        fiveView.setLayoutX(800);
        fiveView.setLayoutY(350);
        
        button.setLayoutX(800);
        button.setLayoutY(450);
        
        layout.getChildren().add(oneView);
        layout.getChildren().add(twoView);
        layout.getChildren().add(threeView);
        layout.getChildren().add(fourView);
        layout.getChildren().add(fiveView);
        
        layout.getChildren().add(button);
        
        Scene scene = new Scene(layout, 1200, 900);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
}
