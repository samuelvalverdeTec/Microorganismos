package game;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Random;

public class Juego implements Constants, Mapeable {

	// Se crea el mapa
	// Se crean los alimentos y los microorganismos
	// Inicia la simulacion
	
	Mapeable[][] mapa = new Mapeable[TABLERO_SIZE_1][TABLERO_SIZE_2];	// 50x50
	ArrayList<Microorganismo> microList = new ArrayList<Microorganismo>();
	ArrayList<Alimento> aliList = new ArrayList<Alimento>();
	
	public void play() {
		
		crearMapeables();
		//cargarMapeables(mapa, microList, aliList);
		
	}
	
	public void crearMapeables() {
		
		microList.add(new MyMicro());
		for(int i=1; i<CANTIDAD_NPCS; i++) {		// 250 microorganismos
			microList.add(new MicroNpc());			// 750 alimentos
			for(int j=0;j<3;j++) {
				aliList.add(new Alimento());
			}
		} 
	}
	
	public void cargarMapeables(Mapeable mapa, ArrayList<Microorganismo> microList, ArrayList<Alimento> aliList) {
		
		Random rnX = new Random();
		Random rnY = new Random();
		
		for(int i=0;i<microList.size();i++) {
			
			int coordX = rnX.nextInt(TABLERO_SIZE_1 - TABLERO_SIZE_2 + 1) + TABLERO_SIZE_2;
			int coordY = rnX.nextInt(TABLERO_SIZE_1 - TABLERO_SIZE_2 + 1) + TABLERO_SIZE_2;
			
			//mapa[coordX][coordY] = microList.get(i);
			
		}
	}
	
}
