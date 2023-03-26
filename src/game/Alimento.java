package game;

public abstract class Alimento implements Constants, Mapeable{

	public int tamanho;
	public int atributo;
	public boolean consumido = false;
	
	public int posX;
	public int posY;

	public Alimento() {
		
		this.tamanho = definirTamanho();
		this.atributo = definirAtributo(tamanho);
		
	}
	
	public void setPosicion(int pPosX, int pPosY) {
		this.posX = pPosX;
		this.posY = pPosY;
		
	}
	
	public int definirTamanho() {
		return (int)(Math.random()*3+1);
		
	}
	
	public abstract int definirAtributo(int tamanho);
	
	public abstract void consumido(Microorganismo micro);
	
}
