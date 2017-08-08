import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PConstants;

public class Potrero {
	int energia;
	PApplet app;
	ArrayList<Cabra> cabras;
	int consumoEnergiaCabra = 10;

	public Potrero(PApplet app) {
		energia = 10000;
		cabras = new ArrayList<Cabra>();
		for (int i = 0; i < 5; i++) {
			cabras.add(new Cabra());
		}
	}

	public void administrarCabra(int code) {
		if (code == PConstants.UP) {
			cabras.add(new Cabra());
		} else if (code == PConstants.DOWN) {
			cabras.remove(cabras.get(0));
		}
	}

	/**
	 * Este metodo recibe la cantidad de segundos que ha transcurrido desde que
	 * inició el juego (no desde que inicio la aplicacion). Cada 10 segundos la
	 * energía del potrero disminuye en x uniddaes por cada cabra que haya en
	 * el potrero. Las unidades estan definidas en la variable consumoEnergíaCabra
	 * 
	 * @param time
	 */
	public void actualizarEnergia(int time) {
		if (time % 10 == 0) {
			energia -= cabras.size() * consumoEnergiaCabra;
			if (energia < 0) {
				energia = 0;
			}
		}
	}
}
