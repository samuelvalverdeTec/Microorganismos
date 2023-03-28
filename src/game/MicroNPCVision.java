package game;

import java.util.ArrayList;

public class MicroNPCVision extends MicroNPC{
	
	public ORIENTATION revisarEntorno(Mapeable[][] mapa) {
		
		ArrayList<Alimento> alimentosEncontrados = new ArrayList<Alimento>();
		ArrayList<Microorganismo> microsEncontrados = new ArrayList<Microorganismo>();
		
		int PosXactual = this.posX;
		int PosYactual = this.posY;
		int radioBusqueda = this.vision;
		int diametroBusqueda = (radioBusqueda*2)+1;
		
		int PosXInicioBusqueda = PosXactual - radioBusqueda;
		int PosYInicioBusqueda = PosYactual - radioBusqueda;
		int PosXFinBusqueda = PosXactual + radioBusqueda;
		int PosYFinBusqueda = PosYactual + radioBusqueda;
		
		//int PosXBusqueda = PosXInicioBusqueda;
		//int PosYBusqueda = PosYInicioBusqueda;
		
		
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
		
		for(int i = 0; i < microsEncontrados.size(); i++) {
			
		}
		
	}

}
