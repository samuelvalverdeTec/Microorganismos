package game;

public class MyMicro extends Microorganismo {

	public MyMicro() {
		
		this.edad = EDAD_JUGADOR;
		this.energia = ENERGIA_JUGADOR;
		this.vision = VISION_JUGADOR;
		this.velocidad = VELOCIDAD_JUGADOR;
		
		this.posX = 0;
		this.posY = 0;
		
	}
	
	public void mover() {
		
	}
	
	public void comer(Alimento alimento) {
		
		this.energia += alimento.energiaAl;
		
	}
	
	
}
