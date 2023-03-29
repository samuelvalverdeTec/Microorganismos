package game;

public class Microorganismo implements Constants, Mapeable {
	
	public int numMicro;

	private boolean isAlive = true;
	
	private int energia;
	private int vision;
	private int velocidad;
	private int edad;
	
	private int posX;
	private int posY;
	
	public void setPosicion(int pPosX, int pPosY) {
		this.posX = pPosX;
		this.posY = pPosY;
	}
	public void setEnergia(int pEnergia) {
		this.energia = pEnergia;
	}
	public void setVision(int pVision) {
		this.vision = pVision;
	}
	public void setVelocidad(int pVelocidad) {
		this.velocidad = pVelocidad;
	}
	public void setEdad(int pEdad) {
		this.edad = pEdad;
	}
	public int getEnergia() {
		return this.energia;
	}
	public int getVision() {
		return this.vision;
	}
	public int getVelocidad() {
		return this.velocidad;
	}
	public int getEdad() {
		return this.edad;
	}
	public void disminuirEnergia(int pEnergia) {
		int newEnergia = this.energia - pEnergia;
		setEnergia(newEnergia);
	}
	public void aumentarEnergia(int pEnergia) {
		int newEnergia = this.energia + pEnergia;
		setEnergia(newEnergia);
	}
	public void disminuirVision(int pVision) {
		int newVision = this.vision - pVision;
		setVision(newVision);
	}
	public void aumentarVision(int pVision) {
		int newVision = this.vision + pVision;
		setVision(newVision);
	}
	public void disminuirVelocidad(int pVelocidad) {
		int newVelocidad = this.velocidad - pVelocidad;
		setVelocidad(newVelocidad);
	}
	public void aumentarVelocidad(int pVelocidad) {
		int newVelocidad = this.velocidad + pVelocidad;
		setVelocidad(newVelocidad);
	}
	public void disminuirEdad(int pEdad) {
		int newEdad = this.edad - pEdad;
		setEdad(newEdad);
	}
	public void aumentarEdad(int pEdad) {
		int newEdad = this.edad + pEdad;
		setEdad(newEdad);
	}
	public boolean getIsAlive() {
		return this.isAlive;
	}
	public void setIsAlive(boolean pIsAlive) {
		this.isAlive = pIsAlive;
	}
	
	
	public void comer(Alimento alimento) {		
		alimento.consumido(this);
		alimento.setIsConsumido(true);
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
	
	
	public void move(Mapeable[][] mapa, ORIENTATION direccion/*, int cantidadCasillas*/) {
		int posXVieja = this.getX();
		int posYVieja = this.getY();
		int posXNueva = this.getX();
		int posYNueva = this.getY();
		
		if(direccion == ORIENTATION.NORTH) {
			//this.posY = this.posY - this.velocidad;
			for(posYNueva = posYVieja-1; posYNueva >= posYVieja-this.getVelocidad(); posYNueva--) {
				this.disminuirEnergia(DISMINUCION_ENERGIA_POR_CASILLA);
				if(mapa[posXNueva][posYNueva] != null) {
					this.revisarPosicion(mapa[posXNueva][posYNueva]);
					break;
				}
			}
			if(posYNueva < posYVieja-this.getVelocidad()) {
				posYNueva = posYNueva +1;
			}
		}
		else if(direccion == ORIENTATION.SOUTH) {
			//this.posY = this.posY + this.velocidad;
			for(posYNueva = posYVieja+1; posYNueva <= posYVieja+this.getVelocidad(); posYNueva++) {
				this.disminuirEnergia(DISMINUCION_ENERGIA_POR_CASILLA);
				if(mapa[posXNueva][posYNueva] != null) {
					this.revisarPosicion(mapa[posXNueva][posYNueva]);
					break;
				}
			}
			if(posYNueva > posYVieja+this.getVelocidad()) {
				posYNueva = posYNueva -1;
			}
		}
		else if(direccion == ORIENTATION.EAST) {
			//this.posX = this.posX + this.velocidad;
			for(posXNueva = posXVieja+1; posXNueva <= posXVieja+this.getVelocidad(); posXNueva++) {
				this.disminuirEnergia(DISMINUCION_ENERGIA_POR_CASILLA);
				if(mapa[posXNueva][posYNueva] != null) {
					this.revisarPosicion(mapa[posXNueva][posYNueva]);
					break;
				}
			}
			if(posXNueva > posXVieja+this.getVelocidad()) {
				posXNueva = posXNueva -1;
			}
		}
		else if(direccion == ORIENTATION.WEST){
			//this.posX = this.posX - this.velocidad;
			for(posXNueva = posXVieja-1; posXNueva >= posXVieja-this.getVelocidad(); posXNueva--) {
				this.disminuirEnergia(DISMINUCION_ENERGIA_POR_CASILLA);
				if(mapa[posXNueva][posYNueva] != null) {
					this.revisarPosicion(mapa[posXNueva][posYNueva]);
					break;
				}
			}
			if(posXNueva < posXVieja-this.getVelocidad()) {
				posXNueva = posXNueva +1;
			}
		}
		
		this.setPosicion(posXNueva, posYNueva);
		//int posXNueva = this.posX;
		//int posYNueva = this.posY;
		mapa[posXVieja][posYVieja] = null;
		mapa[posXNueva][posYNueva] = this;
		
	}
	
}
