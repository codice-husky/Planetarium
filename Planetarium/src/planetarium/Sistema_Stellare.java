package planetarium;
import java.util.Scanner;

public class Sistema_Stellare {
	String nome;
	Stella stella;
	boolean esisteStella = false;
	
	public Sistema_Stellare(String nome) {
		this.nome = nome;
	}
	
	public void aggiungiStella() {}
	public void rimuoviStella() {}
	/** 
     * @return 
     */
	public boolean getEsisteStella() {
		return false;                       //PER CAPIRE SE ESISTE GIA' UNA STELLA E 
	}										//IN QUEL CASO NON AGGIUNGERNE UNA
	/** 
     * @param esisteStella 
     */
	public void setEsisteStella() {
		esisteStella = !esisteStella;
		/*avevo pensato dato che esiste una sola stella bisogna controllare se esiste (quando creo il sistema
		  è settata su false), se non esiste parte l'aggiungiStella che chiamando questo metodo la mette 
		  a true. Quando la vuole togliere chiama il rimuoviStella che sempre attravero questo metodo
		  la rimette a false 
		*/
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
