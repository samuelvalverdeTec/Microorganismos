package gui;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import game.Constants;

public class Interfaz implements Constants {

	public Interfaz() {
	
		int mapa[][] = new int[TABLERO_SIZE_1][TABLERO_SIZE_2];
		
		JFrame f = new JFrame("Microorganismos");
	    JPanel p = new JPanel();
	    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
	    p.setLayout(new GridLayout(TABLERO_SIZE_1, TABLERO_SIZE_2));

	    for(int r = 0; r < TABLERO_SIZE_1; r++){
	        for(int c = 0; c < TABLERO_SIZE_2; c++){
	            ChangingButton button= new ChangingButton(r, c, mapa);
	            p.add(button);
	        }
	    }
	    f.add(p);
	    f.pack();
	    f.setVisible(true);
		
	}

}
