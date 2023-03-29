package game;

public interface Mapeable {

	//public mapa[][];
	
	public boolean esAlimento();
	
	public int getX();
	public int getY();
	public void setPosicion(int pPosX, int pPosY);
}
