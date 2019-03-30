package planetarium;
public abstract class CorpoCeleste {
	String nome;
	int peso;
	String codice;
	Punto punto;
	
	public CorpoCeleste(String _nome, String _codice, int _peso, Punto _punto) {
		nome = _nome;
		codice = _codice;
		peso = _peso;
		punto = _punto;
	}
	
	
	/**
     * @param nome
     */
	public void setNome(String nome){
		this.nome = nome;
	}
	/** 
     * @return 
     */
	public String getNome() {
		return nome;
	}
	/** 
     * @param peso 
     */
	public void setPeso(int peso) {
		this.peso = peso;
	}
	/** 
     * @return 
     */
	public int getPeso() {
		return peso;
	}
	/** 
     * @param codice 
     */
	public void setCodice(String codice) {
		this.codice = codice;
	}
	/** 
     * @return 
     */
	public String getCodice() {
		return codice;
	}
	/** 
     * @return 
     */
	public Punto getCord() {
		return punto;
	}
	
	public double distanzaDa(CorpoCeleste cc) {
		return Math.sqrt(Math.pow(punto.getX()-cc.getCord().getX(), 2) + Math.pow(punto.getY()-cc.getCord().getY(), 2));
	}
	
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
