package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class MyMicro extends Microorganismo {

	
	public MyMicro() {
		
		this.setEdad(EDAD_JUGADOR);
		this.setEnergia(ENERGIA_JUGADOR);
		this.setVision(VISION_JUGADOR);
		this.setVelocidad(VELOCIDAD_JUGADOR);
		this.ruta = "MyMicro.png";
		this.setImg(ruta);
		
		this.setPosicion(0,0);
		
	}
	
	public void setImg(String ruta) {
		
		try {
			
			this.img = ImageIO.read(new File(rutaImagenes + ruta));

		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
