package gui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.event.MouseInputListener;

import centrourbano.CentroUrbano;
import centrourbano.Settori;

	public class CreaStrada extends JPanel {
	
	
	/**
		 * 
		 */
		private static final long serialVersionUID = -3604749607418975087L;
	public CreaStrada(Point numsettore,Settori ilsettore,CentroUrbano uncentro) {
		NSettore = numsettore;
		rifer = ilsettore;
		centro= uncentro;
		addMouseListener(new mouseEvent());
		ActionListener listener = new MioRicevitore();
		Timer timer = new Timer(1000, listener);
		timer.start();
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D u = (Graphics2D) g;
		u.clearRect(0, 0, this.getWidth(), this.getHeight());
		
		int i, j;
		for(i = 0; i < 3 ;i++ ) {
			for(j = 0; j < 5; j++) {
				u.drawRect(PX+(L*j*Z), PY+(L*i*Z), L*Z, L*Z);
				
				//Controllo il tipo del lotto
				switch(rifer.lista[i][j].getTip()) {
				case 1:	paintStrada(u, PX+(L*j*Z), PY+(L*i*Z), L*Z, i, j);	//STRADA
						break;
				case 2:	paintPub(u,PX+(L*j*Z), PY+(L*i*Z), L*Z);			//PUBBLICO
						break;
				case 3: paintPriv(u, PX+(L*j*Z), PY+(L*i*Z), L*Z); 			//PRIVATO
						break;
				default: break;
				}
			}
		}
		
	}
	
	class MioRicevitore implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			repaint();
			
		}
	}
	
	
	private void paintPriv(Graphics2D e, int x, int y, int L) {
		e.drawLine(x+(L/2), y+(L/10), x+(L/10), y+(L/2));
		e.drawLine(x+(L/10), y+(L/2), x+L-(L/10), y+(L/2));
		e.drawLine(x+L-(L/10), y+(L/2), x+(L/2), y+(L/10));
	}
	
	private void paintPub(Graphics2D e, int x, int y, int L) {
		e.drawRect(x+(L/10), y+(L/10), L-(L/5), L-(L/5));
	}
	
	private void paintStrada(Graphics2D e, int x, int y, int L, int i, int j) {
		e.drawLine(x+(L/2), y, x+(L/2), y+L-(L/2));

		//Controllo se i lotti adiacenti hanno una strada
		//Destra
		if( (j) == 4 ) {
		}
		else if(rifer.lista[i][j+1].getTip() == 1)
			e.drawLine(x+(L/2), y+(L/2), x+L, y+(L/2));
		
		
		//Basso
		if( (i) == 2) {
		}
		else if(rifer.lista[i+1][j].getTip() == 1)
			e.drawLine(x+(L/2), y+(L/2), x+(L/2), y+L);
		
		
		//Sinistra
		if( (j) == 0 ) {
		}
		else if(rifer.lista[i][j-1].getTip() == 1)
			e.drawLine(x+(L/2), y+(L/2), x, y+(L/2));
		
		
		//Sopra
		if( (i) == 0) {
		}
		else if(rifer.lista[i-1][j].getTip() == 1)
			e.drawLine(x+(L/2), y+(L/2), x+(L/2), y+L);
		
	}
	
	
	
	
	
	/**
	 * Metodo principale della classe, si avvia quando viene cliccato il mouse
	 * @param e L'evento che genera il clic
	 */
private class mouseEvent implements MouseInputListener{
		
