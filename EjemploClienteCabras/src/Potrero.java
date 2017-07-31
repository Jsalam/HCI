import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PConstants;

public class Potrero {
	int energia;
	PApplet app;
	ArrayList<Cabra> cabras;

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

	public void actualizarEnergia(int time) {
		if (time % 10 == 0) {
			energia -= cabras.size() * 10;
			if (energia < 0) {
				energia = 0;
			}
		}
	}
}
