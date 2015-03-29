package FRAMES;

import DRAW.Disegna;
import DRAW.Immagini;
import GAMEOBJECTS.Carta;
import RES.Gioco;
import com.alee.laf.panel.WebPanel;

import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

/**
 * Created by alexpopa95 on 24/03/15.
 */
public class GiocoPanel extends Canvas implements  Runnable, ComponentListener {
    
    public WebPanel panel; 
    
    //Carta cliccata
    public static Carta carta = null;
    public static int x = 0;
    public static int y = 0;

    private boolean running;
    private boolean caricato = false;

    public int altezza;
    public int larghezza;

    public static ArrayList<Carta> carte;

    public GiocoPanel() {
        addListeners();
        Thread t = new Thread(this);
        t.start();
    }
    
    public WebPanel getPanel() {
        WebPanel panel = new WebPanel();
        panel.add(this);
        return panel;
    }
    
    public void disegna() {
        BufferStrategy buffer = getBufferStrategy();
        if(buffer == null) {
            createBufferStrategy(2);
            return;
        }
        
        Graphics g = buffer.getDrawGraphics();
        
        larghezza = getWidth();
        altezza = getHeight();
        g.drawImage(Immagini.sfondo, 0, 0, larghezza, altezza,  this);
        for(int i=0;i<carte.size();i++) {
            carte.get(i).disegna(g);
        }
    }
    
    public void addListeners() {
        this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                if(carta != null) {
                    carta.setX(e.getX());
                    carta.setY(e.getY());
                }
            }
        });
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                System.out.println("Cliccato");
                int xx = e.getX();
                int yy = e.getY();
                for(int i=0; i<carte.size(); i++) {
                    int x = carte.get(i).getX();
                    int y = carte.get(i).getY();
                    if(xx>x && xx<x+carte.get(i).getLarghezza()) {
                        if(yy>y && yy<y+carte.get(i).getAltezza()) {
                            carte.get(i).setMuovibile(true);
                            System.out.println("Carta trovata");
                        }
                    }
                }
            }
        });
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                //carta = null;
                System.out.println("Rilasciato");
            }
        });
    }

    private void caricaCarte() {
        carte = new ArrayList<Carta>();
        for(int i=0;i<Immagini.numeroCarte;i++) {
            Carta c = new Carta(Immagini.carte.get(i), 0, 0, 132, 80);
            carte.add(c);
        }
    }
    
    @Override
    public void run() {
        running = true;
        while(running) {
            if(Gioco.frame != null && !caricato) {
                Immagini.carica();
                Immagini.caricaImmaginiCarte(1);
                caricaCarte();
            }
            
            //LOOP
            System.out.println("\t \t Aggiorno Canvas");
            disegna();
            //------------
        }
    }

    public void ordinaComponenti() {
        List posizioni = new List();
        for(int i=0;i<Immagini.numeroCarte;i++) {
            if(i<4) //sul lato sinistro
            {
                carte.get(i).setX(10);
                if(i==0) {
                    carte.get(i).setY(30);
                }
                else {
                    carte.get(i).setY(altezza/4+carte.get(i-1).getY()-10);
                }
            }
            else if(i<12) //Sul lato basso
            {
                carte.get(i).setY(altezza - carte.get(i).getAltezza() - 10);
                carte.get(i).setX(larghezza / 10 + carte.get(i - 1).getX());
            } else //sul lato destro
            {
                carte.get(i).setX(larghezza-carte.get(i).getLarghezza()-10);
                if(i==12) {
                    carte.get(i).setY(30);
                }
                else {
                    carte.get(i).setY(altezza/4+carte.get(i-1).getY()-10);
                }
            }
        }
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public int getAltezza() {
        return altezza;
    }

    public void setAltezza(int altezza) {
        this.altezza = altezza;
    }

    public int getLarghezza() {
        return larghezza;
    }

    public void setLarghezza(int larghezza) {
        this.larghezza = larghezza;
    }

    @Override
    public void componentResized(ComponentEvent e) {
        //ordinaComponenti();
    }

    @Override
    public void componentMoved(ComponentEvent e) {

    }

    @Override
    public void componentShown(ComponentEvent e) {

    }

    @Override
    public void componentHidden(ComponentEvent e) {

    }
}
