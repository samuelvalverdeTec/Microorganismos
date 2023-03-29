package game;

public abstract class Alimento implements Constants, Mapeable{

	private int tamanho;
	private int atributo;
	private boolean consumido = false;
	
	private int posX;
	private int posY;

	public Alimento() {
		
		this.tamanho = definirTamanho();
		this.atributo = definirAtributo(tamanho);
		
	}
	
	public void setPosicion(int pPosX, int pPosY) {
		this.posX = pPosX;
		this.posY = pPosY;
	}
	public boolean getIsConsumido() {
		return this.consumido;
	}
	public void setIsConsumido(boolean pIsConsumido) {
		this.consumido = pIsConsumido;
	}
	public int getAtributo() {
		return this.atributo;
	}
	public int getTamano() {
		return this.tamanho;
	}
	
	public int definirTamanho() {
		return (int)(Math.random()*3+1);
		
	}
	
	public abstract int definirAtributo(int tamanho);
	
	public abstract void consumido(Microorganismo micro);
	
	public boolean esAlimento() {
		return true;
	}
	
	public abstract boolean esAlimentoEnergia();
	public abstract boolean esAlimentoVelocidad();
	public abstract boolean esAlimentoVision();
	
	public int getX() {
		return this.posX;
	}
	public int getY() {
		return this.posY;
	}
}
