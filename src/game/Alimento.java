package game;

import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public abstract class Alimento implements Constants, Mapeable{

	private int tamanho;
	private int atributo;
	private boolean consumido = false;
	
	public BufferedImage img = null;
	public String ruta;
	
	private int posX;
	private int posY;

	public Alimento() {
		
		this.tamanho = definirTamanho();
		this.atributo = definirAtributo(tamanho);
		this.setImg();
		
	}
	
	public abstract void setImg();
	
	public void refrescar(JButton casilla) {
		
		casilla.setIcon(new ImageIcon(this.img));
		
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
