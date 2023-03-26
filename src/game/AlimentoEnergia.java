package game;

public class AlimentoEnergia extends Alimento {

	public AlimentoEnergia() {
		
		super();
		
	}
	
	@Override
	public int definirAtributo(int size) {
		
		if(size==1) {
			return (MAX_ENERGIA/6);
		} else if(size==2) {
			return (MAX_ENERGIA/3);
		} else {
			return (MAX_ENERGIA/2);
		}
		
	}
	
	public void consumido(Microorganismo micro) {
		
		micro.energia += atributo;
		
	}
	
}
