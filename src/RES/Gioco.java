package RES;

import FRAMES.GiocoPanel;
import FRAMES.InformazioniPanel;
import FRAMES.IstruzioniPanel;
import com.alee.extended.label.WebLinkLabel;
import com.alee.laf.button.WebButton;
import com.alee.laf.button.WebToggleButton;
import com.alee.laf.label.WebLabel;
import com.alee.laf.panel.WebPanel;
import com.alee.laf.progressbar.WebProgressBar;
import com.alee.laf.splitpane.WebSplitPane;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by alexpopa95 on 24/03/15.
 */
public class Gioco extends JFrame {
    
    public static final String NOME = "Puzzle Palazzo Schiffanoia";
    public static int LARGHEZZA = 1272;
    public static int ALTEZZA = 900;
    
    public final double posizione_divisore = .2d;
    
    private enum Stato {
        GIOCO,
        PAUSA,
        STOP,
        PERSO,
        VINTO
    };
    
    private Stato stato;
    
    public static Gioco frame;
    
    //Componenti finestra
    public WebSplitPane interfaccia;
    public WebLabel label_tempo;
    public WebProgressBar tempo;
    public WebButton inizia;
    public WebButton istruzioni;
    public WebToggleButton pausa;
    public WebLabel label_esci;
    public WebPanel sinistra_panel;
    
    public GiocoPanel gioco_panel_up;
    public WebPanel gioco_panel;
    public WebPanel istruzioni_panel;
    public WebPanel informazioni_panel;
    
    public Gioco() {
        initPanes();
        initComp();
        initList();
        stato = Stato.STOP;
    }
    
    private void initPanes() {
        gioco_panel_up = new GiocoPanel();
        gioco_panel = gioco_panel_up.getPanel();
        istruzioni_panel = new IstruzioniPanel();
        informazioni_panel = new InformazioniPanel();
    }
    
    private void initComp() {
        interfaccia = new WebSplitPane();
        label_tempo = new WebLabel("Tempo rimasto:");
        tempo = new WebProgressBar();
        inizia = new WebButton("Inizia/Finisci");
        istruzioni = new WebButton("Istruzioni");
        pausa = new WebToggleButton("Pausa/Riprendi");
        label_esci = new WebLinkLabel("ESCI");
        label_esci.setHorizontalAlignment(WebLabel.CENTER);
        sinistra_panel = new WebPanel();

        SpringLayout layout = new SpringLayout();
        sinistra_panel.setLayout(layout);

        sinistra_panel.add(tempo);
        sinistra_panel.add(label_tempo);
        sinistra_panel.add(inizia);
        sinistra_panel.add(istruzioni);
        sinistra_panel.add(pausa);
        sinistra_panel.add(label_esci);

        int margine = 5;
        layout.putConstraint(SpringLayout.WEST, label_tempo, margine, SpringLayout.WEST, sinistra_panel);
        layout.putConstraint(SpringLayout.NORTH, label_tempo, margine, SpringLayout.NORTH, sinistra_panel);
        layout.putConstraint(SpringLayout.EAST, label_tempo, -margine, SpringLayout.EAST, sinistra_panel);

        layout.putConstraint(SpringLayout.WEST, tempo, margine, SpringLayout.WEST, sinistra_panel);
        layout.putConstraint(SpringLayout.NORTH, tempo, margine, SpringLayout.SOUTH, label_tempo);
        layout.putConstraint(SpringLayout.EAST, tempo, -margine, SpringLayout.EAST, sinistra_panel);

        layout.putConstraint(SpringLayout.WEST, inizia, margine, SpringLayout.WEST, sinistra_panel);
        layout.putConstraint(SpringLayout.NORTH, inizia, margine, SpringLayout.SOUTH, tempo);
        layout.putConstraint(SpringLayout.EAST, inizia, -margine, SpringLayout.EAST, sinistra_panel);


        layout.putConstraint(SpringLayout.WEST, istruzioni, margine, SpringLayout.WEST, sinistra_panel);
        layout.putConstraint(SpringLayout.NORTH, istruzioni, margine, SpringLayout.SOUTH, inizia);
        layout.putConstraint(SpringLayout.EAST, istruzioni, -margine, SpringLayout.EAST, sinistra_panel);

        layout.putConstraint(SpringLayout.WEST, pausa, margine, SpringLayout.WEST, sinistra_panel);
        layout.putConstraint(SpringLayout.NORTH, pausa, margine+5, SpringLayout.SOUTH, istruzioni);
        layout.putConstraint(SpringLayout.EAST, pausa, -margine, SpringLayout.EAST, sinistra_panel);

        layout.putConstraint(SpringLayout.WEST, label_esci, margine, SpringLayout.WEST, sinistra_panel);
        layout.putConstraint(SpringLayout.EAST, label_esci, -margine, SpringLayout.EAST, sinistra_panel);
        layout.putConstraint(SpringLayout.SOUTH, label_esci, -margine, SpringLayout.SOUTH, sinistra_panel);
        
        interfaccia.setLeftComponent(sinistra_panel);
        interfaccia.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
        interfaccia.setDividerLocation(1d);
        
        setContentPane(interfaccia);
        
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        if (gd.isFullScreenSupported()) {
            LARGHEZZA = gd.getDisplayMode().getWidth();
            ALTEZZA = gd.getDisplayMode().getHeight();
            setUndecorated(true);
            gd.setFullScreenWindow(this);
        } else {
            System.err.println("Full screen not supported");
            setPreferredSize(new Dimension(LARGHEZZA, ALTEZZA));
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            pack();
            setVisible(true);
        }
        
        interfaccia.setDividerLocation(posizione_divisore);
        interfaccia.setContinuousLayout(true);
        interfaccia.setEnabled(true);
    }
    
    private void initList() {
        inizia.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                switch (stato) {
                    case GIOCO:
                        //Metti in stop
                        break;
                    case STOP:
                        setPannelloDestro(gioco_panel);

                        break;
                    case PAUSA:
                        //Niente
                        break;
                    case VINTO:
                        //Inizia partita
                        break;
                    case PERSO:
                        //Inizia partita
                }
            }
        });
        
        istruzioni.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                switch (stato) {
                    case GIOCO:
                        //Metti in stop
                        break;
                    case STOP:
                        //Inizia nuova partita
                        break;
                    case PAUSA:
                        //Niente
                        break;
                    case VINTO:
                        //Inizia partita
                        break;
                    case PERSO:
                        //Inizia partita
                }
            }
        });
        
        pausa.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                switch (stato) {
                    case GIOCO:
                        //Metti in stop
                        break;
                    case STOP:
                        //Inizia nuova partita
                        break;
                    case PAUSA:
                        //Niente
                        break;
                    case VINTO:
                        //Inizia partita
                        break;
                    case PERSO:
                        //Inizia partita
                }
            }
        });
        
        label_esci.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
            }
        });
    }
    
    public void setPannelloDestro(WebPanel panel) {
        interfaccia.setRightComponent(panel);
        revalidate();
    }
    
    public void revalidate() {
        interfaccia.setDividerLocation(posizione_divisore);
        interfaccia.setContinuousLayout(true);
        interfaccia.setEnabled(false);
    }
    
    public static void main(String args[]) {
        frame = new Gioco();
    }
}
