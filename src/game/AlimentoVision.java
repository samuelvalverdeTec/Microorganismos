package game;

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
		
		if(micro.vision != MAX_VISION)
			micro.vision += atributo;
		
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
	
}
