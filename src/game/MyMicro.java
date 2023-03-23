package game;

public class MyMicro extends Microorganismo {

	public MyMicro() {
		
	}
	
	public void mover() {
		
	}
	
	public void comer(Alimento alimento) {
		
		this.energia += alimento.energiaAl;
		
	}
	
	public Alimento buscarAlimento() {
		
		return null;
		
	}
	
}
