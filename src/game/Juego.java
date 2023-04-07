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
	int contMicro = 2;
	
	public Juego() {
		
		this.mapa = new Mapeable[TABLERO_SIZE_1][TABLERO_SIZE_2];
		this.microList = new ArrayList<Microorganismo>();
		this.aliList = new ArrayList<Alimento>();
		this.gui = new Interfaz(this);
		
	}
	
	public void play() {
		
		crearMapeables();
		cargarMapeables();
		controlarMicro();
		//runGame();
		
	}
	
	public String prueba() {
		return "abc";
	}
	
	public void crearMapeables() {
		
		MyMicroorganismo = new MyMicro();
		MyMicroorganismo.numMicro = 1;
		microList.add(MyMicroorganismo);
		aliList.add(new AlimentoEnergia());
		aliList.add(new AlimentoVision());
		aliList.add(new AlimentoVelocidad());
		
		MicroNPC MicroorganismoNPC = null;
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
	
	
	public void runGame() {
		controlarMicro();
	}
	
	
	
	public void cargarMapeables() {
		
		for(int i=0;i<microList.size();i++) {
			encontrarPosRandomValida(microList.get(i));
			/*boolean encontroPos = false;
			while(encontroPos == false) {
				int pPosX = (int)(Math.random()*(TABLERO_SIZE_1-1)+0);
				int pPosY = (int)(Math.random()*(TABLERO_SIZE_2-1)+0);
				if(mapa[pPosX][pPosY] == null) {
					microList.get(i).setPosicion(pPosX, pPosY);
					mapa[pPosX][pPosY] = microList.get(i);
					encontroPos = true;
				}
			}*/			
		}
		
		for(int i=0;i<aliList.size();i++) {
			encontrarPosRandomValida(aliList.get(i));
			/*boolean encontroPos = false;
			while(encontroPos == false) {
				int pPosX = (int)(Math.random()*(TABLERO_SIZE_1-1)+0);
				int pPosY = (int)(Math.random()*(TABLERO_SIZE_2-1)+0);
				if(mapa[pPosX][pPosY] == null) {
					aliList.get(i).setPosicion(pPosX, pPosY);
					mapa[pPosX][pPosY] = aliList.get(i);
					encontroPos = true;
				}
			}*/			
		}
		
	}
	
	public void encontrarPosRandomValida(Mapeable objeto) {
		boolean encontroPos = false;
		while(encontroPos == false) {
			int pPosX = (int)(Math.random()*(TABLERO_SIZE_2-1)+0);
			int pPosY = (int)(Math.random()*(TABLERO_SIZE_1-1)+0);
			if(mapa[pPosY][pPosX] == null) {
				objeto.setPosicion(pPosX, pPosY);
				mapa[pPosY][pPosX] = objeto;
				encontroPos = true;
			}
		}	
	}
	
	
	
	public void controlarMicro() {
		MyMicroorganismo = (MyMicro)microList.get(0);
		gui.refrescar(mapa);
		while(MyMicroorganismo.getIsAlive()) {
			while(MyMicroorganismo.getOrientation() == null) {
				//System.out.println("esperando");
				//System.out.println("ciclo");
				//esperando tecla
			}
			MyMicroorganismo.move(mapa);
			MyMicroorganismo.setOrientation(null);
			MyMicroorganismo.aumentarEdad(AUMENTO_EDAD_POR_TURNO);
			MyMicroorganismo.contAumentoEdad++;
			MyMicroorganismo.revisarAtributos();
			MyMicroorganismo.mostrarInfoMicroorganismo();
			//dibujar
			gui.refrescar(mapa);

			for(int i = 1; i<microList.size(); i++) {
				MicroNPC microorganismoNPC = (MicroNPC)microList.get(i);
				if(microorganismoNPC.getIsAlive()) {
					microorganismoNPC.revisarEntorno(mapa);
					microorganismoNPC.move(mapa);
					microorganismoNPC.aumentarEdad(AUMENTO_EDAD_POR_TURNO);
					microorganismoNPC.contAumentoEdad++;
					microorganismoNPC.revisarAtributos();
					microorganismoNPC.mostrarInfoMicroorganismo();
				}
				else {
					MicroNPC newMicroorganismoNPC = null;
					if(microorganismoNPC.esNPCVision()) {
						newMicroorganismoNPC = new MicroNPCVision();
					}
					else {
						newMicroorganismoNPC = new MicroNPCVelocidad();
					}
					newMicroorganismoNPC.numMicro = contMicro;
					contMicro++;
					encontrarPosRandomValida(newMicroorganismoNPC);
					
					microList.remove(i);
					microList.add(newMicroorganismoNPC);
					newMicroorganismoNPC.mostrarInfoMicroorganismo();
					
				}
				
				
				for(int j = 1; j<aliList.size(); j++) {
					Alimento alimentoAct = aliList.get(j);
					Alimento newAlimento = null;
					if(alimentoAct.getIsConsumido()) {
						if(alimentoAct.esAlimentoEnergia()) {
							newAlimento = new AlimentoEnergia();
						}
						else if (alimentoAct.esAlimentoVelocidad()) {
							newAlimento = new AlimentoVelocidad();
						}
						else if (alimentoAct.esAlimentoVision()) {
							newAlimento = new AlimentoVision();
						}
						encontrarPosRandomValida(newAlimento);
						aliList.remove(j);
						aliList.add(newAlimento);
					}
				}
				
				
				gui.refrescar(mapa);
			
				try {
					//for(int i = 0; i<microList.size(); i++) {
					//	interfaz.mostrarImagen((MyRobot) ListaRobots.get(i));
					//}
					Thread.sleep(3000);
				}
				catch (Exception ex) {
					ex.printStackTrace();
				}
				//gui.refrescar(mapa);
				//interfaz.RepaintRobots();
				//interfaz.RevalidateInfoRobot(CurrentRobot);

				
			}
   
		}
		System.out.println("Perdio");
		gui.gameOver();

	}
	
	
	
	
	
	
	@Override
	public void keyTyped(KeyEvent e) {	
		/*System.out.println("Key typed");
		if(MyMicroorganismo != null) {
			int key = e.getKeyCode();
			System.out.println("Key typed");
		    if (key == KeyEvent.VK_LEFT) {
		    	if(MyMicroorganismo.getIsAlive()) {
			    	if(MyMicroorganismo.getEnergia() > 0) {
			    		System.out.println("Move izq");
			    		MyMicroorganismo.setOrientation(ORIENTATION.WEST);
			    	}
		    	}
		    }
		    else if (key == KeyEvent.VK_RIGHT) {
		    	if(MyMicroorganismo.getIsAlive()) {
		    		if(MyMicroorganismo.getEnergia() > 0) {
		    			System.out.println("Move der");
		    			MyMicroorganismo.setOrientation(ORIENTATION.EAST);
			    	}
		    	}
		    }
		    else if (key == KeyEvent.VK_UP) {
		    	if(MyMicroorganismo.getIsAlive()) {
		    		if(MyMicroorganismo.getEnergia() > 0) {
		    			System.out.println("Move arriba");
		    			MyMicroorganismo.setOrientation(ORIENTATION.NORTH);
			    	}
		    	}
		    }
		    else if (key == KeyEvent.VK_DOWN) {
		    	if(MyMicroorganismo.getIsAlive()) {
		    		if(MyMicroorganismo.getEnergia() > 0) {
		    			System.out.println("Move abajo");
		    			MyMicroorganismo.setOrientation(ORIENTATION.SOUTH);
			    	}
		    	}    	
		    }
		}*/
	}



	@Override
	public void keyPressed(KeyEvent e) {
		if(MyMicroorganismo != null) {
			int key = e.getKeyCode();
		    if (key == KeyEvent.VK_LEFT) {
		    	if(MyMicroorganismo.getIsAlive()) {
			    	MyMicroorganismo.setOrientation(ORIENTATION.WEST);
		    	}
		    }
		    else if (key == KeyEvent.VK_RIGHT) {
		    	if(MyMicroorganismo.getIsAlive()) {
		    		MyMicroorganismo.setOrientation(ORIENTATION.EAST);
		    	}
		    }
		    else if (key == KeyEvent.VK_UP) {
		    	if(MyMicroorganismo.getIsAlive()) {
		    		MyMicroorganismo.setOrientation(ORIENTATION.NORTH);
		    	}
		    }
		    else if (key == KeyEvent.VK_DOWN) {
		    	if(MyMicroorganismo.getIsAlive()) {
		    		MyMicroorganismo.setOrientation(ORIENTATION.SOUTH);
		    	}    	
		    }
		}
	}



	@Override
	public void keyReleased(KeyEvent e) {
	}
	
}
