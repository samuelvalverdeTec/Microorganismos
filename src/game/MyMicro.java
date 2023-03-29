package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class MyMicro extends Microorganismo {

	public MyMicro() {
		
		this.setEdad(EDAD_JUGADOR);
		this.setEnergia(ENERGIA_JUGADOR);
		this.setVision(VISION_JUGADOR);
		this.setVelocidad(VELOCIDAD_JUGADOR);
		
		this.setPosicion(0,0);
		
	}
	
}
