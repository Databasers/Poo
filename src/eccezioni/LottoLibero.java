package eccezioni;

public class LottoLibero extends RuntimeException {
	
	public LottoLibero() {
		super("Il lotto � vuoto:");
	}

}
