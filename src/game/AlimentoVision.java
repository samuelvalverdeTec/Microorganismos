package game;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class AlimentoVision extends Alimento {

	public AlimentoVision() {
		
		super();
		
	}
	
	@Override
	public int definirAtributo(int size) {
		
		if(size==1) {
			return (int)(Math.random()*2+1);
		} else if(size==2) {
			return (int)(Math.random()*4+3);
		} else {
			return (int)(Math.random()*6+5);
		}
		
	}
	
	public void consumido(Microorganismo micro) {
		
		if(micro.getVision() < MAX_VISION) {
			//micro.setVision(micro.getVision() + this.atributo);
			micro.aumentarVision(this.getAtributo());
			this.setIsConsumido(true);
		}
	}
	
	
	public boolean esAlimentoEnergia() {
		return false;
	}
	public boolean esAlimentoVelocidad() {
		return false;
	}
	public boolean esAlimentoVision() {
		return true;
	}

	@Override
	public void setImg() {
		
		this.ruta = "AlimentoVision.png";
		
		try {
			
			this.img = ImageIO.read(new File(rutaImagenes + this.ruta));

		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
