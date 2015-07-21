package baptise.vds.barcoda.server;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class FenPri extends JFrame{
	
    JLabel statut = new JLabel("Aucun client connecté à l'heure actuelle", JLabel.CENTER);
	
	public FenPri() {
	    this.setTitle("Barcoda Server");
	    this.setSize(400, 200);
	    this.setLocationRelativeTo(null);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    GridLayout gl = new GridLayout(2,1);
	    gl.setVgap(5);
	    gl.setHgap(5);
	    
	    JButton bout_quitter = new JButton("Quitter");
	    bout_quitter.addActionListener(new BoutonQuitterListener());
	    
	    this.getContentPane().add(statut);
	    this.getContentPane().add(bout_quitter);
	    
	    this.setLayout(gl);
	    this.setVisible(true);
	  
	}
	
	public void setStat(String stat) {
		statut.setText(stat);
	}

}


class BoutonQuitterListener implements ActionListener{
    //Redéfinition de la méthode actionPerformed()
    public void actionPerformed(ActionEvent e) {
    	System.exit(0);
    }
  }  