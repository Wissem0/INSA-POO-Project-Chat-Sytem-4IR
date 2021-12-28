package Controller;

import java.net.UnknownHostException;

import Interface.ActiveUsers;
import Interface.Authentification;
import Network.TCP_Server;

public class Controller_Interface {
	static String msg;
	protected Authentification view;
	
	public Controller_Interface() throws UnknownHostException {
	}
	
	public static void main(String[] args) throws UnknownHostException {
		//Controller_Interface interf = new Controller_Interface();
		Authentification window = new Authentification();
		TCP_Server.connect(Controller.TCPport_local);
		TCP_Server.recieve(Controller.TCPport_local);

	}
	
}

//2 probl�mes:
//	- Est-ce qu'il faut se connecter � tous les active users pour 
//	potentiellement recevoir des messages de la part de tout le monde?
//	i.e que se passe-t-il si on essaie de communiquer avec quelqu'un 
//	qui n'a pas �tabli de connexion avec nous
//	(test�: ne marche pas)
//	-Il faut utiliser des ports diff�rents pour chaque destinataire
//	->utilisation de threads