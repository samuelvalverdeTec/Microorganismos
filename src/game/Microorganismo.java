package game;

public class Microorganismo implements Constants, Mapeable {

	public boolean isAlive = true;
	
	public int energia;
	public int vision;
	public int velocidad;
	public int edad;
	
	public int posX;
	public int posY;
	
	
	// Clase Padre de Microorganismos
	
	public Microorganismo() {
		
		int pEnergia = (int)(Math.random()*MAX_ENERGIA+MIN_ENERGIA);
		int pVision = (int)(Math.random()*MAX_VISION+MIN_VISION);
		int pVelocidad = (int)(Math.random()*MAX_VELOCIDAD+MIN_VELOCIDAD);
		int pEdad = (int)(Math.random()*MAX_EDAD+MIN_EDAD);
		
		int pPosX = (int)(Math.random()*TABLERO_SIZE_1+1);
		int pPosY = (int)(Math.random()*TABLERO_SIZE_2+1);
		
		this.energia = pEnergia;
		this.vision = pVision;
		this.velocidad = pVelocidad;
		this.edad = pEdad;
		
		this.posX = pPosX;
		this.posY = pPosY;
		
	}
	
}
