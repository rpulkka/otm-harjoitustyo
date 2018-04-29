package hiekkaranta;

import java.util.Random;
import javafx.scene.canvas.Canvas;

/**
 *
 * @author Fayean
 */
public class Simulaatio {
    private int leveys;
    private int korkeus;
    private Tyyppi[][] tyypit;
    
    public Simulaatio(int x, int y){
        this.leveys = x;
        this.korkeus = y;
        this.tyypit = new Tyyppi[this.leveys][this.korkeus];
    }

    
    public void lisaa(int x, int y, Tyyppi tyyppi){
        this.tyypit[x][y] = tyyppi;
    }
    
    public Tyyppi sisalto(int x, int y){
        if(y < 0 || y >= korkeus){
            return Tyyppi.METALLI;
        }
        if(x < 0 || x >= leveys){
            return Tyyppi.METALLI;
        }
        if(this.tyypit[x][y] == null){
            return Tyyppi.TYHJA;
        }
        return this.tyypit[x][y];
    }
    
    public void paivita(){
        Tyyppi[][] uusiTaulukko = new Tyyppi[this.leveys][this.korkeus];
        for (int x = 0; x < this.leveys; x++) {
            for (int y = 0; y < this.korkeus; y++) {
                
                //System.out.println(sisalto(200, 0));
                
                if (this.sisalto(x, y) == Tyyppi.METALLI) {
                    uusiTaulukko[x][y] = Tyyppi.METALLI;
                }
                if(this.sisalto(x, y) == Tyyppi.VESI){
                    if(y + 1 < this.korkeus && this.sisalto(x, y+1) != Tyyppi.TYHJA || y + 1 == this.korkeus){
                        uusiTaulukko[x][y] = Tyyppi.VESI; 
                    }
                    if (y + 1 < this.korkeus && this.sisalto(x, y + 1) == Tyyppi.TYHJA) {
                        uusiTaulukko[x][y] = Tyyppi.TYHJA;
                        uusiTaulukko[x][y + 1] = Tyyppi.VESI;
                    }
                }
                
                if (this.sisalto(x, y) == Tyyppi.HIEKKA) {
                    if(y + 1 < this.korkeus && this.sisalto(x, y+1) != Tyyppi.TYHJA || y +1 == this.korkeus){
                        /*if(y+1 < this.korkeus && this.sisalto(x, y+1) == Tyyppi.VESI){
                            uusiTaulukko[x+1][y] = Tyyppi.VESI;
                            uusiTaulukko[x][y+1] = Tyyppi.HIEKKA;
                        }*/
                        uusiTaulukko[x][y] = Tyyppi.HIEKKA;
                        
                        if(y + 1 < this.korkeus && x -1 > 0 && this.sisalto(x-1, y+1) == Tyyppi.TYHJA){
                            uusiTaulukko[x][y] = Tyyppi.TYHJA;
                            uusiTaulukko[x-1][y+1] = Tyyppi.HIEKKA;
                        }
                        
                        if(y + 1 < this.korkeus && x + 1 < this.leveys && this.sisalto(x+1, y+1) == Tyyppi.TYHJA){
                            uusiTaulukko[x][y] = Tyyppi.TYHJA;
                            uusiTaulukko[x+1][y+1] = Tyyppi.HIEKKA;
                        }
                    }
                    if (y + 1 < this.korkeus && this.sisalto(x, y + 1) == Tyyppi.TYHJA) {
                        uusiTaulukko[x][y] = Tyyppi.TYHJA;
                        uusiTaulukko[x][y + 1] = Tyyppi.HIEKKA;
                
                    }
                }

            }
        }
        this.tyypit = uusiTaulukko.clone();
        
        }
}
