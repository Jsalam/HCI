
public class Filter {

	public int bufferSize;
	public float[] buffer;

	/**
	 * Este filtro es un simple promedio de un numero de valores definido en la
	 * variable bufferSize. Cada valor nuevo es promediado con sus valores
	 * predecesores dando como resutado un valor atenuado, reduciendo las
	 * variaciones de una señal (ruido) de un sensor.
	 */
	public Filter() {

		// Tamaño de buffer
		bufferSize = 20;

		// Instanciamiento de buffer
		buffer = new float[bufferSize];
	}

	/**
	 * Filtrado basado en el promedio de los últimos 20 valores recibidos
	 * 
	 * @param entrada
	 *            el ultimo valor recibido
	 * @return el promedio de los ultimos 20 valores
	 */
	public float filtrado(float entrada) {
		// liberala última posición del buffer descartando el valor de la
		// primera

		for (int i = 0; i < buffer.length - 1; i++) {
			buffer[i] = buffer[i + 1];
		}

		// Adiciona entrada al final del buffer
		buffer[bufferSize - 1] = entrada;

		// promedia valores
		float acumulado = 0;

		for (int i = 0; i < buffer.length - 1; i++) {
			acumulado = +buffer[i];
		}
		float promedio = acumulado / bufferSize;

		// Devuelve valor filtrado
		return promedio;
	}

	public static void main(String[] args) {
		Filter f = new Filter();
		for (int i = 0; i < 100; i++) {
			float valor = f.filtrado((float) i);
			System.out.println(valor);
		}

	}

}
