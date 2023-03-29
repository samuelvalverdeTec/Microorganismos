package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import gui.Interfaz;

public class Juego implements Constants, KeyListener {
	
	MyMicro MyMicroorganismo = null;
	Mapeable[][] mapa;	// 50x50
	ArrayList<Microorganismo> microList;
	ArrayList<Alimento> aliList;
	Interfaz gui;
	
	public Juego() {
		
		this.mapa = new Mapeable[TABLERO_SIZE_1][TABLERO_SIZE_2];
		this.microList = new ArrayList<Microorganismo>();
		this.aliList = new ArrayList<Alimento>();
		this.gui = new Interfaz();
		
	}
	
	public void play() {
		
		crearMapeables();
		cargarMapeables();
		
	}
	
	public void crearMapeables() {
		
		MyMicroorganismo = new MyMicro();
		MyMicroorganismo.numMicro = 1;
		microList.add(MyMicroorganismo);
		int contMicro = 2;
		Microorganismo MicroorganismoNPC = null;
		for(int i=0; i<(CANTIDAD_NPCS/2); i++) {		// 250 microorganismos
			MicroorganismoNPC = new MicroNPCVision();
			MicroorganismoNPC.numMicro = contMicro;
			microList.add(MicroorganismoNPC);			// 750 alimentos
			contMicro++;
			
			aliList.add(new AlimentoEnergia());
			aliList.add(new AlimentoVision());
			aliList.add(new AlimentoVelocidad());
				
		} 
		
		for(int i=0; i<(CANTIDAD_NPCS/2); i++) {		// 250 microorganismos
			MicroorganismoNPC = new MicroNPCVelocidad();
			MicroorganismoNPC.numMicro = contMicro;
			microList.add(MicroorganismoNPC);			// 750 alimentos
			contMicro++;
			
			aliList.add(new AlimentoEnergia());
			aliList.add(new AlimentoVision());
			aliList.add(new AlimentoVelocidad());
			
		} 
	}
	
	public void cargarMapeables() {
		
		for(int i=0;i<microList.size();i++) {
			boolean encontroPos = false;
			while(encontroPos == false) {
				int pPosX = (int)(Math.random()*(TABLERO_SIZE_1-1)+0);
				int pPosY = (int)(Math.random()*(TABLERO_SIZE_2-1)+0);
				if(mapa[pPosX][pPosY] == null) {
					microList.get(i).setPosicion(pPosX, pPosY);
					mapa[pPosX][pPosY] = microList.get(i);
					encontroPos = true;
				}
			}			
		}
		
		for(int i=0;i<aliList.size();i++) {
			boolean encontroPos = false;
			while(encontroPos == false) {
				int pPosX = (int)(Math.random()*(TABLERO_SIZE_1-1)+0);
				int pPosY = (int)(Math.random()*(TABLERO_SIZE_2-1)+0);
				if(mapa[pPosX][pPosY] == null) {
					aliList.get(i).setPosicion(pPosX, pPosY);
					mapa[pPosX][pPosY] = aliList.get(i);
					encontroPos = true;
				}
			}			
		}
		
	}
	
	
	
	@Override
	public void keyTyped(KeyEvent e) {
		if(MyMicroorganismo != null) {
			int key = e.getKeyCode();
		    if (key == KeyEvent.VK_LEFT) {
		    	if(MyMicroorganismo.getIsAlive()) {
			    	if(MyMicroorganismo.getEnergia() > 0) {
			    		MyMicroorganismo.setOrientation(ORIENTATION.WEST);
			    	}
		    	}
		    }
		    else if (key == KeyEvent.VK_RIGHT) {
		    	if(MyMicroorganismo.getIsAlive()) {
		    		if(MyMicroorganismo.getEnergia() > 0) {
		    			MyMicroorganismo.setOrientation(ORIENTATION.EAST);
			    	}
		    	}
		    }
		    else if (key == KeyEvent.VK_UP) {
		    	if(MyMicroorganismo.getIsAlive()) {
		    		if(MyMicroorganismo.getEnergia() > 0) {
		    			MyMicroorganismo.setOrientation(ORIENTATION.NORTH);
			    	}
		    	}
		    }
		    else if (key == KeyEvent.VK_DOWN) {
		    	if(MyMicroorganismo.getIsAlive()) {
		    		if(MyMicroorganismo.getEnergia() > 0) {
		    			MyMicroorganismo.setOrientation(ORIENTATION.SOUTH);
			    	}
		    	}    	
		    }
		}
	}



	@Override
	public void keyPressed(KeyEvent e) {
		/*if(this != null) {
			int key = e.getKeyCode();
		    if (key == KeyEvent.VK_LEFT) {
		    	if(this.isAlive) {
			    	if(this.getEnergia() > 0) {
			    		this.setPosicion(this.getX() - VELOCIDAD_JUGADOR, this.getY());
			    	}
		    	}
		    }
	
		    else if (key == KeyEvent.VK_RIGHT) {
		    	if(this.isAlive) {
		    		if(this.getEnergia() > 0) {
		    			this.setPosicion(this.getX() + VELOCIDAD_JUGADOR, this.getY());
			    	}
		    	}
		    }
	
		    else if (key == KeyEvent.VK_UP) {
		    	if(this.isAlive) {
		    		if(this.getEnergia() > 0) {
		    			this.setPosicion(this.getX(), this.getY() + VELOCIDAD_JUGADOR);
			    	}
		    	}
		    }
	
		    else if (key == KeyEvent.VK_DOWN) {
		    	if(this.isAlive) {
		    		if(this.getEnergia() > 0) {
		    			this.setPosicion(this.getX(), this.getY() - VELOCIDAD_JUGADOR);
			    	}
		    	}    	
		    }

		}*/
	}



	@Override
	public void keyReleased(KeyEvent e) {
		/*if(this != null) {
			int key = e.getKeyCode();
		    if (key == KeyEvent.VK_LEFT) {
		    	
		    }
	
		    else if (key == KeyEvent.VK_RIGHT) {
		    	
		    }
	
		    else if (key == KeyEvent.VK_UP) {
		    	
		    }
	
		    else if (key == KeyEvent.VK_DOWN) {
		    	
		    }
		}*/
	}
	
}
