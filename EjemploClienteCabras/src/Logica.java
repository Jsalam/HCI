import common.Message;
import communication.Communication_to_Server;
import logic.User;
import processing.core.PApplet;
import processing.core.PConstants;

public class Logica extends PApplet {

	Potrero potrero;
	int time;
	int timer = 0;
	boolean empezarServidor;

	User user;
	Communication_to_Server cts;

	public void settings() {
		size(600, 300);
	}

	public void setup() {
		potrero = new Potrero(this);
		time = 0;
		user = new User();
		cts = Communication_to_Server.getInstance(user);
		cts.setIp("127.0.0.1");
	}

	public void draw() {
//System.out.println(cts.lastMessage.getStringValues());
		background(0);
		text("Mi id: " + user.getId(), 100, 90);
		text("Cabras: " + potrero.cabras.size(), 100, 110);
		text("Energia potrero: " + potrero.energia, 100, 130);
		text("Tiempo: " + time, 100, 150);
		if (user.getEmpezar()) {
			text("Empezó ", 100, 170);
			// cada 1 segundos
			if (millis() - timer >= 1000) {
				timer = millis();
				time++;
				// Envia energia al servidor
				int UserID = user.getId();
				// identificar que el mensaje es del potrero
				String tipo = "energy";
				int energia = potrero.energia;
				cts.sendMessage(new Message(UserID,tipo,energia));
			}
			potrero.actualizarEnergia(time);
		} else {
			text("En Pausa ", 100, 170);
		}
	}

	public void keyReleased() {
		potrero.administrarCabra(keyCode);
		// cuando se adicione o se remueva una cabra
		if (keyCode == PConstants.UP || keyCode == PConstants.DOWN) {
			// Datos para el mensaje
			int UserID = user.getId();
			// identificar que el mensaje es de las cabras
			String tipo = "goats";
			int cabras = potrero.cabras.size();
			cts.sendMessage(new Message(UserID,tipo,cabras));
		}
	}

	public static void main(String[] args) {
		PApplet.main("Logica");
	}
}
