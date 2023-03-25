package game;

public class Alimento implements Mapeable{

	public int energiaAl;
	public int velocidadAl;
	public int visionAl;
	
	public int posX;
	public int posY;
	
	
	
	public void setPosicion(int pPosX, int pPosY) {
		this.posX = pPosX;
		this.posY = pPosY;
	}
	
}
