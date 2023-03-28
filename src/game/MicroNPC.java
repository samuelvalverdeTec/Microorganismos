package game;

import java.util.ArrayList;

public abstract class MicroNPC extends Microorganismo {

	int direccion, casillas;
	
	public MicroNPC() {
		
		int pEnergia = (int)(Math.random()*MAX_ENERGIA+MIN_ENERGIA);
		int pVision = (int)(Math.random()*MAX_VISION+MIN_VISION);
		int pVelocidad = (int)(Math.random()*MAX_VELOCIDAD+MIN_VELOCIDAD);
		int pEdad = (int)(Math.random()*MAX_EDAD+MIN_EDAD);
		
		
		this.energia = pEnergia;
		this.vision = pVision;
		this.velocidad = pVelocidad;
		this.edad = pEdad;
		
		this.posX = 0;
		this.posY = 0;
		
	}
	
	//public abstract ORIENTATION revisarEntorno(Mapeable[][] mapa);
	
	public abstract boolean esNPCVision();
	
	
	public ORIENTATION revisarEntorno(Mapeable[][] mapa) {
		
		ORIENTATION dirMover = null;
		ORIENTATION MoverDirFinal = null;
		
		boolean[] direccionesValidas = new boolean[4];
		
		for(int i = 0; i < 4; i++) {
			direccionesValidas[i] = true;
		}
		
		ArrayList<Alimento> alimentosEncontrados = new ArrayList<Alimento>();
		ArrayList<Microorganismo> microsEncontrados = new ArrayList<Microorganismo>();
			
		int PosXInicioBusqueda = this.posX - this.vision;
		int PosYInicioBusqueda = this.posY - this.vision;
		int PosXFinBusqueda = this.posX + this.vision;
		int PosYFinBusqueda = this.posY + this.vision;
			
		for(int PosXBusqueda = PosXInicioBusqueda; PosXBusqueda <= PosXFinBusqueda; PosXBusqueda++) {
			for(int PosYBusqueda = PosYInicioBusqueda; PosYBusqueda <= PosYFinBusqueda; PosYBusqueda++) {
				if(mapa[PosXBusqueda][PosYBusqueda] != this) {
					if(mapa[PosXBusqueda][PosYBusqueda] != null) {
						if(mapa[PosXBusqueda][PosYBusqueda].esAlimento()) {
							alimentosEncontrados.add((Alimento) mapa[PosXBusqueda][PosYBusqueda]);
						}
						else {
							microsEncontrados.add((Microorganismo) mapa[PosXBusqueda][PosYBusqueda]);
						}
					}
				}
			}
		}
				
		int PosXMicroEncontrado;
		int PosYMicroEncontrado;
		Microorganismo microEncontrado;
		boolean atacarMicro;
		for(int i = 0; i < microsEncontrados.size(); i++) {
			microEncontrado = microsEncontrados.get(i);
			PosXMicroEncontrado = microEncontrado.posX;
			PosYMicroEncontrado = microEncontrado.posY;
			
			atacarMicro = this.compararMicros(microEncontrado);
			
			if(!atacarMicro) {
				if((PosXMicroEncontrado < this.posX)&&(PosYMicroEncontrado == this.posY)) {
					direccionesValidas[ORIENTATION.WEST.getValue()] = false;
				}
				else if((PosXMicroEncontrado > this.posX)&&(PosYMicroEncontrado == this.posY)) {
					direccionesValidas[ORIENTATION.EAST.getValue()] = false;
				}
				else if((PosXMicroEncontrado == this.posX)&&(PosYMicroEncontrado < this.posY)) {
					direccionesValidas[ORIENTATION.NORTH.getValue()] = false;
				}
				else if((PosXMicroEncontrado == this.posX)&&(PosYMicroEncontrado > this.posY)) {
					direccionesValidas[ORIENTATION.SOUTH.getValue()] = false;
				}
			}		
		}
		
	
		int PosXAlimentoEncontrado;
		int PosYAlimentoEncontrado;
		Alimento alimentoEncontrado;
		Alimento mejorAlimentoEncontrado = null;
		for(int j = 0; j < 3; j++) {
			if(mejorAlimentoEncontrado == null) {
				for(int i = 0; i < alimentosEncontrados.size(); i++) {
					boolean alimentoAlcanzable = false;
					alimentoEncontrado = alimentosEncontrados.get(i);
					PosXAlimentoEncontrado = alimentoEncontrado.posX;
					PosYAlimentoEncontrado = alimentoEncontrado.posY;
					if(this.esNPCVision()) {
						if ( (j == 0 && alimentoEncontrado.esAlimentoEnergia()) || 
							 (j == 1 && alimentoEncontrado.esAlimentoVision()) ||
							 (j == 2 && alimentoEncontrado.esAlimentoVelocidad()) ) {
							dirMover = this.getDirAlcanzable(alimentoEncontrado);
							/*if((PosXAlimentoEncontrado < this.posX)&&(PosYAlimentoEncontrado == this.posY)) {
								alimentoAlcanzable = true;
								dirMover = ORIENTATION.WEST;
							}
							else if((PosXAlimentoEncontrado > this.posX)&&(PosYAlimentoEncontrado == this.posY)) {
								alimentoAlcanzable = true;
								dirMover = ORIENTATION.EAST;
							}
							else if((PosXAlimentoEncontrado == this.posX)&&(PosYAlimentoEncontrado < this.posY)) {
								alimentoAlcanzable = true;
								dirMover = ORIENTATION.NORTH;
							}
							else if((PosXAlimentoEncontrado == this.posX)&&(PosYAlimentoEncontrado > this.posY)) {
								alimentoAlcanzable = true;
								dirMover = ORIENTATION.SOUTH;
							}*/		
							if(/*alimentoAlcanzable*/ dirMover != null && direccionesValidas[dirMover.getValue()]) {
								if((mejorAlimentoEncontrado == null)||(alimentoEncontrado.atributo > mejorAlimentoEncontrado.atributo)) {
									mejorAlimentoEncontrado = alimentoEncontrado;
									MoverDirFinal = dirMover;
								}
							}
						}
					}
					else {
						if ( (j == 0 && alimentoEncontrado.esAlimentoEnergia()) || 
							 (j == 1 && alimentoEncontrado.esAlimentoVelocidad()) ||
							 (j == 2 && alimentoEncontrado.esAlimentoVision()) ) {
							dirMover = this.getDirAlcanzable(alimentoEncontrado);
							/*if((PosXAlimentoEncontrado < this.posX)&&(PosYAlimentoEncontrado == this.posY)) {
								alimentoAlcanzable = true;
								dirMover = ORIENTATION.WEST;
							}
							else if((PosXAlimentoEncontrado > this.posX)&&(PosYAlimentoEncontrado == this.posY)) {
								alimentoAlcanzable = true;
								dirMover = ORIENTATION.EAST;
							}
							else if((PosXAlimentoEncontrado == this.posX)&&(PosYAlimentoEncontrado < this.posY)) {
								alimentoAlcanzable = true;
								dirMover = ORIENTATION.NORTH;
							}
							else if((PosXAlimentoEncontrado == this.posX)&&(PosYAlimentoEncontrado > this.posY)) {
								alimentoAlcanzable = true;
								dirMover = ORIENTATION.SOUTH;
							}*/		
							if(/*alimentoAlcanzable*/ dirMover != null && direccionesValidas[dirMover.getValue()]) {
								if((mejorAlimentoEncontrado == null)||(alimentoEncontrado.atributo > mejorAlimentoEncontrado.atributo)) {
									mejorAlimentoEncontrado = alimentoEncontrado;
									MoverDirFinal = dirMover;
								}
							}
						}
					}
					
				}
			}
		}
		//analizar los no alcanzables en la cruz
		for(int j = 0; j < 3; j++) {
			if(mejorAlimentoEncontrado == null) {
				for(int i = 0; i < alimentosEncontrados.size(); i++) {
					boolean alimentoAlcanzable = false;
					alimentoEncontrado = alimentosEncontrados.get(i);
					PosXAlimentoEncontrado = alimentoEncontrado.posX;
					PosYAlimentoEncontrado = alimentoEncontrado.posY;
					if(this.esNPCVision()) {
						if ( (j == 0 && alimentoEncontrado.esAlimentoEnergia()) || 
							 (j == 1 && alimentoEncontrado.esAlimentoVision()) ||
							 (j == 2 && alimentoEncontrado.esAlimentoVelocidad()) ){
							dirMover = this.getDirAlcanzable(alimentoEncontrado);
							/*if((PosXAlimentoEncontrado < this.posX)&&(PosYAlimentoEncontrado == this.posY)) {
								alimentoAlcanzable = true;
							}
							else if((PosXAlimentoEncontrado > this.posX)&&(PosYAlimentoEncontrado == this.posY)) {
								alimentoAlcanzable = true;
							}
							else if((PosXAlimentoEncontrado == this.posX)&&(PosYAlimentoEncontrado < this.posY)) {
								alimentoAlcanzable = true;
							}
							else if((PosXAlimentoEncontrado == this.posX)&&(PosYAlimentoEncontrado > this.posY)) {
								alimentoAlcanzable = true;
							}*/
							if(/*!alimentoAlcanzable*/ dirMover == null) {
								if((PosXAlimentoEncontrado < this.posX)&&(PosYAlimentoEncontrado < this.posY)&&(direccionesValidas[ORIENTATION.NORTH.getValue()])) {
									dirMover = ORIENTATION.NORTH;
								}
								else if((PosXAlimentoEncontrado < this.posX)&&(PosYAlimentoEncontrado < this.posY)&&(direccionesValidas[ORIENTATION.WEST.getValue()])) {
									dirMover = ORIENTATION.WEST;
								}
								else if((PosXAlimentoEncontrado > this.posX)&&(PosYAlimentoEncontrado < this.posY)&&(direccionesValidas[ORIENTATION.NORTH.getValue()])) {
									dirMover = ORIENTATION.NORTH;
								}
								else if((PosXAlimentoEncontrado > this.posX)&&(PosYAlimentoEncontrado < this.posY)&&(direccionesValidas[ORIENTATION.EAST.getValue()])) {
									dirMover = ORIENTATION.EAST;
								}
								else if((PosXAlimentoEncontrado < this.posX)&&(PosYAlimentoEncontrado > this.posY)&&(direccionesValidas[ORIENTATION.SOUTH.getValue()])) {
									dirMover = ORIENTATION.SOUTH;
								}
								else if((PosXAlimentoEncontrado < this.posX)&&(PosYAlimentoEncontrado > this.posY)&&(direccionesValidas[ORIENTATION.WEST.getValue()])) {
									dirMover = ORIENTATION.WEST;
								}
								else if((PosXAlimentoEncontrado > this.posX)&&(PosYAlimentoEncontrado > this.posY)&&(direccionesValidas[ORIENTATION.SOUTH.getValue()])) {
									dirMover = ORIENTATION.SOUTH;
								}
								else if((PosXAlimentoEncontrado > this.posX)&&(PosYAlimentoEncontrado > this.posY)&&(direccionesValidas[ORIENTATION.EAST.getValue()])) {
									dirMover = ORIENTATION.EAST;
								}
								if((mejorAlimentoEncontrado == null)||(alimentoEncontrado.atributo > mejorAlimentoEncontrado.atributo)) {
									mejorAlimentoEncontrado = alimentoEncontrado;
									MoverDirFinal = dirMover;
								}
							}
						}
					}
					else {
						if ( (j == 0 && alimentoEncontrado.esAlimentoEnergia()) || 
							 (j == 1 && alimentoEncontrado.esAlimentoVelocidad()) ||
							 (j == 2 && alimentoEncontrado.esAlimentoVision()) ){
							dirMover = this.getDirAlcanzable(alimentoEncontrado);
							/*if((PosXAlimentoEncontrado < this.posX)&&(PosYAlimentoEncontrado == this.posY)) {
								alimentoAlcanzable = true;
							}
							else if((PosXAlimentoEncontrado > this.posX)&&(PosYAlimentoEncontrado == this.posY)) {
								alimentoAlcanzable = true;
							}
							else if((PosXAlimentoEncontrado == this.posX)&&(PosYAlimentoEncontrado < this.posY)) {
								alimentoAlcanzable = true;
							}
							else if((PosXAlimentoEncontrado == this.posX)&&(PosYAlimentoEncontrado > this.posY)) {
								alimentoAlcanzable = true;
							}*/
							if(/*!alimentoAlcanzable*/ dirMover == null) {
								if((PosXAlimentoEncontrado < this.posX)&&(PosYAlimentoEncontrado < this.posY)&&(direccionesValidas[ORIENTATION.NORTH.getValue()])) {
									dirMover = ORIENTATION.NORTH;
								}
								else if((PosXAlimentoEncontrado < this.posX)&&(PosYAlimentoEncontrado < this.posY)&&(direccionesValidas[ORIENTATION.WEST.getValue()])) {
									dirMover = ORIENTATION.WEST;
								}
								else if((PosXAlimentoEncontrado > this.posX)&&(PosYAlimentoEncontrado < this.posY)&&(direccionesValidas[ORIENTATION.NORTH.getValue()])) {
									dirMover = ORIENTATION.NORTH;
								}
								else if((PosXAlimentoEncontrado > this.posX)&&(PosYAlimentoEncontrado < this.posY)&&(direccionesValidas[ORIENTATION.EAST.getValue()])) {
									dirMover = ORIENTATION.EAST;
								}
								else if((PosXAlimentoEncontrado < this.posX)&&(PosYAlimentoEncontrado > this.posY)&&(direccionesValidas[ORIENTATION.SOUTH.getValue()])) {
									dirMover = ORIENTATION.SOUTH;
								}
								else if((PosXAlimentoEncontrado < this.posX)&&(PosYAlimentoEncontrado > this.posY)&&(direccionesValidas[ORIENTATION.WEST.getValue()])) {
									dirMover = ORIENTATION.WEST;
								}
								else if((PosXAlimentoEncontrado > this.posX)&&(PosYAlimentoEncontrado > this.posY)&&(direccionesValidas[ORIENTATION.SOUTH.getValue()])) {
									dirMover = ORIENTATION.SOUTH;
								}
								else if((PosXAlimentoEncontrado > this.posX)&&(PosYAlimentoEncontrado > this.posY)&&(direccionesValidas[ORIENTATION.EAST.getValue()])) {
									dirMover = ORIENTATION.EAST;
								}
								if((mejorAlimentoEncontrado == null)||(alimentoEncontrado.atributo > mejorAlimentoEncontrado.atributo)) {
									mejorAlimentoEncontrado = alimentoEncontrado;
									MoverDirFinal = dirMover;
								}
							}
						}
					}
					
				}
			}
		}
		
		if(mejorAlimentoEncontrado == null) {
			int contDirValidas = 0;
			for(int i = 0; i < 4; i++) {
				if(direccionesValidas[i]) {
					contDirValidas++;
				}
			}
			if(contDirValidas > 0) {
				int numDir = (int)(Math.random()*contDirValidas+0);
				for(int i = 0; i < 4; i++) {
					if(direccionesValidas[i]) {
						if(numDir == 0) {
							if(i == 0) {
								MoverDirFinal = ORIENTATION.NORTH;
							}
							else if(i == 1) {
								MoverDirFinal = ORIENTATION.SOUTH;
							}
							else if(i == 2) {
								MoverDirFinal = ORIENTATION.EAST;
							}
							else if(i == 3) {
								MoverDirFinal = ORIENTATION.WEST;
							}
							break;
						}
					}
					numDir--;
				}
			}
		}
		
		return MoverDirFinal;
		
	}
	
	
	public void move(Mapeable[][] mapa, ORIENTATION direccion/*, int cantidadCasillas*/) {
		int posXVieja = this.posX;
		int posYVieja = this.posY;
		int posXNueva = this.posX;
		int posYNueva = this.posY;
		
		if(direccion == ORIENTATION.NORTH) {
			//this.posY = this.posY - this.velocidad;
			for(posYNueva = posYVieja-1; posYNueva >= posYVieja-this.velocidad; posYNueva--) {
				if(mapa[posXNueva][posYNueva] != null) {
					this.revisarPosicion(mapa[posXNueva][posYNueva]);
					break;
				}
			}
			if(posYNueva < posYVieja-this.velocidad) {
				posYNueva = posYNueva +1;
			}
		}
		else if(direccion == ORIENTATION.SOUTH) {
			//this.posY = this.posY + this.velocidad;
			for(posYNueva = posYVieja+1; posYNueva <= posYVieja+this.velocidad; posYNueva++) {
				if(mapa[posXNueva][posYNueva] != null) {
					this.revisarPosicion(mapa[posXNueva][posYNueva]);
					break;
				}
			}
			if(posYNueva > posYVieja+this.velocidad) {
				posYNueva = posYNueva -1;
			}
		}
		else if(direccion == ORIENTATION.EAST) {
			//this.posX = this.posX + this.velocidad;
			for(posXNueva = posXVieja+1; posXNueva <= posXVieja+this.velocidad; posXNueva++) {
				if(mapa[posXNueva][posYNueva] != null) {
					this.revisarPosicion(mapa[posXNueva][posYNueva]);
					break;
				}
			}
			if(posXNueva > posXVieja+this.velocidad) {
				posXNueva = posXNueva -1;
			}
		}
		else if(direccion == ORIENTATION.WEST){
			//this.posX = this.posX - this.velocidad;
			for(posXNueva = posXVieja-1; posXNueva >= posXVieja-this.velocidad; posXNueva--) {
				if(mapa[posXNueva][posYNueva] != null) {
					this.revisarPosicion(mapa[posXNueva][posYNueva]);
					break;
				}
			}
			if(posXNueva < posXVieja-this.velocidad) {
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
