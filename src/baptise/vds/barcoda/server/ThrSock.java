package baptise.vds.barcoda.server;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

public class ThrSock extends Thread{
	
	int port = 10000;
	FenPri fenetre;
	
	public ThrSock(FenPri fen, int argport){
		this.port = argport;
		this.fenetre = fen;
	}
	
	public void run(){
		ServerSocket socket = null;
		String rs = "";
		
		try {
			socket = new ServerSocket(this.port);
			fenetre.setStat("Aucun client sur le port " + this.port);
			socket.setSoTimeout(0);
			
		} catch(IOException ioe) {
			fenetre.setStat("Le port " + this.port + " est déja occupé");
		}
		Socket client = null;
		try {
			client = socket.accept();
			fenetre.setStat("Un client est connecte sur le port "  + this.port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		boolean co = true;
		while(co) {
		
			try {
				socket.setSoTimeout(1000);	
				BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
				rs = in.readLine();
				if (rs.equals("0XEND")) {;
					co = false;
				} else {
					Robot(StringtoCharArray(rs.toUpperCase()));
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
		
		if (!co) {
			try {
				socket.close();
				MainAct.restartSock(this.port);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}  

	public static void Robot(char[] rs) {
		int tableauVK[] = {KeyEvent.VK_NUMPAD0,KeyEvent.VK_NUMPAD1,KeyEvent.VK_NUMPAD2,KeyEvent.VK_NUMPAD3,KeyEvent.VK_NUMPAD4,KeyEvent.VK_NUMPAD5,KeyEvent.VK_NUMPAD6,KeyEvent.VK_NUMPAD7,KeyEvent.VK_NUMPAD8,KeyEvent.VK_NUMPAD9,KeyEvent.VK_A,KeyEvent.VK_B,KeyEvent.VK_C,KeyEvent.VK_D,KeyEvent.VK_E,KeyEvent.VK_F,KeyEvent.VK_G,KeyEvent.VK_H,KeyEvent.VK_I,KeyEvent.VK_J,KeyEvent.VK_K,KeyEvent.VK_L,KeyEvent.VK_M,KeyEvent.VK_N,KeyEvent.VK_O,KeyEvent.VK_P,KeyEvent.VK_Q,KeyEvent.VK_R,KeyEvent.VK_S,KeyEvent.VK_T,KeyEvent.VK_U,KeyEvent.VK_V,KeyEvent.VK_W,KeyEvent.VK_X,KeyEvent.VK_Y,KeyEvent.VK_Z};
		char[] tableauAlphabet = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
		
		try {
			Robot keyb = new Robot();
			
			keyb.setAutoDelay(50);
			keyb.setAutoWaitForIdle(false);
			
			keyb.keyPress(KeyEvent.VK_CAPS_LOCK);
			keyb.keyRelease(KeyEvent.VK_CAPS_LOCK);

			for(int i = 0; i < rs.length; i++) {
				int index = Arrays.binarySearch(tableauAlphabet, rs[i]);
				keyb.keyPress(tableauVK[index]);
				keyb.keyRelease(tableauVK[index]);
			}
			
			keyb.keyPress(KeyEvent.VK_CAPS_LOCK);
			keyb.keyRelease(KeyEvent.VK_CAPS_LOCK);
			
		} catch(AWTException ex) {
			
		}
	}
	public static char[] StringtoCharArray(String arg1) {
		char retour[] = new char[arg1.length()];
		for (int i = 0; i < arg1.length(); i++) {
			retour[i] = arg1.charAt(i);
		}
		return retour;
	}
}
