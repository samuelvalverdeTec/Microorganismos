package game;

public class Microorganismo implements Constants, Mapeable {
	
	public int numMicro;

	public boolean isAlive = true;
	
	public int energia;
	public int vision;
	public int velocidad;
	public int edad;
	
	public int posX;
	public int posY;
	
	
	// Clase Padre de Microorganismos
	
	public Microorganismo() {
		
	}
	
	public void setPosicion(int pPosX, int pPosY) {
		this.posX = pPosX;
		this.posY = pPosY;
	}
	
}
