package planetarium;

public class SistemaStellare {
	String nome;
	Stella stella = null;
	
	public SistemaStellare(String nome) {
		this.nome = nome;
	}
	
	public boolean aggiungiStella(Stella _stella) {
		if(stella == null) {
			stella = _stella;
			return true;
		} else return false;
	}
	public void rimuoviStella() {}
	

	public Stella getStella() {
		return stella;
	}

	/** 
     * @return 
     */
	public Punto getCentroMassa() {
		//metodo per calcolo centro massa
		return null;
	}
	/** 
     * @param codiceA
     * @param codiceB
     * @return 
     */
	public boolean collisione(int codiceA,int codiceB) {
		//metodo per vedere se si scontrano
		return false;
	}
	/**
	 * @param codice 
     * @return 
     */
	public boolean presenteCorpo(int codice) {
		//ricerca del corpo celeste attraverso il codice
		return false;
	}
	/** 
     * @param codice
     * @return 
     */
	public String percorso(int codice){
		//stampa percorso (stella > pianeta > satellite)
		return "";
	}
	
}
