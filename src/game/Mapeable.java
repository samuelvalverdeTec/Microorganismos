package game;

import javax.swing.JButton;

public interface Mapeable {

	//public mapa[][];
	
	public boolean esAlimento();
	
	public static String rutaImagenes = "C:\\Users\\Erick Kauffmann\\Pictures\\Erick 2023\\TEC\\I Semestre\\POO\\Repositorios\\Microorganismos\\src\\";
	//"C:\\Users\\jcval\\OneDrive - Estudiantes ITCR\\IS2023\\poo\\Microorganismos\\src\\";
	
	public int getX();
	public int getY();
	public void setPosicion(int pPosX, int pPosY);
	
	public abstract void refrescar(JButton casilla);
	
}
