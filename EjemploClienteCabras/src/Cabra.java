
public class Cabra {
	public int energia;
	private boolean conVida = true;

	public Cabra() {
		energia = 150;
		conVida = true;
	}

	public void setEnergia(int energiaDelPotrero) {
		energia += energiaDelPotrero;
		if (energia > 150) {
			energia = 150;
		}
	}

	public void existir() {
		// Cada 2 segundos debe consumir 2 unidades de energia
		// Si el nivel de energia es <= 0 debe invocar el metodo morir()
	}

	public void morir() {
		conVida = false;
	}

	//// ****** Getters & Setters

	/**
	 * La cabra vive?
	 * 
	 * @return false si esta muerta
	 */
	public boolean isConVida() {
		return conVida;
	}

}
