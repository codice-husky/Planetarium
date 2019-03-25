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
	
}
