package planetarium;
public abstract class CorpoCeleste {
	String nome;
	int peso;
	String codice;
	Punto punto;
	/**
	 * Costruttore del corpo celeste che setta i vari attributi di ciascun
	 * corpo celeste
	 * @param _nome Nome del corpo celeste
	 * @param _codice Codice del corpo celeste
	 * @param _peso Peso del corpo celeste
	 * @param _punto Coordinate del corpo celeste
	*/
	public CorpoCeleste(String _nome, String _codice, int _peso, Punto _punto) {
		nome = _nome;
		codice = _codice;
		peso = _peso;
		punto = _punto;
	}
	
	
	/** 
	 * Getter del attributo nome
     * @return nome Nome del corpo
     */
	public String getNome() {
		return nome;
	}
	
	/** 
	 * Getter del attributo peso
     * @return peso Peso del corpo
     */
	public int getPeso() {
		return peso;
	}
	
	/** 
	 * Getter del attributo codice
     * @return codice Codice del corpo
     */
	public String getCodice() {
		return codice;
	}
	/** 
	 * Getter del attributo punto
     * @return punto Coordinate del corpo
     */
	public Punto getCord() {
		return punto;
	}
	/**
	 * Restituisce la distanza dal corpo celeste passato come parametro
	 * @param cc Il corpo celeste da confrontare
	 * @return Distanza da cc
	*/
	public double distanzaDa(CorpoCeleste cc) {
		return Math.sqrt(Math.pow(punto.getX()-cc.getCord().getX(), 2) + Math.pow(punto.getY()-cc.getCord().getY(), 2));
	}
	/**
	 * Restituisce il corpo celeste attorno cui ruota il corpo celeste
	 * @param ss Sistema stellare di riferimento
	 * @return null se è una stella o non esiste
	 * @return stella se questo corpo è un pianeta e quindi rotea attorno ad
	 *                una stella
	*/
	public CorpoCeleste getParent(SistemaStellare ss) {
		Stella stella = ss.getStella();
		if(stella.getCodice().equals(codice)) return null;
		for(Pianeta pianeta : stella.getPianeti()) {
			if(pianeta.getCodice().equals(codice)) return stella;
			for(Satellite satellite : pianeta.getSatelliti()) {
				if(satellite.getCodice().equals(codice)) return pianeta;
			}
		}
		return null;
	}
	/**
	 * Restituisce il tipo di corpo cercato attraverso il codice passato come
	 * parametro
	 * @param ss Sistema stellare di riferimento
	 * @param codice Codice del corpo da cercare
	 * @return null Se non esiste quel codice
	 * @return stella Se il codice corrisponde a quello della stella
	 * @return pianeta  Se il codice corrisponde a quello di un pianeta
	 * @return satellite Se il codice corrisponde a quello di un satellite
	*/
	
	public static CorpoCeleste getCorpoFromCodice(SistemaStellare ss, String codice) {
		Stella stella = ss.getStella();
		if(codice.equals(stella.getCodice())) return stella;
		for(Pianeta pianeta : stella.getPianeti()) {
			if(pianeta.getCodice().equals(codice)) return pianeta;
			for(Satellite satellite : pianeta.getSatelliti()) {
				if(satellite.getCodice().equals(codice)) return satellite;
			}
		}
		return null;
	}
	
}
