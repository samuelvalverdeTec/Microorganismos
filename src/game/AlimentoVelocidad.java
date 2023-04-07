package game;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class AlimentoVelocidad extends Alimento {

	public AlimentoVelocidad() {
		
		super();
		
	}
	
	@Override
	public int definirAtributo(int size) {
		
		if(size==1) {
			return MIN_VELOCIDAD;
		} else if(size==2) {
			return (MAX_VELOCIDAD-MIN_VELOCIDAD);
		} else {
			return MAX_VELOCIDAD;
		}
		
	}
	
	public void consumido(Microorganismo micro) {
		
		if(micro.getVelocidad() < MAX_VELOCIDAD) {
			//micro.setVelocidad(micro.getVelocidad() + this.atributo);
			micro.aumentarVelocidad(this.getAtributo());
			this.setIsConsumido(true);
		}
	}
	
	
	public boolean esAlimentoEnergia() {
		return false;
	}
	public boolean esAlimentoVelocidad() {
		return true;
	}
	public boolean esAlimentoVision() {
		return false;
	}

	@Override
	public void setImg() {
		
		this.ruta = "AlimentoVelocidad.png";
		
		try {
			
			this.img = ImageIO.read(new File(rutaImagenes + this.ruta));

		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
