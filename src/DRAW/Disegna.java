/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DRAW;

import FRAMES.GiocoPanel;
import static FRAMES.GiocoPanel.carte;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

/**
 *
 * @author Alexandru
 */
public class Disegna extends Canvas {
    
    GiocoPanel panel;
    
    public Disegna(GiocoPanel panel) {
        this.panel = panel;
    }
}
