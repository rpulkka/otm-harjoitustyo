
package yahtzee.ui;


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
import javafx.scene.layout.GridPane;
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
        
        InputStream inputStream = urlOne.openStream();
        Image one = new Image(inputStream);
        ImageView oneView = new ImageView(one);
        
        Button button = new Button();
        button.setText("Throw the dice!");
        
        GridPane layout = new GridPane();
        layout.getChildren().add(button);
        layout.getChildren().add(oneView);
        
        Scene scene = new Scene(layout);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
}