	public void mouseClicked(MouseEvent e) {
		if(inizio) {
			inizio = false;
			primoX= e.getX()/(L*Z);		
			primoY= e.getY()/(L*Z);
		}
		else {
			inizio = true;
			secondoX= e.getX()/(L*Z);	
			secondoY= e.getY()/(L*Z);	
			costruisciPercorso();
		}
	
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
	
	/**
	 * Viene chiamato da mouseClicked 
	 * chiama i metodi check che si occupano di controllare  se siano liberi i percorsi 
	 * e poi successivamente costruisci che aggiungono le strade
	 */
	
	private void costruisciPercorso() { 
		if (primoX == secondoX) {
			int diff = primoY - secondoY;
			if(diff < 0) {
				if (checkY(primoY, -diff)) {
					costruisciY(primoY, -diff);
					return;}
				}
			if(diff > 0) {
				if (checkY(secondoY, diff)) {
					costruisciY(secondoY, diff);
					return;}
			}
		}
		
		else if (primoY == secondoY) {
				int diff = primoX - secondoX;
				if(diff < 0) {							//Destra
					if (checkX(primoX, -diff)) {
						costruisciX(primoX, secondoX);
						return;
						}
					}
				if(diff > 0) {							//Sinistra
					if (checkX(secondoX, diff)) {
						costruisciX(secondoX, primoX);
						return;
						}
					}
				}
		if(!flag) {
		JFrame FrameEcc = new JFrame ("Errore");
		JLabel scritta = new JLabel ("Seleziona un percorso ortonale e senza lotti");
		FrameEcc.add(scritta);
		FrameEcc.setSize(350, 60);
		FrameEcc.setResizable(false);
		FrameEcc.setAlwaysOnTop(true);
		
		FrameEcc.setVisible(true);
		flag=true;
		}
		return;
	}
	

	
	/**
	 * Funzione chiamata dal metodo costruisciPercorso, controllano effettivamente se e' libero
	 * @param valoreIniziale Valore da cui far partire il ciclo for
	 * @param diff La differenza di caselle , indica quando si deve fermare
	 * @return True se e' libero, false se non lo e'
	 */
	private boolean checkY(int valoreIniziale, int diff) {
		for(int i = valoreIniziale; i <= diff ; i++) {
			if(rifer.lista[i][primoX].getTip() != 0)
				return false;
		}
		return true;
	}
	

	/**
	 * Funzione chiamata dal metodo costruisciPercorso, controllano effettivamente se e' libero
	 * @param valoreIniziale Valore da cui far partire il ciclo for
	 * @param diff La differenza di caselle , indica quando si deve fermare
	 * @return True se e' libero, false se non lo e'
	 */
	private boolean checkX(int valoreIniziale,int diff) {
		for(int i = valoreIniziale; i <= diff ; i++) {
			if(rifer.lista[primoY][i].getTip() != 0)
				return false;
		}
		return true;
	}
	
	/**
	 * Funzione chiamata dal metodo costruisciPercorso, si occupa di aggiungere la strada
	 * @param valoreIniziale Valore da cui far partire il ciclo for
	 * @param diff La differenza di caselle , indica quando si deve fermare
	 */
	private void costruisciY(int valoreIniziale, int diff) {
		if(valoreIniziale == 1 && diff == 1) diff = 2;
		for(int i= valoreIniziale; i <= diff; i++) {
			centro.addStrada(NSettore.x, NSettore.y, i, primoX);
		}
		this.repaint();
	}
	
	/**
	 * Funzione chiamata dal metodo costruisciPercorso, si occupa di aggiungere la strada
	 * @param valoreIniziale Valore da cui far partire il ciclo for
	 * @param diff La differenza di caselle , indica quando si deve fermare
	 */
	
	private void costruisciX(int valoreIniziale,int diff) {
		for(int i=valoreIniziale;i<=diff;i++) {
			System.out.println("Costruisco su "+i+" Val ="+valoreIniziale+" diff="+diff);
			centro.addStrada(NSettore.x, NSettore.y, primoY, i);
		}
		this.repaint();
	}

	private boolean inizio = true;
	private boolean flag= false;
	private Point NSettore;
	private int primoX,primoY;
	private int secondoX,secondoY;
	private int PX = 3;
	private int PY = 3;
	private int L = 10;
	private Settori rifer;
	private CentroUrbano centro;
	private int Z = 5;
}
