package hiekkaranta;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class HiekkarantaSovellus extends Application{


    public static void main(String[] args) {
        System.out.println("Hello world!");
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Canvas alusta = new Canvas(200, 200);
        Simulaatio sim = new Simulaatio(200,200);
        BorderPane asettelu = new BorderPane();
        VBox napit = new VBox();
        RadioButton hiekka = new RadioButton("Hiekka");
        RadioButton vesi = new RadioButton("Vesi");
        RadioButton metalli = new RadioButton("Metalli");
        ToggleGroup ryhma = new ToggleGroup();
        
        GraphicsContext piirturi = alusta.getGraphicsContext2D();
        piirturi.setFill(Color.BLACK);
        piirturi.fillRect(0, 0, 200, 200);
        
        hiekka.setToggleGroup(ryhma);
        vesi.setToggleGroup(ryhma);
        metalli.setToggleGroup(ryhma);
        metalli.setSelected(true);
        
        napit.getChildren().addAll(metalli, hiekka, vesi);
        
        alusta.setOnMouseDragged((event) -> {
            double kohtaX = event.getX();
            double kohtaY = event.getY();
            if(ryhma.getSelectedToggle() == metalli){
                sim.lisaa((int)kohtaX, (int) kohtaY, Tyyppi.METALLI);
                sim.lisaa((int)kohtaX+1, (int) kohtaY+1, Tyyppi.METALLI);
                sim.lisaa((int)kohtaX-1, (int) kohtaY-1, Tyyppi.METALLI);
                sim.lisaa((int)kohtaX-1, (int) kohtaY+2, Tyyppi.METALLI);
                sim.lisaa((int)kohtaX-1, (int) kohtaY-2, Tyyppi.METALLI);
            }else if(ryhma.getSelectedToggle() == hiekka){
                sim.lisaa((int)kohtaX, (int)kohtaY, Tyyppi.HIEKKA);
                sim.lisaa((int)kohtaX+1, (int)kohtaY+1, Tyyppi.HIEKKA);
                sim.lisaa((int)kohtaX-1, (int)kohtaY-1, Tyyppi.HIEKKA);
                sim.lisaa((int)kohtaX-1, (int)kohtaY+2, Tyyppi.HIEKKA);
                sim.lisaa((int)kohtaX-1, (int)kohtaY-2, Tyyppi.HIEKKA);
            }else{
                sim.lisaa((int)kohtaX, (int)kohtaY, Tyyppi.VESI);
            }           
        });
        
        asettelu.setCenter(alusta);
        asettelu.setRight(napit);
        
        new AnimationTimer(){
            long edellinen = 0;
            
            @Override
            public void handle(long nykyhetki){
                if(nykyhetki - edellinen < 100000000){
                    return;
                }
                sim.paivita();
                this.edellinen = nykyhetki;
            }
        }.start();
        
        new AnimationTimer(){
            long edellinen = 0;
            
            @Override
            public void handle(long nykyhetki){
                if(nykyhetki - edellinen < 100000000){
                    return;
                }
                for(int y = 0; y < alusta.getHeight(); y++){
                    for(int x = 0; x < alusta.getWidth(); x++){
                        if(sim.sisalto((int)x, (int)y) == Tyyppi.METALLI){
                            piirturi.setFill(Color.WHITE);
                            piirturi.fillRect(x, y, 1, 1);
                        }else if(sim.sisalto((int)x, (int)y) == Tyyppi.HIEKKA){
                            piirturi.setFill(Color.ORANGE);
                            piirturi.fillRect(x, y, 1, 1);
                        }else if(sim.sisalto((int)x, (int)y) == Tyyppi.VESI){
                            piirturi.setFill(Color.LIGHTBLUE);
                            piirturi.fillRect(x, y, 1, 1);
                        }else{
                            piirturi.setFill(Color.BLACK);
                            piirturi.fillRect(x, y, 1, 1);
                        }
                    }
                }
                this.edellinen = nykyhetki;
            }
        }.start();
        
        Scene skene = new Scene(asettelu, 300, 200);
        
        primaryStage.setScene(skene);
        primaryStage.show();
    }
}
