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
	
	public void comerAlimento(Alimento alimento) {
		
		alimento.consumido(this);
		alimento.consumido = true;
	}
	
	public void comerMicro(Microorganismo enemigo) {
		
		this.energia += (enemigo.energia)/2;
		this.vision += (enemigo.vision)/2;
		this.velocidad += (enemigo.velocidad)/2;
		enemigo.isAlive = false;
		
	}
	
	public void pelear(Microorganismo enemigo) {
		
		if(energia > enemigo.getEnergia()) {
			this.comerMicro(enemigo);
		}
		else if(energia < enemigo.getEnergia()) {
			enemigo.comerMicro(this);
		} 
		else {
			
			
			
		}
		
	}
	
	public boolean agotado() {
		
		if(this.energia<=0) {
			return true;
		}
		return false;
		
	}

	public void compararEnergia(Microorganismo enemigo) {
	
		if(energia > enemigo.getEnergia()) {
			this.comerMicro(enemigo);
		}
		else if(energia < enemigo.getEnergia()) {
			enemigo.comerMicro(this);
		} 
		else {
			this.compararVelocidad(enemigo);
		}
	}
	
	public void compararVelocidad(Microorganismo enemigo) {
		
		if(velocidad > enemigo.getVelocidad()) {
			this.comerMicro(enemigo);
		}
		else if(velocidad < enemigo.getVelocidad()) {
			enemigo.comerMicro(this);
		} 
		else {
			this.compararEdad(enemigo);
		}
		
	}
	
	public void compararEdad(Microorganismo enemigo) {
		
		if(edad > enemigo.getEdad()) {
			this.comerMicro(enemigo);
		}
		else if(edad < enemigo.getEdad()) {
			enemigo.comerMicro(this);
		} 
		else {
			// Random entre 1 y 2 para decidir el ganador
			int tmp = (int) ( Math.random() * 2 + 1);
			if(tmp == 1) {
				this.comerMicro(enemigo);
			} 
			else {
				enemigo.comerMicro(this);
			}
		}
		
	}
	
	public int getEnergia() {
		return energia;
	}

	public void setEnergia(int energia) {
		this.energia = energia;
	}

	public int getVision() {
		return vision;
	}

	public void setVision(int vision) {
		this.vision = vision;
	}

	public int getVelocidad() {
		return velocidad;
	}

	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}
	
}
