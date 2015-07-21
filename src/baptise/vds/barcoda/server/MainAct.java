package baptise.vds.barcoda.server;

import javax.swing.JOptionPane;

public class MainAct {
	
	static FenPri fenetre = null;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String nom = "";
		while (nom.isEmpty()) {
			nom = JOptionPane.showInputDialog(null, "Veuillez entrer un port d'écoute", "Port du Socket", JOptionPane.QUESTION_MESSAGE);
		}
		fenetre = new FenPri();
	    MainAct.restartSock(Integer.parseInt(nom));
		
	}
	public static void restartSock(int port) {
		ThrSock thrsock = new ThrSock(fenetre, port);
		thrsock.start();
	}


}
