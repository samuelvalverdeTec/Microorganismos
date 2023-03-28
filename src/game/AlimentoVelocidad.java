package game;

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
		
		if(micro.velocidad != MAX_VELOCIDAD)
			micro.velocidad += atributo;
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
	
}
