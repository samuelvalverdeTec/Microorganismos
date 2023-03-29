package game;

public interface Constants {

	public static final int MAX_ENERGIA = 300;
	public static final int MIN_ENERGIA = 1;
	
	public static final int MAX_VISION = 6;
	public static final int MIN_VISION = 1;
	
	public static final int MAX_VELOCIDAD = 3;
	public static final int MIN_VELOCIDAD = 1;
	
	public static final int MAX_EDAD = 100;
	public static final int MIN_EDAD = 1;
	
	public static final int TABLERO_SIZE_1 = 10;
	public static final int TABLERO_SIZE_2 = 10;
	
	public static final int CANTIDAD_NPCS = 249;
	
	
	public static final int EDAD_JUGADOR = 20;
	public static final int ENERGIA_JUGADOR = 100;
	public static final int VISION_JUGADOR = 3;
	public static final int VELOCIDAD_JUGADOR = 2;
	
	
	
	// Agregar constantes de efectos entre atributos
	public static final int DISMINUCION_ENERGIA_POR_CASILLA = 5;
	public static final int DISMINUCION_VISION_POR_10_EDAD = 1;
	public static final int AUMENTO_EDAD_POR_TURNO = 1;
	public static final int DISMINUCION_VELOCIDAD_POR_30_ENERGIA = 1;
	
}
