import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PConstants;

public class Potrero {
	private int energia;
	public PApplet app;
	private ArrayList<Cabra> cabras;
	private int cabrasFallecidas = 0;
	private int consumoEnergiaCabra = 10;
	private boolean inicializado = false;

	public Potrero(PApplet app) {
		cabras = new ArrayList<Cabra>();

	}

	/**
	 * el potrero se puebla con un 5 cabras. Se invoca UNA SOLA VEZ
	 */
	public void init() {
		if (!inicializado) {
			energia = 10000;
			for (int i = 0; i < 5; i++) {
				cabras.add(new Cabra());
			}
			inicializado = true;
		}
	}

	public void administrarCabra(int code) {
		if (code == PConstants.UP) {
			addCabra();
		} else if (code == PConstants.DOWN) {
			removeCabra();
		}
	}

	/**
	 * Adiciona una cabra
	 */
	public void addCabra() {
		cabras.add(new Cabra());
	}

	/**
	 * Remueve la cabra mas vieja
	 */
	public void removeCabra() {
		cabras.remove(cabras.get(0));
		cabrasFallecidas++;
	}

	/**
	 * Este metodo se debe usar cuando una cabra muere de hambre
	 * 
	 * @param cabra
	 *            la cabra que debe removerse del arreglo
	 */
	public void removeCabra(Cabra cabra) {
		// Remover la cabra que recibe como parametro del arreglo
		cabrasFallecidas++;
	}

	/**
	 * Este metodo recibe la cantidad de segundos que ha transcurrido desde que
	 * inició el juego (no desde que inicio la aplicacion). Cada 10 segundos la
	 * energía del potrero disminuye en x unidades por cada cabra que haya en el
	 * potrero. Las unidades estan definidas en la variable consumoEnergíaCabra
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

	/**
	 * Este método debe tomar la lectura de la fotocelda y acumularla en una
	 * variable. La acumulación se hace una vez por segundo. El potrero no tiene
	 * límite de acumulación de energía.
	 */
	public void addEnergia() {
		// Escribir el código aqui
	}

	//// ***** GETTERS & SETTERS ******
	public int getTotalEnergia() {
		return energia;
	}

	public int getNumeroCabras() {
		return cabras.size();
	}

	public int getNumeroCabrasFallecidas() {
		return cabrasFallecidas;
	}

}
