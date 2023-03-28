package game;

public class Microorganismo implements Constants, Mapeable {
	
	public int numMicro;

	public boolean isAlive = true;
	
	public int energia;
	public int vision;
	public int velocidad;
	public int edad;
	
	public int posX;
	public int posY;
	
	public void setPosicion(int pPosX, int pPosY) {
		this.posX = pPosX;
		this.posY = pPosY;
	}
	
	public void comer(Alimento alimento) {
		
		alimento.consumido(this);
		alimento.consumido = true;
	}
	
	public void comer(Microorganismo enemigo) {
		
		this.energia += (enemigo.energia)/2;
		this.vision += (enemigo.vision)/2;
		this.velocidad += (enemigo.velocidad)/2;
		enemigo.isAlive = false;
		
	}
	

	public void revisarPosicion(Mapeable objeto) {
		if(objeto.esAlimento()) {
			this.comer((Alimento)objeto);
		}
		else {
			this.pelear((Microorganismo)objeto);
		}
	}
	
	
	public void pelear(Microorganismo enemigo) {
		
		compararEnergia(enemigo);
		
	}
	
	
	public boolean compararMicros(Microorganismo enemigo) {
		boolean atacable = false;
		if(this.energia > enemigo.energia) {
			atacable = true;
		}
		return atacable;
	}
	
	public boolean agotado() {
		
		if(this.energia<=0) {
			return true;
		}
		return false;
		
	}

	public void compararEnergia(Microorganismo enemigo) {
	
		if(energia > enemigo.energia) {
			this.comer(enemigo);
		}
		else if(energia < enemigo.energia) {
			enemigo.comer(this);
		} 
		else {
			this.compararVelocidad(enemigo);
		}
	}
	
	public void compararVelocidad(Microorganismo enemigo) {
		
		if(velocidad > enemigo.velocidad) {
			this.comer(enemigo);
		}
		else if(velocidad < enemigo.velocidad) {
			enemigo.comer(this);
		} 
		else {
			this.compararEdad(enemigo);
		}
		
	}
	
	public void compararEdad(Microorganismo enemigo) {
		
		if(edad > enemigo.edad) {
			this.comer(enemigo);
		}
		else if(edad < enemigo.edad) {
			enemigo.comer(this);
		} 
		else {
			// Random entre 1 y 2 para decidir el ganador
			int tmp = (int) ( Math.random() * 2 + 1);
			if(tmp == 1) {
				this.comer(enemigo);
			} 
			else {
				enemigo.comer(this);
			}
		}
		
	}
	
	public boolean esAlimento() {
		return false;
	}
	
	public int getX() {
		return this.posX;
	}
	public int getY() {
		return this.posY;
	}
	
	public ORIENTATION getDirAlcanzable(Mapeable objeto) {
		ORIENTATION dirMover = null;
		if((objeto.getX() < this.posX)&&(objeto.getY() == this.posY)) {
			dirMover = ORIENTATION.WEST;
		}
		else if((objeto.getX() > this.posX)&&(objeto.getY() == this.posY)) {
			dirMover = ORIENTATION.EAST;
		}
		else if((objeto.getX() == this.posX)&&(objeto.getY() < this.posY)) {
			dirMover = ORIENTATION.NORTH;
		}
		else if((objeto.getX() == this.posX)&&(objeto.getY() > this.posY)) {
			dirMover = ORIENTATION.SOUTH;
		}
		return dirMover;
	}
	
}
