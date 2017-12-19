package centrourbano;

import java.io.Serializable;

import eccezioni.LottoLibero;


public class Settori implements Serializable{
	private static final long serialVersionUID = 1L;

	public Settori() {
		setValore(0);
		lista = new Lotti[3][5];
		for(int x = 0; x < 3; x++)
			for(int i = 0; i < 5; i++)
				lista[x][i] = new Lotti();

	}


	/**
	 * Calcola il prezzo del lotto, questo metodo si applica solo per gli edifici privati
	 * essendo gli unici con un valore e elimina il lotto essendo stato venduto
	 * @param X Coordinata X, che insieme alla Y indicano che edificio vendere
	 * @param Y Coordinata Y, che insieme alla X indicano che edificio vendere
	 * @return	Ritorna il prezzo di vendita dell'edificio
	 */

	public int vendiEdificio(int X, int Y) {
		Lotti vend = getLotto(X, Y);
		int tot = (vend.getValore() + this.getValore()) * vend.getCeff();
		rmLotto(X, Y);
		return tot;

	}



	/**
	 * In base al tipo di lotto il metodo esegue una di 3 azioni
	 * -Edificio pubblico: Incrementa di 1 il valore del settore
	 * -Strada: Trova i lotti adiacenti nelle 8 direzioni e aumenta il loro valore di 1
	 * -Edificio privato: Controlla i lotti adiacenti in cerca di strade e aumenta per ogni strada il valore di 1
	 * @param Nuovo Il Lotto che va inserito
	 */
<<<<<<< HEAD
	public void addLotto(Lotti Nuovo, int riga, int colonna) {
		if(Nuovo.getTip() == STRADA)
			addStrada( Nuovo,riga,colonna);
		if(Nuovo.getTip() == EPUB)
			addepub(Nuovo,riga,colonna);
		if(Nuovo.getTip() == EPRIV)
			addepriv(Nuovo,  riga, colonna);
=======

	public void addLotto(Lotti Nuovo, int X, int Y) {
		if(Nuovo.getTip() == STRADA)
			addStrada( Nuovo,X,Y);
		if(Nuovo.getTip() == EPUB)
			addepub(Nuovo,X,Y);
		if(Nuovo.getTip() == EPRIV)
			addepriv(Nuovo,  X, Y);
>>>>>>> f6baf0cd88a08fb27b871d78df5f9d0f2e3b65dc
	}




	/**
	 * Rimuove l'edificio e inverte i cambiamenti che questo influiva sui vicini e sul settore.
	 * -Edificio privato: Semplice rimozione
	 * -Edificio pubblico: Il valore del settore viene ridotto di 1
	 * -Strada: Riduce di 1 il valore dei lotti adiacenti
<<<<<<< HEAD
	 * 
	 * 
	 * Nel caso in cui il lotto sia gi� libero lancia un eccezione di tipo "LottoLibero"
	 * @param riga
	 * @param colonna
	 */
	
	public void rmEdificio(int riga, int colonna) throws LottoLibero{
		
	}
	
	/**
	 * Cambia l'edificio selezionato con un altro aggiornando gli effetti
	 * @param riga
	 * @param colonna
	 * @throws LottoLibero
	 */
	
	public void cgEdificio(Lotti nuovo,int riga, int colonna) throws LottoLibero{
		rmEdificio(riga, colonna);
		addLotto(nuovo, riga , colonna);
	}
	
	public Lotti getLotto(int riga, int colonna) {
		Lotti io = lista[riga][colonna]; 		
		
		
		return io;
	}
	
	private void addStrada(Lotti Nuovo, int riga, int colonna) {
		}
	
	private void addepub(Lotti Nuovo, int riga, int colonna) {
		}
	
	private void addepriv(Lotti Nuovo, int riga, int colonna) {
		}
	
=======
	 *
	 *
	 * Nel caso in cui il lotto sia gi� libero lancia un eccezione di tipo "LottoLibero"
	 * @param X Coordinata X, che insieme alla Y indicano che edificio rimuovere
	 * @param Y Coordinata Y, che insieme alla X indicano che edificio rimuovere
	 */

	public void rmLotto(int X, int Y) {
		if((getLotto(X, Y)).getTip() == LIBERO)
			throw new LottoLibero();
		if((getLotto(X, Y)).getTip() == STRADA)
			rmStrada(X,Y);
		if((getLotto(X, Y)).getTip() == EPUB)
			rmepub(X,Y);
		if((getLotto(X, Y)).getTip() == EPRIV)
			rmepriv(X,Y);

	}

	/**
	 * Cambia l'edificio selezionato con un altro aggiornando gli effetti
	 * @param X Coordinata X, che insieme alla Y indicano che edificio cambiare
	 * @param Y Coordinata Y, che insieme alla X indicano che edificio cambiare
	 * @throws LottoLibero Nel caso il lotto sia gia libero lancia una eccezzione
	 */

	public void cgEdificio(Lotti nuovo,int X, int Y) {
		rmLotto(X, Y);
		addLotto(nuovo, X , Y);
	}

	/**
	 * Restituisce il lotto in posizione X, Y
	 * @param X Coordinata X, che insieme alla Y indicano che edificio restituire
	 * @param Y Coordinata Y, che insieme alla X indicano che edificio restituire
	 * @return
	 */

	public Lotti getLotto(int X, int Y) {
		Lotti io = lista[X][Y];


		return io;
	}

	/**
	 * Aggiunge una strada alla posizione indicata da X e Y e aumenta di uno
	 * il valore di tutti i lotti adiacenti interni al settore (per quelli esterni se ne occupa il
	 * metodo di CentroUrbano, perfavore usare quello e non questo nel caso si debba aggiungere una strada)
	 * @param Nuovo Il lotto da inserire
	 * @param X Coordinata X, che insieme alla Y indicano la posizione della strada
	 * @param Y Coordinata Y, che insieme alla X indicano la posizione della strada
	 */

	private void addStrada(Lotti Nuovo, int X, int Y) {
			lista[X][Y] = Nuovo;

			if((Y - 1) > -1)
				addOne(X,Y-1);
			if((Y + 1) < MAX_Y )
				addOne(X,Y+1);
			if((X - 1) > -1) {
				addOne(X-1, Y);
				if((Y-1) > -1)
					addOne(X-1, Y-1);
				if((Y + 1) < MAX_Y)
					addOne(X-1, Y+1);
			}
			if((X + 1) < MAX_X) {
				addOne(X+1, Y);
				if((Y-1) > -1)
					addOne(X+1, Y-1);
				if((Y+1) < MAX_Y)
					addOne(X+1,Y+1);
			}
	}



	/**
	 * Aggiunge un edificio pubblico e aumenta il valore del settore di 1
	 * @param Nuovo Il lotto da aggiungere
	 * @param X Coordinata X, che insieme alla Y indicano in che posizione aggiungere il lotto
	 * @param Y Coordinata Y, che insieme alla X indicano in che posizione aggiungere il lotto
	 */

	private void addepub(Lotti Nuovo, int X, int Y) {
		lista[X][Y] = Nuovo;
		this.setValore(this.getValore() + 1);
	}

	/**
	 * Aggiunge un edificio privato
	 * @param Nuovo Il lotto da aggiungere
	 * @param X Coordinata X, che insieme alla Y indicano in che posizione aggiungere il lotto
	 * @param Y Coordinata Y, che insieme alla X indicano in che posizione aggiungere il lotto
	 */

	private void addepriv(Lotti Nuovo, int X, int Y) {
		lista[X][Y] = Nuovo;
	}

	/**
	 * Rimuove la strada alla posizione indicata da X e Y
	 * Inoltre riduce di uno il valore di tutti i lotti adiacenti in modo da annullare il
	 * bonus dato dalla strada
	 * @param X Coordinata X, che insieme alla Y indicano la posizione della strada da rimuovere
	 * @param Y Coordinata Y, che insieme alla X indicano la posizione della strada da rimuovere
	 */

	private void rmStrada(int X, int Y) {
		lista[X][Y].setEdificio(Lotti.VUOTO);
		if((Y - 1) > -1)
			subOne(X,Y-1);
		if((Y + 1) < MAX_Y )
			subOne(X,Y+1);
		if((X - 1) > -1) {
			subOne(X-1, Y);
			if((Y-1) > -1)
				subOne(X-1, Y-1);
			if((Y + 1) < MAX_Y)
				subOne(X-1, Y+1);
		}
		if((X + 1) < MAX_X) {
			subOne(X+1, Y);
			if((Y-1) > -1)
				subOne(X+1, Y-1);
			if((Y+1) < MAX_Y)
				subOne(X+1,Y+1);
			}
		}

	/**
	 * Rimuovi l'edificio pubblico e rimuove il +1 bonus al settore
	 * @param X Coordinata X, che insieme alla Y indicano in che posizione rimuovere il lotto
	 * @param Y Coordinata Y, che insieme alla X indicano in che posizione rimuovere il lotto
	 */

	private void rmepub(int X,int Y) {
		lista[X][Y].setEdificio(Lotti.VUOTO);
		this.setValore(this.getValore() - 1);

	}

	/**
	 * Rimuovi l'edificio privato
	 * @param X Coordinata X, che insieme alla Y indicano in che posizione rimuovere il lotto
	 * @param Y Coordinata Y, che insieme alla X indicano in che posizione rimuovere il lotto
	 */

	private void rmepriv(int X,int Y) {
		lista[X][Y].setEdificio(Lotti.VUOTO);
	}

	/**
	 * Calcola il numero di lotti presenti nel settore compresi i lotti liberi
	 */

	public int calcolaLotti() {
		int totale = 0;
		totale+=calcolaLottiLiberi();
		totale+=calcolaLottiPrivati();
		totale+=calcolaLottiPubblici();
		totale+=calcolaStrade();
		return totale;
	}

	/**
	 * Calcola il numero di lotti liberi presenti nel settore
	 */

	public int calcolaLottiLiberi() {
		int lottiLiberi = 0;
		for(int i = 0; i < MAX_X ; i++ ) {
			for(int j=0;j < MAX_Y; j++) {
				if(lista[i][j].getTip() == LIBERO)
					lottiLiberi++;
			}

		}

		return lottiLiberi;
	}

	/**
	 * Calcola il numero di strade presenti nel settore
	 */

	public int calcolaStrade() {
		int strade = 0;
		for(int i = 0; i < MAX_X ; i++ ) {
			for(int j=0;j < MAX_Y; j++) {
				if(lista[i][j].getTip() == STRADA)
					strade++;
			}

		}

		return strade;
	}

	/**
	 * Calcola il numero di lotti pubblici presenti nel settore
	 */

	public int calcolaLottiPubblici() {
		int lottiPubblici = 0;
		for(int i = 0; i < MAX_X ; i++ ) {
			for(int j=0;j < MAX_Y; j++) {
				if(lista[i][j].getTip() == EPUB)
					lottiPubblici++;
			}

		}

		return lottiPubblici;
	}

/**
 * Calcola il numero di lotti privati presenti nel settore
 */

	public int calcolaLottiPrivati() {
		int lottiPrivati = 0;
		for(int i = 0; i < MAX_X ; i++ ) {
			for(int j=0;j < MAX_Y; j++) {
				if(lista[i][j].getTip() == EPRIV)
					lottiPrivati++;
			}

		}

		return lottiPrivati;
	}

	/**
	 *Questo metodo aumenta di uno il valore del lotto,  ma solo se � un edificio privato
	 * @param X Coordinata X, che insieme alla Y indicano in che posizione si trova il lotto a cui aumentare il valore
	 * @param Y Coordinata Y, che insieme alla X indicano in che posizione si trova il lotto a cui aumentare il valore
	 */

	public void addOne(int X,int Y) {
		if(lista[X][Y].getTip()==EPRIV)
			lista[X][Y].setValore(lista[X][Y].getValore() + 1);
	}

	/**
	 * Questo metodo riduce di uno il valore del lotto, ma solo se � un edificio privato
	 * @param X Coordinata X, che insieme alla Y indicano in che posizione si trova il lotto a cui ridurre il valore
	 * @param Y Coordinata Y, che insieme alla X indicano in che posizione si trova il lotto a cui ridurre il valore
	 */

	public void subOne(int X,int Y) {
		if(lista[X][Y].getTip()==EPRIV)
			lista[X][Y].setValore(lista[X][Y].getValore() - 1);
	}

	/**
	 * Questo metodo ritorna il valore del settore
	 * @return Valore del settore
	 */

>>>>>>> f6baf0cd88a08fb27b871d78df5f9d0f2e3b65dc
	public int getValore() {
		return valore;
	}

	/**
	 * Questo metodo modifica il valore del settore
	 * @param valore Valore del settore
	 */

	public void setValore(int valore) {
		this.valore = valore;
	}



	//Ho associato delle MACRO ai limiti del settore in modo da rendere piu leggibile il codice
	private static final int MAX_X = 3;
	private static final int MAX_Y = 5;

	//A ogni tipo di edificio ho associato un valore, creando una MACRO che rende piu leggibile il codice
	private static final int LIBERO = 0;
	private static final int STRADA = 1;
	private static final int EPUB = 2;
	private static final int EPRIV = 3;
	private int valore;

<<<<<<< HEAD
	private static int STRADA = 1;
	private static int EPUB = 2;
	private static int EPRIV = 3;
	
=======


>>>>>>> f6baf0cd88a08fb27b871d78df5f9d0f2e3b65dc
	public Lotti[][] lista;
}
