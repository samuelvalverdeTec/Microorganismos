package game;

import java.util.ArrayList;

public class Juego implements Constants {

	// Se crea el mapa
	// Se crean los alimentos y los microorganismos
	// Inicia la simulacion
	
	Mapeable[][] mapa = new Mapeable[TABLERO_SIZE_1][TABLERO_SIZE_2];	// 50x50
	ArrayList<Microorganismo> microList = new ArrayList<Microorganismo>();
	ArrayList<Alimento> aliList = new ArrayList<Alimento>();
	
	
	public void play() {
		
		crearMapeables();
		cargarMapeables();
		
	}
	
	public void crearMapeables() {
		
		Microorganismo MyMicroorganismo = new MyMicro();
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

	
}
