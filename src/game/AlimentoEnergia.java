package game;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class AlimentoEnergia extends Alimento {

	public AlimentoEnergia() {
		
		super();
		
	}
	
	@Override
	public int definirAtributo(int size) {
		
		if(size==1) {
			return (MAX_ENERGIA/100);
		} else if(size==2) {
			return (MAX_ENERGIA/50);
		} else {
			return (MAX_ENERGIA/25);
		}
		
	}
	
	public void consumido(Microorganismo micro) {
		//micro.setEnergia(micro.getEnergia() + this.atributo);	
		micro.aumentarEnergia(this.getAtributo());
		micro.contAumentoEnergia = micro.contAumentoEnergia + this.getAtributo();
		this.setIsConsumido(true);
	}
	
	
	public boolean esAlimentoEnergia() {
		return true;
	}
	public boolean esAlimentoVelocidad() {
		return false;
	}
	public boolean esAlimentoVision() {
		return false;
	}

	@Override
	public void setImg() {
		
		this.ruta = "AlimentoEnergia.png";
		
		try {
			
			this.img = ImageIO.read(new File(rutaImagenes + this.ruta));

		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
