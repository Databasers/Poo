package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


import javax.swing.JPanel;

import ascoltatori.StartFrameListener;
import centrourbano.CentroUrbano;

public class StartFrame extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -825081298686096534L;



	public StartFrame(CentroUrbano c) {
		super("Urban Planner");

		setJMenuBar(addFile());
		
		JPanel grid = new JPanel(new BorderLayout());
		
		JPanel io = new JPanel();
		
		uno = c;
		centro = new DatiPanel(c);
		
		//Aggiunge i bottoni Selezione Visualizzazione e Gestione presenti in tutti i pannel
		selezioneB.addActionListener(new ButtonListener(this));
		gestioneB.addActionListener(new ButtonListener(this));
		visualizzazioneB.addActionListener(new ButtonListener(this));
		visualizzazione = new PannelloVisualizzazione(uno);
		selezione = new PannelloSelezione(c);
		gestione = new PannelloGestione(c);
		
		
		//Li inserisce nel frame
		
		io.add(gestioneB);
		io.add(selezioneB);
		io.add(visualizzazioneB);
		
		//Crea il pannello contenitore
		
		contenitore = new JPanel(new GridLayout(1,1));
		
		//Vengono piazzati i varii oggetti nel frame
		
		grid.add(contenitore, BorderLayout.CENTER);
		grid.add(centro, BorderLayout.NORTH);
		grid.add(io, BorderLayout.SOUTH);
		
		
		visualizzazione.setVisible(false);
		selezione.setVisible(false);
		gestione.setVisible(false);
		
		add(grid);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	          
	   
		setSize(800, 150);
		setVisible(true);
	}
	
	//Menu non implementato per caricare un file contentente il centro da esaminare (cio� penso)
	
	private JMenuBar addFile() {
		JMenuBar due = new JMenuBar();
		JMenu io = new JMenu("File");
		io.add(createItem("Nuovo"));
		io.add(createItem("Salva"));
		io.add(createItem("Carica"));
		io.add(createItem("Esci"));
		due.add(io);
		
		
		
		return due;
	}
	
	
	
	
	
	
	
	private JMenuItem createItem(String a) {
		
		JMenuItem it = new JMenuItem(a);
		it.addActionListener(new StartFrameListener(this));
		return it;
		
	}
		
	public CentroUrbano uno;
	public DatiPanel centro;
	
	private JPanel contenitore;
	private PannelloSelezione selezione;
	private PannelloGestione gestione;
	private PannelloVisualizzazione visualizzazione;
	private JButton selezioneB = new JButton("Selezione");
	private JButton gestioneB = new JButton("Gestione");
	private JButton visualizzazioneB = new JButton("Visualizzazione");
	

	
	//Action Listener dei bottoni Gestione Selezione e Visualizzazione

public class ButtonListener implements ActionListener{
		StartFrame rifer;
	
		public ButtonListener(StartFrame e) {
			rifer = e;
		}
		
		
		public void actionPerformed(ActionEvent e) {
			JButton io = (JButton) e.getSource();
			
			
			String testo = io.getText();
			if(testo.equalsIgnoreCase("Selezione")){			//Ridimensionamento del frame per
				rifer.setSize(800, 400);						//visualizzare il pannello di selezione
				selezioneB.setEnabled(false);
				visualizzazioneB.setEnabled(true);
				gestioneB.setEnabled(true);
				contenitore.removeAll();
				contenitore.add(selezione);
				selezione.setVisible(true);
				gestione.setVisible(false);
				visualizzazione.setVisible(false);
			}
			else if(testo.equalsIgnoreCase("Gestione")){		//visualizzazione del pannello di gestione
				gestioneB.setEnabled(false);
				visualizzazioneB.setEnabled(true);
				selezioneB.setEnabled(true);
				rifer.setSize(800, 300);
				contenitore.removeAll();
				contenitore.add(gestione);
				selezione.setVisible(false);
				gestione.setVisible(true);
				visualizzazione.setVisible(false);
			}
			else if(testo.equalsIgnoreCase("Visualizzazione")){	//visualizzazione del pannello di visualizzazione
				visualizzazioneB.setEnabled(false);
				selezioneB.setEnabled(true);
				gestioneB.setEnabled(true);
				rifer.setSize(800, 450);	
				contenitore.removeAll();
				contenitore.add(visualizzazione);
				selezione.setVisible(false);
				gestione.setVisible(false);
				visualizzazione.setVisible(true);
			}
			
		}
		
	}
}
