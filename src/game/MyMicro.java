package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class MyMicro extends Microorganismo implements KeyListener {

	public MyMicro() {
		
		this.edad = EDAD_JUGADOR;
		this.energia = ENERGIA_JUGADOR;
		this.vision = VISION_JUGADOR;
		this.velocidad = VELOCIDAD_JUGADOR;
		
		this.posX = 0;
		this.posY = 0;
		
	}
	
	public void move() {
		
		
		
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
	}



	@Override
	public void keyPressed(KeyEvent e) {
		if(this != null) {
			int key = e.getKeyCode();
		    if (key == KeyEvent.VK_LEFT) {
		    	if(this.isAlive) {
			    	if(this.energia > 0) {
			    		this.posX = this.posX - VELOCIDAD_JUGADOR;
			    	}
		    	}
		    }
	
		    else if (key == KeyEvent.VK_RIGHT) {
		    	if(this.isAlive) {
		    		if(this.energia > 0) {
			    		this.posX = this.posX + VELOCIDAD_JUGADOR;
			    	}
		    	}
		    }
	
		    else if (key == KeyEvent.VK_UP) {
		    	if(this.isAlive) {
		    		if(this.energia > 0) {
			    		this.posY = this.posY + VELOCIDAD_JUGADOR;
			    	}
		    	}
		    }
	
		    else if (key == KeyEvent.VK_DOWN) {
		    	if(this.isAlive) {
		    		if(this.energia > 0) {
			    		this.posY = this.posY - VELOCIDAD_JUGADOR;
			    	}
		    	}    	
		    }

		}
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
