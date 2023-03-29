package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class MyMicro extends Microorganismo {
	
	private ORIENTATION dirMover;

	public MyMicro() {
		
		this.setEdad(EDAD_JUGADOR);
		this.setEnergia(ENERGIA_JUGADOR);
		this.setVision(VISION_JUGADOR);
		this.setVelocidad(VELOCIDAD_JUGADOR);
		
		this.setPosicion(0,0);
		
	}
	
	public ORIENTATION getOrientation() {
		return this.dirMover;
	}
	public void setOrientation(ORIENTATION pOrientation) {
		this.dirMover = pOrientation;
	}
	
}
