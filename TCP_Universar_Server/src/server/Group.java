package server;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import common.Message;
import logic.User;

public class Group {

	private ArrayList<Client_communication_thread> clients;

	private boolean groupFull;

	private int numberOfClients;
	private int maxClientsAllowed;
	private int groupId;
	private int turn;

	private ServerCom server;

	public Group(ServerCom server, int id) {
		this.server = server;

		groupId = id;
		turn = 1;

		clients = new ArrayList<Client_communication_thread>();
		groupFull = false;
		numberOfClients = 0;
		maxClientsAllowed = 1;

	}// constructor

	public boolean addClient(Client_communication_thread newClient) {
		boolean added = false;
		try {
			// Message msm = new Message(0, "cambiarId",
			// newClient.getClient().getId());
			Message msm = new Message(0, "cambiarId", groupId);
			newClient.start();
			newClient.sendMessage(msm);
			clients.add(newClient);
			added = true;
			numberOfClients++;
		} catch (NullPointerException e) {
			System.out.println("Se ha intentado agregar un NULL al grupo");
		} // try catch
		return added;
	}// adds a new client to the clients list

	public void deleteClient(int idCliente) {
		for (int i = 0; i < clients.size(); i++) {
			if (clients.get(i).getId() == idCliente) {
				clients.remove(i);
			} // if
		} // for of clients
		System.out.println("[ CLIENT " + (idCliente) + " DISCONNECTED FROM GROUP " + groupId + "]");
		if (clients.isEmpty()) {
			//server.deleteGroup(groupId);
		} // is empty
	}// disconnect

	// ------------ FUNCIONES PROPIAS DEL GRUPO -------------

	public void notifyMessage(Message msm) {
		server.notifyMessage(msm);
	}// notify

	public void sendMessage(Message msm) {
		for (int i = 0; i < clients.size(); i++) {
			clients.get(i).sendMessage(msm);
		} // for of clients
	}// send Message

	// ------------- GETTERS Y SETTERS -------------

	public boolean isComplete() {
		if (numberOfClients >= maxClientsAllowed) {
			groupFull = true;
		} // more of 3 clients
		return groupFull;
	}// is full

	public int getNumberOfClients() {
		return numberOfClients;
	}

	public void setNumberOfClients(int numberOfClients) {
		this.numberOfClients = numberOfClients;
	}

	public int getMaxClientsAllowed() {
		return maxClientsAllowed;
	}

	public void setMaxClientsAllowed(int maxClientsAllowed) {
		this.maxClientsAllowed = maxClientsAllowed;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public ArrayList<Client_communication_thread> getClients() {
		return clients;
	}

	public User getClient(int id) throws NullPointerException {
		for (Client_communication_thread cct : clients) {
			if (cct.getClient().getId() == id) {
				return cct.getClient();
			}
		}
		return null;
	}

	public int getTurn() {
		return turn;
	}

}// GROUP
