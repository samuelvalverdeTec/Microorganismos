package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import game.Constants;
import game.Juego;
import game.Mapeable;

public class Interfaz implements Constants {

	JFrame frame;
	JPanel panel;
	JButton gui[][];
	
	public static String rutaImagenes = "C:\\Users\\Erick Kauffmann\\Pictures\\Erick 2023\\TEC\\I Semestre\\POO\\Repositorios\\Microorganismos\\src\\";
	//"C:\\Users\\jcval\\OneDrive - Estudiantes ITCR\\IS2023\\poo\\Microorganismos\\src\\";
	
	private Juego controlador;
	
	public Interfaz(Juego pControlador) {
		this.controlador = pControlador;
	
		this.gui = new JButton[TABLERO_SIZE_1][TABLERO_SIZE_2];
		
		this.frame = new JFrame("Microorganismos");
		if(controlador != null) {
		}
		this.frame.addKeyListener(controlador);
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
	    
	    frame.add(panel);
	    frame.pack();
	    frame.setVisible(true);
	    
	    frame.setFocusable(true);
	    frame.requestFocus();
		
	}

	public void refrescar(Mapeable[][] mapa) {
		
		for(int f = 0; f < TABLERO_SIZE_1; f++){
	        for(int c = 0; c < TABLERO_SIZE_2; c++){
	        	JButton casilla = gui[f][c];
	        	mapa[f][c].refrescar(casilla);
	        }
	    }
		
	}
	
	public void gameOver() {
		
		JLabel gameOverLbl = new JLabel("Game Over");
		gameOverLbl.setFont(new Font("Courier",Font.BOLD/Font.PLAIN/Font.ITALIC,24));
		gameOverLbl.setBackground(Color.lightGray);
	    gameOverLbl.setForeground(Color.RED);
	    panel.add(gameOverLbl);
		
	}
	
}
