package game;

public class MicroNPC extends Microorganismo {

	int direccion, casillas;
	
	public MicroNPC() {
		
		int pEnergia = (int)(Math.random()*MAX_ENERGIA+MIN_ENERGIA);
		int pVision = (int)(Math.random()*MAX_VISION+MIN_VISION);
		int pVelocidad = (int)(Math.random()*MAX_VELOCIDAD+MIN_VELOCIDAD);
		int pEdad = (int)(Math.random()*MAX_EDAD+MIN_EDAD);
		
		
		this.energia = pEnergia;
		this.vision = pVision;
		this.velocidad = pVelocidad;
		this.edad = pEdad;
		
		this.posX = 0;
		this.posY = 0;
		
	}
	
	public void move(Mapeable[][] mapa ,int direccion, int cantidadCasillas) {
		
		
		
	}
	
}
