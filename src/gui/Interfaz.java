package gui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import game.Constants;
import game.Mapeable;

public class Interfaz implements Constants {

	JFrame frame;
	JPanel panel;
	JButton gui[][];
	
	public static String rutaImagenes = "C:\\Users\\jcval\\OneDrive - Estudiantes ITCR\\IS2023\\poo\\Microorganismos\\src\\";
	public String rutaA = "alimento.png";
	public String rutaM = "micro.png";
	public BufferedImage imgA = null;
	public BufferedImage imgM = null;
	
	public Interfaz() {
	
		this.gui = new JButton [TABLERO_SIZE_1][TABLERO_SIZE_2];
		
		this.frame = new JFrame("Microorganismos");
	    this.panel = new JPanel();
	    
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
	    panel.setLayout(new GridLayout(TABLERO_SIZE_1, TABLERO_SIZE_2));

	    for(int f = 0; f < TABLERO_SIZE_1; f++){
	        for(int c = 0; c < TABLERO_SIZE_2; c++){
	            JButton casilla = new JButton();
	            gui[f][c] = casilla;
	            panel.add(casilla);
	        }
	    }
	    
		try {
			
			this.imgA = ImageIO.read(new File(rutaImagenes + rutaA));
			this.imgM = ImageIO.read(new File(rutaImagenes + rutaM));

		} catch (IOException e) {
			e.printStackTrace();
		}
	    
	    frame.add(panel);
	    frame.pack();
	    frame.setVisible(true);
		
	}

	public void refrescar(Mapeable[][] mapa) {
		
		for(int f = 0; f < TABLERO_SIZE_1; f++){
	        for(int c = 0; c < TABLERO_SIZE_2; c++){
	        	JButton casilla = gui[f][c];
	            if(mapa[f][c].esAlimento()) {
	            	casilla.setIcon(new ImageIcon(this.imgA));	//alimento
	            } else if(mapa[f][c].esAlimento() == false) {
	            	casilla.setIcon(new ImageIcon(this.imgM));	//micro
	            } else {
	            }
	            casilla.setBackground(Color.lightGray);
	        }
	    }
		
	}
	
}
