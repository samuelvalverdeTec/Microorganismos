package game;

import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Microorganismo implements Constants, Mapeable {
	
	public int numMicro;
	
	private boolean isAlive = true;
	private ORIENTATION dirMover = null;
	
	public BufferedImage img = null;
	public String ruta;
	
	private int energia;
	private int vision;
	private int velocidad;
	private int edad;
	
	private int posX;
	private int posY;
	
	public int contAumentoEnergia = 0;
	public int contAumentoEdad = 0;
	
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
	public ORIENTATION getOrientation() {
		return this.dirMover;
	}
	public void setOrientation(ORIENTATION pOrientation) {
		this.dirMover = pOrientation;
	}
	
	public void mostrarInfoMicroorganismo() {
		System.out.println("______________________________");
		System.out.println("Microorganismo " + this.numMicro);
		System.out.println("(PosX: " + this.getX() + ", PosY: " + this.getY() + ")");
		System.out.println("Energia: " + this.getEnergia());
		System.out.println("Edad: " + this.getEdad());
		System.out.println("Vision: " + this.getVision());
		System.out.println("Velocidad: " + this.getVelocidad());
	}
	
	public void revisarAtributos() {
		if(this.contAumentoEdad >= 10) {
			this.disminuirVision(DISMINUCION_VISION_POR_10_EDAD);
			this.contAumentoEdad = 0;
		}
		if(this.contAumentoEnergia >= 30) {
			this.disminuirVelocidad(DISMINUCION_VELOCIDAD_POR_30_ENERGIA);
			this.contAumentoEnergia = 0;
		}
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
			this.setEnergia(0);
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
	
	
	public void move(Mapeable[][] mapa) {
		int posXVieja = this.getX();
		int posYVieja = this.getY();
		int posXNueva = this.getX();
		int posYNueva = this.getY();
		ORIENTATION dirMoverAct = this.getOrientation();
		int maxDirNueva;
		if(this.agotado() == false) {
			if(dirMoverAct != null) {
				if(dirMoverAct == ORIENTATION.NORTH) {
					//this.posY = this.posY - this.velocidad;
					maxDirNueva = posYVieja-this.getVelocidad();
					if(maxDirNueva <= 0) {
						maxDirNueva = 0;
					}
					if(posYVieja-1 >= 0) {
						for(posYNueva = posYVieja-1; posYNueva >= maxDirNueva; posYNueva--) {
							if(posYNueva >= 0) {
								this.disminuirEnergia(DISMINUCION_ENERGIA_POR_CASILLA);
								if(mapa[posYNueva][posXNueva] != null) {
									this.revisarPosicion(mapa[posYNueva][posXNueva]);
									break;
								}
							}
							else {
								break;
							}
						}
					}
					if(posYNueva < maxDirNueva) {
						posYNueva = posYNueva +1;
					}
				}
				else if(dirMoverAct == ORIENTATION.SOUTH) {
					//this.posY = this.posY + this.velocidad;
					maxDirNueva = posYVieja+this.getVelocidad();
					if(maxDirNueva >= TABLERO_SIZE_1 -1) {
						maxDirNueva = TABLERO_SIZE_1 -1;
					}
					if(posYVieja+1 <= TABLERO_SIZE_1 -1) {
						for(posYNueva = posYVieja+1; posYNueva <= maxDirNueva; posYNueva++) {
							if(posYNueva < TABLERO_SIZE_1) {
								this.disminuirEnergia(DISMINUCION_ENERGIA_POR_CASILLA);
								if(mapa[posYNueva][posXNueva] != null) {
									this.revisarPosicion(mapa[posYNueva][posXNueva]);
									break;
								}
							}
							else {
								break;
							}
						}
					}
					if(posYNueva > maxDirNueva) {
						posYNueva = posYNueva -1;
					}
				}
				else if(dirMoverAct == ORIENTATION.EAST) {
					//this.posX = this.posX + this.velocidad;
					maxDirNueva = posXVieja+this.getVelocidad();
					if(maxDirNueva >= TABLERO_SIZE_2 -1) {
						maxDirNueva = TABLERO_SIZE_2 -1;
					}
					if(posXVieja+1 <= TABLERO_SIZE_2 -1) {
						for(posXNueva = posXVieja+1; posXNueva <= maxDirNueva; posXNueva++) {
							if(posXNueva < TABLERO_SIZE_2) {
								this.disminuirEnergia(DISMINUCION_ENERGIA_POR_CASILLA);
								if(mapa[posYNueva][posXNueva] != null) {
									this.revisarPosicion(mapa[posYNueva][posXNueva]);
									break;
								}
							}
							else {
								break;
							}
						}
					}
					if(posXNueva > maxDirNueva) {
						posXNueva = posXNueva -1;
					}
				}
				else if(dirMoverAct == ORIENTATION.WEST){
					//this.posX = this.posX - this.velocidad;
					maxDirNueva = posXVieja-this.getVelocidad();
					if(maxDirNueva <= 0) {
						maxDirNueva = 0;
					}
					if(posXVieja-1 >= 0) {
						for(posXNueva = posXVieja-1; posXNueva >= maxDirNueva; posXNueva--) {
							if(posXNueva >= 0) {
								this.disminuirEnergia(DISMINUCION_ENERGIA_POR_CASILLA);
								if(mapa[posYNueva][posXNueva] != null) {
									this.revisarPosicion(mapa[posYNueva][posXNueva]);
									break;
								}
							}
							else {
								break;
							}
						}
					}
					if(posXNueva < maxDirNueva) {
						posXNueva = posXNueva +1;
					}
				}
				
				this.setPosicion(posXNueva, posYNueva);
				//int posXNueva = this.posX;
				//int posYNueva = this.posY;
				mapa[posYVieja][posXVieja] = null;
				mapa[posYNueva][posXNueva] = this;
			}
		}
	}
	
	@Override
	public void refrescar(JButton casilla) {
		
		casilla.setIcon(new ImageIcon(this.img));
		
	}

}
