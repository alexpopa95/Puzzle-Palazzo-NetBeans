package DRAW;

import com.alee.laf.panel.WebPanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by alexpopa95 on 25/03/15.
 */
public class Immagini {

    public static int numeroCarte;

    public static BufferedImage sfondo;
    public static BufferedImage carteTot;
    public static ArrayList<BufferedImage> carte;

    public static void carica() {
        sfondo = Immagine.carica("/IMMAGINI/sfondo.png");
    }

    public static void caricaImmaginiCarte(int num) {
        carte = new ArrayList<BufferedImage>();
        carteTot = Immagine.carica("/IMMAGINI/card/"+num+".jpg");
        SpriteSheet sp = new SpriteSheet(carteTot);
        numeroCarte = 0;
        System.out.println("Carico le immagini delle carte...");
        for(int i=1; i<=4; i++) {
            for(int j=1;j<=4;j++) {
                carte.add(sp.sottraiCarta(i, j));
                numeroCarte++;
            }
        }
    }
}
