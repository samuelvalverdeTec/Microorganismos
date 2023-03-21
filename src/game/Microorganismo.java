package game;

public class Microorganismo implements Constants{

	public boolean vivo = true;
	public int energia;
	public int vision;
	public int velocidad;
	public int edad;
	
	public int posX;
	public int posY;
	
	
	// Clase Padre de Microorganismos
	
	public Microorganismo() {
		
		this.energia = MIN_ENERGIA;
		this.vision = MIN_VISION;
		this.velocidad = MIN_VELOCIDAD;
		this.edad = MIN_EDAD;
		
	}
	
	public Microorganismo(int pEnergia, int pVision, int pVelocidad, int pEdad) {
		
		this.energia = pEnergia;
		this.vision = pVision;
		this.velocidad = pVelocidad;
		this.edad = pEdad;
		
	}
	
}
