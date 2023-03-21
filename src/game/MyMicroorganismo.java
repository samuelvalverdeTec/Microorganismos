package game;

public class MyMicroorganismo extends Microorganismo {

	public void mover() {
		
	}
	
	public void comer(Alimento alimento) {
		
		this.energia += alimento.energiaAl;
		
	}
	
	public Alimento buscarAlimento() {
		
		return null;
		
	}
	
}
