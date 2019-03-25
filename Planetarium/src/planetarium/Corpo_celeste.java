package planetarium;
import java.util.Scanner;
public abstract class Corpo_celeste {
	String nome;
	int peso, codice;
	Punto punto;
	
	/**
     * @param nome
     */
	public void setNome(){
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
	public void setCodice(int codice) {
		this.codice = codice;
	}
	/** 
     * @return 
     */
	public int getCodice() {
		return codice;
	}
	/** 
     * @return 
     */
	public Punto getCord() {
		return punto;
	}
	
}
