package pt.hmsk.week6.ex2.tiago;

import java.awt.Color;
import java.util.Observable;
import java.util.concurrent.ThreadLocalRandom;

public class Bola extends Observable implements DrawableBall, Runnable {
	private float estado=0;
	private Color color=new Color((int)(Math.random()*256), 
			(int)(Math.random()*256), (int)(Math.random()*256));


	@Override
	public void run() {
		while(!bolaAtingiuLimite()){
			//incremento no estado [0.01, 0.1]
			estado += ThreadLocalRandom.current().nextDouble(0.01,0.1);
			if (estado > 1)
				estado=1;
			setChanged();
			notifyObservers();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// Ignora quando interrompido
			}
		}
	}

	public boolean bolaAtingiuLimite(){
		return estado>=1;
	}

	@Override
	public Color getColor() {
		return color;
	}

	@Override
	public float getX() {
		return estado;
	}

	@Override
	public int getSize() {
		return 10;
	}

}
